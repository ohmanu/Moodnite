package tv.oh.moodnite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tv.oh.moodnite.service.TmdbMovieService;
import tv.oh.moodnite.service.TmdbSearchService;

@Controller
public class MainController {
	@Autowired
	private TmdbMovieService tmdbMovieService;
	
	@Autowired
	private TmdbSearchService tmdbSearchService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showIndex(Model model) {
		model.addAttribute("popularMovies", tmdbMovieService.getPopularMovies().get("results"));
		model.addAttribute("upcomingMovies", tmdbMovieService.getUpcomingMovies().get("results"));
		
		return "/index";
	}
	
	@RequestMapping(value = "/search", method=RequestMethod.POST, headers = "Accept=text/html")
	public String submitForm(Model model, @RequestParam String query) {
	    model.addAttribute("films_results", tmdbSearchService.movieSearch(query).get("results"));
	    model.addAttribute("people_results", tmdbSearchService.personSearch(query).get("results"));
	    
	    return "/search/results";
	}
}
