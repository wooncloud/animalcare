<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
${sessionScope.member}
<form action="./insertUserBoard.do" method="post">
<div class="card">
	<div class="card-body">
		<h5 class="card-title my-3">문의하기 </h5>
		<div class="row my-1">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">이름</h6>
			</div>
			<c:if test="${sessionScope.member != null}">
		    <div class="col-3">
				<p class="card-text">${sessionScope.member.name}</p>
			</div>
			</c:if>
			<c:if test="${sessionScope.member eq null}">
			<div class="col-3">
				<input type="text"  class="form-control" name="name" id="name"  >
			</div>
			</c:if>
			<c:if test="${sessionScope.member eq null}">
					<div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">비밀번호</h6>
			</div>
		    <div class="col-3">
		    	<input type="password"  class="form-control" name="password" id="password" >
			</div>
			</c:if>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">이메일</h6>
			</div>
		    <div class="col-9">
				<input type="text" class="form-control" name="email" id="email"  value="${sessionScope.member.email}">
			</div>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">제목</h6>
			</div>
		    <div class="col-9">
				<input type="text" class="form-control" name="title" id="title" >
			</div>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">내용</h6>
			</div>
		    <div class="col-9">
				<input type="text" class="form-control" name="content" id="content" >
			</div>
		</div>
	</div>
</div>
<input type="submit" value="1:1 문의 등록">
<input type="reset" value="초기화">
</form>

<a href="./selUserBoard.do?email=${sessionScope.member.email}">사용자 문의 목록</a>
<button class="btn btn-primary" onclick="javascript:history.back(-1);">목록</button>

<%@include file="/footer.jsp" %>