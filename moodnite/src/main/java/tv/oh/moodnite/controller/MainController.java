package tv.oh.moodnite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tv.oh.moodnite.service.moodnite.UserService;
import tv.oh.moodnite.service.tmdb.TmdbMovieService;
import tv.oh.moodnite.service.tmdb.TmdbSearchService;

@Controller
public class MainController {
	@Autowired
	private TmdbMovieService tmdbMovieService;

	@Autowired
	private TmdbSearchService tmdbSearchService;

	@Autowired
	private UserService userService;
	
	/**
	 * Convierte Strings vacios en null cuando se envía un formulario.
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showIndex(Model model) {
		model.addAttribute("popular_movies", tmdbMovieService.getPopularMovies().get("results"));
		model.addAttribute("upcoming_movies", tmdbMovieService.getUpcomingMovies().get("results"));

		return "/index";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, headers = "Accept=text/html")
	public String submitForm(Model model, @RequestParam String query) {
		if (query != null) {
			model.addAttribute("films_results", tmdbSearchService.movieSearch(query).get("results"));
			model.addAttribute("people_results", tmdbSearchService.personSearch(query).get("results"));
			model.addAttribute("users_results", userService.findByNameLike(query));
		}

		return "/search/results";
	}
}
