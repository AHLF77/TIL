# 0511강의

## 글자태그
- h1 ~ h6 제목 글자 생성
- p : 본문 문단 생성
- br : 줄바꿈 
- hr: 수평 줄 삽입
- a : 하이퍼 링크 생성
- b, i, small, sub, sup, ins, del: 글자 모양 지정

※ br 태그는 다른 글자 태그 내부에 삽입할 수 있지만 hr 태그는 안된다는 것입니다.

## 목록 태그
- ul : 순서가 없는 목록 생성
- ol : 순서삭 있는 목록 생성
- li : 목록 요소 생성
※ 아래의 P79 참조

## 테이블 태그
- table : 표 삽입
- tr : 표에 행 삽입
- th : 표의 제목 셀 생성
- td : 표의 일반 셀 생성

## 테이블 태그의 속성
- border(table): 표의 테두리 두께 지정
- colspan(th,td): 셀의 너비 지정
- rowspan(th,td): 셀의 높이 지정

## 미디어 태그
- img, audio, video: 이미지, 오디오, 비디오 삽입

## 특수문자 표기
- &nbsp; = 공백생성
<body>
	<h1>공백 &nbsp 글자</h1>
</body>

※ 권장하지는 않음.

## 웹 페이지 내부에 연결하기
<a href="http://www.naver.com">네이버</a>
1. 이동할 웹페이지
2. 출력 글자

※ 빈 링크
<li><a href="#">언론사 전체보기</a></li>
- 속성에 '#'을 넣어 처리

### P52(html 복습)
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

### P85
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	img{
		width:150px;
		border:2px dolid red;
	}
</style>
</head>
<body>
	<h1>media tag</h1>
	<img src="https://via.placeholder.com/150x100">
	<a href=""><img src="img/logo.png" alt="No img"></a>
	<br>
	<video src="mv/movie.mp4" controls="controls"></video>
</body>
</html>

### P95
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Form tag</h1>
	<form action="register" method="get">
		<label for="iid">ID</label>
		<input id="iid" type="text" name="id"><br>
		<label for="ipwd">PWD</label>
		<input id="ipwd" type="password" name="pwd"><br>
		<label for="iage">AGE</label>
		<input id="iage" type="number" name="age"><br>
		<input type="submit" value="register">
	</form>
</body>
</html>

### P98
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Form Tag</h1>
	<form action="register2" method="POST">
		<fieldset>
			<legend>회원 가입</legend>	
			ID<input type="text" name="id"><br>
			PWD<input type="password" name="pwd"><br>
			AGE<input type="number" name="age"><br>
			BIRTH<input type="date" name="birth"><br>
			GENDER<br>
			<input type="radio" name="gender" value="f">Female<br>
			<input type="radio" name="gender" value="m">Male<br>
			<input type="radio" name="gender" value="a">Aje<br>
			HOBBY<br>
			<input type="checkbox" name="hobby" value="s">Study<br>
			<input type="checkbox" name="hobby" value="t">Train<br>
			<input type="checkbox" name="hobby" value="e">Eat<br>
			Car<br>
			<select name="car">
				<option value="k1">K1</option>
				<option value="k2">K2</option>
				<option value="k3">K3</option>
			</select><br>
			Resume<br>
			<textarea name="resume" rows="10" cols="100"></textarea>
			<input type="hidden" name="loginid value="leejan">
			<input type="range" name="range" size="10" step="1"><br>
		</fieldset>
		<input type="submit" value="REGISTER">
		<input type="reset" value="RESET">
	</form>
</body>
</html>