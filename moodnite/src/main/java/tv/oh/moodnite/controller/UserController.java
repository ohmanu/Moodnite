package tv.oh.moodnite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.repository.UserRepository;
import tv.oh.moodnite.service.TheMovieDataBaseService;

@Controller
public class UserController {
	
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TheMovieDataBaseService tmdbService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET, headers = "Accept=text/html")
	public String about() {

		return "/index";
	}
	
	@RequestMapping(value = "/sign-in", method = RequestMethod.GET, headers = "Accept=text/html")
	public String createPerson(Model model) {
		model.addAttribute("user", new User());

		return "/user/sign-in";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, headers = "Accept=text/html")
	public String savePerson(@ModelAttribute("user") User user) {
		repo.save(user);

		return "redirect:index";
	}
	
	@RequestMapping(value = "/death-proof", method = RequestMethod.GET, headers = "Accept=text/html")
	public String deathProof(Model model) {
		Map<?, ?> movieInfo = tmdbService.getMovieInfo("1991");
		model.addAttribute("movieInfo", movieInfo);
			
		List<Map<?, ?>> castsList = tmdbService.getMovieCasts("1991");
		model.addAttribute("casts", castsList);

		return "/show";
	}
}
