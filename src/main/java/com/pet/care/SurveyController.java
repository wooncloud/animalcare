package com.pet.care;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/survey")
public class SurveyController {

   @RequestMapping(value="/surveyForm.do", method = RequestMethod.GET)
   public String surveyForm() {
      return "/survey/surveyForm";
   }
}