package tv.oh.moodnite.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import tv.oh.moodnite.domain.DateComparator;
import tv.oh.moodnite.domain.Movie;
import tv.oh.moodnite.domain.Publication;
import tv.oh.moodnite.domain.Rated;
import tv.oh.moodnite.domain.Tag;
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

	/**
	 * Carga el formulario de registro de usuario.
	 * 
	 * @param model
	 * @return 
	 */
	@RequestMapping(value = "sign-in", method = RequestMethod.GET, headers = "Accept=text/html")
	public String createUser(Model model) {
		model.addAttribute("user", new User());

		return "/user/sign-in";
	}
	
	/**
	 * Trata la petición del formulario de registro.
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "sign-in", method = RequestMethod.POST, headers = "Accept=text/html")
	public String saveUser(@ModelAttribute("user") User user) {
		User newUser = userService.saveUser(user);
		
		if(newUser == null)
			return "/user/sign-in";
		
		return "redirect:/";
	}
	
	/**
	 * Carga el formulario de login de usuario.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET, headers = "Accept=text/html")
	public String loginUser(Model model) {
		model.addAttribute("user", new User());

		return "/user/login";
	}
	
	/**
	 * Trata la petición de login de usuario.
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST, headers = "Accept=text/html")
	public String loginUser(@ModelAttribute("user") User user, HttpSession session) {
		User loogedInuser = userService.loginUser(user.getName(), user.getPassword());
		
		if(loogedInuser == null)
			return "/user/login";
		
		session.setAttribute("loggedInUser", loogedInuser);

		return "redirect:/user/wall";
	}
	
	/**
	 * Carga el formulario de configuración de usuario.
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "config", method = RequestMethod.GET, headers = "Accept=text/html")
	public String updateAvatar(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";

		model.addAttribute("user", loggedInUser);
		
		return "/user/config";
	}
	
	/**
	 * Trata la solicitud de modificación de datos de usuario.
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST, headers = "Accept=text/html")
	public String updateUserData(@ModelAttribute("user") User user, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		loggedInUser.setName(user.getName());
		loggedInUser.setBio(user.getBio());
		userService.saveUser(loggedInUser);

		return "/user/config";
	}
	
	/**
	 * Trata la solicitud de agragar/modificar el avatar de usuario.
	 * 
	 * @param file Potencial nuevo avatar de usuario.
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "avatar", method = RequestMethod.POST, headers = "Accept=text/html")
	public String updateAvatar(@RequestParam("file") MultipartFile file, @ModelAttribute("user") User user, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		String fileName = loggedInUser.getName() + "." + file.getOriginalFilename().split("\\.")[1];
		storageService.store(file, fileName);
		loggedInUser.setPhoto(fileName);
		userService.saveUser(loggedInUser);
		
		model.addAttribute("user", loggedInUser);

		return "/user/config";
	}
	
	/**
	 * Logout del usuario en sesión.
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET, headers = "Accept=text/html")
	public String logoutUser(HttpSession session) {
		session.removeAttribute("loggedInUser");

		return "redirect:/";
	}
	
	/**
	 * Carga el listado de películas vistas por el usuario en sesión.
	 *  
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "watched", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showWatchedList(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		List<Publication> sortedList = new ArrayList<Publication>(loggedInUser.getWatchedList());
		Collections.sort(sortedList, new DateComparator());
		
		model.addAttribute("watched_list", sortedList);
		
		return "/user/watched";
	}
	
	/**
	 * Carga el formulario que permite al usuario indicar que ha visto o está viendo una película.
	 * 
	 * @param movieId Id de la película que el usuario solicita marcar como vista.
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "watch/{movieId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String watchMovie(@PathVariable String movieId, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);
		model.addAttribute("watch", new Watched());
		model.addAttribute("movie", movie);
		
		return "/user/watch-movie";
	}
	
	/**
	 * Agrega el visionado de una película a la lista de películas vistas del usuario en sesión.
	 * 
	 * @param movieId ID de la película.
	 * @param watch
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "watch/{movieId}", method = RequestMethod.POST, headers = "Accept=text/html")
	public String watchMovie(@PathVariable String movieId, @ModelAttribute("watch") Watched watch, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		userService.watchMovie(loggedInUser, movie, new Date(), formatter.format(new Date()), watch.getComment());
		
		return "redirect:/movie/" + movie.getTmdbId();
	}
	
	/**
	 * Elimina el visionado de una película de la lista de películas vistas del usuario en sesión.
	 * 
	 * @param watchId ID del visionado que el usuario quiere eliminar.
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "delete/watch/{watchId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String deleteMovieWatch(@PathVariable String watchId, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Watched watch = watchService.findById(Long.valueOf(watchId));
		
		userService.removeWatch(loggedInUser, watch);
		
		return "redirect:/user/watched";
	}

	/**
	 * Carga el listado de reviews del usuario en sesión.
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "reviews", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showUserReviews(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		List<Publication> sortedList = new ArrayList<Publication>(loggedInUser.getRatedList());
		Collections.sort(sortedList, new DateComparator());
		
		model.addAttribute("reviews", sortedList);
		
		return "/user/reviews";
	}
	
	/**
	 * Carga el formulario de valoración en estrellas para una película determinada por el id de la película.
	 * 
	 * @param movieId
	 * @param model
	 * @param session
	 * @return
	 */
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
	
	/**
	 * Almacena la valoración en estrellas en la lista de reviews del usuario en sesión y carga el formulario de review.
	 * 
	 * @param movieId
	 * @param stars
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "rate/{movieId}/{stars}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String rateMovie(@PathVariable String movieId, @PathVariable int stars, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);
		Rated rate = userService.findUserMovieRate(loggedInUser, movie);
		
		if(rate == null) {
			rate = new Rated(loggedInUser, movie, new Date(), stars);
			userService.rateMovie(loggedInUser, rate);
		}
		else {
			rate.setRate(stars);
			rate.setDate(new Date());
			userService.saveUser(loggedInUser);
		}
		
		model.addAttribute("rate", rate);
		model.addAttribute("backdrop_path", movie.getBackground());
		model.addAttribute("movieId", movie.getTmdbId());
		
		return "/user/review-movie";
	}
	
	/**
	 * Agrega la review a la valoración del usuario en sesión a la película determinada.
	 * 
	 * @param movieId
	 * @param rate
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "review/{movieId}", method = RequestMethod.POST, headers = "Accept=text/html")
	public String reviewMovie(@PathVariable String movieId, @ModelAttribute("rate") Rated rate, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);
		Rated rateToUpdate = userService.findUserMovieRate(loggedInUser, movie);

		rateToUpdate.setReviewXS(rate.getReviewXS());
		userService.saveUser(loggedInUser);
		
		return "redirect:/user/reviews";
	}
	
	/**
	 * Elimina la valoración determinada por el id de la película de la lista de valoraciones del usuario en sesión.
	 * 
	 * @param movieId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "delete/rate/{movieId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String deleteMovieRate(@PathVariable String movieId, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);
		Rated rate = userService.findUserMovieRate(loggedInUser, movie);
		
		userService.removeRate(loggedInUser, rate);
		
		return "redirect:/user/reviews";
	}
	
	@RequestMapping(value = "friends", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showUserFriends(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		model.addAttribute("friends", loggedInUser.getFollows());
		
		return "/user/friends";
	}
	
	@RequestMapping(value = "follow/{userId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String followUser(@PathVariable String userId, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		User user = userService.findByUserId(Long.valueOf(userId));
		loggedInUser.addFriend(user);
		loggedInUser.removeNewFollower(user);
		user.addNewFollower(loggedInUser);
		userService.saveUser(loggedInUser);
		
		return "redirect:/user/friends";
	}
	
	@RequestMapping(value = "unfollow/{userId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String unfollowUser(@PathVariable String userId, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		User user = userService.findByUserId(Long.valueOf(userId));
		
		loggedInUser.removeFriend(user);
		userService.saveUser(loggedInUser);
		
		return "redirect:/user/friends";
	}
	
	@RequestMapping(value = "notifications", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showUserNotifications(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		model.addAttribute("notifications", loggedInUser.getNewFollowers());
		
		return "/user/notifications";
	}
	
	@RequestMapping(value = "/notification/ignore/{userId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String ignoreNotification(@PathVariable String userId, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		User user = userService.findByUserId(Long.valueOf(userId));
		
		loggedInUser.removeNewFollower(user);
		userService.saveUser(loggedInUser);
		
		return "redirect:/user/friends";
	}
	
	@RequestMapping(value = "wall", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showWall(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		List<Publication> sortedList = new ArrayList<Publication>();
		sortedList.addAll(loggedInUser.getWatchedList());
		sortedList.addAll(loggedInUser.getRatedList());
		for(User follower : loggedInUser.getFollows())
		{
			sortedList.addAll(follower.getRatedList());
			sortedList.addAll(follower.getWatchedList());
		}
		
		Collections.sort(sortedList, new DateComparator());
		model.addAttribute("publications", sortedList);
		
		return "/user/wall";
	}
	
	@RequestMapping(value = "add-to-list/{movieId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String listMovie(@PathVariable String movieId, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);
		
		Set<String> listsNames = new HashSet<>();
		for(Tag tag : loggedInUser.getTags())
			listsNames.add(tag.getName());
		
		model.addAttribute("movie", movie);
		model.addAttribute("lists_names", listsNames);
			
		
		return "/user/add-to-list";
	}
	
	@RequestMapping(value = "add-to-list/{movieId}", method = RequestMethod.POST, headers = "Accept=text/html")
	public String listMovie(@PathVariable String movieId, @RequestParam("listName") String listName, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Movie movie = movieService.addMovie(movieId);

		userService.listMovie(loggedInUser, movie, listName);
		
		return "redirect:/user/wall";
	}
	
	@RequestMapping(value = "lists", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showLists(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Set<String> listsNames = new HashSet<>();
		for(Tag tag : loggedInUser.getTags())
			listsNames.add(tag.getName());
		
		model.addAttribute("lists_names", listsNames);
		
		return "/user/lists";
	}
	
	@RequestMapping(value = "list/{listName}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showList(@PathVariable String listName, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		Set<Movie> movies = new HashSet<>();
		for(Tag tag : loggedInUser.getTags())
			if(tag.getName().compareTo(listName) == 0)
				movies.add(tag.getMovie());
		
		model.addAttribute("list_name", listName);
		model.addAttribute("movies", movies);
		
		return "/user/listed-movies";
	}
}
