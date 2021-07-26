<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<button type="button" class="btn btn-outline-success" onclick="choiceQuestion()">선호도형 추가</button>
<button type="button" class="btn btn-outline-success" onclick="essayQuestion()">서술형 추가</button>
<div class="card my-1">
	<div class="card-body">
	    <form action="./survey/insertSurveyForm.do" method="post" name="question">
	    	<div class="row text-center justify-content-end">
	    		<div class="col-2 text-center">
					<input type="button" class="btn btn-success" id="" data-bs-toggle="modal" data-bs-target="#dateModal" value="임시저장">
				</div>
				<div class="col-2 text-center">
					<button type="button" class="btn btn-success" id="" onclick="resetForm()">초기화</button>
				</div>
				<br>
			</div>
			<br>
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
				<input type="date" name="startdate"> ~ <input type="date" name="enddate">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" onclick="saveForm()">Save</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function choiceQuestion() {
	    html = "";
	    html += "<div class='card my-1'>";
	    html += "	<div class='card-body'>"
	    html += "	문항번호 <input type='text' class='form-control' id='' name='qnum' required='required'><br>";
	    html += "	제목 <input type='text' class='form-control' id='' name='qtitle' required='required'><br>";
	    html += "	불만족 ";
	    html += "		<input type='radio' id='' name='choiceAnswer' value='1'>&nbsp;";
	    html += "		<input type='radio' id='' name='choiceAnswer' value='2'>&nbsp;";
	    html += "		<input type='radio' id='' name='choiceAnswer' value='3'>&nbsp;";
	    html += "		<input type='radio' id='' name='choiceAnswer' value='4'>&nbsp;";
	    html += "		<input type='radio' id='' name='choiceAnswer' value='5'>";
	    html += "	만족<br>";
	    html += "		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span>1&nbsp;&nbsp;2&nbsp;&nbsp;3&nbsp;&nbsp;4&nbsp;&nbsp;5</span>";
	    html += "	</div>";
	    html += "</div>";
	    $("form>#formQuestion").append(html);
	}
	function essayQuestion(){
	    html = "";
	    html += "<div class='card my-1'>";
	    html += "<div class='card-body'>"
	    html += "	문항번호 <input type='text' class='form-control' id='' name='qnum'><br>";
	    html += "	제목 <input type='text' class='form-control' id='' name='qtitle'><br>";
	    html += "	답변 <textarea rows='5' class='form-control'></textarea>";
	    html += "	</div>";
	    html += "</div>";
	    $("form>#formQuestion").append(html);
	}
	function saveForm(){
// 		var 
		//여기서 다 확ㅇ니해줘햐임라ㅓㄴ;ㅇ러 ;
	}
	function resetForm(){
		 $("form>#formQuestion").empty();
	}
	
	
</script>
    
<%@include file="/footer.jsp" %>