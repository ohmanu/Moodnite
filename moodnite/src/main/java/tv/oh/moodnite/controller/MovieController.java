package tv.oh.moodnite.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.Tag;
import tv.oh.moodnite.service.MovieService;
import tv.oh.moodnite.service.tmdb.TmdbMovieService;

@RequestMapping(value = "/movie/*")
@Controller
public class MovieController {
	@Autowired
	private TmdbMovieService tmdbMovieService;
	
	@Autowired
	private MovieService movieService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "{movieId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showMovieInfo(Model model, @PathVariable String movieId) {
		Map<?, ?> movieDetails = tmdbMovieService.getMovieDetails(movieId);
		Map<?, ?> credits = tmdbMovieService.getMovieCredits(movieId);		
		Movie movieInGraph = movieService.findByTmdbId(movieId);
		
		model.addAttribute("movie_details", movieDetails);
		model.addAttribute("directors", loadDirectors(credits));
		model.addAttribute("year", movieDetails.get("release_date").toString().substring(0, 4));
		model.addAttribute("genres", movieDetails.get("genres"));
		model.addAttribute("production_countries", formatMapList((List<Map<String, String>>) movieDetails.get("production_countries")));
		model.addAttribute("cast", credits.get("cast"));
		if(movieInGraph != null)
		{
			model.addAttribute("reviews", movieInGraph.getRatedList());
			Set<String> tags = new HashSet<>();
			for(Tag tag : movieInGraph.getTags())
				tags.add(tag.getName());
			model.addAttribute("tags", tags);
		}
		model.addAttribute("similar_movies", tmdbMovieService.getSimilarMovies(movieId).get("results"));

		return "/movie/show";
	}
	
	@RequestMapping(value = "{movieId}/credits", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showMovieCredits(Model model, @PathVariable String movieId) {
		Map<?, ?> movieDetails = tmdbMovieService.getMovieDetails(movieId);
		Map<?, ?> credits = tmdbMovieService.getMovieCredits(movieId);
		
		model.addAttribute("movie_details", movieDetails);
		model.addAttribute("cast", credits.get("cast"));
		model.addAttribute("crew", credits.get("crew"));

		return "/movie/credits";
	}
	
	@SuppressWarnings("unchecked")
	private List<Map<String, String>> loadDirectors(Map<?, ?> credits) {
		List<Map<String, String>> directors = new ArrayList<Map<String, String>>();
		Map<String, String> aux = new HashMap<String, String>();
		
		for(Map<?, ?> person:((List<Map<String, String>>) credits.get("crew"))) {
			if(person.get("job").toString().equals("Director"))
			{
				aux.put("id", Integer.toString((Integer) (person.get("id"))));
				aux.put("name", (String) person.get("name"));
				directors.add(aux);
				aux = new HashMap<String, String>();
			}
		}
		
		return formatMapList(directors);
	}

	private List<Map<String, String>> formatMapList(List<Map<String, String>> mapList) {
		int numElements = mapList.size(); 
		
		for(Map<String, String> element:mapList) {
			if(numElements > 2)
				element.put("coma", ",");
			else if(numElements > 1)
				element.put("coma", " &");
			else
				element.put("coma", "");
			
			numElements--;
		}
		
		return mapList;
	}
}
