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

## 입력 양식 태그
- 사용자에게 정보를 입력받는 요소
- form 태그는 method 속성의 방식으로 action 속성 장소에 데이터를 전달 
<form action="전송위치" method="전송방식">
</form>
1. 전달 위치: 데이터를 전달할 목적지
2. 전송방식
- GET: 주소에 데이터를 직접 입력해 전달(비밀 유지 불가능)
- POST: 별도의 방법을 사용해 데이터를 해당 주소로 전달(비밀 유지 가능)

### 입력 양식의 종류
1. form
- 보이지 않음: 입력 양식의 시작과 끝 표시

2. input
- text : 글자 입력 양식 생성
- button : 버튼 생성
- checkbox : 체크 박스 생성
- file : 파일 입력 양식 생성
- hidden : 해당 내용 표시 안함
- image : 이미지 형태 생성
- password : 비밀번호 입력 양식 생성
- radio : 라디오 버튼 생성
- reset : 초기화 버튼 생성
- submit : 제출 버튼 생성

3. textarea
- cols/rows : 여러 행의 글자 입력 양식 생성, cols는 너비를 지정하고 rows는 높이를 지정

4. Select optgroup option
- 선택 양식 생성  
- 옵션 그룹화
- 옵션 생성

5. fieldset legend
- 입력 양식의 그룹 지정
- 입력 양식 그룹의 이름 지정

## HTML5 문서 구조화
- 공간 분할 태그 : 훨씬 더 자유롭게 
1. div 태그: 블록 형식으로 공간 분할(각 태그가 한 행을 모두 차지)
2. span 태그: 인라인 형식으로 공간 분할(글자크기 만큼 차지, 왼쪽에서 오른쪽으로 쌓임)

## 시맨틱 태그
- 컴퓨터 프로그램이 코드를 읽고 의미를 인식할 수 있는 지능형 웹
1. header: 머리말
2. nav: 하이퍼링크들을 모아 둔 내비겡이션
3. aside: 본문 흐름에 벗어나는 노트나 팁
4. section: 문서의 장이나 절에 해당하는 내용
5. article: 본문과 독립적인 콘텐트 영역
6. footer: 꼬리말

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
	<!-- 동영상을 불러오는 동안 다른 이미지 보여 주기 -->
	<img src="https://via.placeholder.com/150x100">
	<!-- 로고 이미지 삽입-->
	<a href=""><img src="img/logo.png" alt="No img"></a>
	<br>
	<!-- 웹 브라우저 제약이 없도록 비디오 삽입 -->
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

- 한 항목만 선택하기: select태그는 목록으로 보여 주는 항목 중 하나 또는 여러 개를 선택할 때 사용하는 입력 양식 요소
- 여러 항목 선택하기: multiple 속성을 사용 
- 선택 옵션 묶기: optgroup 태그를 사용해 선택 옵션을 그룹으로 묶을 수 있습니다.
- legend 태그: 입력 양식을 그룹으로 묶고 이름을 지정할 수 있음.


### Maincontroller
```java
package com.multi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@RequestMapping("/register")
	public void register(String id, String pwd, int age) {
		System.out.println(id+""+pwd+""+age);
	}
	
	@RequestMapping("/register2")
	public void register2(String id, String pwd, int age, 
			String birth, String gender, 
			String hobby, String car,
			String resume,
			String loginid, int range) {
		System.out.println(id+""+pwd+""+age);
		System.out.println(birth+""+gender+""+hobby);
		System.out.println(resume+""+loginid+""+range);
	}

}

```

## 로그인 예제 살펴보기
- MainController
```java
package com.multi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

	@RequestMapping("/")
	public ModelAndView main(ModelAndView mv) { // Main 홈 화면
		mv.addObject("w", "Welcome !!");
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/register_view")
	public String register_view(ModelAndView mv) { // Register 글자 클릭 시
		return "register.jsp";
	}
	
	@RequestMapping("/login_view")
	public ModelAndView login_view(ModelAndView mv) { // Login 글자 클릭 시 로그인 페이지로 이동
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(ModelAndView mv,
			String id, String pwd) { // Login의 ID, PWD 값 비교
		if(id.equals("qqq") && pwd.equals("111")) { 
			mv.addObject("ii", id);
			mv.setViewName("loginok");
		}else {
			mv.setViewName("loginfail");
		}
		return mv;
	}
}

```

- 로그인
```javascript
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Login Page</h1>
	<form action="login" method="post">
		<label for="iid">ID</label>
		<input id="iid" type="text" name="id"><br>
		<label for="ipwd">PWD</label>
		<input id="ipwd" type="password" name="pwd"><br>
		<input type="submit" value="login">
	</form>
</body>
</html>
```

- 로그인 실패 시
```javascript
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>LOGIN FAIL</h1>
</body>
</html>
```

- 로그인 성공 시
```javascript
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>LOGIN SUCCESS</h1>
	<h2>${ii } 환영합니다.</h2>
</body>
</html>
```

- 메인 페이지
```javascript
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Main Page</h1>
	<h2>${w} </h2>
	<h3><a href="register_view">REGISTER</a></h3>
	<h3><a href="login_view">LOGIN</a></h3>
</body>
</html>
```

- 등록 페이지
```javascript
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Register Page</h1>
</body>
</html>
```

- 시맨틱 태그
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
	*{   /* margin: 테두리(border)와 이웃하는 요소 사이의 간격인 마진 영역의 크기*/
	/* padding: 내용(content)과 테두리(border) 사이의 간격인 패딩 영역의 크기*/
		margin:0;
		padding:0;	
	}

	h1{
		/*h1에 해당되는 텍스트값*/
		/*background: 뒷 배경색*/
		color:red;
		background: black;
	}
	#hh1{ 
		color:blue;
		background: yellow;
	}
		
	.c1{
		/*c1에 해당되는 텍스트값*/
		color:green;
		background:pink;
	}
	
	a,p{
	color:blue;
	}
	
	a:hover{
		/*a에 마우스를 올렸을 때 효과*/
		color:white;
		background:black;
	}

</style>
</head>
<body>
	<h1>P105</h1>
	<h1 id="hh1">Header1</h1>
	<h1 class="c1">Header1</h1>
	<h2>Header2</h2>
	<h2 class="c1">Header2</h2>
	<a href="#">Click</a>
	<a href="#">Click</a>
	<span>Span1</span>
	<span>Span2</span>
	<p>Paragraph</p>
</body>
</html>
```