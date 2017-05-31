package tv.oh.moodnite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.Rated;
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
	
	public void rates() {
		rateRepo.findAll();
	}
	
	public Rated findByRateId(Long id) {
		return rateRepo.findOne(id);
	}
}
