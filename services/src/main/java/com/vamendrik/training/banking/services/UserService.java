package com.vamendrik.training.banking.services;

import java.util.Date;
import java.util.List;

import com.vamendrik.training.banking.datamodel.User;

public interface UserService {
	
	public List<User> getAllClients();
	public Long add(String firstName,String lastName,String middleName,
			String numberOfPassport,Date dateBorn,Long cityId,String login,Long roleId);
	public void delete(User client);
	public User getClient(Long id);
}
