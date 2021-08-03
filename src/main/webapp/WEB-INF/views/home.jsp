<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
	
	<a class="btn btn-secondary" href="./payment/payList.do">결제내역</a>
	<a class="btn btn-secondary" href="./survey/adminSurveyList.do">관리자설문리스트</a>
	<a class="btn btn-secondary" href="./survey/userSurveyList.do">사용자설문리스트</a>
	<a class="btn btn-secondary" href="./reservation/moveCalendar.do">달력</a>
	<a class="btn btn-secondary" href="./reservation/userReserveList.do">사용자 예약목록</a>
	<a class="btn btn-secondary" href="./reservation/hospitalStandReserveList.do">병원 예약목록</a>
	<a class="btn btn-secondary" href="./reservation/insertReservation.do">신청</a>
	<a class="btn btn-secondary" href="./answerboard/selAllBoard.do">문의</a>
	<a class="btn btn-secondary" href="./pet/petList.do">내 애완동물보기</a>
	<c:choose>
		<c:when test="${sessionScope.member.usertype eq 'ROLE_USER'}">
			사용자
		</c:when>
		<c:when test="${sessionScope.member.usertype eq 'ROLE_OPER'}">
			병원관계자
		</c:when>
		<c:when test="${sessionScope.member.usertype eq 'ROLE_ADMIN'}">
			<script type="text/javascript">
				location.href='${path}/admin/adminConsole.do';
			</script>
		</c:when>
		<c:otherwise>
			동물병원에 오신것을 환영합니다.
		</c:otherwise>
	</c:choose>
	
<%@ include file="/footer.jsp" %>

