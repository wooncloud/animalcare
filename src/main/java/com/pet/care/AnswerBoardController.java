package com.pet.care;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pet.care.model.service.answerboard.IAnswerBoardService;

@Controller
public class AnswerBoardController {

	@Autowired
	private IAnswerBoardService iService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
}
