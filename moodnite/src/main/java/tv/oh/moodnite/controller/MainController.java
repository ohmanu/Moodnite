package tv.oh.moodnite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.oh.moodnite.service.TmdbMovieService;

@Controller
public class MainController {
	@Autowired
	private TmdbMovieService tmdbMovieService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showIndex(Model model) {
		model.addAttribute("popularMovies", tmdbMovieService.getPopularMovies().get("results"));
		model.addAttribute("upcomingMovies", tmdbMovieService.getUpcomingMovies().get("results"));
		
		return "/index";
	}
}
