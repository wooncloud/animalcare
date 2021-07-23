package com.pet.care.model.dao.user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.MemberDto;

@Repository
public class SecurityDaoImpl implements ISecurityDao {

	private final String NS = "com.pet.care.model.dao.user.IUserDao.";
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public MemberDto emailSecurity(String email) {
		return sqlSession.selectOne(NS + "emailSecurity", email);
	}

	@Override
	public String pwSecurity(String email) {
		return sqlSession.selectOne(NS + "pwSecurity", email);
	}

}
