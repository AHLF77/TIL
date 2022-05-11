# 0511강의

## 글자태그
- h1 ~ h6 제목 글자 생성
- p, br, hr: 본문 생성
- a : 하이퍼 링크 생성
- b, i, small, sub, sup, ins, del: 글자 모양 지정

## 목록 태그
- ul/ol.li : 순서가 없는/있는 목록 생성, 목록 요소 생성

## 테이블 태그
- table, tr, th, td: 표 생성

## 미디어 태그
- img, audio, video: 이미지, 오디오, 비디오 삽입

### P52
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function go(){
		var c = confirm('Are You go .. Naver');
		if(c == true){
			location.href='http://www.naver.com';
		};
	};
</script>
</head>
<body>
	<!-- Comments -->
	<h1>Main&nbsp; Page</h1>
	<p>Paragraph</p>
	<a href="#" onclick="go();">Click</a>
	<a href="#">Click</a>
	<h2>Main Page</h2>
</body>
</html>


### P79
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	a{
		text-decoration: none;
		color:green;
	}
	ol,ul{
		list-style: none;
	}
	ol > li{
		float:left;
		margin:10px;
	}
</style>
</head>
<body>
	<h1>list tag</h1>
	<ol>
		<li><a href="">First</a></li>
		<li><a href="">Second</a></li>
		<li><a href="">Third</a></li>
	</ol>
</body>
</html>


### P82
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table > thead > tr > th{
		color:red;
		width: 300px;
		border:1px solid black;
	}
</style>
</head>
<body>
	<h1>Table</h1>
	<table>
	<thead>
		<tr><th>id</th><th>name</th><th>age</th></tr>
	</thead>
	<tbody>
		<tr><td>id01</td><td>lee</td><td>10</td></tr>
		<tr><td>id02</td><td>kim</td><td>20</td></tr>
		<tr><td rowspan="2">id03</td><td colspan="2">nae</td><td>30</td></tr>
		<tr><td>yang</td><td>40</td></tr>
	</tbody>
	</table>
</body>
</html>