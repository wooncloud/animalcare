<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
  <script src="${path}/js/answerboard.js"></script>
<form action="./insertReply.do" method="post" onsubmit="return insertReplyChk(this)">
<div class="card">
	<div class="card-body">
		<h5 class="card-title my-3">문의내용</h5>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">이름</h6>
			</div>
		    <div class="col-3">
		    	${dto.answerboard_name}
			</div>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">이메일</h6>
			</div>
		    <div class="col-9">
		    ${dto.email}
			</div>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">제목</h6>
			</div>
		    <div class="col-9">
 				${dto.title}
			</div>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">내용</h6>
			</div>
		    <div class="col-9">
				${dto.content}
			</div>
		</div>
	</div>
</div>
<c:if test="${dto.replystatus eq 'Y'}">
<div class="card">
	<div class="card-body">
		<h5 class="card-title my-3">답변 내용 </h5>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">답변</h6>
			</div>
		    <div class="col-3">
		    	${dto.reply}
			</div>
		</div>
	</div>
</div>
</c:if>
<div id="replyForm" style="display: none">
<h5>답변 작성</h5>
<div id="editor"></div>
<input type="hidden" name="reply" id="reply">
<input type="hidden" name="seq" id="seq" value="${dto.seq}">
<input type="hidden" name="password" id="password" value="${dto.password}">
</div>
<c:if test="${sessionScope.member.usertype eq 'ROLE_ADMIN'}">
<c:if test="${dto.replystatus eq 'N'}">
	<input  type="submit"  class="btn btn-outline-primary btn-lg" id="insertBtn" onclick="insertReply()"  value="작성 완료" style="display: none;">
</c:if>
</c:if>
</form>
<c:if test="${dto.password != null && sessionScope.member.usertype != 'ROLE_ADMIN' && dto.replystatus != 'Y'}">
	<button class="btn btn-primary" onclick="location.href='./modifyNonUserForm.do?seq=${dto.seq}'">수정</button>
	<button class= "btn btn-secondary" onclick="deleterBoard(${dto.seq})">삭제</button>
	<button class="btn btn-primary" onclick="javascript:history.back(-1);">목록</button>
</c:if>
<c:if test="${dto.password eq null && sessionScope.member.usertype != 'ROLE_ADMIN' && dto.replystatus != 'Y'}">
	<button class="btn btn-primary" onclick="location.href='./modifyUserForm.do?seq=${dto.seq}' ">수정</button>
	<button class= "btn btn-secondary" onclick="deleterBoard(${dto.seq})">삭제</button>
	<button class="btn btn-primary" onclick="javascript:history.back(-1);">목록</button>
</c:if>


<c:if test="${dto.replystatus eq 'N' && sessionScope.member.usertype eq 'ROLE_ADMIN'}">
	<button class="btn btn-outline-primary btn-lg"  id="replyBtn" onclick="clickReply()">답글 작성</button>
</c:if>	
<script type="text/javascript">

window.onload = function(){
	replyBoard.init();
}


document.onkeydown = function(){
	
	//새로고침 방지
	if(event.ctrlKey==true && (event.keyCode==78|| event.keyCode==82)||event.keyCode==116){
		event.ctrlKey==true 
		event.keyCode==78 
		event.keyCode==82 
		event.keyCode==116
		event.keyCode == 0;
		event.cancelBubble = true;
		event.returnValue = false;
	}
}

</script>

<%@include file="/footer.jsp" %>