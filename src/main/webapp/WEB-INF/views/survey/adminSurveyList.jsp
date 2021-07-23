<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
${adminSurveyList}
<div class="card my-1">
	<div class="card-body">
		<div class="row justify-content-center">
		    <div class="col-1">
				<h5 class="card-title">
				  <input class="form-check-input" type="checkbox" value="" id="">
				</h5>
		    </div>
		    <div class="col-1">
				<h5 class="card-title"> NO</h5>
		    </div>
		    <div class="col-3">
				<h5 class="card-title"> 설문제목</h5>
		    </div>
		    <div class="col-3">
				<h5 class="card-title"> 응답기간</h5>
		    </div>
		    <div class="col-2">
		    	<h5 class="card-title"> 등록일</h5>
		    </div>
		    <div class="col-1">
		    	<h5 class="card-title"> 배포상태</h5>
		    </div>
		    <div class="col-1">
		    	<h5 class="card-title"> 삭제여부</h5>
		    </div>
		</div>
	</div>
</div>

<c:forEach var="survey" items="${adminSurveyList}" varStatus="vs">
	<div class="card my-1">
		<div class="card-body">
			<div class="row">
				<div class="col-1">
					<p class="card-text">
					  <input class="form-check-input" type="checkbox" value="" id="">
					</p>
			    </div>
			    <div class="col-1">
					<p class="card-text">${fn:length(adminSurveyList)-vs.count+1}</p>
			    </div>
			    <div class="col-3">
					<p class="card-text"><a href="./surveyDetail.do?seq=${survey.seq}">${survey.title}</a></p>
			    </div>
			    <div class="col-3">
  					<p class="card-text"></p>
			    </div>
			    <div class="col-2">
			    	<p class="card-text">${survey.regdate}</p>
			    </div>
			    <div class="col-1">
			    	<p class="card-text"></p>
			    </div>
			    <div class="col-1">
			    	<p class="card-text">${survey.delflag}</p>
			    </div>
			</div>
		</div>
	</div>
</c:forEach>


<button type="button" class="btn btn-outline-danger" onclick="javascript:location.href='./surveyForm.do';">작성</button>
<button type="button" class="btn btn-danger" onclick="deleteSurvey()">삭제</button>
<script type="text/javascript">
function createSurvey(){
// 	location.href="./survey/surveyForm.do"
}
function deleteSurvey(){
	var 
}
</script>

<%@include file="/footer.jsp" %>