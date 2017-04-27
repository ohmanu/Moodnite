package tv.oh.moodnite.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RateRepository rateRepo;
	
	@Autowired
	private WatchedRepository watchRepo;
	
	@Autowired
	private TagRepository tagRepo;
	
	public User findByUserId(Long userId) {
		return userRepo.findOne(userId);
	}
	
	public User loginUser(String name, String password) {
		User user = userRepo.findByName(name);
		
		if(user != null && user.getPassword().equals(password))
			return userRepo.findOne(user.getId());
		
		return null;
	}
	
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	public Rated findUserMovieRate(User user, Movie movie) {
		return rateRepo.findUserMovieRate(user.getId(), movie.getTmdbId());
	}
	
	public Watched findUserMovieWatch(User user, Movie movie) {
		return watchRepo.findUserMovieWatch(user.getId(), movie.getTmdbId());
	}
	
	public Iterable<User> findByNameLike(String name) {
		return userRepo.findByNameLike(name);
	}
	
	public void watchMovie(User user, Movie movie, Date date, String formattedDate, String comment) {
		Watched watch = new Watched(user, movie, date, formattedDate, comment);
		user.addWatch(watch);
		movie.addWatch(watch);

		watchRepo.save(watch);
	}
	
	public void removeWatch(User user, Watched watch) {
		Movie movie = watch.getMovie();
		
		user.removeWatch(watch);
		movie.removeWatch(watch);
		
		watchRepo.delete(watch);
	}
	
	public void rateMovie(User user, Rated rate) {
		Movie movie = rate.getMovie();
		
		user.addRate(rate);
		movie.addRate(rate);

		rateRepo.save(rate);
	}
	
	public void removeRate(User user, Rated rate) {
		Movie movie = rate.getMovie();
		
		user.removeRate(rate);
		movie.removeRate(rate);
		
		rateRepo.delete(rate);
	}
	
	public void listMovie(User user, Movie movie, String name) {
		Tag tag = new Tag(user, movie, name);
		user.addTag(tag);
		movie.addTag(tag);
		
		tagRepo.save(tag);
	}
	
	public void removeTag(User user, Long tagId) {
		Tag tag = tagRepo.findOne(tagId);
		Movie movie = tag.getMovie();
		
		user.removeTag(tag);
		movie.removeTag(tag);
		
		tagRepo.delete(tag);
	}
}
