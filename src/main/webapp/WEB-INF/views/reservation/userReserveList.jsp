<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 예약 목록 전체 조회</title>
</head>
<body>
<h1>목록조회</h1>
<c:forEach items="${lists}" var="li" varStatus="vs">
${li}<br>
</c:forEach>
</body>
</html>