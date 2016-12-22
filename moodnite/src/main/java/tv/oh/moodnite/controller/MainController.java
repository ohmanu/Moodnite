package tv.oh.moodnite.controller;

import java.util.List;
import java.util.Map;

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
		List<Map<?, ?>> popularMovies = tmdbMovieService.getPopularMovies();
		model.addAttribute("popularMovies", popularMovies);
		
		List<Map<?, ?>> upcomingMovies = tmdbMovieService.getUpcomingMovies();
		model.addAttribute("upcomingMovies", upcomingMovies);
		
		return "/index";
	}
}
