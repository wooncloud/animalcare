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
	
	<div>
	<form method="post" action="./test/upload.do" enctype="multipart/form-data">
         <label>파일:</label>
         <input type="file" name="file1">
         <input type="submit" value="upload">
  	</form>
	</div>
<%@include file="./footer.jsp" %>