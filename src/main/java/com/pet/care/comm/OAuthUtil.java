package com.pet.care.comm;

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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.ui.Model;

public class OAuthUtil {

	// NAVER ----------------------------------------------------------------------------------------
	
	/**
	 * NAVER Oauth 초기화
	 * @param model
	 */
	public static void initNaverOauth(Model model) {
		Properties prop = new Util().readProperties("properties/oauth_naver.properties");
		
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String redirectURI = "";
		String naverApiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		
		try {
			redirectURI = URLEncoder.encode(prop.getProperty("url"), "UTF-8");

			naverApiURL += "&client_id=" + prop.getProperty("id");
			naverApiURL += "&redirect_uri=" + redirectURI;
			naverApiURL += "&state=" + state;
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("naver", naverApiURL);
		model.addAttribute("state", state);
	}
	
	/**
	 * 네이버 OAuth 콜백
	 * @return
	 */
	public static String naverOAuthCallback(Map<String, String> param) {
		String result = "";

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
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(res.toString());
				
				result = OAuthUtil.getNaverUserInfoOAuth((String) jsonObj.get("access_token"));
			}
		} catch (Exception e) {
			System.out.println(e);
			result = "{\"message\":\"error\"}";
		}

		return result;
	}

	public static String getNaverUserInfoOAuth(String accessToken) {
		String header = "Bearer " + accessToken;

		String apiURL = "https://openapi.naver.com/v1/nid/me";

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("Authorization", header);
		String responseBody = get(apiURL, requestHeaders);

		System.out.println("----------------------");
		System.out.println(responseBody);

		return responseBody;
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
	
	// NAVER END ----------------------------------------------------------------------------------------


	// GOOGLE ----------------------------------------------------------------------------------------

	public static void initGoogleOauth(Model model) {
		Properties prop = new Util().readProperties("properties/oauth_google.properties");
		
		String googleApiURL = "https://accounts.google.com/o/oauth2/v2/auth?";
		googleApiURL += "client_id=" + prop.getProperty("id");
		googleApiURL += "&redirect_uri=" + prop.getProperty("url");
		googleApiURL += "&response_type=code";
		googleApiURL += "&scope=+email%20profile%20openid";
		googleApiURL += "&access_type=offline";
		
		model.addAttribute("google", googleApiURL);
	}





	// GOOGLE END ----------------------------------------------------------------------------------------
}
