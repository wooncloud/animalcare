<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<br><br>

<div class="container">
	<div>
		<div class="row fs-3">
			<div class="col">병원 찾기</div>
			<div class="col d-grid gap-2 d-md-flex justify-content-md-end">
				<input type="button" class="btn btn-outline-success" value="초기화">
			</div>
		</div>
	</div>
	<div>
		<div class="card">
			<div class="card-body">
				<div class="row">
   					<div class="col-2 text-center">
     					 지역 선택
  					</div>
  					<div class="col">
  						<input id="Gangnam" type="checkbox">
  						<label for="Gangnam">강남구</label>
  					</div>
  					<div class="col">
  						<input id="Gangnam" type="checkbox">
  						<label for="Gangnam">거성은 선택해야한다 하드코딩이냐 코드테이블 데이터 입력이냐</label>
  					</div>
  				</div>				
			</div>
		</div>
		<div class="card">
			<div class="card-body">
				<div class="row">
   					<div class="col-2 text-center">
     					 진료 항목
  					</div>
  					<div class="col">
  						항목 넣을 곳
  					</div>
  				</div>				
			</div>
		</div>
		<div class="card">
			<div class="card-body">
				<div class="row">
   					<div class="col-2 text-center">
     					 응급실 유무
  					</div>		
					<div class="col form-check">
						<input class="form-check-input" type="radio" name="flexRadioDefault" id="have"> 
						<label class="form-check-label" for="have">응급실 있음</label>
					</div>
					<div class="col form-check">
						<input class="form-check-input" type="radio" name="flexRadioDefault" id="none"> 
						<label class="form-check-label" for="none">응급실 없음</label>
					</div>
					<div class="col form-check">
						<input class="form-check-input" type="radio" name="flexRadioDefault" id="noMatter" checked> 
						<label class="form-check-label" for="noMatter">상관 없음</label>
					</div>
  				</div>				
			</div>
		</div>
	</div>
	
	<br><br>
	
	<div class="fs-3">병원 목록</div>
	<div>
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-3 text-center">
		     			병원명
		    		</div>
					<div class="col-4 text-center">
		     			위치
		    		</div>
					<div class="col text-center">
		     			운영시간
		    		</div>
					<div class="col text-center">
		     			전화번호
		    		</div>
					<div class="col-2 text-center">
		     			진료항목
		    		</div>
				</div>
			</div>
		</div>
		<c:forEach var="dto" items="${lists}" varStatus="vs">
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3 text-center">
		     				${dto.name}
						</div>
						<div class="col-4">
					     	${dto.address1}&nbsp;${dto.address2}
					    </div>
						<div class="col text-center">
					     	${dto.opentime}
					    </div>
						<div class="col text-center">
					     	${dto.tel}
					    </div>
						<div class="col-2">
					     	${dto.pettypedto.pettype}
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
					<a class="page-link" href="./searchHospital.do?page=${page.startPage-1}" aria-label="Previous">
						<span aria-hidden="true"><i class="fas fa-chevron-left"></i></span>
					</a>
				</li>
			</c:if>
			<c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
				<li class="page-item ${num eq page.page ? 'active' : ''}">
					<a class="page-link" href="./searchHospital.do?page=${num}">${num}</a>
				</li>
			</c:forEach>
			<c:if test="${page.endPage < page.totalPage}">
				<li class="page-item">
					<a class="page-link" href="./searchHospital.do?page=${page.endPage + 1}" aria-label="Next"> 
						<span aria-hidden="true"><i class="fas fa-chevron-right"></i></span>
					</a>
				</li>
			</c:if>
		</ul>
	</div>
	
	
	<br><br>
	<hr>
		${lists}
	<hr>
	<br><br>
	
</div>


<%@include file="/footer.jsp" %>