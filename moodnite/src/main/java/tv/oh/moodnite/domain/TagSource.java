package tv.oh.moodnite.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class TagSource {
	@GraphId
	private Long id;
	
	private String sourceName;
	
	@Relationship(type = "TAGGEDFROMSOURCE", direction = Relationship.OUTGOING)
	private Set<Tag> tags = new HashSet<>();

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sourceName == null) ? 0 : sourceName.hashCode());
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
		TagSource other = (TagSource) obj;
		if (sourceName == null) {
			if (other.sourceName != null)
				return false;
		} else if (!sourceName.equals(other.sourceName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TagSource [id=" + id + ", sourceName=" + sourceName + "]";
	}
}
