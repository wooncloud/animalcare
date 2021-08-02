<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<link href="${path}/css/hospital.css" rel="stylesheet">
<script type="text/javascript" src="${path}/js/hospital.js" ></script>

<div class="container">

<br><br>
	
<div class="fs-3 my-2">병원 진료 내역 리스트</div>
	<div>
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col text-center">
		     			No.
		    		</div>
					<div class="col-5 text-center">
		     			증상
		    		</div>
					<div class="col-2 text-center">
		     			반려동물 이름
		    		</div>
					<div class="col-2 text-center">
		     			반려인 이름
		    		</div>
					<div class="col-2 text-center">
		     			진료 일자
		    		</div>
				</div>
			</div>
		</div>
		<c:forEach var="dto" items="${lists}" varStatus="vs">
			<div class="card">
				<div class="choice-hover card-body">
					<div class="row">
						<div class="col text-center border-end"  >
		     				${dto.seq}
						</div>
						<div class="change-here col-5 text-center border-end" onclick="location.href='./detailRecodeHospital.do?seq=${dto.seq}';" style="cursor:pointer;">
							${dto.symptom}
						</div>
						<div class="col-2 text-center border-end">
							${dto.petdto[0].name}
						</div>
						<div class="col-2 text-center border-end">
							${dto.userdto[0].name}
						</div>
						<div class="col-2 text-center">
							<fmt:formatDate value="${dto.treatdate}" pattern="yyyy-MM-dd" />	
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div>
		<ul class="mt-3 pagination justify-content-center">
			<c:if test="${page.startPage > page.countPage}">
				<li class="page-item">
					<a class="page-link" href="./recodeList.do?page=${page.startPage-1}" aria-label="Previous">
						<span aria-hidden="true"><i class="fas fa-chevron-left"></i></span>
					</a>
				</li>
			</c:if>
			<c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
				<li class="page-item ${num eq page.page ? 'active' : ''}">
					<a class="page-link" href="./recodeList.do?page=${num}">${num}</a>
				</li>
			</c:forEach>
			<c:if test="${page.endPage < page.totalPage}">
				<li class="page-item">
					<a class="page-link" href="./recodeList.do?page=${page.endPage + 1}" aria-label="Next"> 
						<span aria-hidden="true"><i class="fas fa-chevron-right"></i></span>
					</a>
				</li>
			</c:if>
		</ul>
	</div>

<br><br>

</div>


<%@include file="/footer.jsp" %>