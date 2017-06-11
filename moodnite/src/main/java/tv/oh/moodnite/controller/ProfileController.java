package tv.oh.moodnite.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.oh.moodnite.domain.Publication;
import tv.oh.moodnite.domain.Tag;
import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.domain.comparator.DateComparator;
import tv.oh.moodnite.service.moodnite.UserService;

@RequestMapping(value = "/profile/*")
@Controller
public class ProfileController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Carga el listado de películas vistas de un usuario determinado.
	 *  
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "{userId}/watched", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showWatchedList(@PathVariable String userId, Model model, HttpSession session) {
		User user = userService.findByUserId(Long.valueOf(userId));
		
		List<Publication> sortedList = new ArrayList<Publication>(user.getWatchedList());
		Collections.sort(sortedList, new DateComparator());
		
		model.addAttribute("watched_list", sortedList);
		model.addAttribute("profile", user);
		
		return "/profile/watched";
	}

	/**
	 * Carga el listado de reviews de un usuario determinado.
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "{userId}/reviews", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showUserReviews(@PathVariable String userId, Model model, HttpSession session) {
		User user = userService.findByUserId(Long.valueOf(userId));
		
		List<Publication> sortedList = new ArrayList<Publication>(user.getRatedList());
		Collections.sort(sortedList, new DateComparator());
		
		model.addAttribute("reviews", sortedList);
		model.addAttribute("profile", user);
		
		return "/profile/reviews";
	}
	
	@RequestMapping(value = "{userId}/friends", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showUserFriends(@PathVariable String userId, Model model, HttpSession session) {
		User user = userService.findByUserId(Long.valueOf(userId));
		
		model.addAttribute("friends", user.getFollows());
		model.addAttribute("profile", user);
		
		return "/profile/friends";
	}
	
	@RequestMapping(value = "{userId}/lists", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showLists(@PathVariable String userId, Model model, HttpSession session) {
		User user = userService.findByUserId(Long.valueOf(userId));
		
		Set<String> listsNames = new HashSet<>();
		for(Tag tag : user.getTags())
			listsNames.add(tag.getName());
		
		model.addAttribute("lists_names", listsNames);
		model.addAttribute("profile", user);
		
		return "/profile/lists";
	}
	
	@RequestMapping(value = "{userId}/list/{listName}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showList(@PathVariable String userId, @PathVariable String listName, Model model, HttpSession session) {
		User user = userService.findByUserId(Long.valueOf(userId));
		
		Set<Tag> tagMovies = new HashSet<>();
		for(Tag tag : user.getTags())
			if(tag.getName().compareTo(listName) == 0)
				tagMovies.add(tag);
		
		model.addAttribute("list_name", listName);
		model.addAttribute("tag_movies", tagMovies);
		model.addAttribute("profile", user);
		
		return "/profile/listed-movies";
	}
}
