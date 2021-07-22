package com.pet.care.model.dao.user;

import com.pet.care.dto.MemberDto;

public interface ISecurityDao {

	/**
	 * 아이디로 로그인
	 */
	public MemberDto emailSecurity(String email);

	/**
	 * 비밀번호 확인
	 */
	public String pwSecurity(String email);
	
}
