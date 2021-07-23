package com.pet.care.model.dao.survey;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.care.dto.SurveyDto;

@Repository
public class SurveyDaoImpl implements ISurveyDao {

	private final String NS="com.pet.care.model.dao.survey.ISurveyDao.";
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<SurveyDto> adminSurveyList() {
		logger.info("SurveyDaoImpl : adminSurveyList 설문 폼 리스트");
		return sqlSession.selectList(NS+"adminSurveyList");
	}

}
