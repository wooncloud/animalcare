<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
	
	<a class="btn btn-secondary" href="./payment/payList.do">결제내역</a>
	<a class="btn btn-secondary" href="./survey/adminSurveyList.do">설문리스트</a>
	<a class="btn btn-secondary" href="./reservation/moveCalendar.do">달력</a>
	<a class="btn btn-secondary" href="./reservation/userReserveList.do">사용자 예약목록</a>
	<a class="btn btn-secondary" href="./reservation/hospitalReserveList.do">병원 예약목록</a>
	<a class="btn btn-secondary" href="./reservation/insertReservation.do">신청</a>

	<c:choose>
		<c:when test="${sessionScope.member.usertype eq 'ROLE_USER'}">
			사용자
		</c:when>
		<c:when test="${sessionScope.member.usertype eq 'ROLE_OPER'}">
			병원관계자
		</c:when>
		<c:otherwise>
			<h1>PET CARE에 오신것을 환영합니다.</h1>
			
		</c:otherwise>
	</c:choose>
	
<%@ include file="/footer.jsp" %>

