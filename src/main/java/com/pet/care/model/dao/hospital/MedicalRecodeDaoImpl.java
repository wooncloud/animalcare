package com.pet.care.model.dao.hospital;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MedicalRecodeDaoImpl implements IMedicalRecodeDao {

	private final String NS = "com.pet.care.model.dao.hospital.IMedicalRecodeDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
}
