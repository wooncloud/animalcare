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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pet.care.dto.MemberDto;
import com.pet.care.dto.NoteDto;
import com.pet.care.dto.PetDto;
import com.pet.care.model.service.note.INoteService;
import com.pet.care.model.service.pet.IPetService;

import oracle.security.o3logon.a;

@Controller
@RequestMapping("/healthNote")
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
		MemberDto mdto= (MemberDto)session.getAttribute("member");
		List<PetDto> pList = iPetService.petList(mdto.getEmail());
		log.info("-------------------------- {}", pList);
		model.addAttribute("pList", pList);

		return "pet/healthNote";
	}

	// 건강수첩에서 건강수첩 기록 리스트 json 뿌리기
	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value = "/noteList.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray notePetList(HttpSession session, String name) {
		log.info("HealthNoteController noteList조회 받은 name값 : {}", name);
		MemberDto mdto= (MemberDto) session.getAttribute("member");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", mdto.getEmail());
		map.put("name", name);
		log.info("map : {}", map);
		List<NoteDto> lDto = iNoteService.noteList(map);
		JSONObject jsonObject;
		JSONArray jsonArray = new JSONArray();

		for (NoteDto n : lDto) {
			jsonObject = new JSONObject();
			jsonObject.put("id", n.getRegdate().getYear()+1900);
			jsonObject.put("parent", "#");
			jsonObject.put("text", (n.getRegdate().getYear() +1900) + "년");
			if (jsonArray.contains(jsonObject) == false) {
				jsonArray.add(jsonObject);
			}
		}
		for (NoteDto n : lDto) {
			jsonObject = new JSONObject();
			jsonObject.put("id",n.getRegdate().getYear()+1900+"-"+ n.getRegdate().getMonth() + 1);
			jsonObject.put("parent", n.getRegdate().getYear()+1900);
			jsonObject.put("text", n.getRegdate().getMonth() + 1 + "월");
			if (jsonArray.contains(jsonObject) == false) {
				jsonArray.add(jsonObject);
			}
		}
		for (NoteDto n : lDto) {
			jsonObject = new JSONObject();
			jsonObject.put("id", n.getRegdate().getMonth()<9?
					n.getRegdate().getYear()+1900+"-"+"0"+(n.getRegdate().getMonth()+1) + "-" + n.getRegdate().getDate()
					:n.getRegdate().getYear()+1900+"-"+ n.getRegdate().getMonth() + 1 + "-" + n.getRegdate().getDate()
						);
			jsonObject.put("parent", n.getRegdate().getYear()+1900+"-"+ n.getRegdate().getMonth() + 1);
			jsonObject.put("text", n.getRegdate().getDate() <= 9 ? "0" + n.getRegdate().getDate() + "일 기록"
					: n.getRegdate().getDate() + "일 기록");
			jsonObject.put("name", name);
			if (jsonArray.contains(jsonObject) == false) {
				jsonArray.add(jsonObject);
			}
		}
		log.info("jsonArray값은!!!!!!!!!!!! : {}",jsonArray);
		return jsonArray;
	}

	@RequestMapping(value = "/selDateList.do", method = RequestMethod.GET)
	@ResponseBody
	public List<NoteDto> selDateList(HttpSession session, @RequestParam Map<String,Object> map) {
		log.info("HealthNoteController selDateList조회 받은 name값 : {}", map);
		log.info(map.toString());
		MemberDto mdto= (MemberDto) session.getAttribute("member");
		map.put("email", mdto.getEmail());
		List<NoteDto> nDto= iNoteService.selDateList(map);
		
		return nDto;
	}

}
