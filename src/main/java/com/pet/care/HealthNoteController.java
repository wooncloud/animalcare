package com.pet.care;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pet.care.dto.NoteDto;
import com.pet.care.dto.PetDto;
import com.pet.care.model.service.note.INoteService;
import com.pet.care.model.service.pet.IPetService;

import oracle.security.o3logon.a;

@Controller
public class HealthNoteController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private INoteService iNoteService;

	@Autowired
	private IPetService iPetService;

	// 건강수첩으로 이동
	@RequestMapping(value = "/healthNoteList.do", method = RequestMethod.GET)
	public String healthNoteList(HttpSession session, Model model) {
		log.info("HealthNoteController healthNote로 이동");

//		List<PetDto> pList=iPetService.petList(session.getAttribute("user_email").toString());
		List<PetDto> pList = iPetService.petList("user01@gmail.com");
		log.info("-------------------------- {}", pList);
		model.addAttribute("pList", pList);

		return "pet/healthNote";
	}

	// 건강수첩에서 건강수첩 기록 리스트 json 뿌리기
	@RequestMapping(value = "/noteList.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray notePetList(HttpSession session, String name) {
		log.info("HealthNoteController noteList조회 받은 name값 : {}", name);
		Map<String, Object> map = new HashMap<String, Object>();
		log.info("1");
//		map.put("email", session.getAttribute("user_email"));
		map.put("email", "user01@gmail.com");
		map.put("name", name);
		log.info("2");
		log.info("map : {}", map);
		List<NoteDto> lDto = iNoteService.noteList(map);
		log.info("3");
		JSONObject jsonObject;
		JSONArray jsonArray = new JSONArray();

		for (NoteDto n : lDto) {
			jsonObject = new JSONObject();
			jsonObject.put("id", n.getRegdate().getYear());
			jsonObject.put("parent", "#");
			jsonObject.put("text", (n.getRegdate().getYear() - 100) + "년");
			if (jsonArray.contains(jsonObject) == false) {
				jsonArray.add(jsonObject);
			}
		}
		for (NoteDto n : lDto) {
			jsonObject = new JSONObject();
			jsonObject.put("id", n.getRegdate().getMonth() + 1);
			jsonObject.put("parent", n.getRegdate().getYear());
			jsonObject.put("text", n.getRegdate().getMonth() + 1 + "월");
			if (jsonArray.contains(jsonObject) == false) {
				jsonArray.add(jsonObject);
			}
		}
		for (NoteDto n : lDto) {
			jsonObject = new JSONObject();
			jsonObject.put("id", n.getRegdate().getMonth() + 1 + "-" + n.getRegdate().getDate());
			jsonObject.put("parent", n.getRegdate().getMonth() + 1);
			jsonObject.put("text", n.getRegdate().getDate() <= 9 ? "0" + n.getRegdate().getDate() + "일 기록"
					: n.getRegdate().getDate() + "일 기록");
			jsonObject.put("name", name);
			if (jsonArray.contains(jsonObject) == false) {
				jsonArray.add(jsonObject);
			}
		}

		return jsonArray;
	}

	@RequestMapping(value = "/selDateList.do", method = RequestMethod.GET)
	public String selnoteList(HttpSession session, Model model, String seldate) {

		return "";
	}

}
