package com.vamendrik.training.banking.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vamendrik.training.banking.daoapi.IRoleDao;
import com.vamendrik.training.banking.daoapi.IUserDao;
import com.vamendrik.training.banking.datamodel.Role;
import com.vamendrik.training.banking.datamodel.User;


@Service
public class UserSecurityService implements UserDetailsService  {

	@Inject
	IUserDao userDao;
	
	@Inject
	IRoleDao roleDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userDao.getByLogin(username);
		if (user==null) {
			throw new UsernameNotFoundException("User details not found with this username: " + username);
		}
		
		List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
		List<Role> roles=roleDao.getAllByUserId(user.getId());
		for(int i=0; i<roles.size(); i++) {
			authList.add(new SimpleGrantedAuthority(roles.get(i).getRoleName()));
		}
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authList);
		 
        return userDetails;

	}

}
