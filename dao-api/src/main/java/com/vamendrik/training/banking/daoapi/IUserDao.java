package com.vamendrik.training.banking.daoapi;

import java.util.List;

import com.vamendrik.training.banking.datamodel.User;

public interface IUserDao extends IAbstractDao<User,Long>  {
	
	public List<User> getAllByRoleId(Long roleId);
	public List<User> getAllByCityId(Long cityId);
	public List<User> getAllByCountryId(Long countryId);
	public User getByNumberPassport(String numberOfPassport);
	public User getByLogin(String login);

}
