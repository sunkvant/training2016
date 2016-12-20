package com.vamendrik.training.banking.web.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vamendrik.training.banking.datamodel.BankAccount;
import com.vamendrik.training.banking.datamodel.User;
import com.vamendrik.training.banking.services.IUserService;

@RestController
public class AdminController {
	
	@Inject
	IUserService userService;
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public ModelAndView admin(Authentication auth,Model model) {
	
		
		User user=userService.getByLogin(auth.getName());
		List<User> listUsers=userService.getAll();
		
		
		model.addAttribute("currentUser",user);
		model.addAttribute("listUsers",listUsers);
		
		return new ModelAndView("admin","admin",model);
		

	}

}
