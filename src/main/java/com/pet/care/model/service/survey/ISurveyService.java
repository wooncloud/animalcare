package com.pet.care.model.service.survey;

import java.util.List;

import com.pet.care.dto.SurveyDto;

public interface ISurveyService {

	//(관리자) 설문 폼 리스트
		public List<SurveyDto> adminSurveyList();
}
