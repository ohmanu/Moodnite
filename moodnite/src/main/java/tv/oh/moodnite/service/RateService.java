package tv.oh.moodnite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.Rated;
import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.repository.RateRepository;

@Service
public class RateService {
	@Autowired
	RateRepository rateRepo;
	
	public Rated updateRate(Rated rate) {
		return rateRepo.save(rate);
	}
	
	public Rated rate(User user, Movie movie, int stars) {
		Rated rate = rateRepo.findUserMovieRate(user.getId(), movie.getTmdbId());
		System.out.println(rate);
		if(rate != null) {
			rate.setRate(stars);
			return rate;
		}
		
		return rateRepo.save(new Rated(user, movie, stars));
	}
	
	public Rated findByRateId(Long id) {
		return rateRepo.findOne(id);
	}
}
