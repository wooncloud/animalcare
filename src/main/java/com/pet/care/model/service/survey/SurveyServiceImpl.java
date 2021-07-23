package com.pet.care.model.service.survey;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.care.dto.SurveyDto;
import com.pet.care.model.dao.survey.ISurveyDao;

@Service
public class SurveyServiceImpl implements ISurveyService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ISurveyDao iDao;
	
	@Override
	public List<SurveyDto> adminSurveyList() {
		logger.info("SurveyServiceImpl : adminSurveyList 설문 폼 리스트");
		return iDao.adminSurveyList();
	}

}
