package com.pet.care.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pet.care.model.service.user.IUserService;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private IUserService userService;

	private MessageSourceAccessor msg = SpringSecurityMessageSource.getAccessor();

	public UserAuthService() {
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userService.emailSecurity(username);
	}
}
