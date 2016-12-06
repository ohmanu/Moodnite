package tv.oh.moodnite.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.repository.UserRepository;

@Controller
public class UserController {
	protected final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private UserRepository repo;
	
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
		
		try {
			Map value = mapper.readValue(new URL("https://api.themoviedb.org/3/movie/550?api_key=7e5f9a299f1ccb9c13ce6238850bdf7d"), Map.class);
			model.addAttribute("movie", value);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "/show";
	}
}
