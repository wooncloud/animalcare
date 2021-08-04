<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
<script type="text/javascript" src="${path}/js/mycalendar.js" ></script>
<style type="text/css">
a{
 	text-decoration: none;
 	color: black;
}
</style>
<ul class="nav nav-tabs" id="myTab" role="tablist">
  <li class="nav-item" role="presentation">
    <button class="nav-link active" id="standReservation-tab" data-bs-toggle="tab" data-bs-target="#standReservation" type="button" role="tab" aria-controls="standReservation" aria-selected="true" onclick="location.href='./hospitalStandReserveList.do'">미처리 예약</button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link " id="madeReservation-tab" data-bs-toggle="tab" data-bs-target="#madeReservation" type="button" role="tab" aria-controls="madeReservation" aria-selected="false" onclick="location.href='./hospitalReserveList.do'">처리</button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link" id="todayReservation-tab" data-bs-toggle="tab" data-bs-target="#todayReservation" type="button" role="tab" aria-controls="todayReservation" aria-selected="false" onclick="location.href='./todayReserveList.do'">오늘의 예약</button>
  </li>
</ul>

<div class="card my-1">
   <div class="card-body">
      <div class="row">
          <div class="col-2">
            <h5 class="card-title"> 예약자</h5>
          </div>
          <div class="col-2">
            <h5 class="card-title"> 예약 종류</h5>
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


<!-- <div class="tab-content" id="myTabContent"> -->
<!--   <div class="tab-pane fade show active" id="standReservation" role="tabpanel" aria-labelledby="standReservation-tab"> -->
  	<c:if test="${slists != null }">
		<c:if test="${fn:length(slists) eq 0}">
		<div class="card my-1">
				<div class="card-body">
					<div class="row">
						<div class="col">
							<p class="card-text">예약 내용이 없습니다</p>
						</div>
					</div>
				</div>
		</div>
		</c:if>
		<c:forEach var="list" items="${slists}" varStatus="vs">
			<div class="card my-1">
				<div class="card-body">
					<div class="row">
		<!-- 				<div class="col-1"> -->
		<%-- 					<p class="card-text">${vs.count}</p> --%>
		<!-- 				</div> -->
						<div class="col-2">
							<p class="card-text">
							<a href="./hospitalReserveDetail.do?seq=${list.seq}">${list.name}</a></p>
						</div>
						<div class="col-2">
							<p class="card-text">${list.reservetype}</p>
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
		</c:if>
<!--   </div> -->
<!--   <div class="tab-pane fade" id="madeReservation" role="tabpanel" aria-labelledby="madeReservation-tab"> -->
  	<c:if test="${lists != null}">
		<c:if test="${fn:length(lists) eq 0}">
		<div class="card my-1">
				<div class="card-body">
					<div class="row">
						<div class="col">
							<p class="card-text">예약 내용이 없습니다</p>
						</div>
					</div>
				</div>
		</div>
		</c:if>
		<c:forEach var="list" items="${lists}" varStatus="vs">
			<div class="card my-1">
				<div class="card-body">
					<div class="row">
		<!-- 				<div class="col-1"> -->
		<%-- 					<p class="card-text">${vs.count}</p> --%>
		<!-- 				</div> -->
						<div class="col-2">
							<p class="card-text">
							<a href="./hospitalReserveDetail.do?seq=${list.seq}">${list.name}</a></p>
						</div>
						<div class="col-2">
							<p class="card-text">${list.reservetype}</p>
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
	</c:if>
<!--   </div> -->
<!--   <div class="tab-pane fade" id="todayReservation" role="tabpanel" aria-labelledby="todayReservation-tab"> -->
	<c:if test="${tlists != null }">
		<c:if test="${fn:length(tlists) eq 0}">
		<div class="card my-1">
				<div class="card-body">
					<div class="row">
						<div class="col">
							<p class="card-text">예약 내용이 없습니다</p>
						</div>
					</div>
				</div>
		</div>
		</c:if>
		<c:forEach var="list" items="${tlists}" varStatus="vs">
			<div class="card my-1">
				<div class="card-body">
					<div class="row">
						<div class="col-2">
							<p class="card-text">${list.reservetype}</p>
						</div>
						<div class="col-2">
							<p class="card-text">
							<a href="./hospitalReserveDetail.do?seq=${list.seq}">${list.name}</a></p>
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
	</c:if>
<!--   </div> -->
<!-- </div> -->
<!-- </div> -->

<c:if test="${lists != null}">
<ul class="mt-3 pagination justify-content-center">
			<c:if test="${page.startPage > page.countPage}">
				<li class="page-item">
					<a class="page-link" href="./hospitalReserveList.do?page=${page.startPage-1}" aria-label="Previous">
						<span aria-hidden="true"><i class="fas fa-chevron-left"></i></span>
					</a>	
				</li>
				</c:if>
			<c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
				<li class="page-item ${num eq page.page ? 'active' : ''}">
					<a class="page-link" href="./hospitalReserveList.do?page=${num}">${num}</a>	
				</li>
			</c:forEach>
			<c:if test="${page.endPage < page.totalPage}">
				<li class="page-item">
					<a class="page-link" href="./hospitalReserveList.do?page=${page.endPage + 1}" aria-label="Next"> 
						<span aria-hidden="true"><i class="fas fa-chevron-right"></i></span>
					</a>
				</li>
			</c:if>		
</ul>
</c:if>
<c:if test="${slists != null}">
<ul class="mt-3 pagination justify-content-center">
			<c:if test="${page.startPage > page.countPage}">
				<li class="page-item">
					<a class="page-link" href="./hospitalStandReserveList.do?page=${page.startPage-1}" aria-label="Previous">
						<span aria-hidden="true"><i class="fas fa-chevron-left"></i></span>
					</a>	
				</li>
				</c:if>
			<c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
				<li class="page-item ${num eq page.page ? 'active' : ''}">
					<a class="page-link" href="./hospitalStandReserveList.do?page=${num}">${num}</a>	
				</li>
			</c:forEach>
			<c:if test="${page.endPage < page.totalPage}">
				<li class="page-item">
					<a class="page-link" href="./hospitalStandReserveList.do?page=${page.endPage + 1}" aria-label="Next"> 
						<span aria-hidden="true"><i class="fas fa-chevron-right"></i></span>
					</a>
				</li>
			</c:if>		
</ul>
</c:if>
<%@ include file="/footer.jsp" %>