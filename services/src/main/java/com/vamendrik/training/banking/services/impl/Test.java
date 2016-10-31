package com.vamendrik.training.banking.services.impl;

import javax.inject.Inject;

public class Test {
	
	
	UserServiceImpl ser=new UserServiceImpl();

	public static void main(String[] args) {
		
		tes t=new tes();
		
		t.ex();
		

	}

}

class tes {
	
	@Inject
	UserServiceImpl ser;
	
	public void ex() {
		
		ser.getAll();
		
		
	}
	
	
	
}
