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
		User newUser = userService.signInUser(user);
		
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

		return "redirect:/";
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
		userService.updateUser(loggedInUser);

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
		userService.updateUser(loggedInUser);
		
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
		
		model.addAttribute("watched_list", loggedInUser.getWatchedList());
		
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
		model.addAttribute("movieId", movie.getTmdbId());
		model.addAttribute("backdrop_path", movie.getBackground());
		
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
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		loggedInUser.addWatch(new Watched(loggedInUser, movie, formateador.format(new Date()), watch.getComment()));
		userService.updateUser(loggedInUser);
		
		return "redirect:/user/watched";
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
		
		loggedInUser.removeWatch(watch);
		userService.updateUser(loggedInUser);
		
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
		
		model.addAttribute("reviews", loggedInUser.getRatedList());
		
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
		loggedInUser.updateRate(rateToUpdate);
		userService.updateUser(loggedInUser);
		
		return "redirect:/movie/" + movie.getTmdbId();
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
		
		loggedInUser.removeRate(rate);
		userService.updateUser(loggedInUser);
		
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
		userService.updateUser(loggedInUser);
		
		return "redirect:/user/watched";
	}
	
	@RequestMapping(value = "unfollow/{userId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String unfollowUser(@PathVariable String userId, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		User user = userService.findByUserId(Long.valueOf(userId));
		user.removeFriend(loggedInUser);
		userService.updateUser(user);
		
		loggedInUser.removeFriend(user);
		userService.updateUser(loggedInUser);
		
		return "redirect:/user/friends";
	}
}
