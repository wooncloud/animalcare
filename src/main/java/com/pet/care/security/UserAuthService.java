package com.pet.care.security;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pet.care.model.dao.user.IUserDao;

public class UserAuthService implements IUserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private MessageSourceAccessor msg = SpringSecurityMessageSource.getAccessor();

	@Autowired
	private IUserDao dao;

	@Autowired
	private SqlSessionTemplate sqlSession;

	public UserAuthService() {
	}

	public UserAuthService(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

		return null;
		// return iUserService.findBy(id);
		// 아이디로 유저 찾기 -> dao에 user id로 유저 찾는 기능 있어야 함
	}

}
