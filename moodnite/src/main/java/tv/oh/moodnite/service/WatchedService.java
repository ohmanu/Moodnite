package tv.oh.moodnite.service;

import java.util.Date;

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
	
	public Watched watchMovie(User user, Movie movie, Date date, String comment) {
		return watchedRepo.save(new Watched(user, movie, date, comment));
	}
	
	public void wathes() {
		//System.out.println(watchedRepo.findOne((long) 0).getMovie().getTitle());
	}
}
