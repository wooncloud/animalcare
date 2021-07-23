<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<%
	
%>
<div id="container">
<script type="text/javascript">
function tree(name){
	
	var jsonData = $.ajax({
		url : "noteList.do",
		method : "get",
		data:{"name":name},
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
		console.log(data.selected); // jsTree의 노드를 클릭 시 id값이 출력되게끔 함. 추후에 id값을 이용한 상호작용 가능한것으로 보임
		var name = data.node.original.original.name;
		console.log(name);
		if (data.selected.length>2) {
			$.ajax({
				type: "post",
				url:"selDateList.do",
				data:{"date":data, "name":name},
				dataType : "text",
				contentType : "application/json; charset=UTF-8",
				async: false
			})
		}
		
		
		
	});
});




</script>
<div class="petTab">
<div class="row">
<c:forEach var="d" items="${pList}" varStatus="vs">
	<div class="col-2">
		<button class="btn btn-info" id="bt${vs.count}" onclick="tree('${d.name}')">${d.name}</button>
	</div>
</c:forEach>
</div>
</div>
<div id="jstree">
dd
</div>
<div id="notelist">
ddd
</div>
</div>
<%@include file="/footer.jsp" %>