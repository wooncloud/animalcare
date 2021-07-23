<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/header.jsp" %>
${detail}
<table>
	<tr>
		<th>예약 종류</th>
		<td>${detail.reservetype}</td>
	</tr>
	<tr>
		<th>예약 일자</th>
		<td>${detail.reservedate}</td>
	</tr>
	<tr>
		<th>예약 상태</th>
		<c:if test="${detail.status eq 'S'}">
			<td>대기</td>
		</c:if>
		<c:if test="${detail.status eq 'A'}">
			<td>확정</td>
		</c:if>
		<c:if test="${detail.status eq 'C'}">
			<td>취소</td>
		</c:if>
		<c:if test="${detail.status eq 'R'}">
			<td>반려</td>
		</c:if>
	</tr>
</table>

<button onclick="modify()">수정</button>
<%@ include file="/footer.jsp" %>