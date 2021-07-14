package com.pet.care.socket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component(value = "wsChat.do")
public class SocketHandler extends TextWebSocketHandler {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private ArrayList<WebSocketSession> list; // 웹소켓 세션(채팅 대상자를 담음)
	private Map<WebSocketSession, String> map = new HashMap<WebSocketSession, String>(); // 이름 담기

	public SocketHandler() {
		list = new ArrayList<WebSocketSession>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("== 웹소켓 커넥션 생성 : afterConnectionEstablished ==");
		super.afterConnectionEstablished(session);

		list.add(session);
		log.info("웹소켓 커넥션이 생성되어 침야히고 있는 소켓 SessionID : {}", session.getId());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("== 웹소켓 메시지 전달 : handleTextMessage ==");
		String msg = message.getPayload();
		String strMsg = msg.toString();

		super.handleTextMessage(session, message);

		log.info("전달된 메시지 : getPlayLoad() : {}", msg);
		log.info("전달된 메시지 : toString() : {}", strMsg);

		if (msg != null && !msg.equals("")) {
			// 머릿말 "#$nick_" 이 포함되어 있다면, 모든사람에게 입장 메시지를 보내준다. (broadcast)
			if (msg.indexOf("#$nick_") != -1) {
				// #$nick_비둘기 => 비둘기만 추출해서 WebSocketSession과 비둘기를 쌍으로 map에 담아줌
				map.put(session, msg.replace("#$nick_", "")); // {WebSocketSession : 비둘기}
				for (WebSocketSession s : list) {
//					if(s != session) // 현재 자신을 빼고 다른 참여자에게만 보냄
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
					String date = sdf.format(new Date());
					String m = "<div style='color:green; font-weight:bold;'>[" + map.get(session) + "님이 입장하셨습니다..(" + date + ")]</div>";
					s.sendMessage(new TextMessage(m));
				}
			} else { // 머릿말 "#$nick_" 이 포함되어 없다면, 채팅글이기 때문에 모든 참여자에게 메시지를 보낸다.
				for (WebSocketSession s : list) {
					String m = "<div>" + msg + "</div>";
					s.sendMessage(new TextMessage(m));
				}
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("== 웹소켓 커넥션(세션) 삭제: afterConnectionClosed ==");
		super.afterConnectionClosed(session, status);

		// 채팅창이 닫히거나 websocket에서 close()를 했다면 자신이 가지고 있는 WebSocketSession을 삭제하여 데이터가
		// 전송되지 않도록 함.

		list.remove(session);

		// 화면에 메시지 보내기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
		String out = sdf.format(new Date());

		for (WebSocketSession s : list) {
			String m = "<div style='color:tomato; font-weight:bold;'>[" + map.get(session) + "님이 방을 나가셨습니다.(" + out + ")]</div>";
			s.sendMessage(new TextMessage(m));
		}
		map.remove(session);

		log.info("웹소켓 커넥션이 생성되어 침야히고 있는 소켓 SessionID : {}", session.getId());
	}
	
}
