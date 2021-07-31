<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<link href="${path}/css/healthNote.css" rel="stylesheet">
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
		console.log(data); // jsTree의 노드를 클릭 시 id값이 출력되게끔 함. 추후에 id값을 이용한 상호작용 가능한것으로 보임
		var name = data.node.original.name;
		var seldate= data.selected;
		console.log(data.selected);
		if (name) {
			$.ajax({
				type: "get",
				url:"./selDateList.do?name="+name+"&seldate="+seldate,
				dataType : "text",
				contentType : "application/json; charset=UTF-8",
				async: false,
				success:function(result){
					console.log("#######################result넘어오는값"+result);
					var noteListJson = JSON.parse(result);
					console.log(noteListJson);
					console.log(seldate);
					$("#notelist").text("");
					$("#notelist").append("<div><h2>"+seldate+"</h2></div>");
					$("#notelist").append("<a id='insertNote'><span><img src='${path}/img/plus.png'></span></a>");
					
					for (var i = 0; i < noteListJson.length; i++) {
					$("#notelist").append("<div onclick='modal("+noteListJson[i].seq+")'>"+noteListJson[i].title+"</div>");
						
					}
				},
// 				error: function(){
// 					alert("잘못된 요청");
// 				}
			})
		}
		
		
		
	});
});

function modal(seq){
	console.log(seq);
	
}



</script>
<div class="py-4">
<h2>건강수첩</h2>
</div>
<div class="petTab">
<div class="row">
<c:forEach var="d" items="${pList}" varStatus="vs">
	<div class="col-2">
		<button class="btn btn-info" id="bt${vs.count}" onclick="tree('${d.name}')">${d.name}</button>
	</div>
</c:forEach>
</div>
</div>
<div>
	<div id="jstree" class="d-inline-flex p-2 bd-highlight">
	dd
	</div>
	<div id="notelist">
	ddd
	</div>
	
</div>
<a id="insertNote" type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop" >
  <img src='${path}/img/plus.png'>
</a>
</div>
<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
  <div class="container-fluid">
    <div class="row gy-5">
      <div class="col-md-4">이름</div>
      <div class="col-md-8">.col-md-4 .ms-auto</div>
    </div>
    <div class="row gy-5">
      <div class="col-md-4">등록일</div>
      <div class="col-md-8">.col-md-4 .ms-auto</div>
    </div>
    <div class="row">
      <div class="col-md-4">제목</div>
      <div class="col-md-8">.col-md-4 .ms-auto</div>
    </div>
    <div class="row">
      <div class="col-md-4">내용</div>
      <div class="col-md-8">.col-md-4 .ms-auto</div>
    </div>
    <div class="row">
      <div class="col-md-4 ms-auto">.col-md-3 .ms-auto</div>
      <div class="col-md-8 ms-auto">.col-md-2 .ms-auto</div>
    </div>
  </div>
</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Understood</button>
      </div>
    </div>
  </div>
</div>
<%@include file="/footer.jsp" %>