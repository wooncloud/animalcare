<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>주소로 장소 표시하기</title>
    
</head>
<body>
<div>
	<div>병원 상세 정보 보기</div>
	<div id="hospitalName">젤리병원</div>
	<div id="address1">경기도 고양시 덕양구 화신로340</div>	
	<div id="address2">상세주소는 비밀입니다</div>	
	<div id="map" style="width:500px;height:400px;"></div>
	<div>
		<input type="button" value="예약하기">
	</div>
</div>



<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1dad9dd4a53e26ad95b8bc11412cc8cb&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 열릴 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// var targetAddress = '경기도 고양시 덕양구 화신로340'; //document.getElementById('address1').innerText;
var targetAddress = document.getElementById('address1').innerText;
console.log(targetAddress);
console.log(document.getElementById('address1'));
console.log(document.getElementById('address1').value);
console.log(document.getElementById('address1').innerText);
console.log(document.getElementById('address1').innerHTML);
console.log(document.getElementById('address1').textContent);
var targetName = document.getElementById('hospitalName').innerText;

// 주소로 좌표를 검색합니다  // 주소도 화면에 띄울 예정이므로 돔탐색으로 주소값을 기입하여 띄울 예정입니다.
geocoder.addressSearch(targetAddress, function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            position: coords
        });
        
        marker.setMap(map);

        // 인포윈도우로 장소에 대한 설명을 표시합니다 // 장소의 이름을 돔탐색을 통해 가져와 띄워줄예정입니다.
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;"><a href="https://map.kakao.com/link/search/'+targetAddress+'" target="_blank">'+targetName+'</a></div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 

    
});



</script>
</body>
</html>