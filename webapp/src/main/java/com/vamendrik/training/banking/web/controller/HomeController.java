package com.vamendrik.training.banking.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index() {
		
		
		return "index";
	
	}
	
	@RequestMapping(value ={"/","/login"}, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value="failed",required=false) String failed,Model model) {
	
		if (failed!=null) {
			model.addAttribute("failed", "Invalid login or password");
		}
		 return new ModelAndView("login","login",model);

	}
	
	

}
