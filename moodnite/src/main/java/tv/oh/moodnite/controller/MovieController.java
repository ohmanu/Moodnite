package tv.oh.moodnite.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.oh.moodnite.service.TmdbService;


@Controller
public class MovieController {
	@Autowired
	private TmdbService tmdbService;
	
	@RequestMapping(value = "/movie/{movieId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showMovieInfo(Model model, @PathVariable String movieId) {
		Map<?, ?> info = tmdbService.getMovieInfo(movieId);
		Map<?, ?> credits = tmdbService.getMovieCredits(movieId);
		
		model.addAttribute("info", info);
		model.addAttribute("directors", loadDirectors(credits));
		model.addAttribute("genres", info.get("genres"));
		model.addAttribute("production_countries", info.get("production_countries"));
		model.addAttribute("cast", credits.get("cast"));

		return "/movie/show";
	}
	
	@SuppressWarnings("unchecked")
	private List<Map<String, String>> loadDirectors(Map<?, ?> credits) {
		List<Map<String, String>> directors = new ArrayList<Map<String, String>>();
		Map<String, String> aux = new HashMap<String, String>();
		int numElements = 0;
		
		for(Map<?, ?> person:((List<Map<String, String>>) credits.get("crew"))) {
			if(person.get("job").toString().equals("Director"))
			{
				aux.put("id", Integer.toString((Integer) (person.get("id"))));
				aux.put("name", (String) person.get("name"));
				directors.add(aux);
				numElements++;
				aux = new HashMap<String, String>();
			}
		}
		
		for(Map<String, String> director:directors) {
			if(numElements > 2)
				director.put("coma", ",");
			else if(numElements > 1)
				director.put("coma", " &");
			else
				director.put("coma", "");
			
			numElements--;
		}
		
		return directors;
	}
}
