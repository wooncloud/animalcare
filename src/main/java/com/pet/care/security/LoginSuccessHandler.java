package com.pet.care.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.pet.care.model.service.IUserService;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private IUserService iUserService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		// 로그인 성공했을 때, 실행되는 메서드.
		
		// iUserService udpate login date
		// iUserService.updateLoginDateBy(((User) auth.getPrincipal()).getId());
		// 최근 로그인 일자를 수정할 수 있다.
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
