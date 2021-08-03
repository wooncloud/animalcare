<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<script src="https://d3js.org/d3.v5.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/billboard.js/3.1.3/billboard.min.js"></script> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/billboard.js/3.1.3/billboard.min.css">

${surveyResultList}

<div class="card my-1">
	<div class="card-body">
		<div class="row text-center">
		    <div class="col-2">
				<h5 class="card-title"> NO</h5>
		    </div>
		    <div class="col-6">
				<h5 class="card-title"> 설문제목</h5>
		    </div>
		    <div class="col-4">
				<h5 class="card-title"> 응답기간</h5>
		    </div>
		</div>
	</div>
</div>
<c:forEach var="survey" items="${surveyResultList}" varStatus="vs">
	<div class="card my-1">
		<div class="card-body">
			<div class="row text-center">
			    <div class="col-2">
					<p class="card-text">${fn:length(surveyResultList)-vs.count+1}</p>
			    </div>
			    <div class="col-6">
					<p class="card-text"><a href="./surveyResultDetail.do?seq=${survey.seq}">${survey.title}</a></p>
			    </div>
			    <div class="col-4">
  					<p class="card-text">${survey.startdate} ~ ${survey.enddate}</p>
			    </div>
			</div>
		</div>
	</div>
</c:forEach>

<button class="btn btn-primary" onclick="javascript:history.back(-1);">목록</button>


<script type="text/javascript">

</script>
<%@include file="/footer.jsp" %>