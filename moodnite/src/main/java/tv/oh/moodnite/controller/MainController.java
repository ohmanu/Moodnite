package tv.oh.moodnite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tv.oh.moodnite.service.RateService;
import tv.oh.moodnite.service.TagService;
import tv.oh.moodnite.service.UserService;
import tv.oh.moodnite.service.WatchedService;
import tv.oh.moodnite.service.tmdb.TmdbMovieService;
import tv.oh.moodnite.service.tmdb.TmdbSearchService;

@Controller
public class MainController {
	@Autowired
	private TmdbMovieService tmdbMovieService;
	
	@Autowired
	private TmdbSearchService tmdbSearchService;
	
	@Autowired
	private WatchedService watchedService;
	
	@Autowired
	private RateService ratedService;
	
	@Autowired
	private TagService tagRepo;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showIndex(Model model) {
		model.addAttribute("popular_movies", tmdbMovieService.getPopularMovies().get("results"));
		model.addAttribute("upcoming_movies", tmdbMovieService.getUpcomingMovies().get("results"));
		
		//Solución provisional a la carga de relaciones del grafo
		watchedService.wathes();
		ratedService.rates();
		tagRepo.tags();
		
		return "/index";
	}
	
	@RequestMapping(value = "/search", method=RequestMethod.POST, headers = "Accept=text/html")
	public String submitForm(Model model, @RequestParam String query) {
	    model.addAttribute("films_results", tmdbSearchService.movieSearch(query).get("results"));
	    model.addAttribute("people_results", tmdbSearchService.personSearch(query).get("results"));
	    model.addAttribute("users_results", userService.findByNameLike(query));
	    
	    return "/search/results";
	}
}
