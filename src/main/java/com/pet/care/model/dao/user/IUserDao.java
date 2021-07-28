package com.pet.care.model.dao.user;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.MemberDto;
import com.pet.care.dto.OperatorDto;
import com.pet.care.dto.UserDto;

public interface IUserDao {

	/**
	 * 로그인
	 */
	public MemberDto userLogin(Map<String, Object> map);

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
	public int insertOper(OperatorDto dto);

	/**
	 * 가입신청 병원관계자 조회
	 */
	public List<OperatorDto> grantWaitList();

	/**
	 * 가입신청 처리
	 */
	public int grantOper(Map<String, Object> map);

	/**
	 * 병원관계자 정보 조회
	 */
	public OperatorDto detailOper(String email);

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
	public MemberDto emailSecurity(String email);

	/**
	 * 비밀번호 확인
	 */
	public String pwSecurity(String email);
	
	// ------------------------------------------
	
	/**
	 * 인증번호 삽입
	 */
	public int insertVerificationCode(Map<String, Object> map);

	/**
	 * 인증번호 확인
	 */
	public Map<String, Object> getVerificationCode(String email);
	
	/**
	 * 인증번호 삭제
	 */
	public int deleteVerification(String email);
}