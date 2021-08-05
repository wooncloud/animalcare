<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/header.jsp" %>
<%-- <link href="${path}/css/healthNote.css" rel="stylesheet"> --%>
<div class="container-fluid">
<script type="text/javascript">
window.onload = function(){
	
	var jsonData = $.ajax({
		url : "./recodeList.do",
		method : "get",
		dataType : "json",
		contentType : "application/json; charset=UTF-8",
		async: false
	}).responseText; // json object 중 responseText만 받아옴
	console.log(jsonData);
	
	
	var realJson = JSON.parse(jsonData);
	console.log(realJson);
	$("#jstree").jstree({ // div태그에 id를 부여하여 jquery DOM탐색을 통해 받아와서 .jstree()를 실행하면 자동으로 트리구조를 그려줌
		'plugins': ["wholerow", "dnd"],
		'core' : {
			'data' : realJson, // jstree의 'core' 부분의 'data'에 json 객체를 넣고 구동.
			'dataType' : 'json',
			'themes' : {
				"theme" : ["classic"]
			},
			"check_callback" : true
		}
	});
	
}
$(function(){
	$("#jstree").on("changed.jstree", function(e, data){
		console.log(data); // jsTree의 노드를 클릭 시 id값이 출력되게끔 함. 추후에 id값을 이용한 상호작용 가능한것으로 보임
		var seq = data.selected;
		
		console.log(data.selected);
		if (seq) {
			$.ajax({
				type: "get",
				url:"./detailRecode.do?seq="+seq,
				contentType : "application/json; charset=UTF-8",
				async: false,
				success:function(result){
					console.log("#######################result넘어오는값"+result);
// 					var detailRecodeJson = JSON.parse(result);
					console.log(typeof(result));
					console.log(result);
					$("#notelist").text("");
					$("#notelist").append("<div>");
					$("#notelist").append("<h2>"+"이름 :"+result.userdto[0].name+"</h2>");
					$("#notelist").append("<h2>"+"병원명 :"+result.hospitalinfodto[0].name+"</h2>");
					$("#notelist").append("<h2>"+"증상 :"+result.symptom+"</h2>");
					$("#notelist").append("<h2>"+"진료내용 :"+result.treatment+"</h2>");
					$("#notelist").append("<h2>"+"처방 :"+result.prescription+"</h2>");
					
					$("#notelist").append("</div>");
					
						
					
				},
				error: function(){
					alert("잘못된 요청");
				}
			})
		}
		
		
		
	});
});




</script>
<div class="py-4">
<h2>진료 내역</h2>
</div>
<div class="row">
	<div id="jstree" class="col-5">
	
	</div>
	<div id="notelist" class="col-7">
	
	</div>
	
</div>
</div>
<%@include file="/footer.jsp" %>