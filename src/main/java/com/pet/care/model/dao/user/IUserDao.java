package com.pet.care.model.dao.user;

import java.util.Map;

import com.pet.care.dto.UserDto;

public interface IUserDao {

	/**
	 * 로그인
	 */
	public UserDto userLogin(String email);

	/**
	 * 이메일 중복검사
	 */
	public int emailDuplCheck(String email);

	/**
	 * 회원 등록
	 */
	public int insertUser(UserDto dto);

	/**
	 * 병원관계자 가입 신청
	 */
	public int insertOper(UserDto dto);

	/**
	 * 가입신청 병원관계자 조회
	 */
	public UserDto grantWaitList();

	/**
	 * 가입신청 처리
	 */
	public int grantOper(String email);

	/**
	 * 병원관계자 정보 조회
	 */
	public UserDto detailOper(String email);

	/**
	 * 병원관계자 정보 수정
	 */
	public int modifyOper(Map<String, Object> map);

	/**
	 * 병원관계자 탈퇴
	 */
	public int dormancyOper(String email);

	/**
	 * 병원관계자 삭제
	 */
	public int deleteOper(String email);

	/**
	 * 회원 정보 조회
	 */
	public UserDto detailUser(String email);

	/**
	 * 회원 정보 수정
	 */
	public int modifyUser(Map<String, Object> map);

	/**
	 * 회원 탈퇴
	 */
	public int dormancyUser(String email);

	/**
	 * 회원 삭제
	 */
	public int deleteUser(String email);
	
	// ------------------------ security ------------------------ 
	
	/**
	 * 아이디로 로그인
	 */
	public UserDto emailSecurity(String email);

	/**
	 * 비밀번호 확인
	 */
	public String pwSecurity(String email);
	
}