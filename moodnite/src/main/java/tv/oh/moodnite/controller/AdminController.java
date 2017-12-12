package tv.oh.moodnite.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.oh.moodnite.domain.Publication;
import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.domain.comparator.DateComparator;
import tv.oh.moodnite.service.moodnite.UserService;

@RequestMapping(value = "/admin/*")
@Controller
public class AdminController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "user-list", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showUsers(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");

		if (loggedInUser == null)
			return "redirect:/user/login";

		model.addAttribute("users", userService.findAll());

		return "/admin/user-list";
	}
	
	@RequestMapping(value = "user/delete/{userId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String deleteUser(@PathVariable String userId, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");

		if (loggedInUser == null)
			return "redirect:/user/login";

		userService.removeUserById(Long.valueOf(userId));

		return "redirect:/admin/user-list";
	}
	
	@RequestMapping(value = "{userId}/reviews", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showUserReviews(@PathVariable String userId, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");

		if (loggedInUser == null)
			return "redirect:/user/login";
		
		User user = userService.findByUserId(Long.valueOf(userId));

		List<Publication> sortedList = new ArrayList<Publication>(user.getRatedList());
		Collections.sort(sortedList, new DateComparator());

		model.addAttribute("user", user);
		model.addAttribute("reviews", sortedList);

		return "/admin/user-reviews";
	}
}
