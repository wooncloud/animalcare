<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<button type="button" class="btn btn-success" id="" onclick="submitForm()">제출</button>

<div class="card my-1">
	<div class="card-body">
	    <form action="./survey/userSurveySubmit.do" method="post">
			설문 제목<br>
			
			<div id="formQuestion">
			
			</div>
	    </form>
    </div>
</div>

<script type="text/javascript">
window.onload = function(){

	
}
function submitForm(){
	
}
</script>
<%@include file="/footer.jsp" %>