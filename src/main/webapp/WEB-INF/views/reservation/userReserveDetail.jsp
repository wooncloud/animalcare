<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<div class="card">
   <div class="card-body">
      <h5 class="card-title my-3">사용자 예약 상세 내역</h5>
      <div class="row my-2">
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">예약종류</h6>
         </div>
          <div class="col-3">
            <p class="card-text">${reserveDto.reservetype}</p>
         </div>
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">예약일자</h6>
         </div>
          <div class="col-3">
            <p class="card-text">${reserveDto.reservedate}</p>
         </div>
      </div>
      <div class="row my-2">
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">반려동물이름</h6>
         </div>
          <div class="col-3">
            <p class="card-text">${reserveDto.pet_name}</p>
         </div>
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">예약시간</h6>
         </div>
          <div class="col-3">
            <p class="card-text">${reserveDto.reservetime}</p>
         </div>
      </div>
      <div class="row my-2">
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">결제번호</h6>
         </div>
          <div class="col-3">
            <p class="card-text">${reserveDto.paynum}</p>
         </div>
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">예약상태</h6>
         </div>
          <div class="col-3">
             <c:if test="${reserveDto.status eq 'S'}">
               <p class="card-text">대기</p>
            </c:if>
            <c:if test="${reserveDto.status eq 'A'}">
               <p class="card-text">확정</p>
            </c:if>
            <c:if test="${reserveDto.status eq 'C'}">
               <p class="card-text">취소</p>
            </c:if>
            <c:if test="${reserveDto.status eq 'R'}">
               <p class="card-text">반려</p>
            </c:if>
         </div>
      </div>
      <div class="row my-2">
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">병원이름</h6>
         </div>
          <div class="col-9">
            <p class="card-text">${reserveDto.name}</p>
         </div>
      </div>
      <div class="row my-2">
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">병원주소</h6>
         </div>
          <div class="col-9">
            <p class="card-text">${reserveDto.address1}</p>
         </div>
      </div>
   </div>
</div>

<c:if test="${reserveDto.paynum != null}">
   <button class="btn btn-primary" onclick="cancelReservation()">취소</button>
</c:if>
<button class="btn btn-primary" onclick="javascript:history.back(-1);">목록</button>

<%@include file="/footer.jsp" %>