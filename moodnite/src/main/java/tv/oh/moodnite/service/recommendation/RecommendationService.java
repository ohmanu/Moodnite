package tv.oh.moodnite.service.recommendation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.Rated;
import tv.oh.moodnite.domain.Tag;
import tv.oh.moodnite.domain.TagFromSource;
import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.domain.Watched;
import tv.oh.moodnite.repository.UserRepository;
import tv.oh.moodnite.service.recomendation.comparator.ScoreComparator;

@Service
public class RecommendationService {
	@Autowired
	private UserRepository userRepo;

	private RecommendationSummary recommendationSummary;
	private User user;

	public RecommendationSummary build(User user) {
		this.recommendationSummary = new RecommendationSummary();
		this.user = user;
		
		getUserAverageRating();
		getUserBestRatedMovies();
		getSocialNet();
		getSocialNetRates();
		getSocialNetAverageRating();
		getSocialNetBestRatedMovies();
		removeWatchedMovies();
		removeRefusedMovies();
		addCandidates();
		takeMoviesLustrumThisYear();
		getUserFavoriteTags();
		getSocialNetTagsFromMovies();
		calculateCandidatesScore();
		calculateCandidatesScores();
		shortCandidates();
		selectTheChosenOne();

		return this.recommendationSummary;
	}

	private void getUserAverageRating() {
		double sumRating = 0;
		double averageRating = 0;

		for (Rated rate : this.user.getRatedList()) {
			sumRating = sumRating + rate.getRate();
		}
		averageRating = sumRating / this.user.getRatedList().size();

		this.recommendationSummary.setUserAverageRating(averageRating);
	}

	private void getUserBestRatedMovies() {
		Set<Movie> userBestRatedMovies = new HashSet<>();

		for (Rated rate : this.user.getRatedList()) {
			if (rate.getRate() >= this.recommendationSummary.getUserAverageRating())
				userBestRatedMovies.add(rate.getMovie());
		}

		this.recommendationSummary.setUserBestRatedMovies(userBestRatedMovies);
	}

	private void getSocialNet() {
		this.recommendationSummary.setSocialNet(userRepo.findSocialNet(this.user.getName()));
	}

	private void getSocialNetRates() {
		List<Rated> socialNetRates = new ArrayList<>();
		for (User user : this.recommendationSummary.getSocialNet()) {
			socialNetRates.addAll(user.getRatedList());
		}

		this.recommendationSummary.setSocialNetRates(socialNetRates);
	}

	private void getSocialNetAverageRating() {
		double sumRating = 0;
		double averageRating = 0;

		for (Rated rate : this.recommendationSummary.getSocialNetRates()) {
			sumRating = sumRating + rate.getRate();
		}
		averageRating = sumRating / this.recommendationSummary.getSocialNetRates().size();

		this.recommendationSummary.setSocialNetAverageRating(averageRating);
	}

	private void getSocialNetBestRatedMovies() {
		Set<Movie> bestRatedMovies = new HashSet<>();

		for (Rated rate : this.recommendationSummary.getSocialNetRates()) {
			if (rate.getRate() >= this.recommendationSummary.getSocialNetAverageRating() || rate.getRate() >= 7)
				bestRatedMovies.add(rate.getMovie());
		}

		this.recommendationSummary.setSocialNetBestRatedMovies(bestRatedMovies);
	}

	private void removeWatchedMovies() {
		for (Watched watched : this.user.getWatchedList()) {
			this.recommendationSummary.getSocialNetBestRatedMovies().remove(watched.getMovie());
		}
		for (Rated rated : this.user.getRatedList()) {
			this.recommendationSummary.getSocialNetBestRatedMovies().remove(rated.getMovie());
		}
	}
	
	private void removeRefusedMovies() {
		for (Movie movie : this.user.getRefusedList()) {
			this.recommendationSummary.getSocialNetBestRatedMovies().remove(movie);
		}
	}

	private void addCandidates() {
		for (Movie movie : this.recommendationSummary.getSocialNetBestRatedMovies())
			this.recommendationSummary.getCandidates().add(new RecommendationCandidate(movie));
	}

	private void takeMoviesLustrumThisYear() {
		for (RecommendationCandidate candidate : this.recommendationSummary.getCandidates()) {
			if (candidate.getMovie().getYear() != null) {
				int currentYear = Calendar.getInstance().get(Calendar.YEAR);
				int yearsElapsed = currentYear - Integer.parseInt(candidate.getMovie().getYear());
				candidate.setYearsElapsed(yearsElapsed);
				if (yearsElapsed % 5 == 0) {
					candidate.setLustrumThisYear(true);
					candidate.setScore(yearsElapsed / 5);
				}
			}
		}
	}

	private void getUserFavoriteTags() {
		for (Movie movie : this.recommendationSummary.getUserBestRatedMovies()) {
			Set<String> movieTagNames = new HashSet<>();
			for (Tag tag : movie.getTags()) {
				movieTagNames.add(tag.getName());
			}
			for (TagFromSource tagFromSource : movie.getTagsFromSources()) {
				movieTagNames.add(tagFromSource.getName());
			}
			for (String tagName : movieTagNames) {
				this.recommendationSummary.addUserFavoriteTag(tagName);
			}
		}
	}

	private void getSocialNetTagsFromMovies() {
		for (RecommendationCandidate candidate : this.recommendationSummary.getCandidates()) {
			Set<String> movieTagNames = new HashSet<>();
			for (Tag tag : candidate.getMovie().getTags()) {
				movieTagNames.add(tag.getName());
			}
			for (TagFromSource tagFromSource : candidate.getMovie().getTagsFromSources()) {
				movieTagNames.add(tagFromSource.getName());
			}
			for (String tagName : movieTagNames) {
				candidate.getTagValues().put(tagName, 0);
			}
		}
	}

	private void calculateCandidatesScore() {
		for (RecommendationCandidate candidate : this.recommendationSummary.getCandidates()) {
			for (Map.Entry<String, Integer> userTagValue : this.recommendationSummary.getUserFavoriteTags().entrySet()) {
				for (Map.Entry<String, Integer> candidateTagValue : candidate.getTagValues().entrySet()) {
					if (candidateTagValue.getKey().compareTo(userTagValue.getKey()) == 0)
						candidateTagValue.setValue(userTagValue.getValue());
				}
			}
		}
	}

	private void calculateCandidatesScores() {
		for (RecommendationCandidate candidate : this.recommendationSummary.getCandidates()) {
			for (Map.Entry<String, Integer> candidateTagValue : candidate.getTagValues().entrySet()) {
				candidate.setScore(candidateTagValue.getValue());
			}
		}
	}
	
	private void shortCandidates() {
		Collections.sort(this.recommendationSummary.getCandidates(), new ScoreComparator());
	}
	
	private void selectTheChosenOne() {
		if(!this.recommendationSummary.getCandidates().isEmpty()) {
			RecommendationCandidate theChosenOne = this.recommendationSummary.getCandidates().get(0);
			this.recommendationSummary.setTheChosenOne(theChosenOne);
			this.recommendationSummary.getCandidates().remove(theChosenOne);
		}
	}
}
