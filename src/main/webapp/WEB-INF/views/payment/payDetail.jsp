<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

${pDto}

<table>
	<tr>
		<th>결제번호</th>
		<th>결제금액</th>
		<th>결제일</th>
		<th>주문번호</th>
		<th>병원이름</th>
		<th>취소상태</th>
		<th>취소일자</th>
		<th>환불상태</th>
		<th>환불일자</th>
	</tr>
	<tr>
		<td>${pDto.paynum}</td>
		<td>${pDto.paidamount}</td>
		<td>${pDto.paydate}</td>
		<td>${pDto.applynum}</td>
		<td>${pDto.hospital_name}</td>
		<td>${pDto.cancelflag}</td>
		<td>${pDto.canceldate}</td>
		<td>${pDto.refundflag}</td>
		<td>${pDto.refunddate}</td>
	</tr>
</table>
<button onclick="javascript:history.back(-1);">목록</button>

<%@include file="/footer.jsp" %>