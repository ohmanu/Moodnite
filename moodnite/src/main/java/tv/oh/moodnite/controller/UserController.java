package tv.oh.moodnite.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.Rated;
import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.service.MovieService;
import tv.oh.moodnite.service.RateService;
import tv.oh.moodnite.service.UserService;
import tv.oh.moodnite.service.WatchedService;
import tv.oh.moodnite.service.storage.StorageService;

@RequestMapping(value = "/user/*")
@Controller
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private WatchedService watchedService;
	
	@Autowired
	private RateService rateService;
	
	@Autowired
	private StorageService storageService;

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
	
	@RequestMapping(value = "config", method = RequestMethod.GET, headers = "Accept=text/html")
	public String updateAvatar(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";

		model.addAttribute("user", loggedInUser);
		return "/user/config";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST, headers = "Accept=text/html")
	public String updateUserData(@ModelAttribute("user") User user, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		loggedInUser.setName(user.getName());
		loggedInUser.setBio(user.getBio());
		userService.updateUser(loggedInUser);

		return "/user/config";
	}
	
	@RequestMapping(value = "avatar", method = RequestMethod.POST, headers = "Accept=text/html")
	public String updateAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		String fileName = loggedInUser.getName() + "." + file.getOriginalFilename().split("\\.")[1];
		storageService.store(file, fileName);
		loggedInUser.setPhoto(fileName);
		userService.updateUser(loggedInUser);

		return "/user/config";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET, headers = "Accept=text/html")
	public String logoutUser(HttpSession session) {
		session.removeAttribute("loggedInUser");

		return "redirect:/";
	}
	
	@RequestMapping(value = "reviews", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showUserReviews(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		model.addAttribute("reviews", loggedInUser.getRatedList());
		
		return "/user/reviews";
	}
	
	@RequestMapping(value = "watched", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showWatchedList(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		model.addAttribute("watched_list", loggedInUser.getWatchedList());
		
		return "/user/watched";
	}
	
	@RequestMapping(value = "watch/{movieId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String watchMovie(@PathVariable String movieId, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		watchedService.watchMovie(loggedInUser, movie, formateador.format(new Date()), "I'm watching.");
		
		return "redirect:/movie/" + movieId;
	}
	
	@RequestMapping(value = "rate/{movieId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String rateMovie(@PathVariable String movieId, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		model.addAttribute("movieId", movieId);
		model.addAttribute("backdrop_path", movieService.findByTmdbId(movieId).getBackground());
		
		return "/user/rate-movie";
	}
	
	@RequestMapping(value = "rate/{movieId}/{stars}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String rateMovie(@PathVariable String movieId, @PathVariable int stars, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Rated rate = rateService.rate(loggedInUser, movieService.addMovie(movieId), stars);
		
		model.addAttribute("rate", rate);
		model.addAttribute("rateId", rate.getId());
		model.addAttribute("backdrop_path", rate.getMovie().getBackground());
		
		System.out.println(rate);
		
		return "/user/review-movie";
	}
	
	@RequestMapping(value = "review/{rateId}", method = RequestMethod.POST, headers = "Accept=text/html")
	public String reviewMovie(@PathVariable String rateId, @ModelAttribute("rate") Rated rate, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Rated rateToUpdate = rateService.findByRateId(Long.valueOf(rateId));		
		rateToUpdate.setReviewXS(rate.getReviewXS());		
		rateService.updateRate(rateToUpdate);
		
		return "redirect:/movie/" + rateToUpdate.getMovie().getTmdbId();
	}
}
