package com.vamendrik.training.banking.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public ModelAndView index(Authentication auth,Model model) {
		
		List<SimpleGrantedAuthority> authList = (List<SimpleGrantedAuthority>) auth.getAuthorities();
		if (authList.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			
			return new ModelAndView("redirect:/admin");
			
		}
		
		model.addAttribute("name",auth.getName());
		
		return new ModelAndView("user","user",model);
	
	}
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String admin(Authentication auth) {
		
		
		return "admin";
	
	}
	
	@RequestMapping(value ={"/","/login"}, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value="failed",required=false) String failed,Model model) {
	
		if (failed!=null) {
			model.addAttribute("failed", "Invalid login or password");
		}
		 return new ModelAndView("login","login",model);

	}
	
	

}
