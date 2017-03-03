package tv.oh.moodnite.service.tmdb;

import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmdbPersonService {
	private final String PERSON_PATH = "/person/";
	
	@Autowired
	TmdbService tmdbService;
	
	public Map<?, ?> getPersonInfo(String personId) {
		URL url = tmdbService.buildApiURL(PERSON_PATH, personId, "");
		
		return tmdbService.getJsonDataMap(url);
	}
	
	public Map<?, ?> getPersonMovieCredits(String personId) {
		URL url = tmdbService.buildApiURL(PERSON_PATH, personId, "/movie_credits");
		
		return tmdbService.getJsonDataMap(url);
	}
}
