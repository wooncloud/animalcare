<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

<link href="${path}/css/home.css" rel="stylesheet">
	
<!-- 	<a class="btn btn-secondary" href="./payment/payList.do">결제내역</a> -->
<!-- 	<a class="btn btn-secondary" href="./survey/adminSurveyList.do">관리자설문리스트</a> -->
<!-- 	<a class="btn btn-secondary" href="./survey/userSurveyList.do">사용자설문리스트</a> -->
<!-- 	<a class="btn btn-secondary" href="./reservation/moveCalendar.do">달력</a> -->
<!-- 	<a class="btn btn-secondary" href="./reservation/userReserveList.do">사용자 예약목록</a> -->
<!-- 	<a class="btn btn-secondary" href="./reservation/hospitalStandReserveList.do">병원 예약목록</a> -->
<!-- 	<a class="btn btn-secondary" href="./reservation/insertReservation.do">신청</a> -->
<!-- 	<a class="btn btn-secondary" href="./answerboard/selAllBoard.do">문의</a> -->
<!-- 	<a class="btn btn-secondary" href="./pet/petList.do">내 애완동물보기</a> -->
	<c:choose>
		<c:when test="${sessionScope.member.usertype eq 'ROLE_USER'}">
			<div class="main-box my-5">
				<div class="row">
					<div class="col p-0">		
						<div class="card">
	  						<div class="card-body">
	  							<div class="fw-bold fs-3">병원찾기</div>
	   							 병원찾기 넣을 곳
	  						</div>
						</div>
					</div>
					<div class="col p-0">
						<div class="card">
	  						<div class="card-body">
	  							<div class="fw-bold fs-3">내 반려동물</div>
	   							 내 반려동물 넣을 곳
	  						</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col p-0">
						<div class="card">
	  						<div class="card-body">
	  							<div class="fw-bold fs-3">예약내역</div>
	   							 예약내역 넣을 곳
	  						</div>
						</div>
					</div>
					<div class="col p-0">
						<div class="card">
	  						<div class="card-body">
	  							<div class="fw-bold fs-3">건강수첩</div>
	   							건강수첩 넣을 곳
	  						</div>
						</div>
					</div>
					<div class="col p-0">
						<div class="card">
	  						<div class="card-body">
	  							<div class="fw-bold fs-3">진료내역</div>
	   							 진료내역 넣을 곳
	  						</div>
						</div>
					</div>
				</div>
			</div>
		</c:when>
		<c:when test="${sessionScope.member.usertype eq 'ROLE_OPER'}">
<!-- 			병원관계자 -->
			병원관계자 메인 화면
		</c:when>
		<c:when test="${sessionScope.member.usertype eq 'ROLE_ADMIN'}">
			<script type="text/javascript">
				location.href='${path}/admin/adminConsole.do';
			</script>
		</c:when>
		<c:otherwise>
			<div class="main-box my-5">
				<div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">
					<div class="carousel-inner" style="height: 600px;">
						<div class="background-01 carousel-item active" style="background-image: url(${path}/img/background01.jpg);">
							<div class="m-3">
								PETCARE로<br>
								아이들의<br>
								건강을<br>
								지켜주세요
							</div>
						</div>
						<div class="background-01 carousel-item" style="background-image: url(${path}/img/background03.jpg);">
							<div class="m-3">
								반려동물의<br>
								건강을<br>
								체크할 수<br>
								있습니다
							</div>
						</div>
						<div class="background-01 carousel-item" style="background-image: url(${path}/img/background02.jpg);">
							<div class="m-3 float-end">
								PETCARE와<br>
								함께하는 행복한 내일
							</div>
						</div>
					</div>
					<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>
		</c:otherwise>
	</c:choose>	
	
<%@ include file="/footer.jsp" %>

