<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<%-- ${pDto} --%>

<div class="card">
	<div class="card-body">
		<h5 class="card-title my-3">결제 상세 내역</h5>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">결제번호</h6>
			</div>
		    <div class="col-3">
				<p class="card-text">${pDto.paynum}</p>
			</div>
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">결제금액</h6>
			</div>
		    <div class="col-3">
				<p class="card-text">${pDto.paidamount}</p>
			</div>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">주문번호</h6>
			</div>
		    <div class="col-3">
				<p class="card-text">${pDto.applynum}</p>
			</div>
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">결제일</h6>
			</div>
		    <div class="col-3">
				<p class="card-text">${pDto.paydate}</p>
			</div>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">병원이름</h6>
			</div>
		    <div class="col-9">
				<p class="card-text">${pDto.hospital_name}</p>
			</div>
		</div>
		<div class="row my-2">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">취소상태</h6>
			</div>
		    <div class="col-3">
				<p class="card-text">${pDto.cancelflag}</p>
			</div>
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">취소일자</h6>
			</div>
		    <div class="col-3">
				<p class="card-text">${pDto.canceldate}</p>
			</div>
		</div>
		<div class="row">
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">환불상태</h6>
			</div>
		    <div class="col-3">
				<p class="card-text">${pDto.refundflag}</p>
			</div>
		    <div class="col-3">
				<h6 class="card-subtitle mb-2 text-muted">환불일자</h6>
			</div>
		    <div class="col-3">
				<p class="card-text">${pDto.refunddate}</p>
			</div>
		</div>
	</div>
</div>


<button class="btn btn-primary" onclick="javascript:history.back(-1);">목록</button>

<%@include file="/footer.jsp" %>