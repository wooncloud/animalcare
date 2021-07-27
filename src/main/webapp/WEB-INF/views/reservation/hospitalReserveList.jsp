<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
<style type="text/css">
a{
 	text-decoration: none;
 	color: black;
}
</style>
<script type="text/javascript" src="${path}/js/reservation.js" ></script>
<ul class="nav nav-tabs">
  <li class="nav-item" >
    <a class="nav-link active"  href="./hospitalReserveList.do">처리</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" aria-current="page" 	href="./hospitalStandReserveList.do">미처리</a>
  </li>
  <li class="nav-item">
    <a class="nav-link"  	href="./todayReserveList.do">오늘의 예약</a>
  </li>
</ul>
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
<c:if test="${lists != null}">
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
<c:if test="${slists != null }">
<c:forEach var="list" items="${slists}" varStatus="vs">
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
<c:if test="${tlists != null }">
<c:forEach var="list" items="${tlists}" varStatus="vs">
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
<script type="text/javascript">
// $(document).ready(function(){
	
// 	$(".nav-link")[1].click(function(){
		
// 		$(this).addClass("active");
// 		$(".nav-link")[0].removeClass("active");
		
// 	});
	
// 	$(".nav-link")[0].click(function(){
		
// 		$(this).addClass("active");
// 		$(".nav-link")[1].removeClass("active");
		
// 	});
// })

</script>
<%@ include file="/footer.jsp" %>