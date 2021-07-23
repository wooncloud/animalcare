package com.pet.care.comm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class Util {

	/**
	 * Controller에서 View로 Alert 메시지를 전달합니다.<br>
	 * 실행시 무조건 다른 페이지로 리다이렉트 됩니다.
	 * 
	 * @param resp        : HttpServletResponse 객체
	 * @param msg         : 전달할 메시지 명
	 * @param redirectUri : 리다이렉트 uri (null인 경우 index.jsp로 이동)
	 * 
	 * @author WOO SEONG HO
	 */
	public static void PrintWriterMsg(HttpServletResponse resp, String msg, String redirectUri) throws IOException {
		resp.setContentType("text/html; charset=UTF-8;");
		PrintWriter out = resp.getWriter();

		if (redirectUri == null) {
			out.print("<script>alert('" + msg + "'); location.href='/';</script>");
		} else {
			out.print("<script>alert('" + msg + "'); location.href='" + redirectUri + "';</script>");
		}

		out.flush();
		out.close();
	}

	/**
	 * 페이지 객체를 기본세팅을 해주는 메서드입니다.
	 * 
	 * @param page   Paging 객체
	 * @param allCnt 전체 글의 갯수
	 * @return 세팅된 Paging 객체
	 * @author WOO SEONG HO
	 */
//	public static Paging defaultPagingSetting(Paging page, int allCnt) {
//		// 총 게시물의 갯수
//		page.setTotalCount(allCnt);
//		// 출력될 개시글의 수
//		page.setCountList(10);
//		// 화면에 몇개의 페이지를 보여줄건지 (페이지 그룹)
//		page.setCountPage(10);
//		// 총페이지의 갯수
//		page.setTotalPage(page.getTotalCount());
//
//		return page;
//	}

	/**
	 * 랜덤으로 지정한 자릿수의 문자+숫자를 만들어주는 메서드입니다.
	 * 
	 * @return 랜덤 발생한 지정한 자릿수의 문자+숫자
	 * @author Lee Gu Sung
	 */
	public static String randomVal(int len) {
		String randomval = "";

		for (int i = 0; i < len; i++) {
			int rndVal = (int) (Math.random() * 62);
			if (rndVal < 10) {
				randomval += rndVal;
			} else if (rndVal > 35) {
				randomval += (char) (rndVal + 61);
			} else {
				randomval += (char) (rndVal + 55);
			}
		}
		return randomval;
	}
	
	
	/**
	 * 랜덤으로 지정한 자릿수의 숫자를 만들어주는 메서드입니다.
	 * 
	 * @return 랜덤 발생한 지정한 자릿수의 숫자
	 * @author WOO SEONG HO
	 */
	public static String randomNum(int len) {
		Random rand = new Random();
		String numStr = "";
		
		for (int i = 0; i < len; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}
		
		return numStr;
	}

	/**
	 * prop.getProperty("key"); 로 값을 가져올 수 있도록 Properties를 읽어 Properties 객체로 반환합니다.<br>
	 * 기본 경로 : src/main/resources/
	 * 
	 * @param propFileName : 파일명 (경로 : properties/파일명)
	 * @throws FileNotFoundException
	 * @author WOO SEONG HO
	 */
	public Properties readProperties(String propFileName) {
		Properties prop = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		try {
			if (inputStream != null) {
				prop.load(inputStream);
				return prop;
			} else {
				throw new FileNotFoundException("프로퍼티 파일 '" + propFileName + "'을 resource에서 찾을 수 없습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 이메일을 입력받은 내용으로 보내줍니다.<br>
	 * 발송자 이메일 주소 : wooncloud9849@gmail.com
	 *  
	 * @param mailSender :컨트롤러에서 [@Autowired private JavaMailSender mailSender;] 만든 객체
	 * @param to : 받는 사람 이메일 주소
	 * @param subject : 제목
	 * @param content : 내용
	 * 
	 * @author WOO SEONG HO
	 */
	public static void EmailSend(JavaMailSender mailSender, String to ,String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("wooncloud9849@gmail.com"); // 보내는 사람의 이메일
			helper.setTo(to); // 받을 사람 이메일
			helper.setSubject(subject);
			helper.setText(content, true);

			mailSender.send(message);

		} catch (MessagingException e) {

			e.printStackTrace();
		}
	}
	
	
	/**
	 * SMS를 해당 입력받은 번호로 전송합니다. 
	 * @param phoneNumber 전화번호
	 * @author WOO SEONG HO
	 * @return 랜덤으로 만들어진 인증번호가 반환됩니다.
	 */
	public static String sendSMS(String phoneNumber) {
		phoneNumber = phoneNumber.replace("-", ""); // -이 있으면 제거
		return smsModule(phoneNumber);
	}
	
	private static String smsModule(String phoneNumber) {
		Util util = new Util();
		Properties prop = util.readProperties("properties/sms.properties");
		String numStr = randomNum(6);
		
		String text = "[PET CARE] 전화번호 인증번호는 '" + numStr + "' 입니다.";
		String api_key = prop.getProperty("key");
		String api_secret = prop.getProperty("secret");

		// coolsms 객체 생성
		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNumber);
		params.put("from", prop.getProperty("from"));
		params.put("type", "SMS");
		params.put("text", text);
		params.put("app_version", prop.getProperty("app_version")); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
		
		return numStr;
	}
}
