package tv.oh.moodnite.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.repository.MovieRepository;
import tv.oh.moodnite.service.tmdb.TmdbMovieService;

@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepo;
	
	@Autowired
	private TmdbMovieService tmdbMovieService;
	
	public Movie findByTmdbId(String tmdbId) {
		Movie movie = movieRepo.findByTmdbId(tmdbId);
		
		if(movie == null)
			return null;
		else
			return movie;
	}
	
	public Movie addMovie(String tmdbId) {
		Map<?, ?> movieDetails = tmdbMovieService.getMovieDetails(tmdbId);
		Movie movie = new Movie();
		movie.setTmdbId(tmdbId);
		movie.setTitle((String) movieDetails.get("title"));
		movie.setYear((String) movieDetails.get("year"));
		movie.setBackground((String) movieDetails.get("backdrop_path"));
		
		movieRepo.save(movie);
		
		return movie;
	}
}
