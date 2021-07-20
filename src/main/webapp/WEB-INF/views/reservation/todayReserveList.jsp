<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 예약 보기 </title>
</head>
<body>
<h1>오늘의 예약</h1>
<c:forEach items="${todayLists}" var="li" varStatus="vs">
${li}<br>
</c:forEach>

</body>
</html>