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

	private String name;
	private String password;
	private String photo;
	private String bio;
	
	@Relationship(type = "WATCHED", direction = Relationship.OUTGOING)
	private Set<Watched> watchedList = new HashSet<>();
	
	@Relationship(type = "RATED")
	private Set<Rated> ratedList = new HashSet<>();
	
	@Relationship(type = "FOLLOWS", direction = Relationship.OUTGOING)
	private Set<User> follows = new HashSet<>();
	
	@Relationship(type = "NOTIFICATION", direction = Relationship.OUTGOING)
	private Set<User> newFollowers = new HashSet<>();
	
	@Relationship(type = "TAGGED", direction = Relationship.OUTGOING)
	private Set<Tag> tags = new HashSet<>();

	public Set<Watched> getWatchedList() {
		return watchedList;
	}

	public void setWatchedList(Set<Watched> watchedList) {
		this.watchedList = watchedList;
	}

	public Set<Rated> getRatedList() {
		return ratedList;
	}

	public void setRatedList(Set<Rated> ratedList) {
		this.ratedList = ratedList;
	}

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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Long getId() {
		return id;
	}

	public Set<User> getFollows() {
		return follows;
	}

	public void setFollows(Set<User> follows) {
		this.follows = follows;
	}
	
	public Set<User> getNewFollowers() {
		return newFollowers;
	}

	public void setNewFollowers(Set<User> newFollowers) {
		this.newFollowers = newFollowers;
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
	
	public void addFriend(User user) {
		this.follows.add(user);
	}
	
	public void removeFriend(User user) {		
		this.follows.remove(user);
	}
	
	public void addNewFollower(User user) {
		this.newFollowers.add(user);
	}
	
	public void removeNewFollower(User user) {		
		this.newFollowers.remove(user);
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	public void addTag(Tag tag) {
		this.tags.add(tag);
	}
	
	public void removeTag(Tag tag) {		
		this.tags.remove(tag);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", photo=" + photo + ", bio=" + bio + "]";
	}
}
