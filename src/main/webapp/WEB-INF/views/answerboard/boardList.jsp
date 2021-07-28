<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
${lists}<br>
${sessionScope.member}
<div class="card my-1">
   <div class="card-body">
      <div class="row">
          <div class="col-1">
            <h5 class="card-title"> NO</h5>
          </div>
          <div class="col-2">
            <h5 class="card-title"> 제목</h5>
          </div>
          <div class="col-2">
            <h5 class="card-title"> 작성자</h5>
          </div>
          <div class="col-2">
             <h5 class="card-title"> 등록일자</h5>
          </div>
      </div>
   </div>
</div>

<c:forEach var="list" items="${lists}" varStatus="vs">
	<div class="card my-1">
		<div class="card-body">
			<div class="row">
				<div class="col-1">
					<p class="card-text">${vs.count}</p>
				</div>
				<div class="col-2">
					<p class="card-text">${list.title}</p>
				</div>
				<div class="col-2">
					<p class="card-text">
					<a href="./selUserDetail.do?seq=${list.seq}">${list.name}</a></p>
				</div>
				<div class="col-2">
					<p class="card-text">${list.regdate}</p>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
<c:if test="${sessionScope.member.usertype eq 'ROLE_USER'}">
<a class="btn btn-secondary" href="./moveInsert.do">회원 글작성</a>
</c:if>
<c:if test="${sessionScope.member.usertype != 'ROLE_USER'}">
<a class="btn btn-secondary" href="./moveInsert.do">비회원 글작성</a>
</c:if>
<%@include file="/footer.jsp" %>