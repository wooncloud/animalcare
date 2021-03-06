package com.pet.care;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pet.care.comm.JsonUtil;
import com.pet.care.comm.OAuthUtil;
import com.pet.care.comm.Util;
import com.pet.care.dto.MemberDto;
import com.pet.care.dto.OperatorDto;
import com.pet.care.dto.UserDto;
import com.pet.care.model.service.user.IUserService;

@Controller
@RequestMapping("/login")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private IUserService userService;

	// 로그인 화면
	@RequestMapping(value = "/loginForm.do", method = RequestMethod.GET)
	public String loginForm(Model model, HttpSession session) {
		// naver oAuth Init
		OAuthUtil.initNaverOauth(model);
		OAuthUtil.initGoogleOauth(model);
		OAuthUtil.initKakaoOauth(model);
		
		return "login/login";
	}
	

	// 로그인
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@RequestParam Map<String, Object> param, HttpSession session) {
		logger.info("[login] : 로그인 - {}", param);

		MemberDto member = userService.userLogin(param);

		return loginProcess(member, session);
	}

	private String loginProcess(MemberDto member, HttpSession session) {
		// 로그인 실패
		if (member == null) {
			return "redirect:./loginForm.do?type=empty";
		} else {
			System.out.println(member);
			session.setAttribute("member", member);

			// 관리자
			if (member.getUsertype().equals("ROLE_ADMIN")) {
				return "redirect:/admin/adminConsole.do";
			}
			// 병원관계자
			else if (member.getUsertype().equals("ROLE_OPER")) {
				OperatorDto operDto = userService.detailOper(member.getEmail());
				System.out.println(operDto);
				// 계정 승인 여부
				if(operDto.getApprovalflag().equals("Y")) {
					return "redirect:/home.do";					
				} else {
					return "redirect:./loginForm.do?type=approval";
				}
			}
			// 사용자
			else {
				return "redirect:/home.do";
			}
		}
	}
	
	// Naver oAuth Callback
	@RequestMapping(value = "/naver_callback.do", method = RequestMethod.GET)
	public String oauthNaverCallBack(@RequestParam Map<String, String> param, HttpSession session) {
		MemberDto member = null;

		String res = OAuthUtil.naverOAuthCallback(param);
		JSONObject oauthJson = JsonUtil.stringToJson(res);

		if(oauthJson.get("message").equals("success")){
			JSONObject response = (JSONObject) oauthJson.get("response");
			String email = (String) response.get("email");
			member = userService.emailSecurity(email);
		}
		else{
			return "redirect:./loginForm.do?type=empty";
		}

		return loginProcess(member, session);
	}
	
	// Naver oAuth Callback
	@RequestMapping(value = "/kakao_callback.do", method = RequestMethod.GET)
	public String oauthKakaoCallBack(@RequestParam Map<String, String> param, HttpSession session) {
		MemberDto member = null;
		
		if(param.get("code") != null) {
			String code = param.get("code");
			String access_Token = OAuthUtil.kakaoOAuthCallback(code);
			String email = OAuthUtil.getKakaoUserEmail(access_Token);
			
			if(email == null) {
				return "redirect:./loginForm.do?type=empty";				
			} else {
				member = userService.emailSecurity(email);
				return loginProcess(member, session);
			}
			
		} else {
			return "redirect:./loginForm.do?type=empty";			
		}
	}

	// Google oAuth Callback
	@RequestMapping(value = "/google_callback.do", method = RequestMethod.GET)
	public String oauthGoogleCallBack(@RequestParam Map<String, String> param, HttpSession session) {
		Properties prop = new Util().readProperties("properties/oauth_google.properties");

		try {
			String email = OAuthUtil.googleOAuthCallback(param, prop);
			MemberDto member = userService.emailSecurity(email);
			
			return loginProcess(member, session);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "redirect:./loginForm.do?type=empty";
		}
	}

	// 로그아웃
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest req, HttpServletResponse resp) {
		Object obj = session.getAttribute("member");
		if (obj != null) {
			session.removeAttribute("member");
		}

		logger.info("[logout] : 로그아웃 요청 - {}", obj);

		return "redirect:/home.do";
	}

	// 회원가입 동의 화면
	@RequestMapping(value = "/signupAgree.do", method = RequestMethod.GET)
	public String signupAgree() {
		return "login/signupAgree";
	}

	// 회원 종류 선택 화면
	@RequestMapping(value = "/signupSelect.do", method = RequestMethod.GET)
	public String signupSelect() {
		return "login/signupSelect";
	}

	// 사용자 회원 가입 화면
	@RequestMapping(value = "/signupUserForm.do", method = RequestMethod.GET)
	public String signupUserForm() {
		return "login/signupUser";
	}

	// 중복체크
	@RequestMapping(value = "/emailDupl.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean emailDuplCheck(String email) {
		boolean result = false;

		result = userService.emailDuplCheck(email);

		return result;
	}

	// 이메일 인증
	@RequestMapping(value = "/sendEmailCode.do", method = RequestMethod.POST)
	@ResponseBody
	public String sendEmailCode(String email) {
		logger.info("[sendEmailCode] : 이메일 인증 코드 발송 - {}", email);
		String result = "";
		String code = Util.randomVal(8);
		String title = "PET CARE 인증번호 입니다.";
		String content = "PET CARE 회원가입 인증번호는 [" + code + "] 입니다.";

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("email_confirm", code);
		boolean isc = userService.insertVerificationCode(map);

		if (isc) {
			Util.EmailSend(mailSender, email, title, content);
			result = "success";
		} else {
			result = "error";
		}

		return result;
	}

	// 이메일 인증 체크
	@RequestMapping(value = "/checkEmailCode.do", method = RequestMethod.POST)
	@ResponseBody
	public String checkEmailCode(@RequestParam Map<String, Object> param) {
		logger.info("[checkEmailCode] : 이메일 인증 - {}", param);

		boolean isc = userService.checkEmailVerificationCode(param);

		if (isc) {
			return "success"; // 성공
		} else {
			return "error"; // 쿼리중 문제 발생
		}
	}

	// 사용자 회원가입
	@RequestMapping(value = "/signupUser.do", method = RequestMethod.POST)
	public String signupUser(UserDto param) {
		logger.info("[signupUser] : 회원가입 - {}", param);
		boolean isc = userService.insertUser(param);

		if (isc) {
			return "redirect:./loginForm.do";
		} else {
			return "redirect:error/error500.do";
		}
	}

	// 병원관계자 회원 가입 화면
	@RequestMapping(value = "/signupOperForm.do", method = RequestMethod.GET)
	public String signupOperForm() {
		return "login/signupOper";
	}
	
	// 병원관계자 회원가입
	@RequestMapping(value = "/signupOper.do", method = RequestMethod.POST)
	public String signupOper(OperatorDto param) {
		logger.info("[signupUser] : 회원가입 - {}", param);
		boolean isc = userService.insertOper(param);

		if (isc) {
			return "redirect:./loginForm.do";
		} else {
			return "redirect:error/error500.do";
		}
	}

	// 내 정보 화면
	@RequestMapping(value = "/userInfo.do", method = RequestMethod.GET)
	public String userInfo(Model model, HttpSession session) {
		logger.info("[userInfo] : 내 정보");

		MemberDto member = (MemberDto) session.getAttribute("member");
		UserDto dto = userService.detailUser(member.getEmail());
		model.addAttribute("user", dto);

		return "login/userInfo";
	}
	
	// 내 정보 화면
	@RequestMapping(value = "/operInfo.do", method = RequestMethod.GET)
	public String operInfo(Model model, HttpSession session) {
		logger.info("[operInfo] : 병원관계자 정보");
		
		MemberDto member = (MemberDto) session.getAttribute("member");
		OperatorDto dto = userService.detailOper(member.getEmail());
		model.addAttribute("oper", dto);
		
		return "login/operInfo";
	}

	// 비번 변경
	@RequestMapping(value = "/changePW.do", method = RequestMethod.POST)
	@ResponseBody
	public String changePW(@RequestParam Map<String, String> param, HttpSession session) {
		logger.info("[changePW] : 내 정보 변경 - {}", param);
		MemberDto member = (MemberDto) session.getAttribute("member");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", member.getEmail());
		map.put("password", param.get("pw"));

		MemberDto confirmMember = userService.userLogin(map);
		if (confirmMember != null && member.getEmail().equals(confirmMember.getEmail())) {
			map.put("password", param.get("npw"));
			
			boolean isc = false;
			if(member.getUsertype().equals("ROLE_USER")) {
				isc = userService.modifyUser(map);
			}
			else if(member.getUsertype().equals("ROLE_OPER")) {
				isc = userService.modifyOper(map);
			}

			if (isc) {
				return "success"; // 성공
			} else {
				return "error"; // 쿼리중 문제 발생
			}
		} else {
			return "pw"; // 패스워드 일치하지 않음
		}
	}

	// 주소 변경
	@RequestMapping(value = "/changeAddr.do", method = RequestMethod.POST)
	@ResponseBody
	public String changeAddr(@RequestParam Map<String, String> param, HttpSession session) {
		logger.info("[changeAddr] : 내 정보 변경 - {}", param);
		MemberDto member = (MemberDto) session.getAttribute("member");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", member.getEmail());
		map.put("address1", param.get("address1"));
		map.put("address2", param.get("address2"));

		boolean isc = userService.modifyUser(map);

		if (isc) {
			return "success"; // 성공
		} else {
			return "error"; // 쿼리중 문제 발생
		}
	}

	// 전화번호 인증코드 전송
	@RequestMapping(value = "/sendVerifyCode.do", method = RequestMethod.POST)
	@ResponseBody
	public String sendVerifyCode(@RequestParam Map<String, String> param, HttpSession session) {
		logger.info("[sendVerifyCode] : 인증번호 전송 - {}", param);
		MemberDto member = (MemberDto) session.getAttribute("member");

		Map<String, Object> map = new HashMap<String, Object>();

		if (member == null) {
			map.put("email", param.get("email"));
		} else {
			map.put("email", member.getEmail());
		}
		map.put("phone", param.get("phone"));

		String codeNum = Util.sendSMS(param.get("phone"));
		map.put("phone_confirm", codeNum);

		boolean isc = userService.insertVerificationCode(map);

		if (isc) {
			return "success"; // 성공
		} else {
			return "error"; // 쿼리중 문제 발생
		}
	}

	// 전화번호 인증
	@RequestMapping(value = "/checkVerifyCode.do", method = RequestMethod.POST)
	@ResponseBody
	public String checkVerifyCode(@RequestParam Map<String, String> param, HttpSession session) {
		logger.info("[checkVerifyCode] : 전화번호 인증 - {}", param);
		MemberDto member = (MemberDto) session.getAttribute("member");

		Map<String, Object> map = new HashMap<String, Object>();
		if (member == null) {
			map.put("email", param.get("email"));
			map.put("type", "signup");
		} else {
			map.put("email", member.getEmail());
			map.put("type", "modify");
			map.put("role", member.getUsertype());
		}
		map.put("phone", param.get("phone"));
		map.put("code", param.get("code"));

		boolean isc = userService.checkPhoneVerificationCode(map);

		if (isc) {
			return "success"; // 성공
		} else {
			return "error"; // 쿼리중 문제 발생
		}
	}

	// 사용자 탈퇴
	@RequestMapping(value = "/leaveUserForm.do", method = RequestMethod.GET)
	public String leaveUserForm() {
		return "login/leaveUser";
	}

	// 사용자 탈퇴 프로세스
	@RequestMapping(value = "/leaveUser.do", method = RequestMethod.GET)
	public String leaveUser(HttpSession session) {
		MemberDto member = (MemberDto) session.getAttribute("member");
		boolean isc = userService.dormancyUser(member.getEmail());

		if (isc) {
			return "redirect:./logout.do";
		} else {
			return "redirect:error/error500.do";
		}
	}

	// 병원관계자 탈퇴
	@RequestMapping(value = "/leaveOperForm.do", method = RequestMethod.GET)
	public String leaveOperForm() {
		return "login/leaveOper";
	}

	// 병원관계자 탈퇴 프로세스
	@RequestMapping(value = "/leaveOper.do", method = RequestMethod.GET)
	public String leaveOper(HttpSession session) {
		MemberDto member = (MemberDto) session.getAttribute("member");
		boolean isc = userService.dormancyOper(member.getEmail());

		if (isc) {
			return "redirect:./logout.do";
		} else {
			return "redirect:error/error500.do";
		}
	}
}