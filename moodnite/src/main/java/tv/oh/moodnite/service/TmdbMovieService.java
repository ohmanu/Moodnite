package tv.oh.moodnite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmdbMovieService {
	
	private Map<?, ?> info;
	private List<Map<?, ?>> productionCountries;
	private List<Map<?, ?>> genres;
	private List<Map<?, ?>> spokenLenguages;
	private Map<?, ?> credits;
	private List<Map<?, ?>> casts;
	private List<Map<?, ?>> crew;
	private List<Map<String, String>> directors;
	
	@Autowired
	private TmdbService tmdbService;
	
	@SuppressWarnings("unchecked")
	public TmdbMovieService(String movieId) {
		this.info = this.tmdbService.getMovieInfo(movieId);
		this.productionCountries = (List<Map<?, ?>>) info.get("production_countries");
		this.genres = (List<Map<?, ?>>) info.get("genres");
		this.spokenLenguages = (List<Map<?, ?>>) info.get("spoken_lenguages");
		this.credits = tmdbService.getMovieCredits(movieId);
		this.casts = (List<Map<?, ?>>) credits.get("casts");
		this.crew = (List<Map<?, ?>>) credits.get("crew");
		loadDirectors();
	}
	
	public Map<?, ?> getInfo() {
		return info;
	}

	public void setInfo(Map<?, ?> info) {
		this.info = info;
	}

	public List<Map<?, ?>> getProductionCountries() {
		return productionCountries;
	}

	public void setProductionCountries(List<Map<?, ?>> productionCountries) {
		this.productionCountries = productionCountries;
	}

	public List<Map<?, ?>> getGenres() {
		return genres;
	}

	public void setGenres(List<Map<?, ?>> genres) {
		this.genres = genres;
	}

	public List<Map<?, ?>> getSpokenLenguages() {
		return spokenLenguages;
	}

	public void setSpokenLenguages(List<Map<?, ?>> spokenLenguages) {
		this.spokenLenguages = spokenLenguages;
	}

	public Map<?, ?> getCredits() {
		return credits;
	}

	public void setCredits(Map<?, ?> credits) {
		this.credits = credits;
	}

	public List<Map<?, ?>> getCasts() {
		return casts;
	}

	public void setCasts(List<Map<?, ?>> casts) {
		this.casts = casts;
	}

	public List<Map<?, ?>> getCrew() {
		return crew;
	}

	public void setCrew(List<Map<?, ?>> crew) {
		this.crew = crew;
	}

	public List<Map<String, String>> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Map<String, String>> directors) {
		this.directors = directors;
	}

	private void loadDirectors() {
		Map<String, String> aux = new HashMap<String, String>();
		
		for(Map<?, ?> person:this.crew) {
			if(person.get("job").toString().equals("Director"))
			{
				aux.put("id", (String) person.get("id"));
				aux.put("name", (String) person.get("name"));
				this.directors.add(aux);
				aux = new HashMap<String, String>();
			}
		}
	}
}
