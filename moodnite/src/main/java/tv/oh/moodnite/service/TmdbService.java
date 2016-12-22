package tv.oh.moodnite.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class TmdbService {
	private final String URL_BASE = "https://api.themoviedb.org/3";
	private final String API_KEY = "?api_key=7e5f9a299f1ccb9c13ce6238850bdf7d";
	
	private final ObjectMapper MAPPER = new ObjectMapper();
	
	public Map<?, ?> getJsonDataMap(URL url) {
		try {
			Map<?, ?> jsonMap =  MAPPER.readValue(url, Map.class);
			return jsonMap;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public URL buildApiURL(String entity, String id, String method) {
		StringBuilder urlString = new StringBuilder(URL_BASE);
		urlString.append(entity);
		urlString.append(id);
		urlString.append(method);
		urlString.append(API_KEY);
		
		try {
			return new URL(urlString.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
