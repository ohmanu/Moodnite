package tv.oh.moodnite.domain;

import java.util.Date;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "RATED")
public class Rated implements Publication {
	@GraphId
	private Long id;

	@StartNode
	private User user;
	
	@EndNode
	private Movie movie;
	
	private Date date;
	private int rate;
	private String reviewXS;
	private String reviewXL;

	public Rated() {

	}
	
	public Rated(User user, Movie movie, Date date, int rate) {
		this.date = date;
		this.user = user;
		this.movie = movie;
		this.rate = rate;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getStars() {
		StringBuilder stars = new StringBuilder(" ");
		
		for(int i=1; i <= (this.rate); i++)
			stars.append("&#9733; ");
		for(int i=1; i <= (10 - this.rate); i++)
			stars.append("&#9734; ");
		
		return stars.toString();
	}
	
	@Override
	public String getText1() {
		return this.reviewXS;
	}

	@Override
	public String getText2() {
		return this.getStars();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.movie))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rated [id=" + id + ", user=" + user.getName() + ", movie=" + movie.getTitle() + ", rate=" + rate + ", reviewXS=" + reviewXS
				+ ", reviewXL=" + reviewXL + "]";
	}
}
