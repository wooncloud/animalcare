<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<button type="button" class="btn btn-outline-success" onclick="choiceQuestion()">선호도형 추가</button>
<button type="button" class="btn btn-outline-success" onclick="essayQuestion()">서술형 추가</button>
<button type="button" class="btn btn-success" id="" onclick="saveForm()" data-bs-toggle="modal" data-bs-target="#dateModal">임시저장</button>
<button type="button" class="btn btn-success" id="" onclick="resetForm()">초기화</button>

<div class="card my-1">
	<div class="card-body">
	    <form action="./survey/insertSurveyForm.do" method="post">
			설문 제목<br>
			<input type="text" class="form-control" name="title" required>
			<div id="formQuestion">
			</div>
	    </form>
    </div>
</div>
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
	var count = 0;
	var question = [];
	var qnum = document.getElementsByName("qnum");
	var qtitle = document.getElementsByName("qtitle");

	function choiceQuestion() {
	    html = "";
	    html += "<div class='card my-1'>";
	    html += "	<div class='card-body'>"
	    html += "	제목 <input type='text' class='form-control' name='qtitle' required='required' value=''><br>";
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
	    $("form>#formQuestion").append(html);
	    
	    count ++;
	    question.push({
	    	"qnum" : count,
			"qtitle" : "",
			"choiceAnswer" : ""
	    })
	    
	}
	
	function essayQuestion(){
	    html = "";
	    html += "<div class='card my-1'>";
	    html += "<div class='card-body'>"
	    html += "	제목 <input type='text' class='form-control' name='qtitle'><br>";
	    html += "	답변 <textarea rows='5' class='form-control' name='essayAnswer'></textarea>";
	    html += "	</div>";
	    html += "</div>";
	    $("form>#formQuestion").append(html);
	    
	    count ++;
	    question.push({
	    	"qnum" : count,
			"qtitle" : "",
			"essayAnswer" : ""
	    })
	    
	}
	
	function saveForm(){

		var frm = document.forms[0];
		var title = document.getElementsByName("title")[0].value;
		var qnum = document.getElementsByName("qnum");
		var qtitle = document.getElementsByName("qtitle");

		//관리자가 작성한 설문 질문으로 값을 업데이트함
		updateQtitle();
		
		var sendData = {
			"title" : title,
			"question" : JSON.stringify(question)
		}
		console.log("sendData"+JSON.stringify(sendData));
		
		//수정 필요
// 		if(title==""){
// 			alert("설문 제목을 입력해주세요");
// 			return false; //이거 왜 안될까
// 		}else if(count == 0){
// 			alert("설문 질문을 등록해주세요")
// 			return false;
// 		}else {
			
// 		}
	
		var frm2 = document.forms[1];
		var startdate = document.getElementsByName("startdate").value;
		var enddate = document.getElementsByName("enddate").value;

		$.ajax({
			type : "POST",
			url : "./insertSurveyForm.do",
			data : sendData,
			datatype : "JSON",
			success : function(data){
				console.log("성공");
// 				console.log(data);
				var seqData = data;
				
				var emt = document.createElement("div");
				emt.innerHTML = "<input type='hidden' name='seq' value='"+seqData+"'>";
				frm2.append(emt);
			},
			error : function(msg2){
				
			}
		})
				
	}
	
	function updateQtitle(){
		var qtitle = document.getElementsByName("qtitle");
		
		for(var i=0; i < qtitle.length; i++){
			console.log("제목"+qtitle[i].value);
			question[i].qtitle = qtitle[i].value;
		}
		console.log(JSON.stringify(question));
	}
	
	//폼 초기화
	function resetForm(){
		 $("form>#formQuestion").empty();
		 count =0;
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
	function updateDateForm(){
		var frm2 = document.forms[1];
		frm2.submit();
	}
	
	function compareStartDate(){
		var seq = document.getElementsByName("seq")[0].value;
		var startdate = document.getElementsByName("startdate")[0].value;
		console.log(startdate+"startdate");
		console.log(seq+"seq");
		
		$.ajax({
			url:"./compareStartDate.do?startdate="+startdate+"&seq="+seq,
			type:"GET",
// 			data: "startdate="+startdate+"&seq="+seq,
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
		var seq = document.getElementsByName("seq")[0].value;
		var startdate = document.getElementsByName("startdate")[0].value;
		var enddate = document.getElementsByName("enddate")[0].value;
		console.log(enddate+"enddate");
		console.log(seq+"seq");
		$.ajax({
			url:"./compareEndDate.do?startdate="+startdate+"&enddate="+enddate+"&seq="+seq,
			type:"GET",
// 			data: "enddate="+enddate+"&seq="+seq,
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
	
</script>
    
<%@include file="/footer.jsp" %>