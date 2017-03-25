package tv.oh.moodnite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.domain.Watched;
import tv.oh.moodnite.repository.WatchedRepository;

@Service
public class WatchedService {
	@Autowired
	WatchedRepository watchedRepo;
	
	public Watched watchMovie(User user, Movie movie, String date, String comment) {
		return watchedRepo.save(new Watched(user, movie, date, comment));
	}
	
	public void wathes() {
		watchedRepo.findAll();
	}
	
	public Watched findById(Long id) {
		return watchedRepo.findOne(id);
	}
}
