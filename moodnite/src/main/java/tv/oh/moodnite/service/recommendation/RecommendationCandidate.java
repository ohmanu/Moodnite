package tv.oh.moodnite.service.recommendation;

import java.util.HashMap;
import java.util.Map;

import tv.oh.moodnite.domain.Movie;

public class RecommendationCandidate {
	private Movie movie;
	private double score;
	private boolean lustrumThisYear;
	private int yearsElapsed;
	private Map<String, Integer> tagValues;

	public RecommendationCandidate() {
		this.tagValues = new HashMap<String, Integer>();
	}

	public RecommendationCandidate(Movie movie) {
		this();
		this.movie = movie;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = this.score + score;
	}

	public boolean isLustrumThisYear() {
		return lustrumThisYear;
	}

	public void setLustrumThisYear(boolean lustrumThisYear) {
		this.lustrumThisYear = lustrumThisYear;
	}

	public Map<String, Integer> getTagValues() {
		return tagValues;
	}

	public void setTagValues(Map<String, Integer> tagValues) {
		this.tagValues = tagValues;
	}

	public int getYearsElapsed() {
		return yearsElapsed;
	}

	public void setYearsElapsed(int yearsElapsed) {
		this.yearsElapsed = yearsElapsed;
	}
}
