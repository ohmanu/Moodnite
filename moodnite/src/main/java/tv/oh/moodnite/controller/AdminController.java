package tv.oh.moodnite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.oh.moodnite.domain.User;
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
}
