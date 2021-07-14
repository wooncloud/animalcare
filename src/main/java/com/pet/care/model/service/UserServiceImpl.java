package com.pet.care.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pet.care.dto.UserDto;

public class UserServiceImpl implements IUserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// @Autowired
	// Dao
	
	@Override
	public boolean insertUser(UserDto dto) {
		// Encode
		
		return false;
	}
	
}
