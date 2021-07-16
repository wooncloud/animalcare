<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.box{
		border: 1px solid lightgray;
		width: 500px;
		height: 500px;
		padding: 10px;
	}

	.msg-box{
		overflow: auto;
	}

	.msg-box > div{
		font-size: small;
	}

	.chat-box{
		display: none;
		height:inherit;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<title>싶다 만들고 알림을 거성이는</title>
</head>
<body>
	<input type="button" value="연결종료" style="margin: 10px 0" onclick="disconnection()">
	<div id="msgBox" class="box msg-box">
		<input type="text" id="nickName" onkeyup="if(event.keyCode == 13){$('#joinRoom').click()}">
		<input type="button" value="대화 입장" id="joinRoom">
	</div>
	<div id="chatDiv" class="box chat-box">
		<input type="text" id="chat" onkeyup="if(event.keyCode == 13){$('#chatBtn').click()}">
		<input type="button" value="입력" id="chatBtn">
	</div>

	<script type="text/javascript">
		// 웹소켓 객체
		let ws = null;
		// 웹소켓 주소
		let url = null;
		// 대화
		let nickName = null;
		
		$(document).ready(function(){
			// 처음 화면시 대화명을 입력하라는 무언의 압박
			$("#nickName").focus();

			$("#joinRoom").bind("click", function(){
				if($("#nickName").val() == ""){
					alert("닉네임을 입력해 주세요.");
					$("#nickName").focus();
					return;
				}

				// 대화탕에서 사용한 닉네임 => serverside에서는 Map[websocket SessionId : nick]
				nickName = $("#nickName").val();

				$("#msgBox").html("");
				$("#chatDiv").show();
				$("#chat").focus();
				
				//웹 소켓 객체를 생성하며 Spring의 Bean을 설정함.
				ws = new WebSocket("ws://localhost:8099/PetCare/wsChat.do");
				// 웹소켓 오픈 후 send()를 통해서 서버에 텍스트를 전송함 => handleTextMessage
				ws.onopen = function(){
					console.log("웹소켓 객체 open");

					// 웹소켓 서버에 글 전달 => 
					// handleTextMessage => 
					// 로직을 처리 => 
					// s.sendMessage(new TextMessage(m)); 통해서 브로드 캐스팅
					
					ws.send("#$nick_" + nickName);
				}

				ws.onmessage = function(event){
					$("#msgBox").append(event.data);
				}

				ws.onclose = function(){
					alert("서버와 연결이 종료되었습니다.");
				}
			});

			$("#chatBtn").bind("click", function(){
				let chat = $("#chat").val();
				if(chat == ""){
					alert("대화 내용을 입력해 주세요.");
					return;
				}else{
					ws.send("[" + nickName + "] : " + chat);
					$("#chat").val("");
					$("#chat").focus();
				}

				ws.onmessage = function(event){
					$("#msgBox").append(event.data);
					document.getElementById("msgBox").scrollTo(0,document.body.scrollHeight);
				}
			});
		});
		
		// 창을 닫았을 경우
		window.onbeforeunload = function(){
			ws.close();
			ws = null;
		}

		// 닫기
		function disconnection(){
			ws.close();
			ws = null;
			window.close();
		}
	</script>
</body>
</html>