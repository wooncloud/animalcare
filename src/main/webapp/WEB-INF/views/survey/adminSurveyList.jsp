<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<%-- ${adminSurveyList} --%>

<form action="./delflagForm.do" method="post" id="frm" name="frm" onsubmit="return chkbox()"><!-- document.frm 쓰면 됨 form만! []이렇게 말고 -->
<div class="card my-1">
	<div class="card-body">
		<div class="row justify-content-center">
		    <div class="col-1">
				<h5 class="card-title">
				  <input class="form-check-input" type="checkbox" onclick="deleteSurvey(this.checked)">
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
					  <input class="form-check-input" type="checkbox" value="${survey.seq}" name="chkVal">
					</p>
			    </div>
			    <div class="col-1">
					<p class="card-text">${fn:length(adminSurveyList)-vs.count+1}</p>
			    </div>
			    <div class="col-3">
					<p class="card-text"><a href="./surveyDetail.do?seq=${survey.seq}">${survey.title}</a></p>
			    </div>
			    <div class="col-3">
			    	<c:if test="${survey.startdate eq null}">
  					<p class="card-text"> - </p>
			    	</c:if>
			    	<c:if test="${survey.startdate != null}">
  					<p class="card-text">${survey.startdate} ~ ${survey.enddate}</p>
  					</c:if>
			    </div>
			    <div class="col-2">
			    	<p class="card-text">${survey.regdate}</p>
			    </div>
			    <div class="col-1">
			    	<p class="card-text">${survey.surveyflag}</p>
			    </div>
			    <div class="col-1">
			    	<p class="card-text">${survey.delflag}</p>
			    </div>
			</div>
		</div>
	</div>
</c:forEach>
<button type="button" class="btn btn-outline-danger" onclick="javascript:location.href='./surveyForm.do';">작성</button>
<input type="submit" class="btn btn-danger" value="삭제">
</form>

<script type="text/javascript">
function chkbox(){
	var chks = document.getElementsByName("chkVal");
	var cnt = 0;
	for(let c of chks){
		if(c.checked){
			cnt++;
		}
	}
	if(cnt==0){
		alert("한개 이상의 글을 선택해주세요");
		return false;
	}
}
function deleteSurvey(val){
	var chks = document.getElementsByName("chkVal");
	
	for(let c of chks){
		c.checked = val;
	}
		
}
</script>

<%@include file="/footer.jsp" %>