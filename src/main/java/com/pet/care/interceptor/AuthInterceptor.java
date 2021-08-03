package com.pet.care.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pet.care.dto.MemberDto;

import edu.emory.mathcs.backport.java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		logger.info("[ * AuthInterceptor * ] preHandle");
		
		HttpSession session = req.getSession();
		MemberDto member = (MemberDto)session.getAttribute("member");
		String[] sURI = req.getRequestURI().split("/");
		
		System.out.println(Arrays.toString(sURI));
		
		// 로그인하지 않을 경우
		if(member == null) {
			resp.sendRedirect("/PetCare/error/error403.do");
			return false;
		}
		
		// USER
		// favo, healthNote, pet
		
//		if(!member.getUsertype().equalsIgnoreCase("ROLE_USER")) {
//			resp.sendRedirect("./error403.do");
//			return false;
//		}
//		else {
//			return true;
//		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
}
