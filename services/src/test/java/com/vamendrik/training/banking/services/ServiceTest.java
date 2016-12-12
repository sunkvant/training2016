package com.vamendrik.training.banking.services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.datamodel.Country;
import com.vamendrik.training.banking.datamodel.Role;
import com.vamendrik.training.banking.datamodel.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class ServiceTest {
	
	@Inject
	private ICityService cityService;
	
	@Inject
	private ICountryService countryService;
	
	@Inject
	private IRoleService roleService;
	
	@Inject
	private IUserService userService;
	
	private Long countryId;
	private Long cityId;
	private Long roleId;
	private Long clientId;

	@Before
	public void setData() {

		
		this.countryId=countryService.add("Test");
		
		this.cityId=cityService.add("Test", countryId);
		
		this.roleId=roleService.add("Test");
		
		//this.clientId=userService.add("Test", "Test", "Test", "Test", new Date(), this.cityId, "Test", this.roleId);
		
	}
	
	@Test
	public void getByIdTest() {
		
		Assert.assertEquals(countryId, countryService.get(countryId).getId());
		
		Assert.assertEquals(cityId, cityService.get(cityId).getId());
		
		Assert.assertEquals(roleId, roleService.get(roleId).getId());
		
		//Assert.assertEquals(clientId, userService.getClient(clientId).getId());
		
		
	}
	
	
	@Test
	public void getAllTest() {
		
		List<City> cities=cityService.getAll();
		
		List<Country> countries=countryService.getAll();
		
		List<Role> roles=roleService.getAll();
		
		//List<User> clients=userService.getAllClients();
		
		
		Assert.assertNotNull(cities);
		
		Assert.assertNotNull(countries);
		
		Assert.assertNotNull(roles);
		
		//Assert.assertNotNull(clients);
		
	}
	
	@After
	public void deleteData() {
		
		City city=new City();
		Country country=new Country();
		User client=new User();
		Role role=new Role();
		
		city.setId(cityId);
		country.setId(countryId);
		role.setId(roleId);
		client.setId(clientId);
		
		cityService.delete(city);
		countryService.delete(country);
		roleService.delete(role);
		userService.delete(client);
		
		
		
	}

}
