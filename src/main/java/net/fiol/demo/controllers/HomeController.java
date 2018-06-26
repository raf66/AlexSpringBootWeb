package net.fiol.demo.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.fiol.demo.alexa.utils.AlexaUtils;
import net.fiol.demo.models.NumberTrivia;
import net.fiol.demo.services.NumbersAPIService;

@Controller
public class HomeController {

	@Autowired
	private NumbersAPIService numbersService;
	
	
	@RequestMapping(value={"/", "/home"})	
	public String home(@RequestParam(name="year", required=false, defaultValue="0") int year, Model model) {
		
		if ( year == 0 ) 
			year = AlexaUtils.randomInt(1900, LocalDate.now().getYear() -1 );
		
		NumberTrivia trivia = numbersService.getYearTrivia(year);
		model.addAttribute("trivia", trivia);
		
		return "home";
	}
}
