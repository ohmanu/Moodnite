package tv.oh.moodnite.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Movie {
	@GraphId
	private Long id;

	@Relationship(type = "WATCHED", direction = Relationship.INCOMING)
	private Set<Watched> watchedList = new HashSet<>();
	
	@Relationship(type = "RATED", direction = Relationship.INCOMING)
	private Set<Rated> ratedList = new HashSet<>();

	private String tmdbId;
	private String title;
	private String year;
	private String background;

	public Long getId() {
		return id;
	}

	public String getTmdbId() {
		return tmdbId;
	}

	public void setTmdbId(String tmdbId) {
		this.tmdbId = tmdbId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@Relationship(type = "WATCHED", direction = Relationship.INCOMING)
	public Set<Watched> getWatchedList() {
		return watchedList;
	}

	@Relationship(type = "WATCHED", direction = Relationship.INCOMING)
	public void setWatchedList(Set<Watched> watchedList) {
		this.watchedList = watchedList;
	}

	@Relationship(type = "RATED", direction = Relationship.INCOMING)
	public Set<Rated> getRatedList() {
		return ratedList;
	}

	@Relationship(type = "RATED", direction = Relationship.INCOMING)
	public void setRatedList(Set<Rated> ratedList) {
		this.ratedList = ratedList;
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
		Movie other = (Movie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
