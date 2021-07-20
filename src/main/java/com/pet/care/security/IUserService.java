package com.pet.care.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService extends UserDetailsService {

	@Override
	public abstract UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
