<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<button type="button" class="btn btn-outline-success" onclick="choiceQuestion()">선호도형 추가</button>
<button type="button" class="btn btn-outline-success" onclick="essayQuestion()">서술형 추가</button>
<div class="card my-1">
	<div class="card-body">
	    <form>
	        <input type="submit" class="btn btn-success" id="" name="" value="설문저장">
	    </form>
    </div>
</div>

<script type="text/javascript">
	function choiceQuestion() {
	    html = "";
	    html += "<div class='card my-1'>";
	    html += "	<div class='card-body'>"
	    html += "	문항번호 <input type='text' class='form-control' id='' name='qnum'><br>";
	    html += "	제목 <input type='text' class='form-control' id='' name='title'><br>";
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
	    $("form").append(html);
	}
	function essayQuestion(){
	    html = "";
	    html += "<div class='card my-1'>";
	    html += "<div class='card-body'>"
	    html += "	문항번호 <input type='text' class='form-control' id='' name='qnum'><br>";
	    html += "	제목 <input type='text' class='form-control' id='' name='title'><br>";
	    html += "	답변 <textarea rows='10' cols='30' class='form-control'></textarea>";
	    html += "	</div>";
	    html += "</div>";
	    $("form").append(html);
	}
</script>
    
<%@include file="/footer.jsp" %>