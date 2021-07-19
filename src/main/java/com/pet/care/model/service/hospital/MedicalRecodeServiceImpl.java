package com.pet.care.model.service.hospital;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.care.model.dao.hospital.IMedicalRecodeDao;

@Service
public class MedicalRecodeServiceImpl implements IMedicalRecodeService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired IMedicalRecodeDao dao;
	
}
