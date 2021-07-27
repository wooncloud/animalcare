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
	    
	    console.log(qtitle.value);
	}
	
	function saveForm(){

		var frm = document.forms[0];

// 		var formQuestion = document.getElementById("formQuestion");
// 		var formContext = formQuestion.innerHTML;
// 		console.log(formContext);
// 		//이게 아닌거같고
		
		var title = document.getElementsByName("title")[0].value;
		var qnum = document.getElementsByName("qnum");
		var qtitle = document.getElementsByName("qtitle");


		//관리자가 작성한 설문 질문으로 값을 업데이트함
		updateQtitle();
		
		
		var sendData = {
				"title" : title,
				"question" : JSON.stringify(question)
				}
		
// 		console.log(JSON.stringfy(question));
		console.log("sendData"+JSON.stringify(sendData));
		
// 		if(title==""){
// 			alert("설문 제목을 입력해주세요");
// 			return false;
// 		}else if(count == 0){
// 			alert("설문 질문을 등록해주세요")
// 			return false;
// 		}else {
	
		var frm2 = document.forms[1];
		var startdate = document.getElementsByName("startdate").value;
		var enddate = document.getElementsByName("enddate").value;
// 		var seqDate = "";
		console.log("시작ㄴㄴㄴㄴㄴㄴㄴ"+startdate)
			$.ajax({
				type : "POST",
				url : "./insertSurveyForm.do",
				data : sendData,
				datatype : "JSON",
				success : function(data){
					console.log("성공");
					console.log(data);
					var seqData = data; 
					seqDate = "";
					seqDate += "<input type='hidden' name='seq' value='"+seqData+"'>";
					frm2.append(seqDate);
					
					if(typeof startdate != "undefined" && typeof enddate != "undefined"){
						updateDateForm();
						console.log("확ㅇ닌ㄴㄴㄴㄴㄴ")
					}else if(startdate==''){
			 			alert("설문 배포 시작 날짜를 입력해주세요");
					}else if(enddate==''){
						alert("설문 배포 끝 날짜를 입력해주세요");
					}else{
						alert("날짜를 입력하거나 취소버튼을 누르세요");
					}
				},
				error : function(msg2){
					
				}
			})
				
		}
// 	}
	
	function updateQtitle(){
		var qtitle = document.getElementsByName("qtitle");
		
		for(var i=0; i < qtitle.length; i++){
			console.log("제목"+qtitle[i].value);
			question[i].qtitle = qtitle[i].value;
		}
		console.log(JSON.stringify(question));
	}
	
	function resetForm(){
		 $("form>#formQuestion").empty();
		 count =0;
	}
	
	
	function updateDateForm(){
		var frm2 = document.forms[1];
		frm2.submit();
	}
	
	
</script>
    
<%@include file="/footer.jsp" %>