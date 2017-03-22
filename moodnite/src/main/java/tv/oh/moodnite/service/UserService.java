package tv.oh.moodnite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.Rated;
import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.repository.RateRepository;
import tv.oh.moodnite.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	RateRepository rateRepo;
	
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
	
	public Rated rateMovie(User user, Movie movie, int stars) {
		Rated rate = findUserMovieRate(user, movie);
		System.out.println("RATE - " + rate);
		
		if(rate == null) {
			rate = new Rated(user, movie, stars);
			user.getRatedList().add(rate);
			userRepo.save(user);
			
			return rate;
		}
		
		user.getRatedList().remove(rate);
		rate.setRate(stars);		
		user.getRatedList().add(rate);
		userRepo.save(user);
		
		return rate;
	}
	
	public Rated reviewMovie(User user, Movie movie, String reviewXS) {
		Rated rate = findUserMovieRate(user, movie);
		
		if(rate == null) {
			return null;
		}
		
		user.getRatedList().remove(rate);
		rate.setReviewXS(reviewXS);
		user.getRatedList().add(rate);
		userRepo.save(user);
		
		return rate;
	}
	
	public void deleteRate(User user, Movie movie) {
		System.out.println("MOVIE - " + movie);
		Rated rate = findUserMovieRate(user, movie);
		
		System.out.println("RATE - " + rate);
		user.getRatedList().remove(rate);
		rateRepo.delete(rate);
		System.out.println("USUARIO - " + user);
		userRepo.save(user);
	}
	
	public Rated findUserMovieRate(User user, Movie movie) {
		return rateRepo.findUserMovieRate(user.getId(), movie.getTmdbId());
	}
}
