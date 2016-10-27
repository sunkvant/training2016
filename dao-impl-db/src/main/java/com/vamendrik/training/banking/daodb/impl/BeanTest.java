package com.vamendrik.training.banking.daodb.impl;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BeanTest {
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
    public int get() {
        return jdbcTemplate.queryForInt("INSERT INTO public.bank_account(number_account, sum, status) VALUES ('ert44', 4444, true)");
}
	
	
	
	

}
