<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<table>
	<tr>
		<th>NO</th>
		<th>예약종류</th>
		<th>반려동물</th>
		<th>병원명</th>
		<th>예약일자</th>
		<th>예약상태</th>
	</tr>
<c:forEach var="list" items="${lists}" varStatus="vs">
	<tr>
		<td>${fn:length(lists)-vs.count+1}</td>
		<td>${list.reservetype}</td>
		<td><a href="./userReserveDetail.do?seq=${list.seq}">${list.pet_name}</a></td>
		<td>${list.name}</td>
		<td>${list.reservedate}</td>
		<c:if test="${list.status eq 'S'}">
			<td>대기</td>
		</c:if>
		<c:if test="${list.status eq 'A'}">
			<td>확정</td>
		</c:if>
		<c:if test="${list.status eq 'C'}">
			<td>취소</td>
		</c:if>
		<c:if test="${list.status eq 'R'}">
			<td>반려</td>
		</c:if>
	</tr>
		</c:forEach>
</table>
<%@include file="/footer.jsp" %>