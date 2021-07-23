<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>

	<h1>동물병원</h1>
	${sessionScope.member }
	
	<a class="btn btn-secondary" href="./payment/payList.do">결제내역</a>
	<a class="btn btn-secondary" href="./survey/adminSurveyList.do">설문리스트</a>
	<a class="btn btn-secondary" href="./reservation/moveCalendar.do">달력</a>
	<a class="btn btn-secondary" href="./reservation/userReserveList.do">사용자 예약목록</a>
	<a class="btn btn-secondary" href="./reservation/hospitalReserveList.do">병원 예약목록</a>
	<a class="btn btn-secondary" href="./reservation/insertReservation.do">신청</a>
<%@ include file="/footer.jsp" %>

