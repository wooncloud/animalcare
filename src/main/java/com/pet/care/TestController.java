package com.pet.care;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pet.care.comm.Util;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
public class TestController {

	@RequestMapping(value = "/test/sms.do", method = RequestMethod.GET)
	public String smsPage() {

		return "test/SMS";
	}

	@RequestMapping(value = "/test/sendSMS.do", method = RequestMethod.POST)
	@ResponseBody
	public String sendSMS(String phoneNumber) {
		Random rand = new Random();
		String numStr = "";
		for (int i = 0; i < 6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}

		System.out.println("수신자 번호 : " + phoneNumber);
		System.out.println("인증번호 : " + numStr);

		smsModule(phoneNumber, numStr);

		return numStr;
	}

	private void smsModule(String phoneNumber, String numStr) {
		Util util = new Util();
		Properties prop = util.readProperties("properties/sms.properties");

		String api_key = prop.getProperty("key");
		String api_secret = prop.getProperty("secret");

		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNumber);
		params.put("from", "01030509849");
		params.put("type", "SMS");
		params.put("text", "[PET CARE] 회원가입 인증번호는 '" + numStr + "' 입니다.");
		params.put("app_version", "PET CARE test v0.1"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}

	@RequestMapping(value = "/test/oauth.do", method = RequestMethod.GET)
	public String oauthPage(Model model, HttpSession session) {
		Properties prop = new Util().readProperties("properties/oauth_naver.properties");

		String clientId = prop.getProperty("id");
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String redirectURI = "";
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";

		try {
			redirectURI = URLEncoder.encode(prop.getProperty("url"), "UTF-8");

			apiURL += "&client_id=" + clientId;
			apiURL += "&redirect_uri=" + redirectURI;
			apiURL += "&state=" + state;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		model.addAttribute("apiURL", apiURL);
		session.setAttribute("state", state);

		return "test/OAuth";
	}

	@RequestMapping(value = "/user/naver/callback.do", method = RequestMethod.GET)
	public String oauthNaverCallBack(@RequestParam Map<String, String> param, Model model) {
		Properties prop = new Util().readProperties("properties/oauth_naver.properties");

		String clientId = prop.getProperty("id");
		String clientSecret = prop.getProperty("secret");
		String code = param.get("code");
		String state = param.get("state");
		String redirectURI = "";

		try {
			redirectURI = URLEncoder.encode(prop.getProperty("url"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + clientId;
		apiURL += "&client_secret=" + clientSecret;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;

		System.out.println("apiURL=" + apiURL);

		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);

			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String inputLine;
			StringBuffer res = new StringBuffer();

			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}

			br.close();

			if (responseCode == 200) {
				JSONParser jParser = new JSONParser();
				JSONObject jsonObj = (JSONObject) jParser.parse(res.toString());

				getNaverUserInfoOAuth((String) jsonObj.get("access_token"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return "test/OAuth";
	}

	private void getNaverUserInfoOAuth(String accessToken) {
		String token = accessToken;
		String header = "Bearer " + token;

		String apiURL = "https://openapi.naver.com/v1/nid/me";

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("Authorization", header);
		String responseBody = get(apiURL, requestHeaders);

		System.out.println(responseBody);
	}

	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}
}
