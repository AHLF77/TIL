# 0524 개인 프로젝트

## 주제: 샌드위치 매장 홈페이지

### 메인 홈페이지
- 메인 홈페이지 html

```html
<!DOCTYPE html>

<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SandWitch Market WS</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-more.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
        
<!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />

<style>
@import url('https://fonts.googleapis.com/css2?family=Anton&family=Koulen&family=Oleo+Script+Swash+Caps:wght@700&family=Water+Brush&display=swap');
footer {
	width: 100%;
	height: 50px;
	background: #FFFACD;
	margin: 0 auto;
}

footer>div>a {
	display: block;
	text-align: center;
}


footer>div>a:hover {
	color: blue;
	font-size: 1.5em;
	font-family: 'Oleo Script Swash Caps', cursive;
}
</style>

</head>

<body>
<!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="/">SandWitchFD</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" href="/">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="about">About</a></li>
                        <li class="nav-item"><a class="nav-link" href="menu">Menu</a></li>
                        <li class="nav-item"><a class="nav-link" href="event">Event</a></li>
                        <li class="nav-item"><a class="nav-link" href="calories">Calories</a></li>
                        <li class="nav-item"><a class="nav-link" href="sr">sales rate</a></li>
                    </ul>
                    <form class="d-flex">
                        <a class="btn btn-outline-dark" href='loidentify'>
                        <span class="glyphicon glyphicon-log-in"></span>
                            로그인
                            <span class="badge bg-dark text-white ms-1 rounded-pill"></span>
                        </a>
                        <a class="btn btn-outline-dark" href='ridentify'>
                        <span class="glyphicon glyphicon-user"></span>
                            회원가입
                            <span class="badge bg-dark text-white ms-1 rounded-pill"></span>
                        </a>
                    </form>
                </div>
            </div>
        </nav>
	  
	  <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
          
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Welcome To My Han's SandWitch Market</h1>
                </div>
            </div>
        </header>
        
	<div class="container-fluid text-center">    
	  <div class="row content">
	    <div class="col-sm-1 sidenav"
	    th:insert="${left} ? ${left} : left">
	    </div>
	    <div class="col-sm-9 text-left" 
	    th:insert="${center} ? ${center} : center"> 
	    </div>
	  </div>
	</div>
	
		<footer class="py-2 bg-secondary">
		<div class="container">
			<a href="privacy" class="text-center text-black">개인정보처리방침</a>
            <p class="m-1 text-center text-black"><span style=" font: italic bold 1.5em/1em Georgia, serif ;">
			Copyright &copy; Your Website 2022
			</span></p></div>
        </footer>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
</body>
</html>
```

- 메인 홈페이지(left)
```html
<meta charset="UTF-8">
<style>
 .well{
	width: 100%;
	height: 100%;
 }

</style>

<div class="well">
<p>ADfit</p>
</div>
```

- 메인 홈페이지(center(지도 및 동영상))
```html
<meta charset="UTF-8">
<style>
#video{
 	margin: auto;
 	text-align: center;
}

#map{
		width:100%;
		height:400px;
		border: 2px solid blue;
	}
h4{
 
 	text-align: center;
}	
	
</style>

<script>
$(document).ready(function(){
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(37.5126252, 127.1026583), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

	// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
	var mapTypeControl = new kakao.maps.MapTypeControl();

	// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
	// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

	// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	
	
	// 마커를 표시할 위치와 title 객체 배열입니다 
	var positions = [
	    {
	    	content: '<div>제1지점: 서울특별시 강남구 역삼동</div>', 
	        latlng: new kakao.maps.LatLng(37.5047331, 127.0484548)
	    },
	    {
	    	content: '<div>제2지점: 서울특별시 강남구 삼성1동</div>', 
	        latlng: new kakao.maps.LatLng(37.5086115, 127.0560241)
	    },
	    {
	    	content: '<div>제3지점: 서울특별시 송파구 신천동</div>', 
	        latlng: new kakao.maps.LatLng(37.5151796, 127.1025957)
	    },
	    {
	    	content: '<div>제4지점: 서울특별시 광진구 화양동</div>',
	        latlng: new kakao.maps.LatLng(37.540577, 127.0703847)
	    }
	];

	// 마커 이미지의 이미지 주소입니다
	var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
	    
	for (var i = 0; i < positions.length; i ++) {
	    
	    // 마커 이미지의 이미지 크기 입니다
	    var imageSize = new kakao.maps.Size(30, 41); 
	    
	    // 마커 이미지를 생성합니다    
	    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
	    
	}
  
	
	for (var i = 0; i < positions.length; i ++) {
	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({
	        map: map, // 마커를 표시할 지도
	        position: positions[i].latlng, // 마커의 위치
	        image : markerImage // 마커 이미지
	    });

	    // 마커에 표시할 인포윈도우를 생성합니다 
	    var infowindow = new kakao.maps.InfoWindow({
	        content: positions[i].content // 인포윈도우에 표시할 내용
	    });

	    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
	    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
	}

	// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
	function makeOverListener(map, marker, infowindow) {
	    return function() {
	        infowindow.open(map, marker);
	    };
	}

	// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
	function makeOutListener(infowindow) {
	    return function() {
	        infowindow.close();
	    };
	}
	
	
	$('#go1').click(function(){
	    var moveLatLon = new kakao.maps.LatLng(37.5047331, 127.0484548);
	    map.setCenter(moveLatLon);
	 // 이동할 위도 경도 위치를 생성합니다 
	});
	
	$('#go2').click(function(){
		var moveLatLon = new kakao.maps.LatLng(37.5086115, 127.0560241);
	    map.panTo(moveLatLon);
	});
	
	$('#go3').click(function(){
		var moveLatLon = new kakao.maps.LatLng(37.5151796, 127.1025957);
	    map.panTo(moveLatLon);
	});
	
	$('#go4').click(function(){
		var moveLatLon = new kakao.maps.LatLng(37.540577, 127.0703847);
	    map.panTo(moveLatLon);
	});
});
</script>

<video width=100% height="300px" src="mv/sand_ads.mp4" controls autoplay muted></video>

<h4>매장위치</h4>
<button id="go1">제1지점</button>
<button id="go2">제2지점</button>
<button id="go3">제3지점</button>
<button id="go4">제4지점</button>
<div id="map"></div>

```

### 판매율 현황
- sales rates(center)
```html
<meta charset="UTF-8">
<style>
	#container{
		width: 500px;
		height: 400px;
	}
</style>
<script>
function display(data){
	const chart = Highcharts.chart('container', {
	    title: {
	        text: "Han's SandWitch"
	    },
	    xAxis: {
	        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
	    },
	    series: [{
	        type: 'column',
	        colorByPoint: true,
	        data: data,
	        showInLegend: false
	    }]
	});
	
};

function getdata(){
	$.ajax({
		url:'getchart',
		success:function(data){
			display(data);
		}
	});
};

$(document).ready(function() {
		getdata();
});

</script>
<h1>2021년 실적</h1>
<div id="container"></div>
```

### about 탭 
- about 탭(역사)
```html
<meta charset="UTF-8">
<style>
#tb1 > tbody > tr:nth-child(2n+1){
 	  	background:#A4A4A4;
}

#tb1 > tbody > tr > td:nth-child(3){
 	  font-family: 'Oleo Script Swash Caps', cursive;
}

#tb1 > tbody > tr:hover{
 	  font-family: 'Oleo Script Swash Caps', cursive;
 	  	font-size: 1.2em;
 	  	font-weight:bold;
 	  	background:#9FC93C;
 	  	color:blue;
 	  	}

</style>

<body>
<table border="1" bordercolor="blue" align = "center" id="tb1">
    	<thead align = "center" bgcolor="#2E2EFE">
    	<tr>
    	<th width="276">년도</th>
    	<th width="276">내용</th>
    	</tr>
    	</thead>
    	
    	<tbody>
    	<tr>
		<td align = "center">1997년 3월</td>
		<td align = "center">오픈</td>
    	</tr>
    	
    	<tr>
		<td align = "center">1998년 7월</td>
		<td align = "center">신선한 빵 제공 시작</td>
    	</tr>
    	
    	<tr>
		<td align = "center">1998년 12월</td>
		<td align = "center">글로벌 사업 확장</td>
    	</tr>
    	
    	<tr>
		<td align = "center">2000년 08월</td>
		<td align = "center">샐러드 판매 시작</td>
    	</tr>
    	
    	<tr>
		<td align = "center">2003년 05월</td>
		<td align = "center">빵의 인공 첨가물 제거</td>
    	</tr>
    	
    	<tr>
		<td align = "center">2007년 09월</td>
		<td align = "center">쿠키 판매 시작</td>
    	</tr>
    	
    	<tr>
		<td align = "center">2010년 02월</td>
		<td align = "center">웨지 & 해쉬 브라운 판매 시작</td>
    	</tr>
    	
    	 <tr>
		<td align = "center">2017년 08월</td>
		<td align = "center">태블릿 및 앱 최적화하여 출시</td>
    	</tr>
    	
    	</tbody>	
		</table>
</body>
```

- about 탭(left)
```html
<meta charset="UTF-8">
<p><a href="history">역사</a></p>
<p><a href="promise">약속</a></p>

```

- about 탭(promise)
```html
<meta charset="UTF-8">
<style>

</style>

<body>

<div class="wrap">
	<div class= "info">
			<div class= "tit">
		<span class="num">01</span>
		<strong>엄격하게 관리되는 우리의 재료</strong>
		<p>
		신선한 야채들은 각 매장에서 정성스럽게 손질됩니다.<br>
		엄격한 규율에 따라 세척 과정을 거친 야채들은<br>
		당일 판매되는 양만큼 준비되며 언제나 신선한 최상의 야채를<br>
		안전하게 제공하는 것이 써브웨이의 목표입니다.
		</p>
		</div>
		</div>
	</div>

<div class="wrap">
	<div class= "info">
			<div class= "tit">
	<span class="num">02</span>
		<strong>써브웨이만의 특별한 빵</strong>
		<p>
		han's sandwitch는 각 매장에서 매일 직접 구워 낸 신선한 샌드위치 빵을 제공합니다.<br>
		신선한 샌드위치는 신선한 빵에서 시작된다는 철학으로<br>
		매일 최상의 샌드위치 빵을 제공하기 위해 노력하고 있습니다.
		</p>
		</div>
		</div>
	</div>

<div class="wrap">
		<div class= "info">
			<div class= "tit">
		<span class="num">03</span>
		<strong>환경을 위한 우리의 노력</strong>
		<p>
		han's sandwitch의 샐러드 보울은 95% 재생 용기로 만들어졌으며,<br>
		매장 내 일회용품 사용을 줄여 나가고 있습니다.<br>
		써브웨이는 작은 부분이라도 놓치지 않고<br>
		환경을 늘 생각하는 브랜드가 되기 위해 지속적으로 노력하고 있습니다.
		</p>
		</div>
		</div>
	</div>

	<div class="wrap">
		<div class= "info">
			<div class= "tit">
	<span class="num">04</span>
		<strong>신선함을 위한 우리의 노력</strong>
		<p>
		han's sandwitch 빵은 인위적 당분이 함유되어 있지 않으며<br>
		비타민과 칼슘을 강화하고 나트륨을 줄이는 등<br>
		건강한 먹거리를 제공하기 위해 노력하고 있습니다.
		</p>
		</div>
		</div>
	</div>
	
</body>
```

### calories 탭
- etcca
```html
<meta charset="UTF-8">
<style>
#tb1 > tbody > tr:nth-child(2n+1){
 	  	background:#A4A4A4;
}

#tb1 > tbody > tr > td:nth-child(3){
 	  font-family: 'Oleo Script Swash Caps', cursive;
}

#tb1 > tbody > tr:hover{
 	  font-family: 'Oleo Script Swash Caps', cursive;
 	  	font-size: 1.2em;
 	  	font-weight:bold;
 	  	background:#81F7F3;
 	  	color:#A901DB;
 	  	}

</style>

<body>
<table border="1" bordercolor="blue" align = "center" id="tb1">
    	<thead align = "center" bgcolor="#DF7401">
    	<tr>
    	<th width="276">사이드 메뉴명</th>
    	<th width="276">중량</th>
    	<th width="276">칼로리</th>
    	</tr>
    	</thead>
    	
    	<tbody>
    	<tr>
		<td align = "center">수프</td>
		<td align = "center">243g</td>
		<td align = "center">147Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">웨지 포테이토</td>
		<td align = "center">100g</td>
		<td align = "center">140Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">해쉬 브라운</td>
		<td align = "center">82.4g</td>
		<td align = "center">148Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">쿠키</td>
		<td align = "center">45g</td>
		<td align = "center">212Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">탄삼음료</td>
		<td align = "center">400g</td>
		<td align = "center">160Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">커피</td>
		<td align = "center">400g</td>
		<td align = "center">15Kcal</td>
    	</tr>

    	</tbody>	
		</table>
</body>
```

- left
```html
<meta charset="UTF-8">

<p><a href="sandwitchca">샌드위치 칼로리</a></p>
<p><a href="saladca">샐러드 칼로리</a></p>
<p><a href="etcca">기타 칼로리</a></p>
```

- saladca
```html
<meta charset="UTF-8">
<style>
#tb1 > tbody > tr:nth-child(2n+1){
 	  	background:#A4A4A4;
}

#tb1 > tbody > tr > td:nth-child(3){
 	  font-family: 'Oleo Script Swash Caps', cursive;
}

#tb1 > tbody > tr:hover{
 	  font-family: 'Oleo Script Swash Caps', cursive;
 	  	font-size: 1.2em;
 	  	font-weight:bold;
 	  	background:#FFFF00;
 	  	color:blue;
 	  	}

</style>

<body>
<table border="1" bordercolor="blue" align = "center" id="tb1">
    	<thead align = "center" bgcolor="#FF00BF">
    	<tr>
    	<th width="276">샌드위치명</th>
    	<th width="276">중량</th>
    	<th width="276">칼로리</th>
    	</tr>
    	</thead>
    	
    	<tbody>
    	<tr>
		<td align = "center">치킨 샐러드</td>
		<td align = "center">344g</td>
		<td align = "center">138Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">에그 샐러드</td>
		<td align = "center">346g</td>
		<td align = "center">254Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">미트볼 샐러드</td>
		<td align = "center">431g</td>
		<td align = "center">284Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">참치 샐러드</td>
		<td align = "center">346g</td>
		<td align = "center">153Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">새우 샐러드</td>
		<td align = "center">229g</td>
		<td align = "center">66.9Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">야채 샐러드</td>
		<td align = "center">271g</td>
		<td align = "center">46.7Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">스테이크 샐러드</td>
		<td align = "center">352g</td>
		<td align = "center">193Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">햄 샐러드</td>
		<td align = "center">327g</td>
		<td align = "center">99.5Kcal</td>
    	</tr>
    	</tbody>	
		</table>
</body>
```

- sandwitchca
```html
<meta charset="UTF-8">
<style>
#tb1 > tbody > tr:nth-child(2n+1){
 	  	background:#A4A4A4;
}

#tb1 > tbody > tr > td:nth-child(3){
 	  font-family: 'Oleo Script Swash Caps', cursive;
}

#tb1 > tbody > tr:hover{
 	  font-family: 'Oleo Script Swash Caps', cursive;
 	  	font-size: 1.2em;
 	  	font-weight:bold;
 	  	background:#9FC93C;
 	  	color:blue;
 	  	}

</style>

<body>
<table border="1" bordercolor="blue" align = "center" id="tb1">
    	<thead align = "center" bgcolor="skybule">
    	<tr>
    	<th width="276">샌드위치명</th>
    	<th width="276">중량</th>
    	<th width="276">칼로리</th>
    	</tr>
    	</thead>
    	
    	<tbody>
    	<tr>
		<td align = "center">치킨 샌드위치</td>
		<td align = "center">237g</td>
		<td align = "center">300Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">에그 샌드위치</td>
		<td align = "center">238g</td>
		<td align = "center">416Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">미트볼 샌드위치</td>
		<td align = "center">323g</td>
		<td align = "center">446Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">참치 샌드위치</td>
		<td align = "center">238g</td>
		<td align = "center">316Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">새우 샌드위치</td>
		<td align = "center">192g</td>
		<td align = "center">229Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">야채 샌드위치</td>
		<td align = "center">164g</td>
		<td align = "center">209Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">스테이크 샌드위치</td>
		<td align = "center">245g</td>
		<td align = "center">355Kcal</td>
    	</tr>
    	
    	<tr>
		<td align = "center">햄 샌드위치</td>
		<td align = "center">220g</td>
		<td align = "center">262Kcal</td>
    	</tr>
    	</tbody>	
		</table>
</body>
```

### 로그인
- center
```html
<meta charset="UTF-8">
<style>
	.b{
		color: black;
		background:#BDBDBD;
	}
	.btn-default{
	margin-top: 50px;
	width: 100%;
  	height: 50px;
    border: 0;
  	outline: none;
  	border-radius: 40px;
  	background: linear-gradient(to left, rgb(255, 77, 46), rgb(255, 155, 47));
  	color: white;
  	font-size: 1.5em;
  	letter-spacing: 2px;
	}
	.form-control{
	border:0; 
	padding-top:2; 
	}
</style>
<script>
$(document).ready(function(){
	
	$('#login_form > input[name="id"]').blur(function(){
		var id = $(this).val();
		if(id == ''){
			$(this).focus();
		};
	});
	$('#login_form > input[name="pwd"]').blur(function(){
		var pwd = $(this).val();
		if(pwd == ''){
			$(this).focus();
		};
	});
	
	$('button').click(function(){
		var id = $('input[name="id"]').val();
		var pwd = $('input[name="pwd"]').val();
		// LOGIN 버튼 클릭 시
		// id와 pwd가 공란인지 확인 후
		// focus 이동 및 span 메시지 출력
		if(id == ''){
			$('#sid').text('ID는 필수 항목 입니다.');
			$('input[name="id"]').focus();
			return;
		};
		
		if(pwd == ''){
			$('#spwd').text('비밀번호는 필수 항목 입니다.');
			$('input[name="pwd"]').focus();
			return;
		};
		
		$('#login_form').attr({
			'action':'loginimpl',
			'method':'post'
		});
		$('#login_form').submit();
		
		});
});
</script>

<div class="container col-sm-4">

<h1>로그인</h1>
<form id="login_form">
 <div class="form-group">
	<label for="id">ID:</label>
	<input type="text" class="form-control" id="id" name="id"> 
	<span id="sid"></span>
 </div>
 <div class="form-group">
	<label for="pwd">Password:</label>
	<input type="password" class="form-control" id="pwd" name="pwd"> 
 	<span id="spwd"></span>
 </div>
</form>
<button class="btn btn-default">Submit</button>
</div>
```

- loginfail
```html
<meta charset="UTF-8">

<h1>Login Fail</h1>
```

- loginok
```html
<meta charset="UTF-8">

<h1>Login SUCCESS</h1>
<h2><span th:text="관리자"></span>님 환영합니다.</h2>
```

### 메뉴
- sandwitch
```html
<!-- Section-->       
<section class="py-5">     

            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                       
                       <meta charset="UTF-8">

                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/Roasted_Chicken.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">치킨 샌드위치</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    7500\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                    <meta charset="UTF-8">
 					<div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/Egg-Mayo.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">에그 샌드위치</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    8500\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                        
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/Meatball.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">미트볼 샌드위치</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    5500\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/Tuna.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">참치 샌드위치</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    3500\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/Shrimp.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">새우 샌드위치</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    9500\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/Veggie.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">야채 샌드위치</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                   3000\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/Steak.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">스테이크 샌드위치</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    10000\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/Ham.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">햄 샌드위치</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    2000/
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        </div>
                    </div>
        </section>
```

### 메뉴
- salad
```html
<!-- Section-->       
<section class="py-5">     

            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                       
                       <meta charset="UTF-8">

                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/tsalad.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">참치 샐러드</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    5500\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                    <meta charset="UTF-8">
 					<div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/egg_salad.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">에그 샐러드<span class="label label-default">New</span></h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    3500\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                        
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/meetball_salad.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">미트볼 샐러드</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    4500\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/Veggie_salad.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">야채 샐러드</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    2500\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/shrimp_salad.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">새우 샐러드</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    7500\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/lost_chicken.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">치킨 샐러드</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                   4000\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/sc_salad.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">스테이크 & 치즈 샐러드</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    10000\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/ham_salad.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">햄 샐러드</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    5000/
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        </div>
                    </div>
        </section>
```

- etc
```html
<!-- Section-->       
<section class="py-5">     

            <div class="container px-3 px-lg-5 mt-5">
                <div class="row gx-3 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-3 justify-content-center">
                       
                       <meta charset="UTF-8">

                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/soup.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">수프</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    2000\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                    <meta charset="UTF-8">
 					<div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/potatoes.jpg" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">웨지 포테이토</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    2600\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                        
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/hashbrown.jpg" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">해쉬 브라운</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    1000\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/chocolate_chip.jpg" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">초코 칩쿠키</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    1500\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/soda.jpg" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">탄산음료</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    500\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>
                    
                        <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="img/coffee.png" alt="#" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">커피</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                   500\
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>      
                    
                        </div>
                    </div>
        </section>
```

- left
```html
<meta charset="UTF-8">

<p><a href="sandwitch">샌드위치</a></p>
<p><a href="salad">샐러드</a></p>
<p><a href="etc">기타</a></p>
```

### 개인정보처리방침
- center
```html
<meta charset="UTF-8">
<style>
.pemain{
 	text-align: center;
}
</style>

<h1 class="pemain">개인정보 처리방침</h1>
<h3>제1조(개인정보의 처리목적)</h3>
<p>han's snadwitch는 다음의 목적을 위하여 개인정보를 처리합니다. 
처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며, 
이용 목적이 변경되는 경우에는 ｢개인정보 보호법｣ 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.</p>
<hr>
<h3>제2조(개인정보의 처리 및 보유기간)</h3>
<p>han's snadwitch는 법령에 따른 개인정보 보유․이용기간 또는 정보주체로부터 개인정보를 수집 시에 동의받은 개인정보 보유·
이용기간 내에서 개인정보를 처리·보유합니다. </p>
<hr>
<h3>제3조(개인정보의 제3자 제공)</h3>
<p>han's snadwitch는 정보주체의 개인정보를 제1조(개인정보의 처리 목적)에서 명시한 범위 내에서만 처리하며, 정보주체의 동의, 법률의 특별한 규정 등 
｢개인정보 보호법｣ 제17조 및 제18조에 해당하는 경우에만 개인정보를 제3자에게 제공합니다. </p>
<hr>
<h3>제4조(개인정보처리의 위탁)</h3>
<p>han's snadwitch는 원활한 개인정보 업무처리를 위하여 다음과 같이 개인정보 처리업무를 위탁하고 있습니다. </p>
<hr>
<h3>제6조(처리하는 개인정보 항목)</h3>
<p> han's snadwitch는 다음의 개인정보 항목을 처리하고 있습니다. <br>
  1. 홈페이지 회원 가입 및 관리 
   ·필수항목 : 성명, 생년월일, 아이디, 비밀번호, 주소, 휴대전화번호, 이메일주소 
 </p>
<hr>
<h3>제7조(개인정보의 파기) </h3>
<p> han's snadwitch은(는) 개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 
지체없이 해당 개인정보를 파기합니다. 
</p>
<hr>
<h3>제8조(개인정보의 안전성 확보조치)</h3>
<p> han's snadwitch은(는) 개인정보의 안전성 확보를 위해 다음과 같은 조치를 취하고 있습니다. 
</p>
<hr>
<h3>제9조(개인정보 보호책임자)</h3>
<p> han's snadwitch은(는) 은(는) 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 
불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.  
</p>
<hr>
```

### 회원가입
- center
```html
<meta charset="UTF-8">

<style>

register_form {
	margin-left: 300px; 
}

.reg_bt{
	width: 100px;
  	height: 30px;
    border: 0;
  	outline: none;
  	border-radius: 40px;
  	background: linear-gradient(to left, rgb(255, 77, 46), rgb(255, 155, 47));
  	color: white;
  	font-size: 1.5em;
  	letter-spacing: 1px;
}
	
</style>

<script>
	$(document).ready(function(){	
		
		$('button').attr('disabled','disabled');
		
		$('button').click(function() {
			$('#register_form').attr({
				'action':'register_formimpl2',
				'method':'post'
			});
			
			$('#register_form').submit(); 
			
		});
		
		// 이름 제약 조건 설정
		$('input[name="named"]').keyup(function(){
			var names = $('input[name="named"]').val();
			if(names == ''){
				$('#nm').text('이름을 입력하세요.');
			}else{
				$('#nm').text('양호');
			}
		});
		
		// 아이디 제약 조건 설정
		$('input[name="id"]').keyup(function(){
			var id = $('input[name="id"]').val();
			if(id == ''){
				$('#iid').text('아이디를 입력하세요.');
			}else if(id.length < 7){
				$('#iid').text('아이디를 7자리 이상 설정해 주세요.');
			}else if(/(\w)\1\1/.test(id)){
				$('#iid').text('같은 문자를 3번 이상 사용하실 수 없습니다.');
			}else{
				$('#iid').text('양호');
			}
			
		});
		
		// 비밀번호 제약 조건 설정
		$('input[name="pwd1"]').keyup(function(){
			var id = $('input[name="id"]').val();
			var pwd1 = $('input[name="pwd1"]').val();
			var pwdcheck = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,15}$/;
			
			if(pwd1 == ''){
				$('#spwd1').text('비밀번호를 입력하세요.');
			}else if(pwd1 == id){
				alert("아이디와 비밀번호는 같으면 안됩니다!");
			}else if(false === pwdcheck.test(pwd1)){
				$('#spwd1').text('비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
			}else if(/(\w)\1\1/.test(pwd1)){
				$('#spwd1').text('같은 문자를 3번 이상 사용하실 수 없습니다.');
			}else{
				$('#spwd1').text('양호');
				
			}
			
		});
		
		// 비밀번호 확인 조건 설정
		$('input[name="pwd2"]').keyup(function(){
			var pwd1 = $('input[name="pwd1"]').val();
			var pwd2 = $('input[name="pwd2"]').val();	//this 써도됨 
			if(pwd1 == pwd2) {
				$('#spwd2').text('일치');
				
			}else{
				$('#spwd2').text('비밀번호가 일치하지 않습니다.');
			}
		});
		
		// 휴대폰 번호 제약 조건 설정
		$('input[name="mobile"]').keyup(function(){
			var phone = $('input[name="mobile"]').val();
			if(phone == ''){
				$('#mp').text('휴대폰 번호를 입력하세요.');
			}else{
				$('#mp').text('양호');
				$('register_bt').removeAttr('disabled');
			}
		});
		
		// 이메일 제약 조건 설정
		$('input[name="email_id"]').keyup(function(){
			var phone = $('input[name="email_id"]').val();
			if(phone == ''){
				$('#emid').text('이메일 주소를 입력하세요.');
			}else{
				$('#emid').text('양호');
				$('button').removeAttr('disabled');
			}
		});
		
	});
	
	// 이메일 주소 변경
	function change_email() {
		  var email_add = document.getElementById("email_add");
		  var email_sel = document.getElementById("email_sel");

		  //지금 골라진 옵션의 순서와 값 구하기
		  var idx = email_sel.options.selectedIndex;
		  var val = email_sel.options[idx].value;

		  email_add.value = val;
		}

</script>

<div class="container register_form">
  <div class="col-sm-offset-2 col-sm-10">
	<h3></h3>
  </div>
  <form class="form-horizontal" action="/action_page.php" id="register_form">
  
   <div class="form-group">
      <label class="control-label col-sm-2" for="named">이름</label>
      <div class="col-sm-6">
        <input type="text" class="form-control" id="named" placeholder="이름을 입력하시오." name="named">
        <span id="nm"></span><br>
      </div>
    </div>
  
    <div class="form-group">
      <label class="control-label col-sm-2" for="id">아이디</label>
      <div class="col-sm-6">
        <input type="text" class="form-control" id="id" placeholder="ID를 입력하시오." name="id">
        <span id="iid"></span><br>
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd1">비밀번호</label>
      <div class="col-sm-6">          
        <input type="password" class="form-control" id="pwd1" placeholder="비밀번호를 입력하시오." name="pwd1">
      <span id="spwd1"></span><br>
      </div>
    </div>
    
      <div class="form-group">
      <label class="control-label col-sm-2" for="pwd2">비밀번호 재확인</label>
      <div class="col-sm-6">          
        <input type="password" class="form-control" id="pwd2" name="pwd2">
      <span id="spwd2"></span><br>
    </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="mobile">휴대폰 번호</label>
      <div class="col-sm-6">          
        <input type="tel" class="form-control" id="mobile" placeholder="'-'없이 숫자만 입력" name="mobile">
      <span id="mp"></span><br>
    </div>
    </div>
    
    <div class="form-group">
	<label class="control-label col-sm-2" for="gender">성별</label>
	<div class="col-sm-6">
    <input type="radio" value="Man" name="gender" id="male" checked>남자
    <input type="radio" value="Woman" name="gender" id="female">여자 <br>
    <span id="ge"></span>
    </div>
  	</div>
    
    <div class="form-group">
    <label class="control-label col-sm-2" for="email">이메일</label>
    <div class="col-sm-6"> 
    <input type="text" name="email_id" id="email_id" placeholder="이메일 주소를 입력하시오.">@</label>
    <input type="text" name="email_add"  id="email_add" placeholder="도메인 주소를 입력하시오.">
    <select name="email_sel" id="email_sel" onchange="change_email();">
    <option value="">직접입력</option>
    <option value="naver.com">naver</option>
    <option value="gmail.com">gmail</option>
    <option value="nate.com">nate</option>
    <option value="daum.net">daum</option>
    <option value="kakao.com">kakao</option>
    </select>
    <span id="emid"></span>
  	</div>
  	</div>
    
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10"></div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="reg_bt" id="Register_btn">등록</button>
      </div>
    </div>
  </form>
</div>
```

- registerok
```html
<meta charset="UTF-8">

<h1>Register SUCCESS</h1>
<h2><span th:text="${rnamed}"></span>님 가입을 환영합니다.</h2>
<h2><span th:text="${rid}"> ID로 가입이 되었습니다.</span></h2>
```

### 세일즈 레이트
- center
```html
<meta charset="UTF-8">
<style>
	#container{
		width: 500px;
		height: 400px;
	}
</style>
<script>
function display(data){
	const chart = Highcharts.chart('container', {
	    title: {
	        text: "Han's SandWitch"
	    },
	    xAxis: {
	        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
	    },
	    series: [{
	        type: 'column',
	        colorByPoint: true,
	        data: data,
	        showInLegend: false
	    }]
	});
	
};

function getdata(){
	$.ajax({
		url:'getchart',
		success:function(data){
			display(data);
		}
	});
};

$(document).ready(function() {
		getdata();
});

</script>
<h1>2021년 실적</h1>
<div id="container"></div>

 
```