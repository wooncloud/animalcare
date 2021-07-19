package com.pet.care.model.service.hospital;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.care.model.dao.hospital.IHospitalScheduleDao;

@Service
public class HospitalScheduleServiceImpl implements IHospitalScheduleService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired IHospitalScheduleDao dao;
	
}
