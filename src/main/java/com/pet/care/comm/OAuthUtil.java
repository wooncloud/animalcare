package com.pet.care.comm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OAuthUtil {

	// NAVER
	// ----------------------------------------------------------------------------------------

	/**
	 * NAVER Oauth 초기화
	 * 
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
	 * 
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

	// NAVER END
	// ----------------------------------------------------------------------------------------
	
	// KAKAO
	// ----------------------------------------------------------------------------------------
	
	public static void initKakaoOauth(Model model) {
		Properties prop = new Util().readProperties("properties/oauth_kakao.properties");

		String kakaoApiURL = "https://kauth.kakao.com/oauth/authorize?";
		kakaoApiURL += "client_id=" + prop.getProperty("key");
		kakaoApiURL += "&redirect_uri=" + prop.getProperty("url");
		kakaoApiURL += "&response_type=code";

		model.addAttribute("kakao", kakaoApiURL);
	}
	
	public static String kakaoOAuthCallback(String authorize_code) {
		Properties prop = new Util().readProperties("properties/oauth_kakao.properties");
		String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + prop.getProperty("key"));
            sb.append("&redirect_uri=" + prop.getProperty("url"));
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            // 코드 200
            int responseCode = conn.getResponseCode();
            // System.out.println("responseCode : " + responseCode);

            // Response 메시지
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            // System.out.println("response body : " + result);

            // Jackson mapper
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(result, Map.class);

            System.out.println(map);
            
            access_Token = map.get("access_token");
            refresh_Token = map.get("refresh_token");
            
//            System.out.println("access_token : " + access_Token);
//            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
	}
	
	public static String getKakaoUserEmail (String access_Token) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        String email = null;
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Header 정보
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            email = kakao_account.getAsJsonObject().get("email").getAsString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return email;
    }
	
	// KAKAO END
	// ----------------------------------------------------------------------------------------

	// GOOGLE
	// ----------------------------------------------------------------------------------------

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

	public static String googleOAuthCallback(Map<String, String> param, Properties prop) throws JsonMappingException, JsonProcessingException {
		String code = param.get("code");
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.add("code", code);
		parameters.add("client_id", prop.getProperty("id"));
		parameters.add("client_secret", prop.getProperty("secret"));
		parameters.add("redirect_uri", prop.getProperty("url"));
		parameters.add("grant_type", "authorization_code");

		HttpEntity<MultiValueMap<String, String>> rest_request = new HttpEntity<>(parameters, headers);

		URI uri = URI.create("https://www.googleapis.com/oauth2/v4/token");

		ResponseEntity<Map> rest_reponse;
		rest_reponse = restTemplate.postForEntity(uri, rest_request, Map.class);
		Map<String, String> bodys = rest_reponse.getBody();

		Map<String, String> decode = JsonUtil.decodeJWT(bodys.get("id_token"));
		Map<String, String> payload = new ObjectMapper().readValue(decode.get("payload"), Map.class);

		return payload.get("email");
	}

	// GOOGLE END
	// ----------------------------------------------------------------------------------------
}
