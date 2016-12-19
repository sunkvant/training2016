package com.vamendrik.training.banking.web.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vamendrik.training.banking.datamodel.BankAccount;
import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.datamodel.Country;
import com.vamendrik.training.banking.datamodel.CreditCard;
import com.vamendrik.training.banking.datamodel.User;
import com.vamendrik.training.banking.services.IBankAccountService;
import com.vamendrik.training.banking.services.ICityService;
import com.vamendrik.training.banking.services.ICountryService;
import com.vamendrik.training.banking.services.ICreditCardService;
import com.vamendrik.training.banking.services.IUserService;

@RestController
public class UserController {
	
	@Inject
	IBankAccountService bankAccountService;
	
	@Inject
	IUserService userService;
	
	@Inject
	ICreditCardService creditCardService;
	
	@Inject
	ICityService cityService;
	
	@Inject
	ICountryService countryService;
	
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public ModelAndView user(Authentication auth,Model model) {
		
		
		//User user=userService.getByLogin(auth.getName());
		
		//List<BankAccount> bankAccounts=bankAccountService.getAllByUserId(user.getId());
		
		//model.addAttribute("currentUser",user);
		//model.addAttribute("listBankAccounts", bankAccounts);
		
		return new ModelAndView("user","user",model);
		

	}
	
	
	@RequestMapping(value="/user/bank/{numberAccount}",method=RequestMethod.GET)
	public ModelAndView user(Authentication auth,Model model,@PathVariable Long numberAccount) {
		 
		BankAccount bankAccount=bankAccountService.getByNumberAccount(numberAccount);
		List<CreditCard> creditCards=creditCardService.getAllByNumberBankAccount(numberAccount);
		User user=userService.getByLogin(auth.getName());
		
		model.addAttribute("currentUser",user);
		model.addAttribute("bankAccount", bankAccount);
		model.addAttribute("listCreditCards", creditCards);
		
		return new ModelAndView("bankaccount","bankaccount",model);
		

	}
	
	@RequestMapping(value="/user/profile",method=RequestMethod.GET)
	public ModelAndView profile(Authentication auth,Model model) {
		 
		
		//User user=userService.getByLogin(auth.getName());
		//Long currentCountry=cityService.get(user.getCityId()).getCountryId();
		//List<City> cities=cityService.getAllByCountryId(currentCountry);
		//List<Country> countries=countryService.getAll();
		
		//model.addAttribute("currentUser", user);
		//model.addAttribute("listCities", cities);
		//model.addAttribute("listCountries", countries);
		//model.addAttribute("currentCountry", currentCountry);
		
		return new ModelAndView("profile","profile",model);
	
		
		

	}
	
	
	@RequestMapping(value="/user/profile/update",method=RequestMethod.POST)
	public User profilePut(@RequestBody User user,Authentication auth,Model model) {
		 
		
		
		//User user=userService.getByLogin(auth.getName());
		//Long currentCountry=cityService.get(user.getCityId()).getCountryId();
		//List<City> cities=cityService.getAllByCountryId(currentCountry);
		//List<Country> countries=countryService.getAll();
		
		//model.addAttribute("currentUser", user);
		//model.addAttribute("listCities", cities);
		//model.addAttribute("listCountries", countries);
		//model.addAttribute("currentCountry", currentCountry);
		
		return user;
	

	}
	
	@RequestMapping(value="/user/transfer/{numberAccount}",method=RequestMethod.GET)
	public ModelAndView transfer(@PathVariable Long numberAccount,Authentication auth,Model model) {
		 
		
		//User user=userService.getByLogin(auth.getName());
		//List<CreditCard> creditCards=creditCardService.getAllByNumberBankAccount(numberAccount);
		
		//model.addAttribute("listCards",creditCards);
		
		return new ModelAndView("transfer","transfer",model);
	
		
		

	}
	
	
	@RequestMapping(value="/user/transfer/",method=RequestMethod.POST)
	public @ResponseBody String transfer4(HttpEntity<String> obj,Authentication auth,Model model) {
		 
		
		//User user=userService.getByLogin(auth.getName());
		//List<CreditCard> creditCards=creditCardService.getAllByNumberBankAccount(numberAccount);
		
		//model.addAttribute("listCards",creditCards);
		
		return obj.getBody()+" custom";
	
		
		

	}
	
	@RequestMapping(value="/ajax/city",method=RequestMethod.POST)
	public List<City> ajax(@RequestBody Country country,Authentication auth) {
		
		List<City> cities=cityService.getAllByCountryId(country.getId());
		
		return cities;
		
	}
	

}
