package tv.oh.moodnite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.repository.UserRepository;

@Controller
public class UserController {
	
	
	@Autowired
	private UserRepository repo;

	
	@RequestMapping(value = "/sign-in", method = RequestMethod.GET, headers = "Accept=text/html")
	public String createUser(Model model) {
		model.addAttribute("user", new User());

		return "/user/sign-in";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, headers = "Accept=text/html")
	public String saveUser(@ModelAttribute("user") User user) {
		repo.save(user);

		return "redirect:/";
	}
	
	@RequestMapping(value = "/log-in", method = RequestMethod.POST, headers = "Accept=text/html")
	public String loginUser(@RequestParam String login, @RequestParam String password, HttpSession session) {
		User user = repo.findByLogin(login);
		
		if(user == null)
			return "/user/sign-in";
		
		System.out.println(user.getLogin());
		session.setAttribute("loggedInUser", user);

		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET, headers = "Accept=text/html")
	public String logoutUser(HttpSession session) {
		session.removeAttribute("loggedInUser");

		return "redirect:/";
	}
}
