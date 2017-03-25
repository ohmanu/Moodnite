package tv.oh.moodnite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.Rated;
import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.domain.Watched;
import tv.oh.moodnite.repository.RateRepository;
import tv.oh.moodnite.repository.UserRepository;
import tv.oh.moodnite.repository.WatchedRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	RateRepository rateRepo;
	
	@Autowired
	WatchedRepository watchRepo;
	
	public User loginUser(String name, String password) {
		User user = userRepo.findByName(name);
		
		if(user != null && user.getPassword().equals(password))
			return user;
		
		return null;
	}
	
	public User signInUser(User user) {
		return userRepo.save(user);
	}
	
	public User updateUser(User user) {
		return userRepo.save(user);
	}
	
	public Rated findUserMovieRate(User user, Movie movie) {
		return rateRepo.findUserMovieRate(user.getId(), movie.getTmdbId());
	}
	
	public Watched findUserMovieWatch(User user, Movie movie) {
		return watchRepo.findUserMovieWatch(user.getId(), movie.getTmdbId());
	}
}
