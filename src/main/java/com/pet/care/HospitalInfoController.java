package com.pet.care;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HospitalInfoController {

	@RequestMapping(value = "searchHospital.do", method = RequestMethod.GET)
	public String loginPage(@RequestParam Map<String, Object> param, Model model) {
		
		
		return "hospital/index";
	}
}
