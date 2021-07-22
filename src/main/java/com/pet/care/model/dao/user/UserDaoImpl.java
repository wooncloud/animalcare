package com.pet.care.model.dao.user;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.MemberDto;
import com.pet.care.dto.OperatorDto;
import com.pet.care.dto.UserDto;

@Repository
public class UserDaoImpl implements IUserDao {

	private final String NS = "com.pet.care.model.dao.user.IUserDao.";
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public MemberDto userLogin(Map<String, Object> map) {
		MemberDto dto = null;

		String pw = pwSecurity((String) map.get("email"));
		if (passwordEncoder.matches((String) map.get("password"), pw)) {
			dto = emailSecurity((String) map.get("email"));
		}
		
		return dto;
	}

	@Override
	public int emailDuplCheck(String email) {
		return sqlSession.selectOne(NS + "emailDuplCheck", email);
	}

	@Override
	public int insertUser(UserDto dto) {
		String enPW = passwordEncoder.encode(dto.getPassword());
		dto.setPassword(enPW);

		return sqlSession.insert(NS + "insertUser", dto);
	}

	@Override
	public int insertOper(OperatorDto dto) {
		String enPW = passwordEncoder.encode(dto.getPassword());
		dto.setPassword(enPW);

		return sqlSession.insert(NS + "insertOper", dto);
	}

	@Override
	public List<OperatorDto> grantWaitList() {
		return sqlSession.selectList(NS + "grantWaitList");
	}

	@Override
	public int grantOper(String email) {
		return sqlSession.update(NS + "grantOper", email);
	}

	@Override
	public OperatorDto detailOper(String email) {
		return sqlSession.selectOne(NS + "detailOper", email);
	}

	@Override
	public int modifyOper(Map<String, Object> map) {
		// 비번 있을경우 encode
		if (map.get("password") != null) {
			String enPW = passwordEncoder.encode((String) map.get("password"));
			map.put("password", enPW);
		}

		return sqlSession.update(NS + "modifyOper", map);
	}

	@Override
	public int dormancyOper(String email) {
		return sqlSession.update(NS + "dormancyOper", email);
	}

	@Override
	public int deleteOper(String email) {
		return sqlSession.delete(NS + "deleteOper", email);
	}

	@Override
	public UserDto detailUser(String email) {
		return sqlSession.selectOne(NS + "detailUser", email);
	}

	@Override
	public int modifyUser(Map<String, Object> map) {
		// 비번 있을경우 encode
		if (map.get("password") != null) {
			String enPW = passwordEncoder.encode((String) map.get("password"));
			map.put("password", enPW);
		}

		return sqlSession.update(NS + "modifyUser", map);
	}

	@Override
	public int dormancyUser(String email) {
		return sqlSession.update(NS + "dormancyUser", email);
	}

	@Override
	public int deleteUser(String email) {
		return sqlSession.delete(NS + "deleteUser", email);
	}

	@Override
	public MemberDto emailSecurity(String email) {
		return sqlSession.selectOne(NS + "emailSecurity", email);
	}

	@Override
	public String pwSecurity(String email) {
		return sqlSession.selectOne(NS + "pwSecurity", email);
	}
}
