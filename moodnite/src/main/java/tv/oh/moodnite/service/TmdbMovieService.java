package tv.oh.moodnite.service;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmdbMovieService {
	private final String MOVIE_PATH = "/movie/";
	
	@Autowired
	TmdbService tmdbService;
	
	public Map<?, ?> getMovieInfo(String movieId) {
		URL url = tmdbService.buildApiURL(MOVIE_PATH, movieId, "");
		
		return tmdbService.getJsonDataMap(url);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<?, ?>> getPopularMovies() {
		URL url = tmdbService.buildApiURL(MOVIE_PATH, "popular", "");
		Map<?, ?> popularMovies = tmdbService.getJsonDataMap(url);
		
		return (List<Map<?, ?>>) popularMovies.get("results");
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<?, ?>> getUpcomingMovies() {
		URL url = tmdbService.buildApiURL(MOVIE_PATH, "upcoming", "");
		Map<?, ?> popularMovies = tmdbService.getJsonDataMap(url);
		
		return (List<Map<?, ?>>) popularMovies.get("results");
	}
	
	public Map<?, ?> getMovieCredits(String movieId) {
		URL url = tmdbService.buildApiURL(MOVIE_PATH, movieId, "/credits");
		
		return tmdbService.getJsonDataMap(url);
	}
}
