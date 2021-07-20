<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>병원 예약 목록 조회</title>
</head>
<body>
<c:forEach items="${lists}" var="li" varStatus="vs">
${li}<br>
</c:forEach>
</body>
</html>