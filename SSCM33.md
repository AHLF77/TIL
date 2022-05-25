# 0525강의(카카오 지도 기능 강의)

- main
```html
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-more.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fa61433b35290130f3b741d1f5190c95&libraries=services"></script>

<style>
   /* Remove the navbar's default margin-bottom and rounded borders */ 
   .navbar {
     margin-bottom: 0;
     border-radius: 0;
   }
   
   /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
   .row.content {height: 700px}
   
   /* Set gray background color and 100% height */
   .sidenav {
     padding-top: 20px;
     background-color: #f1f1f1;
     height: 100%;
   }
   
   /* Set black background color, white text and some padding */
   footer {
     background-color: #555;
     color: white;
     padding: 15px;
   }
   
   /* On small screens, set height to 'auto' for sidenav and grid */
   @media screen and (max-width: 767px) {
     .sidenav {
       height: auto;
       padding: 15px;
     }
     .row.content {height:auto;} 
   }
</style>


</head>
<body>
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	      <a class="navbar-brand" href="#">한글</a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav">
	        <li><a href="/">Home</a></li>
	        <li><a href="js">JS</a></li>
	        <li><a href="jq">jQuery</a></li>
	        <li><a href="ajax">AJAX</a></li>
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
	      </ul>
	    </div>
	  </div>
	</nav>
	  
	<div class="container-fluid text-center">    
	  <div class="row content">
	    <div class="col-sm-1 sidenav"
	    th:insert="${left} ? ${left} : left">
	    </div>
	    <div class="col-sm-9 text-left" 
	    th:insert="${center} ? ${center} : center"> 
	    </div>
	    <div class="col-sm-2 sidenav">
	      <div class="well">
	        <p>ADS</p>
	      </div>
	      <div class="well">
	        <p>ADS</p>
	      </div>
	    </div>
	  </div>
	</div>
	
	<footer class="container-fluid text-center">
	  <p>Footer Text</p>
	</footer>
</body>
</html>
```

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
	
	var iwContent = 
	'<div style="padding:5px;">Hello World!</div><h3><a href="">GO</a></h3><img width="50px" src="img/subwaylo.png">'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
	    content : iwContent
	});
	
	// 마커에 마우스오버 이벤트를 등록합니다
	kakao.maps.event.addListener(marker, 'mouseout', function() {
	  // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
	    infowindow.close();
	});

	// 마커에 마우스아웃 이벤트를 등록합니다
	kakao.maps.event.addListener(marker, 'click', function() {
	    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
	    location.href="aj06";
	});
	
});
</script>

<h1>AJ09 Main</h1>

<div id="map"></div>
```

- aj10
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
var map = null;
function gomap(loc){
	var latlng = null;
	if(loc == 's'){
		latlng = new kakao.maps.LatLng(37.55041692365908, 126.91037178013711);
	}else if(loc == 'b'){
		latlng = new kakao.maps.LatLng(35.17642453774257, 129.16669784099807);
	}else if(loc == 'k'){
		latlng = new kakao.maps.LatLng(35.16173425533525, 126.88758871719189);
	}
	map.panTo(latlng);
};

function displaymap(lat, lng){
	var mapContainer = document.getElementById('map'); // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(37.503376, 127.049776), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };

	map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다.
};

// #서울 37.55041692365908, 126.91037178013711
// #부산 35.17642453774257, 129.16669784099807
// #광주 35.16173425533525, 126.88758871719189

$(document).ready(function(){
	displaymap();
	$('#s').click(function(){
		gomap('s');
	});
	$('#b').click(function(){
		gomap('b');
	});
	$('#k').click(function(){
		gomap('k');
	});

});

</script>

<h1>AJ10 Main</h1>
<button id="s">Seoul</button>
<button id="b">Busan</button>
<button id="k">kwangju</button>
<div id="map"></div>
```