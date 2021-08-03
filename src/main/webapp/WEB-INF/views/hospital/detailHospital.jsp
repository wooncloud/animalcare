<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header.jsp" %>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1dad9dd4a53e26ad95b8bc11412cc8cb&libraries=services"></script>
<link href="${path}/css/hospital.css" rel="stylesheet">
<script type="text/javascript" src="${path}/js/hospital.js" ></script>

<div class="container">
<br><br>
	<c:if test="${sessionScope.member.usertype eq 'ROLE_OPER'}">
	<div class="my-2">
	- 다른곳에 뜯어붙일 친구들<br>
		<input type="button" class="btn btn-outline-primary" value="일정등록(병원)" onclick="insertSchedulePage()">
		<input type="button" class="btn btn-outline-primary" value="일정 상세정보 조회(병원)" onclick="detailSchedulePage()">
		
		<input type="button" class="btn btn-outline-primary" value="진료기록 조회(병원)" onclick="medicalListPage()">
		<input type="button" class="btn btn-outline-primary" value="진료기록 추가(병원)" onclick="insertRecodePage()">
	</div>
	</c:if>
	<div class="row fs-3 my-2">
		<div class="col">병원 상세 정보</div>
		<c:if test="${(sessionScope.member.usertype ne 'ROLE_OPER')}">
			<div class="col d-grid gap-2 d-md-flex justify-content-md-end">	
				<input type="button" class="btn btn-outline-primary" value="이전페이지" onclick="location.href = './searchHospitalPage.do';">
			</div>
		</c:if>
	</div>
	<div>
		<div class="card">
			<div class="card-body">
				<div class="row">
   					<div class="col-2 text-center border-end">
     					 병원명
  					</div>
  					<div id="hospitalName" class="col">
  						${dto.name}
  					</div>
  				</div>				
			</div>
		</div>
		<div class="card">
			<div class="card-body">
				<div class="row">
   					<div class="col-2 text-center border-end">
     					 진료항목
  					</div>
  					<div id="pettypes" class="col">
						 ${dto.pettypedto[0].pettype}
  					</div>
  				</div>				
			</div>
		</div>
		<div class="card">
			<div class="card-body">
				<div class="row">
   					<div class="col-2 text-center border-end">
     					 전화번호
  					</div>
  					<div class="col-4 border-end">
						 ${dto.tel}
  					</div>
   					<div class="col-2 text-center border-end">
     					 응급실 여부
  					</div>
  					<div class="col-4">
						 ${dto.emergency}
  					</div>
  				</div>				
			</div>
		</div>
		<div class="card">
			<div class="card-body">
				<div class="row">
   					<div class="col-2 text-center border-end">
     					 주소
  					</div>
  					<div id="address" class="col-4 border-end">
						 ${dto.address1}&nbsp;${dto.address2}
  					</div>
   					<div class="col-2 text-center border-end">
     					 운영시간
  					</div>
  					<div class="col-4">
						 ${dto.opentime}
  					</div>
  				</div>				
			</div>
		</div>
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-2 text-center">
     					 내용
  					</div>
					<div class="col">
	   					${dto.content}
  					</div>
  				</div>				
			</div>
		</div>
		<div class="card">
			<div class="card-body">
				<div class="row">
					<div class="col-2 text-center">
     					 상세지도
  					</div>
					<div class="col">
	   					<div id="map" style="width:500px;height:400px;"></div>
  					</div>
  				</div>				
			</div>
		</div>
	</div>
	
	<br>
	
	<c:choose>
		<c:when test="${(sessionScope.member eq null) or (sessionScope.member.usertype eq 'ROLE_USER')}">	
			<div class="d-grid gap-2 d-md-flex justify-content-md-center">
				<button type="button" class="btn btn-outline-primary btn-lg" onclick="a()">문의하기</button>	
				<c:if test="${sessionScope.member.usertype eq 'ROLE_USER'}">
     				<button type="button" class="btn btn-outline-primary btn-lg" onclick="location.href='../reservation/moveCalendar.do?hospital_seq=${dto.seq}&hospital_name=${dto.name}'">예약하기</button>		 
     				<button type="button" class="btn btn-outline-primary btn-lg">관심병원 등록</button>	
				</c:if>			 			      				 
				<c:if test="${sessionScope.member.usertype ne 'ROLE_USER'}">
     				<button type="button" class="btn btn-outline-primary btn-lg" onclick="nullCanNot()">예약하기</button>		 
     				<button type="button" class="btn btn-outline-primary btn-lg" onclick="nullCanNot()">관심병원 등록</button>	
				</c:if>			 			      				 
     			<button type="button" class="btn btn-outline-secondary btn-lg">신고하기</button>		  		
			</div>
		</c:when>
		<c:when test="${sessionScope.member.usertype eq 'ROLE_OPER'}">
			<div class="d-grid gap-2 d-md-flex justify-content-md-center">
				<input type="button" class="btn btn-outline-primary btn-lg" value="수정하기" onclick="modifyHospitalPage('${dto.seq}')"/>				 	
    	 		<input type="button" class="btn btn-outline-secondary btn-lg" value="삭제하기" onclick="deleteHospital('${dto.seq}')"/>		
			</div>
		</c:when>
	</c:choose>

<br><br>

</div>

<script type="text/javascript">

// 펫타입 코드
	window.onload = detailHospital.init;     
	let petCode = ${petJson}
	
// 지도 API 스크립트 부분
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 열릴 지도의 중심좌표
    level: 4 // 지도의 확대 레벨
};  

//지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

//돔탐색을 통해 데이터베이스에서 불러온 주소 및 이름을 가져옵니다
var targetAddress = document.getElementById('address').innerText;
var targetName = document.getElementById('hospitalName').innerText;

//주소로 좌표를 검색합니다  
geocoder.addressSearch(targetAddress, function(result, status) {

// 정상적으로 검색이 완료됐으면 
 if (status === kakao.maps.services.Status.OK) {

    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

    // 결과값으로 받은 위치를 마커로 표시합니다
    var marker = new kakao.maps.Marker({
        position: coords
    });
    
    marker.setMap(map);

    // 인포윈도우로 장소에 대한 설명을 표시합니다
    var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="width:150px;text-align:center;padding:6px 0;"><a href="https://map.kakao.com/link/search/'+targetAddress+'" target="_blank">'+targetName+'</a></div>'
    });
    infowindow.open(map, marker);

    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    map.setCenter(coords);
} 

});

</script>
<%@include file="/footer.jsp" %>