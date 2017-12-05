package tv.oh.moodnite.service.moodnite;

import java.util.Date;
import java.util.List;

import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.Rated;
import tv.oh.moodnite.domain.Tag;
import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.domain.Watched;
import tv.oh.moodnite.repository.RateRepository;
import tv.oh.moodnite.repository.TagRepository;
import tv.oh.moodnite.repository.UserRepository;
import tv.oh.moodnite.repository.WatchedRepository;

@Service
public class UserService {
	private static int DEPTH = 2;

	@Autowired
    private Session session;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RateRepository rateRepo;
	
	@Autowired
	private WatchedRepository watchRepo;
	
	@Autowired
	private TagRepository tagRepo;
	
	public Iterable<User> findAll() {
		return userRepo.findAll();
	}
	
	public User findByUserId(Long userId) {
		return userRepo.findOne(userId);
	}
	
	public User findByName(String name) {
		return IteratorUtil.firstOrNull(findUsersByProperty("name", name).iterator());
	}
	
	public User loginUser(String name, String password) {
		User user = findByName(name);		
		
		if(user != null && user.getPassword().equals(password))
			return session.load(User.class, user.getId(), DEPTH);

		return null;
	}
	
	@Transactional
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	public Rated findUserMovieRate(User user, Movie movie) {
		for(Rated rated : user.getRatedList())
			if(rated.getMovie().equals(movie))
				return rated;
		
		return null;
	}
	
	public Watched findUserMovieWatch(User user, Movie movie) {
		for(Watched watched : user.getWatchedList())
			if(watched.getMovie().equals(movie))
				return watched;
		
		return null;
	}
	
	public Iterable<User> findByNameLike(String name) {
		return userRepo.findByNameLike(".*" + name + ".*");
	}
	
	public Iterable<User> findUsersLimit(Integer limit) {
		return userRepo.findUsersLimit(limit);
	}
	
	@Transactional
	public void watchMovie(User user, Movie movie, Date date, String formattedDate, String comment) {
		Watched watch = new Watched(user, movie, date, formattedDate, comment);
		user.addWatch(watch);
		movie.addWatch(watch);

		watchRepo.save(watch);
	}
	
	@Transactional
	public void removeWatch(User user, Watched watch) {
		Movie movie = watch.getMovie();
		
		user.removeWatch(watch);
		movie.removeWatch(watch);
		
		watchRepo.delete(watch);
	}
	
	@Transactional
	public void rateMovie(User user, Rated rate) {
		Movie movie = rate.getMovie();
		
		user.addRate(rate);
		movie.addRate(rate);

		rateRepo.save(rate);
	}
	
	@Transactional
	public void removeRate(User user, Rated rate) {
		Movie movie = rate.getMovie();
		
		user.removeRate(rate);
		movie.removeRate(rate);
		
		rateRepo.delete(rate);
	}
	
	@Transactional
	public void listMovie(User user, Movie movie, String name) {
		Tag tag = new Tag(user, movie, name);
		user.addTag(tag);
		movie.addTag(tag);
		
		tagRepo.save(tag);
	}
	
	@Transactional
	public void removeTag(User user, Long tagId) {
		Tag tag = tagRepo.findOne(tagId);
		Movie movie = tag.getMovie();
		
		user.removeTag(tag);
		movie.removeTag(tag);
		
		tagRepo.delete(tag);
	}
	
	public List<User> findSocialNet(String name) {
		List<User> socialNet = userRepo.findSocialNet(name);
		for(User user : socialNet) {
			findByName(user.getName());
		}
		
		return socialNet;
	}
	
	private Iterable<User> findUsersByProperty(String propertyName, Object propertyValue) {
        return session.loadAll(User.class, new Filter(propertyName, propertyValue));
    }
}
