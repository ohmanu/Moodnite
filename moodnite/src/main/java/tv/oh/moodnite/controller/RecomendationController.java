package tv.oh.moodnite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.service.moodnite.MovieService;
import tv.oh.moodnite.service.moodnite.UserService;
import tv.oh.moodnite.service.recomendation.RecomendationService;
import tv.oh.moodnite.service.recomendation.RecomendationSummary;

@RequestMapping(value = "/recomendation/*")
@Controller
public class RecomendationController {
	
	@Autowired
	private RecomendationService recomendationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MovieService movieService;

	@RequestMapping(value = "recomendations", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showRecomendations(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		RecomendationSummary recomendationSummary = recomendationService.build(loggedInUser);
		model.addAttribute("recomendation_summary", recomendationSummary);
		model.addAttribute("the_chosen_one", recomendationSummary.getTheChosenOne());
		
		return "/recomendation/recomendations";
	}
	
	@RequestMapping(value = "recomendations_debug_mode", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showRecomendationsDebugMode(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		model.addAttribute("recomendation_summary", recomendationService.build(loggedInUser));
		
		return "/recomendation/recomendations-debug-mode";
	}
	
	@RequestMapping(value = "refuse/{movieId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String refuseFilm(@PathVariable String movieId, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");

		if (loggedInUser == null)
			return "redirect:/user/login";

		Movie movie = movieService.findByTmdbId(movieId);
		loggedInUser.addRefused(movie);
		userService.saveUser(loggedInUser);

		return "redirect:/user/friends";
	}
}
