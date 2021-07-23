<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<%-- ${payList} --%>

<div class="card my-1">
	<div class="card-body">
		<div class="row">
		    <div class="col-1">
				<h5 class="card-title"> NO</h5>
		    </div>
		    <div class="col-4">
				<h5 class="card-title"> 주문번호</h5>
		    </div>
		    <div class="col-3">
				<h5 class="card-title"> 결제일</h5>
		    </div>
		    <div class="col-2">
		    	<h5 class="card-title"> 취소상태</h5>
		    </div>
		    <div class="col-2">
		    	<h5 class="card-title"> 환불상태</h5>
		    </div>
		</div>
	</div>
</div>

<c:forEach var="pay" items="${payList}" varStatus="vs">
	<div class="card my-1">
		<div class="card-body">
			<div class="row">
			    <div class="col-1">
					<p class="card-text">${fn:length(payList)-vs.count+1}</p>
			    </div>
			    <div class="col-4">
					<p class="card-text"><a href="./payDetailList.do?seq=${pay.seq}">${pay.applynum}</a></p>
			    </div>
			    <div class="col-3">
  					<p class="card-text">${pay.paydate}</p>
			    </div>
			    <div class="col-2">
			    	<p class="card-text">${pay.cancelflag}</p>
			    </div>
			    <div class="col-2">
			    	<p class="card-text">${pay.cancelflag}</p>
			    </div>
			</div>
		</div>
	</div>
</c:forEach>


<%@include file="/footer.jsp" %>