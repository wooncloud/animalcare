package com.pet.care.model.service.user;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pet.care.dto.MemberDto;
import com.pet.care.dto.OperatorDto;
import com.pet.care.dto.UserDto;
import com.pet.care.model.dao.user.IUserDao;

@Service
public class UserServiceImpl implements IUserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserDao dao;

	@Override
	public MemberDto userLogin(Map<String, Object> map) {
		logger.info("userLogin : {}", map);
		return dao.userLogin(map);
	}

	@Override
	public boolean emailDuplCheck(String email) {
		logger.info("emailDuplCheck : {}", email);
		return dao.emailDuplCheck(email) > 0 ? true : false;
	}

	@Override
	public boolean insertUser(UserDto dto) {
		logger.info("insertUser : {}", dto);
		return dao.insertUser(dto) > 0 ? true : false;
	}

	@Override
	public boolean insertOper(OperatorDto dto) {
		logger.info("insertOper : {}", dto);
		return dao.insertOper(dto) > 0 ? true : false;
	}

	@Override
	public List<OperatorDto> grantWaitList() {
		logger.info("grantWaitList");
		return dao.grantWaitList();
	}

	@Override
	public boolean grantOper(String email) {
		logger.info("grantOper : {}", email);
		return dao.grantOper(email) > 0 ? true : false;
	}

	@Override
	public OperatorDto detailOper(String email) {
		logger.info("detailOper : {}", email);
		return dao.detailOper(email);
	}

	@Override
	public boolean modifyOper(Map<String, Object> map) {
		logger.info("modifyOper : {}", map);
		return dao.modifyOper(map) > 0 ? true : false;
	}

	@Override
	public boolean dormancyOper(String email) {
		logger.info("dormancyOper : {}", email);
		return dao.dormancyOper(email) > 0 ? true : false;
	}

	@Override
	public boolean deleteOper(String email) {
		logger.info("deleteOper : {}", email);
		return dao.deleteOper(email) > 0 ? true : false;
	}

	@Override
	public UserDto detailUser(String email) {
		logger.info("detailUser : {}", email);
		return dao.detailUser(email);
	}

	@Override
	public boolean modifyUser(Map<String, Object> map) {
		logger.info("modifyUser : {}", map);
		return dao.modifyUser(map) > 0 ? true : false;
	}

	@Override
	public boolean dormancyUser(String email) {
		logger.info("dormancyUser : {}", email);
		return dao.dormancyUser(email) > 0 ? true : false;
	}

	@Override
	public boolean deleteUser(String email) {
		logger.info("deleteUser : {}", email);
		return dao.deleteUser(email) > 0 ? true : false;
	}

	@Override
	public MemberDto emailSecurity(String email) {
		logger.info("emailSecurity : {}", email);
		return dao.emailSecurity(email);
	}

	@Override
	public String pwSecurity(String email) {
		logger.info("pwSecurity : {}", email);
		return dao.pwSecurity(email);
	}

	@Override
	@Transactional
	public boolean insertVerificationCode(Map<String, Object> map) {
		logger.info("insertVerificationCode : {}", map);
		dao.deleteVerification((String)map.get("email"));
		return dao.insertVerificationCode(map) > 0 ? true : false;
	}

	@Override
	@Transactional
	public boolean checkPhoneVerificationCode(Map<String, Object> param) {
		logger.info("checkPhoneVerificationCode : {}", param);
		String type = (String)param.get("type");

		Map<String, Object> map = dao.getVerificationCode((String) param.get("email"));
		
		String userCode = (String) param.get("code");
		String realCode = (String) map.get("PHONE_CONFIRM");
		
		if (realCode.equals(userCode)) {
			
			int n = 0;
			if(type.equals("modify")) {
				if(param.get("role").equals("ROLE_USER")) {
					n = dao.modifyUser(param);
				}else if (param.get("role").equals("ROLE_OPER")) {
					n = dao.modifyOper(param);
				}
			}
			else {
				n = 1;
			}
			
			if(n > 0) {
				n = dao.deleteVerification((String)param.get("email"));
			}else {
				return false;
			}
			
			return n > 0 ? true : false;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean checkEmailVerificationCode(Map<String, Object> param) {
		logger.info("checkEmailVerificationCode : {}", param);
		Map<String, Object> map = dao.getVerificationCode((String) param.get("email"));
		
		String userCode = (String) param.get("code");
		String realCode = (String) map.get("EMAIL_CONFIRM");
		
		if (realCode.equals(userCode)) {
			return dao.deleteVerification((String)param.get("email")) > 0 ? true : false;
		} else {
			return false;
		}
	}
}
