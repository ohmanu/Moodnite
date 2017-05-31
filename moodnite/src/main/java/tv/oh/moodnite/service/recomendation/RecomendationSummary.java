package tv.oh.moodnite.service.recomendation;

import java.util.List;
import java.util.Set;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.Rated;
import tv.oh.moodnite.domain.User;

public class RecomendationSummary {
	private List<User> socialNet;
	private double averageRating;
	private List<Rated> socialNetRates;
	private Set<Movie> bestRatedMovies;

	public List<User> getSocialNet() {
		return socialNet;
	}

	public void setSocialNet(List<User> socialNet) {
		this.socialNet = socialNet;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public List<Rated> getSocialNetRates() {
		return socialNetRates;
	}

	public void setSocialNetRates(List<Rated> socialNetRates) {
		this.socialNetRates = socialNetRates;
	}

	public Set<Movie> getBestRatedMovies() {
		return bestRatedMovies;
	}

	public void setBestRatedMovies(Set<Movie> bestRatedMovies) {
		this.bestRatedMovies = bestRatedMovies;
	}
}
