<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<script src="https://d3js.org/d3.v5.min.js"></script> 
<script src=".././js/billboard.js"></script> 
<link rel="stylesheet" href=".././css/billboard.css">

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

<div>
	<ul class="mt-3 pagination justify-content-center">
		<c:if test="${page.startPage > page.countPage}">
			<li class="page-item">
				<a class="page-link" href="./surveyResultList.do?page=${page.startPage-1}" aria-label="Previous">
					<span aria-hidden="true"><i class="fas fa-chevron-left"></i></span>
				</a>
			</li>
		</c:if>
		<c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
			<li class="page-item ${num eq page.page ? 'active' : ''}">
				<a class="page-link" href="./surveyResultList.do?page=${num}">${num}</a>
			</li>
		</c:forEach>
		<c:if test="${page.endPage < page.totalPage}">
			<li class="page-item">
				<a class="page-link" href="./surveyResultList.do?page=${page.endPage + 1}" aria-label="Next"> 
					<span aria-hidden="true"><i class="fas fa-chevron-right"></i></span>
				</a>
			</li>
		</c:if>
	</ul>
</div>

<script type="text/javascript">

</script>
<%@include file="/footer.jsp" %>