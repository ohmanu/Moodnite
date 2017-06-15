package tv.oh.moodnite.service.recomendation;

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
public class RecomendationService {
	@Autowired
	private UserRepository userRepo;

	private RecomendationSummary recomendationSummary;
	private User user;

	public RecomendationSummary build(User user) {
		this.recomendationSummary = new RecomendationSummary();
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

		return this.recomendationSummary;
	}

	private void getUserAverageRating() {
		double sumRating = 0;
		double averageRating = 0;

		for (Rated rate : this.user.getRatedList()) {
			sumRating = sumRating + rate.getRate();
		}
		averageRating = sumRating / this.user.getRatedList().size();

		this.recomendationSummary.setUserAverageRating(averageRating);
	}

	private void getUserBestRatedMovies() {
		Set<Movie> userBestRatedMovies = new HashSet<>();

		for (Rated rate : this.user.getRatedList()) {
			if (rate.getRate() >= this.recomendationSummary.getUserAverageRating())
				userBestRatedMovies.add(rate.getMovie());
		}

		this.recomendationSummary.setUserBestRatedMovies(userBestRatedMovies);
	}

	private void getSocialNet() {
		this.recomendationSummary.setSocialNet(userRepo.findSocialNet(this.user.getName()));
	}

	private void getSocialNetRates() {
		List<Rated> socialNetRates = new ArrayList<>();
		for (User user : this.recomendationSummary.getSocialNet()) {
			socialNetRates.addAll(user.getRatedList());
		}

		this.recomendationSummary.setSocialNetRates(socialNetRates);
	}

	private void getSocialNetAverageRating() {
		double sumRating = 0;
		double averageRating = 0;

		for (Rated rate : this.recomendationSummary.getSocialNetRates()) {
			sumRating = sumRating + rate.getRate();
		}
		averageRating = sumRating / this.recomendationSummary.getSocialNetRates().size();

		this.recomendationSummary.setSocialNetAverageRating(averageRating);
	}

	private void getSocialNetBestRatedMovies() {
		Set<Movie> bestRatedMovies = new HashSet<>();

		for (Rated rate : this.recomendationSummary.getSocialNetRates()) {
			if (rate.getRate() >= this.recomendationSummary.getSocialNetAverageRating())
				bestRatedMovies.add(rate.getMovie());
		}

		this.recomendationSummary.setSocialNetBestRatedMovies(bestRatedMovies);
	}

	private void removeWatchedMovies() {
		for (Watched watched : this.user.getWatchedList()) {
			this.recomendationSummary.getSocialNetBestRatedMovies().remove(watched.getMovie());
		}
		for (Rated rated : this.user.getRatedList()) {
			this.recomendationSummary.getSocialNetBestRatedMovies().remove(rated.getMovie());
		}
	}
	
	private void removeRefusedMovies() {
		for (Movie movie : this.user.getRefusedList()) {
			this.recomendationSummary.getSocialNetBestRatedMovies().remove(movie);
		}
	}

	private void addCandidates() {
		for (Movie movie : this.recomendationSummary.getSocialNetBestRatedMovies())
			this.recomendationSummary.getCandidates().add(new RecomendationCandidate(movie));
	}

	private void takeMoviesLustrumThisYear() {
		for (RecomendationCandidate candidate : this.recomendationSummary.getCandidates()) {
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
		for (Movie movie : this.recomendationSummary.getUserBestRatedMovies()) {
			Set<String> movieTagNames = new HashSet<>();
			for (Tag tag : movie.getTags()) {
				movieTagNames.add(tag.getName());
			}
			for (TagFromSource tagFromSource : movie.getTagsFromSources()) {
				movieTagNames.add(tagFromSource.getName());
			}
			for (String tagName : movieTagNames) {
				this.recomendationSummary.addUserFavoriteTag(tagName);
			}
		}
	}

	private void getSocialNetTagsFromMovies() {
		for (RecomendationCandidate candidate : this.recomendationSummary.getCandidates()) {
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
		for (RecomendationCandidate candidate : this.recomendationSummary.getCandidates()) {
			for (Map.Entry<String, Integer> userTagValue : this.recomendationSummary.getUserFavoriteTags().entrySet()) {
				for (Map.Entry<String, Integer> candidateTagValue : candidate.getTagValues().entrySet()) {
					if (candidateTagValue.getKey().compareTo(userTagValue.getKey()) == 0)
						candidateTagValue.setValue(userTagValue.getValue());
				}
			}
		}
	}

	private void calculateCandidatesScores() {
		for (RecomendationCandidate candidate : this.recomendationSummary.getCandidates()) {
			for (Map.Entry<String, Integer> candidateTagValue : candidate.getTagValues().entrySet()) {
				candidate.setScore(candidateTagValue.getValue());
			}
		}
	}
	
	private void shortCandidates() {
		Collections.sort(this.recomendationSummary.getCandidates(), new ScoreComparator());
	}
	
	private void selectTheChosenOne() {
		if(!this.recomendationSummary.getCandidates().isEmpty()) {
			RecomendationCandidate theChosenOne = this.recomendationSummary.getCandidates().get(0);
			this.recomendationSummary.setTheChosenOne(theChosenOne);
			this.recomendationSummary.getCandidates().remove(theChosenOne);
		}
	}
}
