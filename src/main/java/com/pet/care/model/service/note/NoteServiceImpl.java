package com.pet.care.model.service.note;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.care.dto.NoteDto;
import com.pet.care.model.dao.note.INoteDao;

@Service
public class NoteServiceImpl implements INoteService {

	@Autowired
	private INoteDao iNoteDao;
	
	@Override
	public List<NoteDto> noteList(Map<String, Object> map) {
		return iNoteDao.noteList(map);
	}

	@Override
	public List<NoteDto> selNoteList(Map<String, Object> map) {
		return iNoteDao.selNoteList(map);
	}

	@Override
	public NoteDto noteDetail(int seq) {
		// TODO Auto-generated method stub
		return iNoteDao.noteDetail(seq);
	}


	
}
