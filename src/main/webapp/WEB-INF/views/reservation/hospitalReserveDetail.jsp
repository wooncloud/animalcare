<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>
${hospitalReserveDetail}
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
   <button type="button" class="btn btn-primary" onclick="acceptReservation('${hospitalReserveDetail.seq}')">확정</button>
   <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#rejectReservation">반려</button>
   <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifyReservation">수정</button>
</c:if>
<c:if test="${hospitalReserveDetail.status eq 'A'}">
   <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modifyReservation">수정</button>
   <button type="button" class="btn btn-primary" onclick="operCancelReservation('${hospitalReserveDetail.seq}','${hospitalReserveDetail.status}','${hospitalReserveDetail.reservedate}')">취소</button>
</c:if>
<button type="button" class="btn btn-primary" onclick="javascript:history.back(-1);">목록</button>
<!-- 예약 반려 모달 -->
<div class="modal fade" id="rejectReservation" tabindex="-1" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title">반려 사유</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
         </div>
         <form action="./rejectReserve.do" method="post">
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
					<input type="text" class="form-control" id="reservetype" name="reservetype" value="${hospitalReserveDetail.pet_name}" readonly>
					  </div>
					  <div class="form-group">
						 <label for="reservedate">예약 일자:</label>
						 <input type="date" class="form-control" id="reservedate" name="reservedate" value="${hospitalReserveDetail.reservedate}" >
					  </div>
					  <div class="form-group">
						 <label for="reservetime">예약 시간:</label>
							<select class="form-select" aria-label="Default select example" name="reservetime" id="reservetime" >
								<option id="09001100" value="09001100">0900~1100</option>
								<option id="11001300" value="11001300">1100~1300</option>
								<option id="13001500" value="13001500">1300~1500</option>
								<option id="15001700" value="15001700">1500~1700</option>						
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
						 <input type="text" class="form-control" id="phone" name="phone" value="010-1234-0000" readonly="readonly">
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
function acceptReservation(seq){
	alert("예약 확정")
	
   location.href="./acceptReserve.do?seq="+seq+"&status="+status;
}

function operCancelReservation(seq,status,reservedate){
	
	var date = new Date();
	var day = date.getDate();
	var year = date.getFullYear();
	var month = (1+date.getMonth());
	month = (month >= 10) ? month : '0' + month;
	day = (day >= 10) ? day : '0' + day; 
		
	var today = year+"-"+month+"-"+day;//날짜
	
	if(today > reservedate){
		alert("취소 기간이 아닙니다");
	} else{
		var frm = confirm("취소 하시겠습니까?");
	if(frm){
		// 이게 맞음 20210724 4:17 pm
		location.href="../payment/operCancelPayRefund.do?seq="+seq+"&status="+status;
	}
}	
}

function rejectReservation(){
	
	var commnet = document.getElementById("commnet")
 	var frm = document.forms[0];
	
	if(commnet.value == ''){
		alert("반려 사유를 작성해주세요");
		return;
	}else{
		var chk = confirm("반려하시겠습니까?");
		
		if(chk){
			// 이게 맞음 20210724 4:37 pm
			frm.submit();
		}
		
	}
	
}


function modifyReserve(){
	
	var reservedate = document.getElementById("reservedate").value;
	var reservetime = document.getElementById("reservetime").value;
	console.log(reservedate);
	console.log(reservetime);
	
	var frm1 = document.forms[1];
	console.log(frm1);
	
	$.ajax({
		type:"get",
		url:"./checkReservation.do",
		data:"reservedate="+reservedate+"&reservetime="+reservetime,
		success:function(msg){
			console.log("왔니"+msg);
			
			if(msg=='false'){
				frm1.submit();
			}else{
				alert("선택한 시간으로 수정이 불가능합니다.")
				return false;
			}
		},
		error:function(){
			alert("잘못된 요청");
			
		}
		
	});
	
}
</script>


<%@ include file="/footer.jsp" %>