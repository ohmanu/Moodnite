package tv.oh.moodnite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.oh.moodnite.service.tmdb.TmdbPersonService;

@RequestMapping(value = "/person/*")
@Controller
public class PersonController {
	@Autowired
	TmdbPersonService tmdbPersonService;
	
	@RequestMapping(value = "{personId}", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showPersonInfo(Model model, @PathVariable String personId) {
		Map<?, ?> personMovieService = tmdbPersonService.getPersonMovieCredits(personId);
		
		model.addAttribute("personInfo", tmdbPersonService.getPersonInfo(personId));
		model.addAttribute("personCastCredits", personMovieService.get("cast"));
		model.addAttribute("personCrewCredits", personMovieService.get("crew"));

		return "/person/show";
	}
}
