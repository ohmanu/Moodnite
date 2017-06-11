package tv.oh.moodnite.service.recomendation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.Rated;
import tv.oh.moodnite.domain.User;

public class RecomendationSummary {
	private List<User> socialNet;
	private double userAverageRating;
	private double socialNetAverageRating;
	private List<Rated> socialNetRates;
	private Set<Movie> userBestRatedMovies;
	private Set<Movie> socialNetBestRatedMovies;
	private List<RecomendationCandidate> candidates;
	private Map<String, Integer> userFavoriteTags;
	private RecomendationCandidate theChosenOne;

	public RecomendationSummary() {
		this.socialNet = new ArrayList<User>();
		this.userAverageRating = 0.0;
		this.socialNetAverageRating = 0.0;
		this.socialNetRates = new ArrayList<Rated>();
		this.userBestRatedMovies = new HashSet<Movie>();
		this.socialNetBestRatedMovies = new HashSet<Movie>();
		this.candidates = new ArrayList<RecomendationCandidate>();
		this.userFavoriteTags = new HashMap<String, Integer>();
	}

	public List<User> getSocialNet() {
		return socialNet;
	}

	public void setSocialNet(List<User> socialNet) {
		this.socialNet = socialNet;
	}

	public List<Rated> getSocialNetRates() {
		return socialNetRates;
	}

	public void setSocialNetRates(List<Rated> socialNetRates) {
		this.socialNetRates = socialNetRates;
	}

	public Set<Movie> getSocialNetBestRatedMovies() {
		return socialNetBestRatedMovies;
	}

	public void setSocialNetBestRatedMovies(Set<Movie> socialNetBestRatedMovies) {
		this.socialNetBestRatedMovies = socialNetBestRatedMovies;
	}

	public double getSocialNetAverageRating() {
		return socialNetAverageRating;
	}

	public void setSocialNetAverageRating(double socialNetAverageRating) {
		this.socialNetAverageRating = socialNetAverageRating;
	}

	public double getUserAverageRating() {
		return userAverageRating;
	}

	public void setUserAverageRating(double userAverageRating) {
		this.userAverageRating = userAverageRating;
	}

	public Set<Movie> getUserBestRatedMovies() {
		return userBestRatedMovies;
	}

	public void setUserBestRatedMovies(Set<Movie> userBestRatedMovies) {
		this.userBestRatedMovies = userBestRatedMovies;
	}

	public List<RecomendationCandidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<RecomendationCandidate> candidates) {
		this.candidates = candidates;
	}

	public void setUserFavoriteTags(Map<String, Integer> userFavoriteTags) {
		this.userFavoriteTags = userFavoriteTags;
	}

	public Map<String, Integer> getUserFavoriteTags() {
		return userFavoriteTags;
	}

	public RecomendationCandidate getTheChosenOne() {
		return theChosenOne;
	}

	public void setTheChosenOne(RecomendationCandidate theChosenOne) {
		this.theChosenOne = theChosenOne;
	}

	public void addUserFavoriteTag(String tagName) {
		Integer ocurrences = this.userFavoriteTags.get(tagName);
		if (ocurrences == null)
			ocurrences = 1;
		else
			ocurrences++;

		this.userFavoriteTags.put(tagName, ocurrences);
	}
}
