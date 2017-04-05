package tv.oh.moodnite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.Rated;
import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.repository.MovieRepository;
import tv.oh.moodnite.repository.RateRepository;
import tv.oh.moodnite.repository.UserRepository;

@Service
public class RateService {
	@Autowired
	RateRepository rateRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MovieRepository movieRepo;
	
	public Rated updateRate(Rated rate) {
		return rateRepo.save(rate);
	}
	
	public Rated findByRateId(Long id) {
		return rateRepo.findOne(id);
	}
	
	public Rated findUserMovieRate(User user, Movie movie) {
		return rateRepo.findUserMovieRate(user.getId(), movie.getTmdbId());
	}
	
	public void rates() {
		rateRepo.findAll();
	}
	
	public void deleteRate(Rated rate) {
		rateRepo.delete(rate);
	}
}
