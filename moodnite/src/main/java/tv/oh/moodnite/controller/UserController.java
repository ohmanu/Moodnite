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
import tv.oh.moodnite.domain.Watched;
import tv.oh.moodnite.service.MovieService;
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
	private WatchedService watchService;
	
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
	public String updateAvatar(@RequestParam("file") MultipartFile file, @ModelAttribute("user") User user, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		String fileName = loggedInUser.getName() + "." + file.getOriginalFilename().split("\\.")[1];
		storageService.store(file, fileName);
		loggedInUser.setPhoto(fileName);
		userService.updateUser(loggedInUser);
		
		model.addAttribute("user", loggedInUser);

		return "/user/config";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET, headers = "Accept=text/html")
	public String logoutUser(HttpSession session) {
		session.removeAttribute("loggedInUser");

		return "redirect:/";
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
	public String watchMovie(@PathVariable String movieId, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);
		model.addAttribute("watch", new Watched());
		model.addAttribute("movieId", movie.getTmdbId());
		model.addAttribute("backdrop_path", movie.getBackground());
		
		return "/user/watch-movie";
	}
	
	@RequestMapping(value = "watch/{movieId}", method = RequestMethod.POST, headers = "Accept=text/html")
	public String watchMovie(@PathVariable String movieId, @ModelAttribute("watch") Watched watch, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		loggedInUser.addWatch(new Watched(loggedInUser, movie, formateador.format(new Date()), watch.getComment()));
		userService.updateUser(loggedInUser);
		
		return "redirect:/user/watched";
	}
	
	@RequestMapping(value = "delete/watch/{watchId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String deleteMovieWatch(@PathVariable String watchId, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Watched watch = watchService.findById(Long.valueOf(watchId));
		
		loggedInUser.removeWatch(watch);
		userService.updateUser(loggedInUser);
		
		return "redirect:/user/watched";
	}

	@RequestMapping(value = "reviews", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showUserReviews(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		System.out.println("1. LOGGEDIN USER RATES (REVIEW): " + loggedInUser.getRatedList().size());
		model.addAttribute("reviews", loggedInUser.getRatedList());
		
		return "/user/reviews";
	}
	
	@RequestMapping(value = "rate/{movieId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String rateMovie(@PathVariable String movieId, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);
		model.addAttribute("movieId", movie.getTmdbId());
		model.addAttribute("backdrop_path", movie.getBackground());
		
		return "/user/rate-movie";
	}
	
	@RequestMapping(value = "rate/{movieId}/{stars}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String rateMovie(@PathVariable String movieId, @PathVariable int stars, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);
		Rated rate = userService.findUserMovieRate(loggedInUser, movie);
		
		if(rate == null) {
			rate = new Rated(loggedInUser, movie, stars);
			loggedInUser.addRate(rate);
		}
		else {
			rate.setRate(stars);
			loggedInUser.updateRate(rate);
		}
		
		userService.updateUser(loggedInUser);
		
		model.addAttribute("rate", rate);
		model.addAttribute("backdrop_path", movie.getBackground());
		model.addAttribute("movieId", movie.getTmdbId());
		
		return "/user/review-movie";
	}
	
	@RequestMapping(value = "review/{movieId}", method = RequestMethod.POST, headers = "Accept=text/html")
	public String reviewMovie(@PathVariable String movieId, @ModelAttribute("rate") Rated rate, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);
		Rated rateToUpdate = userService.findUserMovieRate(loggedInUser, movie);

		rateToUpdate.setReviewXS(rate.getReviewXS());
		loggedInUser.updateRate(rateToUpdate);
		userService.updateUser(loggedInUser);
		
		return "redirect:/movie/" + movie.getTmdbId();
	}
	
	@RequestMapping(value = "delete/rate/{movieId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String deleteMovieRate(@PathVariable String movieId, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);
		Rated rate = userService.findUserMovieRate(loggedInUser, movie);
		
		loggedInUser.removeRate(rate);
		userService.updateUser(loggedInUser);
		
		return "redirect:/user/reviews";
	}
}
