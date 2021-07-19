package com.pet.care.model.dao.hospital;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HospitalScheduleDaoImpl implements IHospitalScheduleDao {

	private final String NS = "com.pet.care.model.dao.hospital.IHospitalScheduleDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
}
