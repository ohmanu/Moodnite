package tv.oh.moodnite.service;

import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmdbMovieService {
	private final String MOVIE_PATH = "/movie/";
	
	@Autowired
	TmdbService tmdbService;
	
	public Map<?, ?> getMovieDetails(String movieId) {
		URL url = tmdbService.buildApiURL(MOVIE_PATH, movieId, "");
		
		return tmdbService.getJsonDataMap(url);
	}
	
	public Map<?, ?> getPopularMovies() {
		URL url = tmdbService.buildApiURL(MOVIE_PATH, "popular", "");
		
		return tmdbService.getJsonDataMap(url);
	}
	
	public Map<?, ?> getUpcomingMovies() {
		URL url = tmdbService.buildApiURL(MOVIE_PATH, "upcoming", "");
		
		return tmdbService.getJsonDataMap(url);
	}
	
	public Map<?, ?> getRelatedMovies(String movieId) {
		URL url = tmdbService.buildApiURL(MOVIE_PATH, movieId, "/recommendations");
		
		return tmdbService.getJsonDataMap(url);
	}
	
	public Map<?, ?> getMovieCredits(String movieId) {
		URL url = tmdbService.buildApiURL(MOVIE_PATH, movieId, "/credits");
		
		return tmdbService.getJsonDataMap(url);
	}
}
