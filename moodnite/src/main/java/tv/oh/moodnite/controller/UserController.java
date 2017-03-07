package tv.oh.moodnite.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.service.MovieService;
import tv.oh.moodnite.service.UserService;
import tv.oh.moodnite.service.WatchedService;

@RequestMapping(value = "/user/*")
@Controller
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private WatchedService watchedService;

	
	@RequestMapping(value = "sign-in", method = RequestMethod.GET, headers = "Accept=text/html")
	public String createUser(Model model) {
		model.addAttribute("user", new User());

		return "/user/sign-in";
	}
	
	@RequestMapping(value = "sign-in", method = RequestMethod.POST, headers = "Accept=text/html")
	public String saveUser(@ModelAttribute("user") User user) {
		User newUser = userService.signInUser(user);
		
		if(newUser == null)
			return "/user/sign-in";
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET, headers = "Accept=text/html")
	public String loginUser(Model model) {
		model.addAttribute("user", new User());

		return "/user/login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST, headers = "Accept=text/html")
	public String loginUser(@ModelAttribute("user") User user, HttpSession session) {
		User loogedInuser = userService.loginUser(user.getName(), user.getPassword());
		
		if(loogedInuser == null)
			return "/user/login";
		
		session.setAttribute("loggedInUser", loogedInuser);

		return "redirect:/";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET, headers = "Accept=text/html")
	public String logoutUser(HttpSession session) {
		session.removeAttribute("loggedInUser");

		return "redirect:/";
	}
	
	@RequestMapping(value = "reviews", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showUserReviews(HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		return "/user/reviews";
	}
	
	@RequestMapping(value = "watch/{movieId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String watchingAMovie(@PathVariable String movieId, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);		
		watchedService.watchMovie(loggedInUser, movie, new Date(), "I'm watching.");
		
		return "redirect:/movie/" + movieId;
	}
}
