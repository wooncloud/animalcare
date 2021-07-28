package com.pet.care;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pet.care.dto.PetDto;
import com.pet.care.model.service.pet.IPetService;

@Controller
@RequestMapping("/pet")
public class PetController {

	private Logger log=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IPetService iPetService;
	
	
	
	
	//애완동물 리스트조회
	@RequestMapping(value = "/petList.do", method = RequestMethod.GET)
	public String petList(HttpSession session, Model model) {
		log.info("PetController petList조회");
		List<PetDto> pList = iPetService.petList("user01@gmail.com");
		log.info("------{}",pList);
		model.addAttribute("pList", pList);
		return "pet/petList";
	}
	
	//애완동물 등록
	@RequestMapping(value = "/insertPet.do", method = RequestMethod.POST)
	public String insertPet(PetDto pdto, HttpSession session, Model model) {
		log.info("{}",session.getAttribute("user_email").toString().split("@").toString());
		log.info("PetController insertPet");
		return "/";
	}
	
	
}
