package com.pet.care.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pet.care.model.service.IUserService;

public class UserAuthService implements UserDetailsService {

	@Autowired
	private IUserService iUserService;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		return null;
		// return iUserService.findBy(id);
		// 아이디로 유저 찾기 -> dao에 user id로 유저 찾는 기능 있어야 함
	}
	
	

}
