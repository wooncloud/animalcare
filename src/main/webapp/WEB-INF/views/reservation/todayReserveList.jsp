<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
<h1>오늘의 예약</h1>
<c:forEach items="${todayLists}" var="li" varStatus="vs">
${li}<br>
</c:forEach>
<%@ include file="/footer.jsp" %>