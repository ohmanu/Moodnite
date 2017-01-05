package tv.oh.moodnite.service;

import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmdbSearchService {
	private final String SEARCH_PATH = "/search/";
	
	@Autowired
	TmdbService tmdbService;
	
	public Map<?, ?> searchMovie(String query) {
		URL url = tmdbService.buildApiURL(SEARCH_PATH, "", "movie", "&query=" + query.replace(" ", "%20"));
		
		return tmdbService.getJsonDataMap(url);
	}
}
