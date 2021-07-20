<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
${payList}

<table>
	<tr>
		<th>NO</th>
		<th>주문번호</th>
		<th>결제일</th>
		<th>취소상태</th>
		<th>환불상태</th>
	</tr>
	<c:forEach var="pay" items="${payList}">
		<tr>
			<td>${pay.seq}</td>
			<td><a href="./payDetailList.do?seq=${pay.seq}">${pay.applynum}</a></td>
			<td>${pay.paydate}</td>
			<td>${pay.cancelflag}</td>
			<td>${pay.refundflag}</td>
		</tr>
	</c:forEach>
	
</table>
<%@include file="/footer.jsp" %>