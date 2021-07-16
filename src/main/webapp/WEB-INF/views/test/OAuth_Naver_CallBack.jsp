<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<title></title>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function(){
			let json = JSON.parse('${res}');
			console.log(json.token_type + " " +  json.access_token);
			
			$.ajax({
				type: "POST",
                url: "https://openapi.naver.com/v1/nid/me",
				beforeSend: function (xhr) {
					xhr.setRequestHeader("Content-type", "*/*");
					xhr.setRequestHeader("Authorization", json.token_type + " " +  json.access_token);
				},
				success: function (res) {
					console.log(res);
				},
				error:function (msg) {
					console.log(msg);
				},
			});
		});
	</script>
</body>
</html>