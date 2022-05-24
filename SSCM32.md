# 0520강의(jQuery AJAX 강의)

- AJAXController
```java
package com.shop.controller;

import java.util.Date;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AJAXController {
	
	@RequestMapping("/gettime")
	@ResponseBody
	public Object gettime() {
		Date d = new Date();
		return "서버시간: "+d.toString();
	}
	
	@RequestMapping("/getchart")
	public Object getchart() {
		JSONArray ja = new JSONArray();
		// [10,20,33,44,...]
		for(int i=0; i< 15; i++) {
			Random r = new Random();
			int data = r.nextInt(50)+1;
			ja.add(data);
		}
		return ja;
	}
	
	@RequestMapping("/checkid")
	public Object checkid(String id) {
		String result = "";
		if(id.equals("aaaa") || id.equals("bbbb") || id.equals("cccc")) {
			result = "0";
		}else {
			result = "1";
		}
		return result;
	}
	
	/*
	@RequestMapping("/checkpwd")
	public Object checkpwd(String pwd) {
		String result2 = "";
		if(id.equals("aaaa") || id.equals("bbbb") || id.equals("cccc")) {
			result2 = "0";
		}else {
			result2 = "1";
		}
		return result2;
	}
	*/
	
	@RequestMapping("/search")
	@ResponseBody
	public Object search(String s) {
		String data = "";
		if(s.equals("a")) {
			data = "금융에 관심";
		}else if(s.equals("b")) {
			data = "영화에 관심";
		}else {
			data = "게임에 관심";
		}
		return data;
	}
	
	@RequestMapping("/getdata")
	public Object getdata() {
		//[{},{},.....] = JSON 형태라고 함.
		JSONArray ja = new JSONArray();
		for(int i = 0; i<6; i++){
			JSONObject jo = new JSONObject();
			jo.put("id", "id0"+i);
			jo.put("name", "james"+i);
			Random r = new Random();
			int a = r.nextInt(50)+1;			
			jo.put("age", a);
			ja.add(jo);
		}
		return ja;	
	}
}
```

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
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fa61433b35290130f3b741d1f5190c95"></script>

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

- aj05
```html
<meta charset="UTF-8">

<style>
	#result{
		width:300px;
		border: 2px solid red;
	}
</style>

<script>
function display(data){
	var str = '';
	$(data).each(function(index,item){
		str += '<h3>';
		str += item.id+' '+item.name+' '+item.age;
		str += '</h3>';
	});
	$('#result').html(str);
};

function getdata(){
	$.ajax({
		url:'getdata',
		success:function(data){
			//[{},{},.....] = JSON 형태라고 함.
			display(data);
		}
	});
};

$(document).ready(function(){
	getdata();
	setInterval(() => {
		getdata();
	}, 3000);
});
</script>

<h1>AJ05 Main</h1>
<button>GET DATA</button>
<div id="result"></div>
```

- aj06
```html
<meta charset="UTF-8">
<style>
	#container{
		width: 500px;
		height: 400px;
	}
</style>
<script>
function display(){
	const chart = Highcharts.chart('container', {
	    title: {
	        text: 'Chart.update'
	    },
	    subtitle: {
	        text: 'Plain'
	    },
	    xAxis: {
	        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
	    },
	    series: [{
	        type: 'column',
	        colorByPoint: true,
	        data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4],
	        showInLegend: false
	    }]
	});
	
	document.getElementById('plain').addEventListener('click', () => {
	    chart.update({
	        chart: {
	            inverted: false,
	            polar: false
	        },
	        subtitle: {
	            text: 'Plain'
	        }
	    });
	});

	document.getElementById('inverted').addEventListener('click', () => {
	    chart.update({
	        chart: {
	            inverted: true,
	            polar: false
	        },
	        subtitle: {
	            text: 'Inverted'
	        }
	    });
	});

	document.getElementById('polar').addEventListener('click', () => {
	    chart.update({
	        chart: {
	            inverted: false,
	            polar: true
	        },
	        subtitle: {
	            text: 'Polar'
	        }
	    });
	});
	
};

$(document).ready(function() {
	display();
});

</script>
<h1>AJ06 Main</h1>

<div id="container"></div>
<button id="plain">Plain</button>
<button id="inverted">Inverted</button>
<button id="polar">Polar</button>
```

- aj07
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
	        text: 'Chart.update'
	    },
	    subtitle: {
	        text: 'Plain'
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
	
	document.getElementById('plain').addEventListener('click', () => {
	    chart.update({
	        chart: {
	            inverted: false,
	            polar: false
	        },
	        subtitle: {
	            text: 'Plain'
	        }
	    });
	});

	document.getElementById('inverted').addEventListener('click', () => {
	    chart.update({
	        chart: {
	            inverted: true,
	            polar: false
	        },
	        subtitle: {
	            text: 'Inverted'
	        }
	    });
	});

	document.getElementById('polar').addEventListener('click', () => {
	    chart.update({
	        chart: {
	            inverted: false,
	            polar: true
	        },
	        subtitle: {
	            text: 'Polar'
	        }
	    });
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
	$('#getdata').click(function(){
		getdata();
	});
});

</script>
<h1>AJ07 Main</h1>
<button id="getdata">GET DATA</button>
<div id="container"></div>
<button id="plain">Plain</button>
<button id="inverted">Inverted</button>
<button id="polar">Polar</button>
 
```

- aj08
```html
<meta charset="UTF-8">
<style>
	#map{
		width:500px;
		height:400px;
		border: 2px solid blue;
	}
</style>
<script>
$(document).ready(function(){
	var mapContainer = document.getElementById('map'); // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(37.5126252, 127.1026583), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
	var mapTypeControl = new kakao.maps.MapTypeControl();

	// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
	// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

	// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	
	// 마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
	
	var iwContent = '<div style="padding:5px;">Hello World! <br><a href="https://map.kakao.com/link/map/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    	iwPosition = new kakao.maps.LatLng(33.450701, 126.570667); //인포윈도우 표시 위치입니다

	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
    	position : iwPosition, 
    	content : iwContent 
	});
  
	// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
	infowindow.open(map, marker); 
	
	
	$('#go1').click(function(){
	    var moveLatLon = new kakao.maps.LatLng(37.5126252, 127.1026583);
	    map.setCenter(moveLatLon);
	});
	
	$('#go2').click(function(){
		var moveLatLon = new kakao.maps.LatLng(33.452613, 126.570888);
	    map.panTo(moveLatLon);
	});
});
</script>

<h1>AJ08 Main</h1>
<button id="go1">G01</button>
<button id="go2">G02</button> 
<div id="map"></div>
```