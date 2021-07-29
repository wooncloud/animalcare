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
import org.springframework.web.bind.annotation.ResponseBody;

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
	//이거 그냥 하나로 합쳐도 될 듯
	@RequestMapping(value = "/insertUserBoard.do", method = RequestMethod.POST)
	public String insertUserBoard(AnswerBoardDto dto, Model model) {
		logger.info("AnswerBoardController insertUserBoard {} ", dto);
		
 		String password = dto.getPassword();
 		System.out.println("passssssssssssword"+password);
   		int seq =dto.getSeq();
   		System.out.println("seqqqqqqqqqqqqq"+seq);
 		
 		if(password == null) {
 			boolean isc  = aService.insertUserBoard(dto);
 			return isc?"redirect:/answerboard/selAllBoard.do":"answerboard/index";
 		} else {
 			boolean isc  = aService.insertNonUserBoard(dto);
 			return isc?"redirect:/answerboard/selAllBoard.do":"answerboard/index";
 		}
	}
		

	
	//비로그인 문의 게시글 작성
	@RequestMapping(value = "/insertNonUserBoard.do", method = RequestMethod.POST)
	public String insertNonUserBoard(AnswerBoardDto dto) {
		logger.info("AnswerBoardController insertNonUserBoard {} ", dto);

		boolean isc = aService.insertNonUserBoard(dto);
		
		if(isc) {
			//전체 글 목록 페이지로 가야하는게 맞는 것 같다
			return "redirect:/answerboard/selAllBoard.do";
		}
		return "answerboard/index";

	}
	
	// 회원 수정 폼 이동
	@RequestMapping(value ="/modifyUserForm.do", method = RequestMethod.GET)
	public String modifyUserForm(@RequestParam Map<String, Object>map, Model model) {
		logger.info("AnswerBoardController modifyUserForm {} ", map);
		
		AnswerBoardDto dto = aService.selUserDetail(map);
		
		model.addAttribute("dto",dto);
		
		return "answerboard/modifyBoard";
	}
	
	//비회원 수정 폼 이동
		@RequestMapping(value ="/modifyNonUserForm.do", method = RequestMethod.GET)
		public String modifyNonUserForm(@RequestParam Map<String, Object>map, Model model) {
			logger.info("AnswerBoardController modifyNonUserForm {} ", map);
			
			AnswerBoardDto dto = aService.selNonUserDetail(map);
			
			model.addAttribute("dto",dto);
			
			return "answerboard/modifyBoard";
		}
	
	//로그인 회원 문의 게시글  수정
	@RequestMapping(value="/modifyUserBoard.do", method = RequestMethod.POST)
	public String modifyUserBoard(@RequestParam Map<String,Object>map, HttpSession session) {
		logger.info("AnswerBoardController modifyUserBoard {} ", map);
		
		MemberDto dto = (MemberDto)session.getAttribute("member");
		
		boolean isc = aService.modifyUserBoard(map);
		
		if(isc && (dto!=null)) {
			//해당 글 상세로 가게 해주겠니
			return "redirect:/answerboard/selUserDetail.do?seq="+map.get("seq");
		} else {
			return "redirect:/answerboard/selNonUserDetail.do?seq="+map.get("seq");
		}
	}
	
	//비회원 문의 게시글  수정
		@RequestMapping(value="/modifyNonUserBoard.do", method = RequestMethod.POST)
		public String modifyNonUserBoard(@RequestParam Map<String,Object>map) {
			logger.info("AnswerBoardController modifyNonUserBoard {} ", map);
			boolean isc = aService.modifyNonUserBoard(map);
			
			if(isc) {
				//해당 게시글 상세로 가게 만들어줘라
				return "redirect:/answerboard/selUserDetail.do?seq="+map.get("seq");
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
	
	
	//  비회원 문의 게시글 삭제
		@RequestMapping(value="/deleteNonUserBoard.do", method = RequestMethod.GET)
		public String deleteNonUserBoard(@RequestParam Map<String,Object>map) {
			logger.info("AnswerBoardController deleteUserBoard {} ", map);
			boolean isc = aService.deleteNonUserBoard(map);
			
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
	
	
	// 비회원 문의 게시글 목록 조회
	@RequestMapping(value="/selNonUserBoard.do", method = RequestMethod.GET)
	public String selNonUserBoard(HttpSession session, Model model) {
		logger.info("AnswerBoardController selUserBoard {} ", session);
		
		MemberDto mDto = (MemberDto)session.getAttribute("member");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", mDto.getEmail());
	
		List<AnswerBoardDto> lists = aService.selNonUserBoard(map);
		
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
		
	//비회원 글 상세 조회 체크
		@RequestMapping(value="/checkInfo.do", method = RequestMethod.GET)
		@ResponseBody
		public String checkInfo(@RequestParam Map<String,Object>map) {
				logger.info("AnswerBoardController checkInfo {} ", map);
				
				boolean isc = aService.checkNonUser(map);
					
				return String.valueOf(isc);
			}
		
		@RequestMapping(value = "/selNonUserDetail.do", method = RequestMethod.GET)
		public String selNonUserDetail(@RequestParam Map<String, Object>map, Model model){
			logger.info("AnswerBoardController selNonUserDetail {} ", map);
			
			AnswerBoardDto dto = aService.selNonUserDetail(map);
			
			model.addAttribute("dto", dto);
			
			return "answerboard/boardDetail"; 
		}
		
		@RequestMapping(value = "/insertReply.do", method = RequestMethod.GET)
		public String insertReply(AnswerBoardDto dto) {
			
			boolean isc = aService.insertReply(dto);
			
			return "";
		}

}


