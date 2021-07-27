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

import com.pet.care.comm.JsonUtil;
import com.pet.care.dto.CodeDto;
import com.pet.care.dto.FavoriteDto;
import com.pet.care.dto.MemberDto;
import com.pet.care.model.service.code.ICodeService;
import com.pet.care.model.service.favorite.IFavoriteService;

@Controller
@RequestMapping("/favo")
public class FavoriteController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IFavoriteService service;
	@Autowired
	private ICodeService codeService;
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String favoriteList(Model model, HttpSession session) {
		logger.info("[favoriteList] : 관심병원 리스트");
		MemberDto member = (MemberDto) session.getAttribute("member");
		List<FavoriteDto> list = service.favoriteList(member.getEmail());
		List<CodeDto> codes = codeService.categoryCodeSelect("PET");
		
		model.addAttribute("list", list);
		model.addAttribute("pettype", JsonUtil.CommonCodeJson(codes));

		return "favorite/favorite";
	}
	
}
