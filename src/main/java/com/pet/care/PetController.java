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
import org.springframework.web.bind.annotation.ResponseBody;

import com.pet.care.dto.MedicalRecodeJoinDto;
import com.pet.care.dto.MemberDto;
import com.pet.care.dto.NoteDto;
import com.pet.care.dto.PetDto;
import com.pet.care.dto.UserDto;
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
		MemberDto mdto= (MemberDto) session.getAttribute("member");
//		mdto.setEmail("user01@gmail.com");
		List<PetDto> pList = iPetService.petList(mdto.getEmail());				
		
		log.info("---세션값---{}",mdto.getEmail());
		log.info("------{}",pList);
		model.addAttribute("pList", pList);
		return "pet/petList";
	}
	
	//애완동물 등록
	@RequestMapping(value = "/insertPet.do", method = RequestMethod.POST)
	public String insertPet(PetDto pdto, HttpSession session, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=UTF-8;");
		PrintWriter out = resp.getWriter();
		MemberDto mdto= (MemberDto) session.getAttribute("member");
		
//		log.info("email:{}",((String) session.getAttribute("user_email")).split("@"));
		log.info("email:{}",mdto.getEmail().split("@"));
		log.info("pdto.getType:{}",pdto.getType().substring(3)); 
		
		List<PetDto> pList=iPetService.petList(mdto.getEmail());					//나중에 세션값으로 바꿀것임
		log.info("pList.size:{}",pList.size());
		log.info("PetController insertPet");
		log.info("id값 : {}",mdto.getEmail().split("@")[0]+
		pdto.getType().substring(3)+pdto.getGender()+"0"+(pList.size()+1));
		
		pdto.setId(mdto.getEmail().split("@")[0]+
				pdto.getType().substring(3)+pdto.getGender()+"0"+(pList.size()+1));	//pdto id값 생성
		pdto.setUser_email(mdto.getEmail());				//pdto email값 입력
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
		MemberDto mdto= (MemberDto) session.getAttribute("member");
		
		log.info("name값은 :{}",name);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_email", mdto.getEmail());       
		map.put("name", name);
		boolean isc=iPetService.deletePet(map);
		if (isc) {
			out.print("<script>alert('애완동물 삭제 성공'); location.href='./petList.do';</script>");
		} else {
			out.print("<script>alert('애완동물 삭제 실패'); location.href='./petList.do';</script>");
		}
		
		out.flush();
		out.close();
		
		return null;
	}
	
	//애완동물 상세보기
	@RequestMapping(value = "/detailPet.do", method =RequestMethod.GET)
	public String detailPet(HttpSession session, Model model, String name) {
		log.info("name : {}",name);
		MemberDto mdto= (MemberDto) session.getAttribute("member");
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("user_email", mdto.getEmail());
		map.put("name", name);
		PetDto pdto = iPetService.detailPet(map);
		model.addAttribute("pdto", pdto);
		return "pet/detailPet";
	}
	
	//애완동물 정보변경페이지 이동
	@RequestMapping(value = "/modifyPage.do", method = RequestMethod.GET)
	public String modifyPage(HttpSession session, Model model, String name) {
		log.info("PetController modifyPet페이지 이동");
		MemberDto mdto= (MemberDto) session.getAttribute("member");
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("user_email", mdto.getEmail());
		map.put("name", name);
		PetDto pdto = iPetService.detailPet(map);
		model.addAttribute("pdto", pdto);
		return "pet/modifyPet";
	}
	
	//애완동물 정보변경
	@RequestMapping(value = "/modifyPet.do", method = RequestMethod.POST)
	public String modifyPet(HttpSession session, PetDto pdto, HttpServletResponse resp) throws IOException {
		log.info("PetController modifyPet 펫 정보변경");
		resp.setContentType("text/html; charset=UTF-8;");
		MemberDto mdto =(MemberDto) session.getAttribute("member");
		pdto.setUser_email(mdto.getEmail());
		boolean isc = iPetService.modifyPet(pdto);
		
		PrintWriter out = resp.getWriter();

		if (isc) {
			out.print("<script>alert('애완동물의 정보가 변경되었습니다.'); location.href='./detailPet.do?name="+pdto.getName()+"';</script>");
		} else {
			out.print("<script>alert('정보수정이 실패하였습니다.');  location.href='./detailPet.do?name="+pdto.getName()+"';</script>");
		}

		out.flush();
		out.close();
		
		return null;
	}
	
	//애완동물 별 진료기록페이지로 이동
	@RequestMapping(value = "/petMedicalRecodeList.do", method = RequestMethod.GET)
	public String petMedicalRecodeList() {
		log.info("PetController petMedicalRecodeList로 이동");
		
		return "pet/petMedicalRecodeList";
	}
	
	@RequestMapping(value = "/recodeList.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray recodeList(HttpSession session) {
		MemberDto mdto=(MemberDto) session.getAttribute("member");
		log.info("PetController recodeList조회 받은 user_email값 : {}", mdto.getEmail());
		List<MedicalRecodeJoinDto> recodeList= iPetService.recodeList(mdto.getEmail());
		log.info("####################### : {}",recodeList);
		JSONObject jsonObject;
		JSONArray jsonArray = new JSONArray();
		JSONArray jsonTmp = new JSONArray();
		
		
		
		for (MedicalRecodeJoinDto n : recodeList) {
			jsonObject = new JSONObject();
			jsonObject.put("id", n.getPetdto().get(0).getName());
			jsonObject.put("parent", "#");
			jsonObject.put("text", n.getPetdto().get(0).getName());
			if (jsonArray.contains(jsonObject) == false) {
				jsonArray.add(jsonObject);
			}
		}
		for (MedicalRecodeJoinDto n : recodeList) {
			jsonObject = new JSONObject();
			jsonObject.put("id",n.getPetdto().get(0).getName()+(n.getTreatdate().getYear()+1900));
			jsonObject.put("parent", n.getPetdto().get(0).getName());
			jsonObject.put("text", n.getTreatdate().getYear()+1900);
			if (jsonArray.contains(jsonObject) == false) {
				jsonArray.add(jsonObject);
			}
		}
		for (MedicalRecodeJoinDto n : recodeList) {
			jsonObject = new JSONObject();
			
			jsonObject.put("id", n.getSeq());
			jsonObject.put("parent", n.getPetdto().get(0).getName()+(n.getTreatdate().getYear()+1900));
			jsonObject.put("text", (n.getTreatdate().getMonth()+1) + "월" + n.getTreatdate().getDate()+"일 기록");
			
			jsonArray.add(jsonObject);
			
		}
		log.info("jsonArray : {}",jsonArray);
		
		return jsonArray;
	}
	
	@RequestMapping(value = "/detailRecode.do", method = RequestMethod.GET)
	@ResponseBody
	public MedicalRecodeJoinDto detailRecode(int seq) {
		log.info("PetController detailRecode값");
		MedicalRecodeJoinDto mdto=iPetService.detailRecode(seq);
		return mdto;
	}
	
	
	
	
	
	
}
