package tv.oh.moodnite.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "RATED")
public class Rated {
	@GraphId
	private Long id;

	@StartNode
	private User user;
	
	@EndNode
	private Movie movie;
	
	private int rate;
	private String reviewXS;
	private String reviewXL;

	public Rated() {

	}

	public Rated(User user, Movie movie, int rate, String reviewXS, String reviewXL) {
		this.user = user;
		this.movie = movie;
		this.rate = rate;
		this.reviewXS = reviewXS;
		this.reviewXL = reviewXL;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getReviewXS() {
		return reviewXS;
	}

	public void setReviewXS(String reviewXS) {
		this.reviewXS = reviewXS;
	}

	public String getReviewXL() {
		return reviewXL;
	}

	public void setReviewXL(String reviewXL) {
		this.reviewXL = reviewXL;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rated other = (Rated) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rated [id=" + id + ", rate=" + rate + ", reviewXS=" + reviewXS + ", reviewXL=" + reviewXL + "]";
	}
}
