package com.vamendrik.training.banking.services;

import java.util.Date;
import java.util.List;

import com.vamendrik.training.banking.datamodel.Autorization;
import com.vamendrik.training.banking.datamodel.Client;

public interface UserService {
	
	public List<Client> getAllClients();
	public List<Autorization> getAllClientsAutorization();
	public Long add(String firstName,String lastName,String middleName,
			String numberOfPassport,Date dateBorn,Long cityId,String login,Long roleId);
	public void delete(Client client);
	public Client getClient(Long id);
	public Autorization getAutorization(Long id);
}
