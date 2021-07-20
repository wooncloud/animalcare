<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="./header.jsp" %>
	<h1>동찬이의 pet care</h1>
	
	<a href="./test/sms.do">SMS</a>
	<a href="./test/oauth.do">OAuth</a>

	<div>
		<button type="button" class="btn btn-primary">Primary</button>
		<button type="button" class="btn btn-secondary">Secondary</button>
		<button type="button" class="btn btn-success">Success</button>
		<button type="button" class="btn btn-danger">Danger</button>
		<button type="button" class="btn btn-warning">Warning</button>
		<button type="button" class="btn btn-info">Info</button>
		<button type="button" class="btn btn-light">Light</button>
		<button type="button" class="btn btn-dark">Dark</button>
	</div>
	<div>
		<button type="button" class="btn btn-outline-primary">Primary</button>
		<button type="button" class="btn btn-outline-secondary">Secondary</button>
		<button type="button" class="btn btn-outline-success">Success</button>
		<button type="button" class="btn btn-outline-danger">Danger</button>
		<button type="button" class="btn btn-outline-warning">Warning</button>
		<button type="button" class="btn btn-outline-info">Info</button>
		<button type="button" class="btn btn-outline-light">Light</button>
		<button type="button" class="btn btn-outline-dark">Dark</button>
	</div>
	
	<a href="./payment/payList.do">결제내역</a>
	<a href="./payment/payment.do">결제</a>
<%@include file="./footer.jsp" %>