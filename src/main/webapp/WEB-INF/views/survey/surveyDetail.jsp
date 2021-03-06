<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<%-- ${detail} --%>
<div class="card my-1">
	<div class="card-body">
		설문 제목<br>
		<input type="text" class="form-control" name="title" value="${detail.title}" disabled="disabled">
		
		<div id="formQuestion">
		</div>

		<c:if test="${detail.startdate != null}">
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
		</c:if>
    </div>
</div>

<c:if test="${sessionScope.member.usertype eq 'ROLE_USER'}">
	<button type="button" class="btn btn-success" id="" onclick="submitForm()">제출</button>
</c:if>
	
<c:if test="${sessionScope.member.usertype eq 'ROLE_ADMIN'}">
	<c:if test="${detail.surveyflag eq 'N'}">
		<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#dateModal">응답기간 설정</button>
	</c:if>
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
					<input type="date" name="startdate" onchange="compareStartDate()"> ~ <input type="date" name="enddate" onchange="compareEndDate()">
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" onclick="checkDateForm()">Save</button>
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

//배포날짜 설정 유효값
function checkDateForm(){
	var startdate = document.getElementsByName("startdate").value;
	var enddate = document.getElementsByName("enddate").value;
	
	if(typeof startdate != "" && typeof enddate != ""){
		updateDateForm();
		console.log("날짜 업데이트 완료");
	}else if(startdate==""){
			alert("설문 시작일을 선택해주세요");
	}else if(enddate==""){
		alert("설문 마감일을 선택해주세요");
	}else{
		alert("날짜를 입력하거나 취소버튼을 누르세요");
	}
}

//배포날짜 설정 submit
var seq = ${detail.seq};	
function updateDateForm(){
	var frm = document.forms[0];
	
	var emt = document.createElement("div");
	emt.innerHTML = "<input type='hidden' name='seq' value='"+seq+"'>";
	
	frm.append(emt);
	frm.submit();
}

function compareStartDate(){
// 	var seq = document.getElementsByName("seq")[0].value;
	var startdate = document.getElementsByName("startdate")[0].value;
	console.log(startdate+"startdate");
	console.log(seq+"seq");
	
	$.ajax({
		url:"./compareStartDate.do?startdate="+startdate+"&seq="+seq,
		type:"GET",
//			data: "startdate="+startdate+"&seq="+seq,
		success:function(msg){
			if(msg=="true"){
				alert("현재 날짜보다 이후로 설정 / 이미 해당날짜에 배포된 설문이 존재합니다 ");
			}
			console.log(msg);
		},
		error:function(){
			alert("에러 발생")
		}
	})
}

function compareEndDate(){
// 	var seq = document.getElementsByName("seq")[0].value;
	var startdate = document.getElementsByName("startdate")[0].value;
	var enddate = document.getElementsByName("enddate")[0].value;
	console.log(enddate+"enddate");
	console.log(seq+"seq");
	$.ajax({
		url:"./compareEndDate.do?startdate="+startdate+"&enddate="+enddate+"&seq="+seq,
		type:"GET",
//			data: "enddate="+enddate+"&seq="+seq,
		success:function(msg){
			if(msg=="true"){
				alert("응답기간 시작일 보다 이후로 설정해주세요");
			}
			console.log(msg);
		},
		error:function(){
			alert("에러 발생")
		}
	})
	
}



var answer = [];

//사용자
function submitForm(){
	var list = ${detail.question}
	
	var title = document.getElementsByName("title")[0];
	
	var choiceAnswer = document.getElementsByName("choiceAnswer");
	var essayAnswer = document.getElementsByName("essayAnswer");
	
	var choiceAnswerVal;
	var essayAnswerVal;
	var count = 0 ;
	var total = 5;
	
	console.log(choiceAnswer.length+"==========================");
	for (var i=0; i<choiceAnswer.length; i++) {
        if (choiceAnswer[i].checked == true) {
        	choiceAnswerVal = choiceAnswer[i].value;
        	console.log(choiceAnswerVal);
        } else if(choiceAnswer[i].checked == false){
        	count++;
        }
    }
	
	//유효값처리 수정필요
  	if(count == total){
  		alert("값을 선택해주세요");
  		return false;
  	}
    console.log(count+"count");
	
	for (var i=0; i<essayAnswer.length; i++) {
		essayAnswerVal = essayAnswer[i].value;
       	console.log(essayAnswerVal);
    }
	
	for (var i = 0; i < list.length; i++) {
		var keys = Object.keys(list[i]);
		var values = Object.values(list[i]);
		
		if(keys[2]=="choiceAnswer"){
			answer.push({
				"qnum" : values[0],
				"qtitle" : values[1],
				"choiceAnswer" : choiceAnswerVal
			})
		}else if(keys[2]=="essayAnswer"){
			answer.push({
				"qnum" : values[0],
				"qtitle" : values[1],
				"essayAnswer" : essayAnswerVal
			})
		}

	}
	
	var sendData = {
		"survey_seq" : seq,
		"title" : title.value,
		"answer" : JSON.stringify(answer)
	}
	console.log("answer"+JSON.stringify(answer));
	console.log("sendData"+JSON.stringify(sendData));
	
	$.ajax({
		type : "POST",
		url : "./userSurveySubmit.do",
		data : sendData,
		datatype : "JSON",
		success : function(data){
			console.log("성공");
			console.log(data);
			if(data == "true"){
				alert("제출이 완료되었습니다. 설문에 응해주셔서 감사합니다.");
				/////버튼 설문완료로 disabled 하면 될듯/////////////////////////////////////////////////
				location.href="./userSurveyList.do";//사용자 리스트로 이동
			}else{
				alert("이미 작성한 설문입니다. 설문 리스트로 돌아갑니다");
				location.href="./userSurveyList.do";
			}
		},
		error : function(msg2){
			alert("에러가 발생했습니다. 관리자에게 문의바랍니다.");
		}
	})

	
}

</script>
<%@include file="/footer.jsp" %>