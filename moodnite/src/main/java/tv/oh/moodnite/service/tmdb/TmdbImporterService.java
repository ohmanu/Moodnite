package tv.oh.moodnite.service.tmdb;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.oh.moodnite.domain.Movie;

@Service
public class TmdbImporterService {
	@Autowired
	private TmdbMovieService tmdbMovieService;
	
	private Movie movie;
	
	public Movie importMovie(String movieId) {
		Map<?, ?> movieDetails = tmdbMovieService.getMovieDetails(movieId);
		movie = new Movie();
		movie.setTmdbId(movieId);
		movie.setTitle((String) movieDetails.get("title"));
		movie.setYear((String) movieDetails.get("year"));
		movie.setBackground((String) movieDetails.get("backdrop_path"));
		
		return movie;
	}
}
