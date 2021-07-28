package com.pet.care.model.service.note;

import java.util.List;
import java.util.Map;

import com.pet.care.dto.NoteDto;

public interface INoteService {

	//건강수첩 리스트 조회 
	public List<NoteDto> noteList(Map<String, Object> map);
	
	//건강수첩 일별 리스트 조회
	public List<NoteDto> selDateList(Map<String, Object> map);
	
	//건강수첩 상세조회
	public NoteDto noteDetail(int seq);
}
