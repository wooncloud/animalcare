package com.pet.care.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pet.care.model.dao.user.ISecurityDao;

public class UserAuthService implements UserDetailsService {

	@Autowired
	private ISecurityDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return dao.emailSecurity(username);
	}
}
