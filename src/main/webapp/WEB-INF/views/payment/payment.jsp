<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<!-- 아임포트 -->
<script type="text/javascript" 	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script><!-- 결제하려면 없어지면 안됨 -->
<script type="text/javascript" src="${path}/js/iamport.js"></script>
<button onclick="a()">결제</button>

<%@include file="/footer.jsp" %>
