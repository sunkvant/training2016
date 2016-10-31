package com.vamendrik.training.banking.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daodb.AbstractDAO;

@Service
public class UserServiceImpl {
	
	@Inject
	private AbstractDAO df;
	
	public void getAll() {
		
		List obj=df.getAll();
		
		//System.out.println(obj.get(2).get);
		
		
	}

}
