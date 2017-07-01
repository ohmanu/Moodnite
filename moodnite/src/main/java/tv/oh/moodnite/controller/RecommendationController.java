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
import tv.oh.moodnite.service.recommendation.RecommendationService;
import tv.oh.moodnite.service.recommendation.RecommendationSummary;

@RequestMapping(value = "/recommendation/*")
@Controller
public class RecommendationController {
	
	@Autowired
	private RecommendationService recommendationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MovieService movieService;

	@RequestMapping(value = "recommendations", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showRecommendations(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		RecommendationSummary recommendationSummary = recommendationService.build(loggedInUser);
		model.addAttribute("recommendation_summary", recommendationSummary);
		model.addAttribute("the_chosen_one", recommendationSummary.getTheChosenOne());
		model.addAttribute("people_you_should_follow", userService.findUsersLimit(6));
		
		return "/recommendation/recommendations";
	}
	
	@RequestMapping(value = "recommendations-debug-mode", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showRecommendationsDebugMode(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		model.addAttribute("recommendation_summary", recommendationService.build(loggedInUser));
		
		return "/recommendation/recommendations-debug-mode";
	}
	
	@RequestMapping(value = "refuse/{movieId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String refuseFilm(@PathVariable String movieId, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");

		if (loggedInUser == null)
			return "redirect:/user/login";

		Movie movie = movieService.findByTmdbId(movieId);
		loggedInUser.addRefused(movie);
		userService.saveUser(loggedInUser);

		return "redirect:/recommendation/recommendations";
	}
}
