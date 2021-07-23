package com.pet.care.model.service.user;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.MemberDto;
import com.pet.care.dto.OperatorDto;
import com.pet.care.dto.UserDto;

public interface IUserService {

	/**
	 * 로그인
	 */
	public MemberDto userLogin(Map<String, Object> map);

	/**
	 * 이메일 중복검사
	 */
	public boolean emailDuplCheck(String email);

	/**
	 * 회원 등록
	 */
	public boolean insertUser(UserDto dto);

	/**
	 * 병원관계자 가입 신청
	 */
	public boolean insertOper(OperatorDto dto);

	/**
	 * 가입신청 병원관계자 조회
	 */
	public List<OperatorDto> grantWaitList();

	/**
	 * 가입신청 처리
	 */
	public boolean grantOper(String email);

	/**
	 * 병원관계자 정보 조회
	 */
	public OperatorDto detailOper(String email);

	/**
	 * 병원관계자 정보 수정
	 */
	public boolean modifyOper(Map<String, Object> map);

	/**
	 * 병원관계자 탈퇴
	 */
	public boolean dormancyOper(String email);

	/**
	 * 병원관계자 삭제
	 */
	public boolean deleteOper(String email);

	/**
	 * 회원 정보 조회
	 */
	public UserDto detailUser(String email);

	/**
	 * 회원 정보 수정
	 */
	public boolean modifyUser(Map<String, Object> map);

	/**
	 * 회원 탈퇴
	 */
	public boolean dormancyUser(String email);

	/**
	 * 회원 삭제
	 */
	public boolean deleteUser(String email);
	
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
	public boolean insertVerificationCode(Map<String, Object> map);
	
	/**
	 * 전화번호 인증번호 확인
	 */
	public boolean checkPhoneVerificationCode(Map<String, Object> param);
	
	/**
	 * 이메일 인증번호 확인
	 */
	public boolean checkEmailVerificationCode(Map<String, Object> param);
}
