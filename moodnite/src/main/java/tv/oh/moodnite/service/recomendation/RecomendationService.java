package tv.oh.moodnite.service.recomendation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.Rated;
import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.domain.Watched;
import tv.oh.moodnite.repository.UserRepository;

@Service
public class RecomendationService {
	@Autowired
	private UserRepository userRepo;
	
	private RecomendationSummary recomendationSummary;
	private User user;
	
	public RecomendationSummary build(User user) {
		this.recomendationSummary = new RecomendationSummary();
		this.user = user;
		getSocialNet();
		getSocialNetRates();
		getAverageRating();
		getBestRatedMovies();
		removeWatchedMovies();
		
		return this.recomendationSummary;
	}
	
	private void getSocialNet() {
		this.recomendationSummary.setSocialNet(userRepo.findSocialNet(this.user.getName()));
	}
	
	private void getSocialNetRates() {
		List<Rated> socialNetRates = new ArrayList<>();
		for(User user : this.recomendationSummary.getSocialNet()) {
			socialNetRates.addAll(user.getRatedList());
		}
		
		this.recomendationSummary.setSocialNetRates(socialNetRates);
	}
	
	private void getAverageRating() {
		double sumRating = 0;
		double averageRating = 0;
		
		for(Rated rate : this.recomendationSummary.getSocialNetRates()) {
			sumRating = sumRating + rate.getRate();
		}
		averageRating = sumRating / this.recomendationSummary.getSocialNetRates().size();
		
		this.recomendationSummary.setAverageRating(averageRating);
	}
	
	private void getBestRatedMovies() {
		Set<Movie> bestRatedMovies = new HashSet<>();
		
		for(Rated rate : this.recomendationSummary.getSocialNetRates()) {
			if(rate.getRate() >= this.recomendationSummary.getAverageRating())
				bestRatedMovies.add(rate.getMovie());
		}
		
		this.recomendationSummary.setBestRatedMovies(bestRatedMovies);
	}
	
	private void removeWatchedMovies() {
		for(Watched watched : this.user.getWatchedList()) {
			this.recomendationSummary.getBestRatedMovies().remove(watched.getMovie());
		}
		for(Rated rated : this.user.getRatedList()) {
			this.recomendationSummary.getBestRatedMovies().remove(rated.getMovie());
		}
	}
}
