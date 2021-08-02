package com.pet.care.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// HttpSession session = request.getSession();
		// Object userSession =  session.getAttribute("userInfoDto");
		// if(userSession == null) {
		// 	response.sendRedirect("./loginForm.do");
		// 	return false;
		// }
		// else {
		// 	return true;
		// }
	}
	
	//컨트롤러 실행 후 수행 로직
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.info("[ * Interceptor * ] postHandle");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	// View 랜더링이 끝난 직후 수행
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		logger.info("[ * Interceptor * ] afterCompletion");
		super.afterCompletion(request, response, handler, ex);
	}
	
	//비동기식 호출 수행
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
	
}
