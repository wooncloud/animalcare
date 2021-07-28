package com.pet.care;

import java.util.HashMap;
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

import com.pet.care.dto.AnswerBoardDto;
import com.pet.care.dto.MemberDto;
import com.pet.care.model.service.answerboard.IAnswerBoardService;

@Controller
@RequestMapping("/answerboard")
public class AnswerBoardController {

	@Autowired
	private IAnswerBoardService aService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//게시글 작성 페이지 이동
	@RequestMapping(value = "/moveInsert.do", method = RequestMethod.GET)
	public String moveInsert() {
		
		return "answerboard/insertBoard";
	}
		
	
	//로그인 회원  문의 게시글  작성
	@RequestMapping(value = "/insertUserBoard.do", method = RequestMethod.POST)
	public String insertUserBoard(AnswerBoardDto dto, Model model) {
		logger.info("AnswerBoardController insertUserBoard {} ", dto);
		
		boolean isc = aService.insertUserBoard(dto);

		if(isc) {
			return "redirect:/answerboard/selUserBoard.do";
		}
		return "answerboard/index";
	}
	
	//로그인 회원 수정 폼 이동
	@RequestMapping(value ="/modifyForm.do", method = RequestMethod.GET)
	public String modifyForm(AnswerBoardDto dto, Model model) {
		logger.info("AnswerBoardController modifyForm {} ", dto);
		
		model.addAttribute("dto",dto);
		
		return "answerboard/modifyForm";
	}
	
	//로그인 회원 문의 게시글  수정
	@RequestMapping(value="/modifyUserBoard.do", method = RequestMethod.POST)
	public String modifyUserBoard(@RequestParam Map<String,Object>map) {
		logger.info("AnswerBoardController modifyUserBoard {} ", map);
		boolean isc = aService.modifyUserBoard(map);
		
		if(isc) {
			return "redirect:/answerboard/selUserDetail.do";
		}
		return "";
	}
	
	// 로그인 회원 문의 게시글 삭제
	@RequestMapping(value="/deleteUserBoard.do", method = RequestMethod.GET)
	public String deleteUserBoard(@RequestParam Map<String,Object>map) {
		logger.info("AnswerBoardController deleteUserBoard {} ", map);
		boolean isc = aService.deleteUserBoard(map);
		
		if(isc) {
			return "redirect:/answerboard/selUserBoard.do";
		}
		return "answerboard/index";
	}
	
	// 로그인 회원 문의 게시글 목록 조회
	@RequestMapping(value="/selUserBoard.do", method = RequestMethod.GET)
	public String selUserBoard(HttpSession session, Model model) {
		logger.info("AnswerBoardController selUserBoard {} ", session);
		
		MemberDto mDto = (MemberDto)session.getAttribute("member");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", mDto.getEmail());
	
		List<AnswerBoardDto> lists = aService.selUserBoard(map);
		
		model.addAttribute("lists", lists);
			
		return  "answerboard/boardList";
	}
	
	
	//전체 문의 게시글 목록
	@RequestMapping(value="/selAllBoard.do", method = RequestMethod.GET)
	public String selAllBoard(Model model) {
		logger.info("AnswerBoardController selAllBoard {} ");
		List<AnswerBoardDto> lists = aService.selAllBoard();
		
		model.addAttribute("lists", lists);
		
		return "answerboard/boardList";
	}
	
	
	//로그인 회원 상세 조회
		@RequestMapping(value="/selUserDetail.do", method = RequestMethod.GET)
		public String selUserDetail(@RequestParam Map<String,Object>map, Model model) {
			logger.info("AnswerBoardController selUserDetail {} ", map);
			AnswerBoardDto dto = aService.selUserDetail(map);
			
			model.addAttribute("dto", dto);
			
			return "answerboard/boardDetail";
		}
}
