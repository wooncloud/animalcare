package com.pet.care;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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

import com.pet.care.dto.NoteDto;
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
		session.setAttribute("user_email", "user01@gmail.com");									//임시 세션값
		List<PetDto> pList = iPetService.petList((String) session.getAttribute("user_email"));				
		
		log.info("---세션값---{}",(String) session.getAttribute("user_email"));
		log.info("------{}",pList);
		model.addAttribute("pList", pList);
		return "pet/petList";
	}
	
	//애완동물 등록
	@RequestMapping(value = "/insertPet.do", method = RequestMethod.POST)
	public String insertPet(PetDto pdto, HttpSession session, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8;");
		PrintWriter out = resp.getWriter();
		
		log.info("email:{}",((String) session.getAttribute("user_email")).split("@"));
		log.info("pdto.getType:{}",pdto.getType().substring(3)); 
		
		List<PetDto> pList=iPetService.petList((String) session.getAttribute("user_email"));					//나중에 세션값으로 바꿀것임
		log.info("pList.size:{}",pList.size());
		log.info("PetController insertPet");
		log.info("id값 : {}",((String) session.getAttribute("user_email")).split("@")[0]+
		pdto.getType().substring(3)+pdto.getGender()+"0"+(pList.size()+1));
		
		pdto.setId(((String) session.getAttribute("user_email")).split("@")[0]+
				pdto.getType().substring(3)+pdto.getGender()+"0"+(pList.size()+1));	//pdto id값 생성
		pdto.setUser_email((String)session.getAttribute("user_email"));				//pdto email값 입력
		log.info("pdto :{}",pdto);
		boolean isc;
		if (pList.size()==6) {
			isc=false;
		} else {
			isc= iPetService.insertPet(pdto);

		}
		if (isc) {
			out.print("<script>alert('애완동물 등록 성공')</script>");
		} else {
			out.print("<script>alert('애완동물 등록 실패')</script>");

		}
		return "redirect:./petList.do";
	}
	
	//애완동물 삭제
	@RequestMapping(value = "/deletePet.do", method = RequestMethod.GET)
	public String deletePet(String name, HttpServletResponse resp, HttpSession session) throws IOException {
		resp.setContentType("text/html; charset=UTF-8;");
		PrintWriter out = resp.getWriter();
		
		log.info("name값은 :{}",name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_email", session.getAttribute("user_email"));       
		map.put("name", name);
		boolean isc=iPetService.deletePet(map);
		if (isc) {
			out.print("<script>alert('애완동물 삭제 성공'); location.href='./petList.do';</script>");
		} else {
			out.print("<script>alert('애완동물 삭제 실패'); location.href='./petList.do';</script>");
		}
		return null;
	}
	
	//애완동물 상세보기
	@RequestMapping(value = "/detailPet.do", method =RequestMethod.GET)
	public String detailPet(HttpSession session, Model model, String name) {
		log.info("name : {}",name);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("user_email", session.getAttribute("user_email"));
		map.put("name", name);
		PetDto pdto = iPetService.detailPet(map);
		model.addAttribute("pdto", pdto);
		return "pet/detailPet";
	}
	
	//애완동물 정보변경페이지로 이등
	@RequestMapping(value = "/modifyPet.do", method = RequestMethod.GET)
	public String modifyPet(HttpSession session, Model model, String name) {
		log.info("PetController modifyPet페이지 이동");
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("user_email", session.getAttribute("user_email"));
		map.put("name", name);
		PetDto pdto = iPetService.detailPet(map);
		model.addAttribute("pdto", pdto);
		return "pet/modifyPet";
	}
	
	//애완동물 별 진료기록페이지로 이동
	@RequestMapping(value = "/petMedicalRecodeList.do", method = RequestMethod.GET)
	public String petMedicalRecodeList(HttpSession session, Model model) {
		log.info("PetController petMedicalRecodeList로 이동");

		List<PetDto> pList = iPetService.petList((String) session.getAttribute("user_email"));
		log.info("-------------------------- {}", pList);
		model.addAttribute("pList", pList);
		
		return "pet/petMedicalRecodeList";
	}
	
	@RequestMapping(value = "/recodeList.do")
	public JSONArray recodeList(HttpSession session, String name) {
		log.info("HealthNoteController noteList조회 받은 name값 : {}", name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", session.getAttribute("user_email"));
		map.put("name", name);
		log.info("map : {}", map);
//		iPetService.
		JSONObject jsonObject;
		JSONArray jsonArray = new JSONArray();
		
		return jsonArray;
	}
	
	
	
	
	
	
}
