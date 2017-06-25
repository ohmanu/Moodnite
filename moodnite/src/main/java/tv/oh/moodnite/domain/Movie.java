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

	@Relationship(type = "TAGGED", direction = Relationship.INCOMING)
	private Set<Tag> tags = new HashSet<>();

	@Relationship(type = "TAGGED", direction = Relationship.INCOMING)
	private Set<TagFromSource> tagsFromSources = new HashSet<>();

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

	public void addRate(Rated rate) {
		this.ratedList.add(rate);
	}

	public void removeRate(Rated rate) {
		this.ratedList.remove(rate);
	}

	public void addWatch(Watched watch) {
		this.watchedList.add(watch);
	}

	public void removeWatch(Watched watch) {
		this.watchedList.remove(watch);
	}

	@Relationship(type = "TAGGED", direction = Relationship.INCOMING)
	public Set<Tag> getTags() {
		return tags;
	}

	@Relationship(type = "TAGGED", direction = Relationship.INCOMING)
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public void addTag(Tag tag) {
		this.tags.add(tag);
	}

	public void removeTag(Tag tag) {
		this.tags.remove(tag);
	}
	
	@Relationship(type = "TAGGEDFROMSOURCE", direction = Relationship.INCOMING)
	public Set<TagFromSource> getTagsFromSources() {
		return tagsFromSources;
	}

	@Relationship(type = "TAGGEDFROMSOURCE", direction = Relationship.INCOMING)
	public void setTagsFromSources(Set<TagFromSource> tagsFromSources) {
		this.tagsFromSources = tagsFromSources;
	}
	
	public void addTagFromSource(TagFromSource tagFromSource) {
		this.tagsFromSources.add(tagFromSource);
	}

	public void removeTagFromSource(TagFromSource tagFromSource) {
		this.tagsFromSources.remove(tagFromSource);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((tmdbId == null) ? 0 : tmdbId.hashCode());
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
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (tmdbId == null) {
			if (other.tmdbId != null)
				return false;
		} else if (!tmdbId.equals(other.tmdbId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", tmdbId=" + tmdbId + ", title=" + title + ", year=" + year + ", background="
				+ background + "]";
	}
}
