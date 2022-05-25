# 0524강의(카카오 지도 기능 강의)

- aj09
```html
<meta charset="UTF-8">
<style>
	#map{
		width:700px;
		height:400px;
		border: 2px solid blue;
	}
</style>

<script>
$(document).ready(function(){
	var mapContainer = document.getElementById('map'); // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	// 마커를 표시할 위치입니다 
	var position =  new kakao.maps.LatLng(33.450701, 126.570667);

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	  position: position
	});

	// 마커를 지도에 표시합니다.
	marker.setMap(map);
	
});
</script>

<h1>AJ09 Main</h1>

<div id="map"></div>
```