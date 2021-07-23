package com.pet.care.model.dao.survey;

import java.util.List;

import com.pet.care.dto.SurveyDto;

public interface ISurveyDao {

	//(관리자) 설문 폼 리스트
	public List<SurveyDto> adminSurveyList();
}
