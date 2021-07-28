<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<%-- ${detail} --%>
<div class="card my-1">
	<div class="card-body">
		설문 제목<br>
		<input type="text" class="form-control" name="title" value="${detail.title}" disabled="disabled">
		<div id="formQuestion">
		</div>
		
		<div class="row my-2 text-center">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">응답 기간</h6>
			</div>
		    <div class="col-3">
				<input type="text" class="form-control" name="startdate" value="${detail.startdate}" disabled="disabled">
			</div>
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted"> ~ </h6>
			</div>
		    <div class="col-3">
				<input type="text" class="form-control" name="enddate" value="${detail.enddate}" disabled="disabled">
			</div>
		</div>
		
    </div>
</div>
<c:if test="${detail.surveyflag eq 'N'}">
	<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#dateModal">배포</button>
</c:if>
<button class="btn btn-primary" onclick="javascript:history.back(-1);">목록</button>

<div class="modal fade" id="dateModal" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">설문 응답 기간</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form action="./updateDateForm.do" method="post">
					<input type="date" name="startdate"> ~ <input type="date" name="enddate">
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" onclick="updateDateForm()">Save</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
window.onload = function(){

	var list = ${detail.question}
// 	console.log(list);
	console.log(list[0]);
	console.log("길이"+list.length);
	
// 	console.log(keys[2]+"키ㅣㅣㅣㅣㅣ");
// 	console.log(values[1]);
	for (var i = 0; i < list.length; i++) {
		
	var keys = Object.keys(list[i]);
	var values = Object.values(list[i]);
		
		if(keys[2]=="choiceAnswer"){
			console.log("객관식");
			
			html = "";
		    html += "<div class='card my-1'>";
		    html += "	<div class='card-body'>"
		    html += "	문항번호 <input type='text' class='form-control' name='qnum' required='required' value='"+values[0]+"' disabled='disabled'><br>";
		    html += "	제목 <input type='text' class='form-control' name='qtitle' required='required' value='"+values[1]+"' disabled='disabled'><br>";
		    html += "	불만족 ";
		    html += "		<input type='radio' name='choiceAnswer' value='1'>&nbsp;";
		    html += "		<input type='radio' name='choiceAnswer' value='2'>&nbsp;";
		    html += "		<input type='radio' name='choiceAnswer' value='3'>&nbsp;";
		    html += "		<input type='radio' name='choiceAnswer' value='4'>&nbsp;";
		    html += "		<input type='radio' name='choiceAnswer' value='5'>";
		    html += "	만족<br>";
		    html += "		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span>1&nbsp;&nbsp;2&nbsp;&nbsp;3&nbsp;&nbsp;4&nbsp;&nbsp;5</span>";
		    html += "	</div>";
		    html += "</div>";
// 		    $("form>#formQuestion").append(html);
		    $("#formQuestion").append(html);
		    
		}else if(keys[2]=="essayAnswer"){
			console.log("주관식");
			
			html = "";
		    html += "<div class='card my-1'>";
		    html += "<div class='card-body'>"
		    html += "	문항번호 <input type='text' class='form-control' name='qnum' required='required' value='"+values[0]+"' disabled='disabled'><br>";
		    html += "	제목 <input type='text' class='form-control' name='qtitle' value='"+values[1]+"' disabled='disabled'><br>";
		    html += "	답변 <textarea rows='5' class='form-control' name='essayAnswer'></textarea>";
		    html += "	</div>";
		    html += "</div>";
		    
// 		    $("form>#formQuestion").append(html);
		    $("#formQuestion").append(html);
		}
	}

}

function updateDateForm(){
	var seq = ${detail.seq};
	var frm = document.forms[0];
	
	var emt = document.createElement("div");
	emt.innerHTML = "<input type='hidden' name='seq' value='"+seq+"'>";
	frm.append(emt);
	frm.submit();
}

</script>
<%@include file="/footer.jsp" %>