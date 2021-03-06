<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
${userSurveyList}
<nav>
	<div class="nav nav-tabs" id="nav-tab" role="tablist">
		<button class="nav-link active" id="respondable-tab" data-bs-toggle="tab" data-bs-target="#respondable" type="button" role="tab" aria-controls="respondable" aria-selected="true">응답가능</button>
		<button class="nav-link" id="responsed-tab" data-bs-toggle="tab" data-bs-target="#responsed" type="button" role="tab" aria-controls="responsed" aria-selected="false">응답완료</button>
	</div>
</nav>
<div class="card my-1 text-center">
	<div class="card-body">
		<div class="row">
			<div class="col-2">
				<h5 class="card-title">NO</h5>
			</div>
			<div class="col-6">
				<h5 class="card-title">설문제목</h5>
			</div>
			<div class="col-4">
				<h5 class="card-title">응답기간</h5>
			</div>
		</div>
	</div>
</div>
<div class="tab-content" id="nav-tabContent">
	<div class="tab-pane fade show active" id="respondable" role="tabpanel" aria-labelledby="respondable-tab">
<!-- 		배포상태가 Y이고 스타트 데이트가 현재날짜가 비트윈 스타트데이트 엔드데이트 , 사용자가 답변리스트에 없으면.... -->
		<c:forEach var="doSurvey" items="${userDoSurveyList}" varStatus="vs">
			<div class="card my-1 text-center">
				<div class="card-body">
					<div class="row">
						<div class="col-2">
							<p class="card-text">${fn:length(userDoSurveyList)-vs.count+1}</p>
						</div>
						<div class="col-6">
							<p class="card-text"><a href="./surveyDetail.do?seq=${doSurvey.seq}">${doSurvey.title}</a></p>
						</div>
						<div class="col-4">
							<p class="card-text">${doSurvey.startdate} ~ ${doSurvey.enddate}</p>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<c:if test="${userDoSurveyList != null}">
			<div>
				<ul class="mt-3 pagination justify-content-center">
					<c:if test="${page.startPage > page.countPage}">
						<li class="page-item">
							<a class="page-link" href="./userSurveyList.do?page=${page.startPage-1}" aria-label="Previous">
								<span aria-hidden="true"><i class="fas fa-chevron-left"></i></span>
							</a>
						</li>
					</c:if>
					<c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
						<li class="page-item ${num eq page.page ? 'active' : ''}">
							<a class="page-link" href="./userSurveyList.do?page=${num}">${num}</a>
						</li>
					</c:forEach>
					<c:if test="${page.endPage < page.totalPage}">
						<li class="page-item">
							<a class="page-link" href="./userSurveyList.do?page=${page.endPage + 1}" aria-label="Next"> 
								<span aria-hidden="true"><i class="fas fa-chevron-right"></i></span>
							</a>
						</li>
					</c:if>
				</ul>
			</div>
		</c:if>
	</div>
	
	<!-- 		배포상태가 Y이고 답변리스트에 있으면.... -->
	<!-- 	 배포상태가 Y이지만, 응답가능일이 현재날짜보다 전에있으면 => 응답불가 -->
	<div class="tab-pane fade" id="responsed" role="tabpanel" aria-labelledby="responsed-tab">
		<c:forEach var="endSurvey" items="${userEndSurveyList}" varStatus="vs">
			<div class="card my-1 text-center">
				<div class="card-body">
					<div class="row">
						<div class="col-2">
							<p class="card-text">${fn:length(userEndSurveyList)-vs.count+1}</p>
						</div>
						<div class="col-6">
							<p class="card-text">${endSurvey.title}</p>
						</div>
						<div class="col-4">
							<p class="card-text">${endSurvey.startdate} ~ ${endSurvey.enddate}</p>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<c:if test="${userEndSurveyList != null}">
			<div>
				<ul class="mt-3 pagination justify-content-center">
					<c:if test="${page2.startPage > page2.countPage}">
						<li class="page-item">
							<a class="page-link" href="./userSurveyList.do?page=${page2.startPage-1}" aria-label="Previous">
								<span aria-hidden="true"><i class="fas fa-chevron-left"></i></span>
							</a>
						</li>
					</c:if>
					<c:forEach var="num" begin="${page2.startPage}" end="${page2.endPage}">
						<li class="page-item ${num eq page2.page ? 'active' : ''}">
							<a class="page-link" href="./userSurveyList.do?page=${num}">${num}</a>
						</li>
					</c:forEach>
					<c:if test="${page2.endPage < page2.totalPage}">
						<li class="page-item">
							<a class="page-link" href="./userSurveyList.do?page=${page2.endPage + 1}" aria-label="Next"> 
								<span aria-hidden="true"><i class="fas fa-chevron-right"></i></span>
							</a>
						</li>
					</c:if>
				</ul>
			</div>
		</c:if>
	</div>
</div>

<button class="btn btn-primary" onclick="javascript:history.back(-1);">목록</button>











<script type="text/javascript">

</script>

<%@include file="/footer.jsp" %>