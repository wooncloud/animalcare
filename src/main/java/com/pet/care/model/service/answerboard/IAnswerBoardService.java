package com.pet.care.model.service.answerboard;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.AnswerBoardDto;

public interface IAnswerBoardService {
	//로그인된 회원이 문의 글 작성
		public boolean insertUserBoard(AnswerBoardDto dto);
		
		//로그인하지 않은 비회원이 이메일과 비밀번호를 입력하고 문의 글을 작성
		public boolean insertNonUserBoard(AnswerBoardDto dto);
		
		//로그인된 회원이 문의 글 수정
		public boolean modifyUserBoard(Map<String, Object>map);
		
		//로그인하지 않은 비회원이 이메일과 비밀번호를 입력하고 문의 글을 수정
		public boolean modifyNonUserBoard(Map<String, Object>map);
		
		//로그인된 회원이 작성한 문의글 삭제
		public boolean deleterBoard(Map<String, Object>map);
				
		//로그인된 회원의 EMAIL을 통하여 작성한 문의 글을 조회
		public List<AnswerBoardDto>  selUserBoard(Map<String, Object>map);
		
		//로그인 하지 않은 비회원이 글을 작성했던 EMAIL과 PASSWORD를 통하여 작성한 문의 글을 조회
		public List<AnswerBoardDto>  selNonUserBoard(Map<String, Object>map);
		
		//전체 1:1문의 게시글 조회
		public List<AnswerBoardDto> selAllBoard();
		
		//회원 작성글 상세 조회
		public AnswerBoardDto selUserDetail(Map<String, Object>map);
		
		//비회원 작성글 상세 조회
		public AnswerBoardDto selNonUserDetail(Map<String, Object>map);
		
//		//관리자 답글 작성
//		public boolean insertReply(AnswerBoardDto dto);
		
		//관리자 답글 작성
		public boolean insertReply(Map<String, Object> map);
		
		//비회원 조회
		public boolean checkNonUser(Map<String, Object>map);
		
		//이름 조회
		public List<AnswerBoardDto> searchName(Map<String, Object>map);
		
		//제목 조회
		public List<AnswerBoardDto> searchTitle(Map<String, Object>map);
		
}
