<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
<style type="text/css">
a{
 	text-decoration: none;
 	color: black;
}

</style>
<div class="card my-1">
   <div class="card-body">
      <div class="row">
          <div class="col-1">
            <h5 class="card-title"> NO</h5>
          </div>
          <div class="col-2">
            <h5 class="card-title"> 예약 종류</h5>
          </div>
          <div class="col-2">
            <h5 class="card-title"> 반려 동물</h5>
          </div>
          <div class="col-2">
             <h5 class="card-title"> 병원명</h5>
          </div>
          <div class="col-2"> 
             <h5 class="card-title"> 예약 일자</h5>
          </div>
          <div class="col-2">
             <h5 class="card-title"> 예약 상태</h5>
          </div>
      </div>
   </div>
</div>
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
				<div class="col-1">
					<p class="card-text">${vs.count}</p>
				</div>
				<div class="col-2">
					<p class="card-text">${list.reservetype}</p>
				</div>
				<div class="col-2">
					<p class="card-text">
					<a href="./userReserveDetail.do?seq=${list.seq}">${list.pet_name}</a></p>
				</div>
				<div class="col-2">
					<p class="card-text">${list.name}</p>
				</div>
				<div class="col-2">
					<p class="card-text">${list.reservedate}</p>
				</div>
				<div class="col-1">
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
	<ul class="mt-3 pagination justify-content-center">
			<c:if test="${page.startPage > page.countPage}">
				<li class="page-item">
					<a class="page-link" href="./userReserveList.do?page=${page.startPage-1}" aria-label="Previous">
						<span aria-hidden="true"><i class="fas fa-chevron-left"></i></span>
					</a>
				</li>
			</c:if>
			<c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
				<li class="page-item ${num eq page.page ? 'active' : ''}">
					<a class="page-link" href="./userReserveList.do?page=${num}">${num}</a>
				</li>
			</c:forEach>
			<c:if test="${page.endPage < page.totalPage}">
				<li class="page-item">
					<a class="page-link" href="./userReserveList.do?page=${page.endPage + 1}" aria-label="Next"> 
						<span aria-hidden="true"><i class="fas fa-chevron-right"></i></span>
					</a>
				</li>
			</c:if>
		</ul>
<%@ include file="/footer.jsp" %>