package tv.oh.moodnite.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "TAGGED")
public class TagFromSource {
	@GraphId
	private Long id;

	@StartNode
	private TagSource tagSource;

	@EndNode
	private Movie movie;

	private String name;

	public TagFromSource() {
	}

	public TagFromSource(TagSource tagSource, Movie movie, String name) {
		this.tagSource = tagSource;
		this.movie = movie;
		this.name = name;
	}

	public TagSource getTagSource() {
		return tagSource;
	}

	public void setTagSource(TagSource tagSource) {
		this.tagSource = tagSource;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tagSource == null) ? 0 : tagSource.hashCode());
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
		TagFromSource other = (TagFromSource) obj;
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.movie))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tagSource == null) {
			if (other.tagSource != null)
				return false;
		} else if (!tagSource.equals(other.tagSource))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", tagSource=" + tagSource.getSourceName() + ", movie=" + movie.getTitle() + ", name="
				+ name + "]";
	}
}
