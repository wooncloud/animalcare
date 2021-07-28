package com.pet.care;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value = "/modifyFavorite.do", method = RequestMethod.POST)
	@ResponseBody
	public String toggleFavorite(@RequestParam Map<String, Object> param, HttpSession session) {
		logger.info("[toggleFavorite] : 관심병원 토글링");
		String result = null;
		
		boolean isc = false;
		if (((String) param.get("value")).equals("true")) {
			// insert
			MemberDto member = (MemberDto)session.getAttribute("member");
			param.put("email", member.getEmail());
			isc = service.favoriteInsert(param);
		} else {
			// delete
			int seq = Integer.parseInt((String)param.get("seq"));
			isc = service.favoriteDelete(seq);
		}
		
		result = isc ? "success" : "error";
		return result;
	}
	
}
