package com.pet.care.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessLogFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);

	public AccessLogFilter() {
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("[ * Access Log * ] Filter Init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// HttpServletRequest의 정보를 추출하여 Client정보를 추출
		HttpServletRequest req = (HttpServletRequest) request;

		// 주소
		String remoteAddr = StringUtils.defaultString(req.getRemoteAddr(), "-");

		// URL
		String uri = StringUtils.defaultString(req.getRequestURI().toString(), "-");

		// Query String
		String queryString = StringUtils.defaultString(req.getQueryString(), "");

		// Header
		String referer = StringUtils.defaultString(req.getHeader("Referer"), "-");
		String agent = StringUtils.defaultString(req.getHeader("User-Agent"), "-");

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(remoteAddr).append(":").append(uri).append("?").append(queryString).append("#")
				.append(referer).append(":").append(agent);

		logger.info("[ * Access Log * ] : {}", stringBuffer.toString());

		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		logger.info("[ * Access Log * ] Filter Destroy");

	}

}
