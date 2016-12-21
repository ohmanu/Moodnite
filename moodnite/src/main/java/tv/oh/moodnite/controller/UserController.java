package tv.oh.moodnite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.repository.UserRepository;
import tv.oh.moodnite.service.TmdbService;

@Controller
public class UserController {
	
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TmdbService tmdbService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showIndex(Model model) {
		List<Map<?, ?>> popularMovies = tmdbService.getPopularMovies();
		model.addAttribute("popularMovies", popularMovies);
		
		List<Map<?, ?>> upcomingMovies = tmdbService.getUpcomingMovies();
		model.addAttribute("upcomingMovies", upcomingMovies);
		
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

		return "redirect:/";
	}
	
	@RequestMapping(value = "/person/{personId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showPersonInfo(Model model, @PathVariable String personId) {
		Map<?, ?> personInfo = tmdbService.getPersonInfo(personId);
		model.addAttribute("personInfo", personInfo);

		return "/person/show";
	}
}
