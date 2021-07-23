<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<link href="${path}/css/hospital.css" rel="stylesheet">
<script type="text/javascript" src="${path}/js/hospital.js" ></script>

<div class="container">

<br><br>

	
	<div class="row fs-3 my-2">
		<div class="col">병원 찾기</div>
		<div class="col d-grid gap-2 d-md-flex justify-content-md-end">
			<input type="button" class="btn btn-outline-success" value="초기화" onclick="window.location.reload();">
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
  						<c:set var="i" value="0"/>
  						<c:set var="j" value="5"/>
  						<c:forEach var="loc" items="${loc}" varStatus="vs" >
  							<c:if test="${i%j==0}">
  								<div class="row">
  							</c:if>
									<div class="col my-1">
  										<input class="form-check-input" id="${loc.codeid}" type="checkbox" value="${loc.codename}" name="chkBox" onchange="chkBox(this)">
  										<label for="${loc.codeid}">${loc.codename}</label>
									</div>
							<c:if test="${i%j==j-1}">
  								</div>
  							</c:if>
  						<c:set var="i" value="${i+1}"/>
  						</c:forEach>
  					</div>
  				</div>				
			</div>
		</div>
		<div class="card" id="petType">
			<div class="card-body">
				<div class="row my-2" >
   					<div class="col-2 text-center">
     					 진료 항목
  					</div>
  					<div class="col-2" >
							<select class="form-select" id="selectPetType" onchange="selectType()">
								<option value="" selected>&nbsp;선택&nbsp;</option>
								<c:forEach var="dto" items="${petlist}" varStatus="vs">
									<option  value="${dto.codeid}" >${dto.codename}</option>
								</c:forEach>
							</select>
						</div>
  				</div>				
 				<div class="choice-div" id="choice">
 					
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
	
	<div class="fs-3 my-2">병원 목록</div>
	<div>
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-4 text-center">
		     			병원명
		    		</div>
					<div class="col-3 text-center">
		     			위치
		    		</div>
					<div class="col text-center">
		     			운영시간
		    		</div>
					<div class="col text-center">
		     			전화번호
		    		</div>
		    		<c:if test="${sessionScope.auth eq 'user'}">
						<div class="col text-center">
		     			 	관심등록
		    			</div>
		    		</c:if>
				</div>
			</div>
		</div>
		<c:forEach var="dto" items="${lists}" varStatus="vs">
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-4 text-center" onclick="location.href='./detailHospital.do?seq=${dto.seq}'; " style="cursor:pointer;">
		     				${dto.name}
						</div>
						<div class="col-3">
					     	${dto.address1}
					    </div>
						<div class="col text-center">
					     	${dto.opentime}
					    </div>
						<div class="col text-center">
					     	${dto.tel}
					    </div>
					    <c:if test="${sessionScope.auth eq 'user'}">
							<div class="col text-center">
					     		추천☆즐찾
						    </div>
					    </c:if>
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
	
</div>

<!-- 테스트 -->


<%@include file="/footer.jsp" %>