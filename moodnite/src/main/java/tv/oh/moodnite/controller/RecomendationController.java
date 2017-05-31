package tv.oh.moodnite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.oh.moodnite.domain.User;
import tv.oh.moodnite.service.recomendation.RecomendationService;

@RequestMapping(value = "/recomendation/*")
@Controller
public class RecomendationController {
	
	@Autowired
	private RecomendationService recomendationService;
	

	@RequestMapping(value = "recomendation", method = RequestMethod.GET, headers = "Accept=text/html")
	public String showWatchedList(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if(loggedInUser == null)
			return "redirect:/user/login";
		
		model.addAttribute("recomendation_summary", recomendationService.build(loggedInUser));
		return "/recomendation/recomendation";
	}
}
