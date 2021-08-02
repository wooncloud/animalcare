<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
<script type="text/javascript" src="${path}/js/mycalendar.js" ></script>
<div class="card">
   <div class="card-body">
      <h5 class="card-title my-3">병원관계자 예약 상세 내역</h5>
      <div class="row my-2">
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">예약종류</h6>
         </div>
          <div class="col-3">
            <p class="card-text">${hospitalReserveDetail.reservetype}</p>
         </div>
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">예약일자</h6>
         </div>
          <div class="col-3">
            <p class="card-text">${hospitalReserveDetail.reservedate}</p>
         </div>
      </div>
      <div class="row my-2">
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">반려동물이름</h6>
         </div>
          <div class="col-3">
            <p class="card-text">${hospitalReserveDetail.pet_name}</p>
         </div>
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">예약시간</h6>
         </div>
          <div class="col-3">
            <p class="card-text">${hospitalReserveDetail.reservetime}</p>
         </div>
      </div>
      
      <div class="row my-2">
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">예약자</h6>
         </div>
          <div class="col-3">
            <p class="card-text">${hospitalReserveDetail.name}</p>
         </div>
           <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">예약 상태</h6>
         </div>
          <div class="col-3">
        	<c:if test="${hospitalReserveDetail.status eq 'S'}">
				<p class="card-text">대기</p>
			</c:if>
			<c:if test="${hospitalReserveDetail.status eq 'A'}">
				<p class="card-text">확정</p>
			</c:if>
			<c:if test="${hospitalReserveDetail.status eq 'C'}">
				<p class="card-text">취소</p>
			</c:if>
			<c:if test="${hospitalReserveDetail.status eq 'R'}">
				<p class="card-text">반려</p>
			</c:if>
         </div>
      </div>
      <div class="row my-2">
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">이메일</h6>
         </div>
          <div class="col-3">
            <p class="card-text">${hospitalReserveDetail.user_email}</p>
         </div>
      </div>
      <div class="row my-2">
          <div class="col-3">
            <h6 class="card-subtitle mb-2 text-muted">연락처</h6>
         </div>
          <div class="col-3">
            <p class="card-text">${hospitalReserveDetail.phone}</p>
         </div>
      </div>
   </div>
</div>
<c:if test="${hospitalReserveDetail.status eq 'S'}">
   <button type="button" class="btn btn-primary" name="confirm" onclick="acceptReservation('${hospitalReserveDetail.seq}')">확정</button>
   <button type="button" class="btn btn-primary" name="reject"  data-bs-toggle="modal" data-bs-target="#rejectReservation">반려</button>
   <button type="button" class="btn btn-primary" name="modify" data-bs-toggle="modal" data-bs-target="#modifyReservation">수정</button>
   <button type="button" class="btn btn-primary" name="list" onclick="javascript:history.back(-1);">목록</button>
</c:if>
<c:if test="${hospitalReserveDetail.status eq 'A'}">
   <button type="button" class="btn btn-primary" name="modify" data-bs-toggle="modal" data-bs-target="#modifyReservation">수정</button>
    <button type="button" class="btn btn-primary"name="cancel"  onclick="operCancelReservation('${hospitalReserveDetail.seq}','${hospitalReserveDetail.status}','${hospitalReserveDetail.reservedate}')">취소</button>
   <button type="button" class="btn btn-primary" name="list" onclick="javascript:history.back(-1);">목록</button>
</c:if>
<c:if test="${hospitalReserveDetail.status eq 'R' or hospitalReserveDetail.status eq 'C'}">
   <button type="button" class="btn btn-primary"name="list" onclick="javascript:history.back(-1);">목록</button>
</c:if>

<!-- 예약 반려 모달 -->
<div class="modal fade" id="rejectReservation" tabindex="-1" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title">반려 사유</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
         </div>
         <form action="./rejectCommnetReserve.do" method="post">
         <div class="modal-body">
         <div class="card">
				<div class="card-body">
					  <div class="form-group">
                     <input type="text" class="form-control" id="commnet" name="commnet">
                     <input type="hidden" value="${hospitalReserveDetail.seq}" name="seq">
                     <input type="hidden" value="${hospitalReserveDetail.status}" name="status"><!-- 이게 맞음 20210724 4:36pm -->
                  </div>
				</div>
			</div>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-primary" value="반려" onclick="rejectReservation()">예약 반려</button>
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
         </div>
         </form>
      </div>
   </div>
</div>

<!-- 예약수정 모달 입력 -->
<div class="modal fade" id="modifyReservation" tabindex="-1" aria-hidden="true">
   <div class="modal-dialog">
		  <div class="modal-content">
			 <div class="modal-header">
				<h5 class="modal-title">예약수정</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			 </div>
			 <div class="modal-body">
				<div>
			   <form action="./modifyReserve.do" method="post">
					  <div class="form-group">
						 <label>예약 종류:</label>
						 <input type="text" class="form-control" id="reservetype" name="reservetype" value="${hospitalReserveDetail.reservetype}" readonly>
					  </div>
					  <div class="form-group">
						 <label>반려 동물:</label>
					<input type="text" class="form-control" id="pettype" name="pettype" value="${hospitalReserveDetail.pet_name}" readonly>
					  </div>
					  <div class="form-group">
						 <label for="phone">증상:</label>
						 <input type="text" class="form-control" id="symptom" name="symptom" >
					  </div>
					  <div class="form-group">
						 <label for="reservedate">예약 일자:</label>
						 <input type="date" class="form-control" id="modifyReservedate" name="modifyReservedate" value="${hospitalReserveDetail.reservedate}" >
					  </div>
					  <div class="form-group">
						 <label for="reservetime">예약 시간:</label>
							<select class="form-select" aria-label="Default select example" name="modifyReservetime" id="modifyReservetime" onchange="modifyChk()">
								<option value="modifyDefault" id="modifyDefault" selected="selected">선택</option>
								<option id="09001100" value="09001100">09:00~11:00</option>
								<option id="11001300" value="11001300">11:00~13:00</option>
								<option id="13001500" value="13001500">13:00~15:00</option>
								<option id="15001700" value="15001700">15:00~17:00</option>					
							</select>
					  </div>
					  <div class="form-group">
						 <label for="name">이름:</label>
						 <input type="text" class="form-control" id="name" name="name" value="${hospitalReserveDetail.name}" readonly="readonly">
					  </div>
					  <div class="form-group">
						 <label for="email">이메일:</label>
						 <input type="text" class="form-control" id="user_email" name="user_email" value="${hospitalReserveDetail.user_email}" readonly="readonly">
					  </div>
					  <div class="form-group">
						 <label for="phone">전화번호:</label>
						 <input type="text" class="form-control" id="phone" name="phone" value="${hospitalReserveDetail.phone}" readonly="readonly">
					  </div>
					  <input type="hidden" value="${hospitalReserveDetail.seq}" name="seq" id="seq">
	    </form>
				</div>
			 </div>
			 <div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal" >닫기</button>
				<input type="button" class="btn btn-primary" value="수정" onclick="modifyReserve()">
			 </div>
		  </div>
	   </div>
</div>


<script type="text/javascript">


window.onload = function(){
	
	var getdate = `${hospitalReserveDetail.reservedate}`
	var reservetype = `${hospitalReserveDetail.reservetype}`
	var reservedate = new Date(getdate);
	console.log(reservedate);
	
	var date = new Date();
	console.log(date);

	
	if(reservedate < date && reservetype =='A'){
		
		document.getElementsByName("confirm")[0].style.display="none";
		document.getElementsByName("reject")[0].style.display="none";
		document.getElementsByName("modify")[0].style.display="none";
		document.getElementsByName("modify")[1].style.display="none";
		document.getElementsByName("cancel")[0].style.display="none";
	}
}

</script>
<%@ include file="/footer.jsp" %>