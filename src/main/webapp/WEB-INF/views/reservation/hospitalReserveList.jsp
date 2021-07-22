<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
<div class="card my-1">
   <div class="card-body">
      <div class="row">
          <div class="col-1">
            <h5 class="card-title"> NO</h5>
          </div>
          <div class="col-2">
            <h5 class="card-title"> 예약 종류</h5>
          </div>
          <div class="col-1">
            <h5 class="card-title"> 예약자</h5>
          </div>
          <div class="col-2">
             <h5 class="card-title"> 연락처</h5>
          </div>
          <div class="col-2"> 
             <h5 class="card-title"> 예약 일자</h5>
          </div>
          <div class="col-2">
             <h5 class="card-title"> 예약 시간</h5>
          </div>
          <div class="col-2">
             <h5 class="card-title"> 예약 상태</h5>
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
					<p class="card-text">${list.reservetype}</p>
				</div>
				<div class="col-1">
					<p class="card-text">
					<a href="./userReserveDetail.do?seq=${list.seq}">${list.name}</a></p>
				</div>
				<div class="col-2">
					<p class="card-text">${list.phone}</p>
				</div>
				<div class="col-2">
					<p class="card-text">${list.reservedate}</p>
				</div>
				<div class="col-2">
					<p class="card-text">${list.reservetime}</p>
				</div>
				<div class="col-2">
						<c:if test="${list.status eq 'S'}">
							<p class="card-text">대기</p>
						</c:if>
						<c:if test="${list.status eq 'A'}">
							<p class="card-text">확정</p>
						</c:if>
						<c:if test="${list.status eq 'C'}">
							<p class="card-text">취소</p>
						</c:if>
						<c:if test="${list.status eq 'R'}">
							<p class="card-text">반려</p>
						</c:if>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
<%@ include file="/footer.jsp" %>