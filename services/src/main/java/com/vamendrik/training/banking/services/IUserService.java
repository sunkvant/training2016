package com.vamendrik.training.banking.services;

import java.util.Date;
import java.util.List;

import com.vamendrik.training.banking.datamodel.City;
import com.vamendrik.training.banking.datamodel.Role;
import com.vamendrik.training.banking.datamodel.User;

public interface IUserService extends IAbstractService<User,Long> {
	
	public List<User> getAllByRoleId(Long roleId);
	public List<User> getAllByCityId(Long cityId);
	public List<User> getAllByCountryId(Long countryId);
	public User getByNumberPassport(String numberOfPassport);
	public User getByLogin(String login);
	public Long createUser(String firstName,String lastName,String middleName,
			String numberOfPassport,Date dateBorn,Long cityId,String login,String password,List<Role> roles);
	
}
