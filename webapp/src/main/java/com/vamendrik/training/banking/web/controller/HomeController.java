package com.vamendrik.training.banking.web.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vamendrik.training.banking.datamodel.BankAccount;
import com.vamendrik.training.banking.datamodel.User;
import com.vamendrik.training.banking.services.IBankAccountService;
import com.vamendrik.training.banking.services.IUserService;

@Controller
public class HomeController {
	
	@Inject
	IBankAccountService bankAccountService;
	
	@Inject
	IUserService userService;
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public ModelAndView index(Authentication auth,Model model) {
		
		List<SimpleGrantedAuthority> authList = (List<SimpleGrantedAuthority>) auth.getAuthorities();
		if (authList.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			
			return new ModelAndView("redirect:/admin");
			
		}
		
		
		logger.info("User with [UserName="+auth.getName()+"] success loginned");
		try {
		
		User user=userService.getByLogin(auth.getName());
		
		List<BankAccount> bankAccounts=bankAccountService.getAllByUserId(user.getId());
		
		model.addAttribute("name",auth.getName());
		model.addAttribute("listBankAccounts", bankAccounts);
		
		
	
		
		} catch (Exception e) {
			
			logger.error("User with [UserName="+auth.getName()+"] not found");
			
			
		}
		
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
