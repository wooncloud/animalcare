<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<script type="text/javascript" src="${path}/js/mycalendar.js" ></script>
	<div class="row fs-3 my-2">
			<div class="col d-grid gap-2 d-md-flex justify-content-md-end">	
				<input type="button" class="btn btn-outline-primary" value="이전페이지" onclick="javascript:history.back(-1);">
			</div>
		</div>
<div class="card">
   <div class="card-body">
      <h5 class="card-title my-3">예약 상세 내역</h5>
      <div class="row my-2">
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">예약종류</h6>
         </div>
          <div class="col-3">
            <p class="card-text">${reserveDto.reservetype}</p>
            <input type="hidden" name="reservetype" id="reservetype" value="${reserveDto.reservetype}">
         </div>
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">예약일자</h6>
            <input type="hidden" name="reservedate" id="reservedate" value="${reserveDto.reservedate}">
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
            <p class="card-text">
            ${fn:substring(reserveDto.reservetime,0,2)}:${fn:substring(reserveDto.reservetime,2,4)}~${fn:substring(reserveDto.reservetime,4,6)}:${fn:substring(reserveDto.reservetime,6,8)}
            </p>
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
      <c:if test="${reserveDto.status eq 'R'}">
           <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">반려내용</h6>
         </div>
         <div class="col-3">
           	<p class="card-text">${reserveDto.commnet}</p>
          </div>
         </c:if>
   </div>
</div>

<c:if test="${reserveDto.status == 'S' or reserveDto.status =='A' }">
   <button name="cancelBtn" class="btn btn-primary" onclick="userCancelReservation('${reserveDto.seq}','${reserveDto.status}');">취소</button>
</c:if>

<script type="text/javascript">
window.onload = function(){
	
	var reserve = document.getElementById("reservedate").value;
	var reservetype = document.getElementById("reservetype").value;
	var date = new Date();
	var reservedate = new Date(reserve);

	if(date > reservedate){
		document.getElementsByTagName("button")[0].style.display="none";
	} 
}

</script>
<%@include file="/footer.jsp" %>