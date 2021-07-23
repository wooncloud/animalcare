<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	*{
		transition: all 0.6s;
	}
	
	html {
		height: 100%;
	}
	
	body{
		font-family: 'Lato', sans-serif;
		color: #888;
		margin: 0;
	}
	
	#main{
		display: table;
		width: 100%;
		height: 100vh;
		text-align: center;
	}
	
	.fof{
		  display: table-cell;
		  vertical-align: middle;
	}
	
	.fof h1{
		  font-size: 50px;
		  display: inline-block;
		  padding-right: 12px;
		  animation: type .5s alternate infinite;
	}
	
	@keyframes type{
		  from{box-shadow: inset -3px 0px 0px #888;}
		  to{box-shadow: inset -3px 0px 0px transparent;}
	}
	
	#return{
		padding: 10px 20px;
		text-decoration: none;
		color: dodgerblue;
		border:1px solid lightgray;
		border-radius: 10px;
		cursor: pointer;
		font-weight: bold;
		font-size: large;
	}
	
	#return:hover{
		color: white;
		background-color: lightgray;
	}
</style>

<title>ERROR</title>
</head>
<body>
	<div id="main">
		<div class="fof">
			<h1>Error ${code}</h1>
			<h2>${text}</h2>
			<a id="return" href="${pageContext.request.contextPath}/home.do">메인화면으로 돌아가기</a>
		</div>
	</div>
</body>
</html>