package com.vamendrik.training.banking.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;

import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.datamodel.Country;
import com.vamendrik.training.banking.services.ICityService;

@RestController

public class CityController {
	
	@Inject
	private ICityService service;
	
	@RequestMapping(value="city/{id}",method=RequestMethod.GET)
	
    public  ResponseEntity<City> getAll(@PathVariable Long id) {
        City city = service.get(id);
        
        if (city==null) {
        	
        	
        	return new ResponseEntity<City>(city,HttpStatus.BAD_REQUEST);
        	
        	
        	
        }
        
        //System.out.println(headers.getLocation().toString());
        return new ResponseEntity<City>(city,HttpStatus.OK);
        
}
	
	
	@RequestMapping(value="city/my/",method=RequestMethod.POST)
	
    public ResponseEntity<City> getAlle(HttpEntity<City> requestEntity) {
		

		City city=requestEntity.getBody();

        System.out.println("request body : " + requestEntity.getBody());
        HttpHeaders headers = requestEntity.getHeaders();
        System.out.println("request headers : " + headers);
       MultiValueMap<String,String> map=new HttpHeaders();
        map.add("Access-Control-Allow-Origin", "*");
        map.add("Access-Control-Allow-Credentials", "true");
       map.add("Access-Control-Allow-Methods", "*");
        ResponseEntity<City> responseEntity = new ResponseEntity<City>(city,
                                                                     HttpStatus.OK);
        return responseEntity;
}
	
	@RequestMapping(value="/city/",method=RequestMethod.POST)
    public Country createAll(@RequestBody Country country,@RequestHeader String host) {
		//System.out.println(city.getCityName());

        
        System.out.println(host);
        return country;
        
	}
	
	@RequestMapping(value="/wel",method=RequestMethod.GET)
    public ModelAndView createAll1() {
        
        return new ModelAndView("wel","command",new City());
        
	}
	
	@RequestMapping(value="/main",method=RequestMethod.POST)
    public ModelAndView createAll2(@ModelAttribute City city,@RequestBody String req,Model model) {
		
		System.out.println(req);
		
		//model.addAttribute("cityName",city.getCityName());
        
		return new ModelAndView("main","cit",city);
        
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		  ModelAndView model = new ModelAndView();

		  //check if user is login


		  model.setViewName("403");
		  return model;

		}
	


}
