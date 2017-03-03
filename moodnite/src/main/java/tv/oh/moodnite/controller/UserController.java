package tv.oh.moodnite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.service.UserService;

@RequestMapping(value = "/user/*")
@Controller
public class UserController {
	
	
	@Autowired
	private UserService userService;

	
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
}
