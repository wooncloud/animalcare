package com.pet.care.model.dao.user;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.pet.care.dto.UserDto;

public class UserDaoImpl implements IUserDao {

	private final String NS = "com.pet.care.model.dao.user.IUserDao.";
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public UserDto userLogin(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int emailDuplCheck(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertUser(UserDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertOper(UserDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserDto grantWaitList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int grantOper(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserDto detailOper(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyOper(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int dormancyOper(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteOper(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserDto detailUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int dormancyUser(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserDto emailSecurity(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pwSecurity(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
