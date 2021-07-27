package com.pet.care.model.dao.survey;

import java.util.List;
import java.util.Map;

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

	@Override
	public boolean insertSurveyForm(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : insertSurveyForm 설문 폼 작성 - {} ", map);
		int isc = sqlSession.insert(NS+"insertSurveyForm", map);
		return (isc>0)?true:false; 
	}

	@Override
	public boolean updateDateForm(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : updateDateForm 설문 폼 배포기간 설정 - {} ", map);
		int isc = sqlSession.update(NS+"updateDateForm",map);
		return (isc>0)?true:false;
	}
	
	@Override
	public SurveyDto surveyDetail(Map<String, Object> map) {
		logger.info("SurveyDaoImpl : surveyDetail 설문 폼 상세 - {}",map);
		return sqlSession.selectOne(NS+"surveyDetail",map);
	}


}
