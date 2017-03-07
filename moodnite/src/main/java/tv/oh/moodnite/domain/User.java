package tv.oh.moodnite.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class User {
	@GraphId
	private Long id;

	@Relationship(type = "WATCHED")
	private Set<Watched> watchedList = new HashSet<>();
	
	@Relationship(type = "RATED")
	private Set<Rated> ratedList = new HashSet<>();

	private String name;
	private String password;
	private String photo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Set<Rated> getRatedList() {
		return ratedList;
	}

	public void setRatedList(Set<Rated> ratedList) {
		this.ratedList = ratedList;
	}

	public Set<Watched> getWatchedList() {
		return watchedList;
	}

	public void setWatchedList(Set<Watched> watchedList) {
		this.watchedList = watchedList;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", photo=" + photo + "]";
	}
}
