# 0512강의(CSS)

## CSS3 기초
- 특정 HTML 태그를 선택할 때 주로 사용
ex)
h1{color:red;}
- h1: 선택자
- color: 스타일 속성
- red: 스타일 값

### CSS3 선택자 종류
- 전체 선택자: *(ex: *)
- 태그 선택자: 태그(ex: h1)
- 아이디 선택자: #아이디(ex: #id)
- 클래스 선택자: .클래스(ex: .header)
- 후손 선택자: 선택자 선택자(ex: header h1)
- 자손 선택자: 선택자 > 선택자(ex: header > h1)
- 구조 선택자: 선택자:nth-child(수열)(ex: li:nth-child(2n+1))
※ 다 외우기는 어려우므로 w3c 공식 문서(http://www.w3.org/TR/CSS/)를 참고 바람.


## 기본 선택자
- 전체 선택자: HTML 페이지 내부의 태그를 모두 선택
- 태그 선택자: HTML 페이지 내부의 특정 태그 하나를 선택할 때 사용
- 아이디 선택자: 특정 ID 속성이 있는 태그 선택, 웹 표준에 ID 속성은 웹 페이지 내부에서 중복되면 안된다는 규정이 있으므로 아이디 선택지는 특정 태그 하나를 선택할 때 사용.
- 클래스: 특정 클래스가 있는 태그 선택

## 속성 선택자
- 선택자[속성]: 특정한 속성이 있는 태그 선택
- 선택자[속성=값]: 특정한 속성 내부 값이 특정 값과 같은 태그 선택
- input 태그는 이름이 모두 같지만 type 속성에 따라 형태가 다릅니다. 그래서 input 태그를 선택할 때는 속성 선택자를 많이 사용합니다.
- ex) <input type="password">

## 후손 선택자
- 특정한 태그의 후손을 선택할 때 사용
- 선택자A 선택자B: 선택자A의 후손인 선택자B 선택

## 자손 선택자
- 특정한 태그의 자손을 선택할 때 사용
- 선택자A > 선택자B: 선택자A의 자손인 선택자B 선택

## 반응 선택자
- 사용자 반응으로 생성되는 특정한 상태를 선택
- :active: 사용자가 마우스로 클릭한 태그 선택
- :hover: 사용자가 마우스 커서를 올린 태그 선택

## 구조 선택자
- 특정한 위치에 있는 태그를 선택할 때 사용
- :first-child: 형제 관계에서 첫 번째로 등장하는 태그 선택
- :last-child: 형제 관계에서 마지막으로 등장하는 태그 선택
- :nth-child(수열): 형제 관계에서 앞에서 수열 번째로 등장하는 태그 선택
- :nth-last-child(수열): 형제 관계에서 뒤에서 수열 번째로 등장하는 태그 선택

## CSS3 단위
### 크기 단위
- 크기는 CSS3에서 가장 많이 사용하는 단위
- % : 백분율 단위
- em : 배수 단위
- px : 픽셀 단위

### URL 단위
- 이미지나 글꼴 파일으르 불러올 때는 URL 단위를 사용.
- background-image 속성을 사용하면 배경 이미지를 지정할 수 있음.
- background-image 속성에는 url('경로') 형태로 URL 단위를 입력함.

## 박스 속성
- 사각 영역을 생성하고, 이 영역이나 이것을 둘러싼 테두리에 크기, 색상, 위치 등과 관련한 속성을 지정함으로써 스타일을 변경
- margin: 테두리와 다른 태그 사이의 테두리 바깥쪽 여백
- border: 테두리
- padding: 테두리와 글자 사이의 테두리 안쪽 여백, 배경색은 padding 영역까지만 적용
- width: 글자를 감싸는 영역의 가로 크기
- height: 글자를 감싸는 영역의 세로 크기

## 박스 테두리
- 테두리 두께는 border-width 속성을 사용하고, 테두리 형태는 border-style 속성을 사용하며, 테두리 색상은 border-color 속성을 사용.
- border-radius 속성을 사용하면 박스 테두리를 둥글게 만들 수 있음.(4개를 입력하면 각 값에 맞게 모서리가 둥글게 됨.)

## 가시 속성
- 태그가 화면에 보이는 방식을 지정
- 대표적으로 diplay 속성을 가지고 있음.
- 박스를 인라인 형식, 블록 형식, 인라인-블록 형식으로 사용
- display:키워드(none, block, inline, inline-block)
- none: 화면에 보이지 않음.
- block: 블록 박스 형식으로 지정
- inline: 인라인 박스 형식으로 지정
- inline-block: 블록과 인라인의 중간 형태로 지정.

※ display:none과 visibility:hidden 속성의 차이점
- display:none은 화면 상 어떤 영역을 차지하지 않고 완전히 삭제된 것처럼 보이게 하지만 visibility:hidden의 경우 해당 요소가 보이지 않을 뿐 요소가 존재하는 영역은 확실히 보이게 됨.

## 배경 속성
- background-image: 배경 이미지 삽입(ex: url('bg.png');)
- background-size: 배경 이미지 크기 지정(ex: size: 100%;, size: 100%, 250px;)
- background-repeat: 배경 이미지의 반복 형태 지정
1. repeat-x: x축 방향으로만 이미지가 반복
2. repeat-y: y축 방향으로만 이미지가 반복
- background-attachment: 배경 이미지의 부탁 형태 지정
1. scroll 기본값으로, 이미지가 요소에 상대적인 위치.
2. fixed  이미지가  윈도우 창에 상대적인 위치.
3. local 스크롤할 때 요소의 내용에 상대적인 위치.
- background-position: 배경 이미지의 위치 지정
1. left top 왼쪽 상단
2. left center 왼쪽 중앙
3. left bottom 왼쪽 맨아래
4. right top 오른쪽 상단
5. right center 오른쪽 중앙
6. right bottom 오른쪽 맨아래
7. center top 가운데 상단
8. center center 정 가운데
9. center bottom 가운데 맨아래
- background: 한 번에 모든 배경 속성 입력
1. ex: background: green url('brabbit.png') no-repeat fixed 50% 50%/100% 100%;  

## 글자 속성
- 글자 크기를 지정하는 font-size 속성
- 글꼴을 지정하는 font-family 속성에 설치된 글꼴 입력

### 글자의 스타일과 두께
- font-style 속성은 글자의 스타일을 지정
- font-weight 속성은 글자 두께를 지정

### 글자 정렬
- text-align 속성은 글자 정렬을 지정
- line-height 글자 높이를 지정하는 속성

### 링크 글자의 정렬
-text-decoration : 태그에 적용되는 밑줄을 제거해주는 역할

## 위치 속성
- X 좌표와 Y 좌표를 지정하여 절대 위치 좌표 방법과 요소를 입력한 순서에 따라 지정
- 요소의 위치 지정 형식을 지정할 때 position 속성을 사용
1. absolute: 절대 위치 좌표 설정
2. fixed: 화면을 기준으로 절대 위치 좌표 설정
3. relative: 초기 위치에서 상하좌우로 위치 이동
4. static: 위쪽에서 아래쪽으로 순서대로 배치
- left 속성과 top 속성 적용하기
1. left: 왼쪽에서 띄움
2. top: 해당 태그 요소의 위치를 상단을 기준으로 얼마나 밑에 위치시킬건지 지정
- z-index 속성: 도형 순서 변경, 요소를 겹치게 놓을 수 있음, 값은 정수이며, 숫자가 클 수록 위로 올라오고, 숫자가 작을 수록 아래로 내려감.

## 유동 속성
- float 속성을 이용하여 웹페이지의 레이아웃을 잡을 때 많이 사용
- 태그를 오른쪽이나 왼쪽에 붙일 수 있음.
- left: 태그를 왼쪽에 붙임
- right: 태그를 오른쪽에 붙임
- 적용 방법
<style>
	img{
		float: left;
	}
</style>
<style>
	.box{
		float: right;
	}
</style>

## 그림자 속성
- text-shadow: 글자에 그림자를 부여
ex) text-shadow (오른쪽) (아래) (흐림도) (색상)
- box-shadow: 박스에 그림자를 부여
ex) box-shadow (오른쪽) (아래) (흐림도) (색상)

## 그레디언트 속성
- 두 가지 이상의 색상을 혼합하는 채색 기능
- lineart-gradient (각도), (색상블록: 색상, 위치)

#### P135
- focus: 사용자가 입력 시 집중할 수 있도록 하는 문법
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
	input[type="text"]{
		background:pink;
	}
	input[name="pwd"]{
		background:pink;
	}
	input[type="submit"]{
		color:white;
		background:black;
	}
	input:focus{
		background:blue;
	}
</style>
</head>
<body>
	<h1>P135</h1>
	<form>
		ID<input type="text" name="id"><span>Mandatory</span><br>
		PWD<input type="password" name="pwd"><span>Mandatory</span><br>
		<input type="submit" value="login"><br>
	</form>
</body>
</html>
```


#### P139
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="css/p139.css">
<style>
	 
</style>
</head>
<body>
	<h1>p139</h1>
	<!-- HTML Comments -->
	<table id="tb1">
		<thead>
			<tr><th>header1</th><th>header2</th><th>header3</th></tr>
		</thead>
		<tbody>
		 	<tr><td>item1</td><td>item2</td><td>item3</td></tr>
		 	<tr><td>item1</td><td>item2</td><td>item3</td></tr>
		 	<tr><td>item1</td><td>item2</td><td>item3</td></tr>
		 	<tr><td>item1</td><td>item2</td><td>item3</td></tr>
		 	<tr><td>item1</td><td>item2</td><td>item3</td></tr>
		 	<tr><td>item1</td><td>item2</td><td>item3</td></tr>
		 	<tr><td>item1</td><td>item2</td><td>item3</td></tr>
		</tbody>	
	</table>
</body>
</html>
- <thead> 태그는 HTML 테이블에서 헤더 콘텐츠(header content)들을 하나의 그룹으로 묶을 때 사용
- <tbody> 태그는 HTML 테이블에서 내용 콘텐츠(body content)들을 하나의 그룹으로 묶을 때 사용

```css
@charset "EUC-KR";
@import url('https://fonts.googleapis.com/css2?family=Koulen&family=Oleo+Script+Swash+Caps:wght@700&display=swap');/*구글에서 내려받은 폰트 import*/
/* 2022.05.12 */

*{
 		margin:0;
 		padding:0;
 	  }
 	  #tb1{
 	  	border-collapse: collapse;
 	  	width:300px;
 	  	height:150px;
 	  	border:1px solid black;
 	  	font-family: 'Koulen', cursive;
 	  }
 	  #tb1 > thead > tr{
 	  	background:black;
 	  	color:white;
 	  	font-family: 'Koulen', cursive;
 	  }
 	  #tb1 > tbody > tr:nth-child(2n+1){
 	  	background:gray;
 	  	color:black;
 	  }
 	  #tb1 > tbody > tr > td:nth-child(3){
 	  font-family: 'Oleo Script Swash Caps', cursive;
 	  	text-align: right;
 	  }
 	  #tb1 > tbody > tr:hover{
 	  font-family: 'Oleo Script Swash Caps', cursive;
 	  	font-size: 1.2em;
 	  	font-weight:bold;
 	  	background:#9FC93C;
 	  	color:blue;
 	  }
 	  /* CSS Comments */
 	  body{
 	  	background-color: #5CD1E5;
 	  	background-image: url('img/bg.jpg');
 	  }
```

#### P159
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Koulen&family=Oleo+Script+Swash+Caps:wght@700&display=swap" rel="stylesheet">

<style>
	*{
		margin:0;
		padding:0;
	}
	div{
		width: 200px;
		height: 200px;
		border: 5px dashed red;
		border-radius: 50px 50px 50px 50px;
		margin: 20px;
		padding: 10px;
	}
	p{
		/* display: none;	
		 visibility: hidden; */
		 display: inline-bock;
		 width:100px;
		 font-family: 'Koulen', cursive;
	}
	span{
		font-family: 'Oleo Script Swash Caps', cursive;
	}
</style>
</head>
<body>

	<span>Span1</span><span>Span2</span>
	<p>Paragraph</p>
	<span>Span3</span><span>Span4</span>
	
	<h1>P159</h1>
	<div><span>TEXT</span></div>
</body>
</html>

#### P189

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Koulen&family=Oleo+Script+Swash+Caps:wght@700&display=swap" rel="stylesheet">
<style>
.big_bt{
	width:150px;
	height:70px;
	background-color: #FF6A00;
	border: 10px solid #FFFFFF;
	border-radius: 30px;
	box-shadow: 5px 5px 5px #A9A9A9;
}
.big_bt > a{
	text-decoration: none;
	color: black;
	display: block;
	line-height: 70px;
	text-align: center;
	font-size: 2em;
	font-weight: bold;
	font-family: 'Oleo Script Swash Caps', cursive;
}
.big_bt > a:hover{
	color:yellow;
}
.big_bt:hover{
	background: blue;
	border: 10px solid black;
}
</style>
</head>
<body>
	<h1>P189</h1>
	<div class="big_bt">
		<a href="#">Click</a>
	</div>
</body>
</html>

#### P192
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
	.box{
		width:100px;
		height:100px;
		position: absolute;
		opacity: 0.7;
		/*float:left;*/
	}
	.box:nth-child(1){
		background-color:red;
		left:10px;
		top:10px;
		z-index: 100;
	}
	.box:nth-child(2){
		background-color:blue;
		left:50px;
		top:50px;
		z-index: 10;
	}
	.box:nth-child(3){
		background-color:green;
		left:90px;
		top:90px;
		z-index: 20;
	}
</style>
</head>
<body>
	<div class="box"></div>
	<div class="box"></div>
	<div class="box"></div>
</body>
</html>
 
### day033 spring(html과 spring을 통한 웹 페이지 구성 예제)
- Main Controller
```java
package com.multi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/")
	public ModelAndView main(ModelAndView mv) {
		mv.addObject("w","Welcome!!!"); //main.jsp에 있는 c tag를 불러옴
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/p105")
	public ModelAndView p105(ModelAndView mv) {
		mv.setViewName("p105");
		return mv;
	}
	
	@RequestMapping("/p135")
	public ModelAndView p135(ModelAndView mv) {
		mv.setViewName("p135");
		return mv;
	}
	
	@RequestMapping("/p139")
	public ModelAndView p139(ModelAndView mv) {
		mv.setViewName("p139");
		return mv;
	}
	
	@RequestMapping("/p159")
	public ModelAndView p159(ModelAndView mv) {
		mv.setViewName("p159");
		return mv;
	}
	
	@RequestMapping("/p189")
	public ModelAndView p189(ModelAndView mv) {
		mv.setViewName("p189");
		return mv;
	}
	
	@RequestMapping("/p192")
	public ModelAndView p192(ModelAndView mv) {
		mv.setViewName("p192");
		return mv;
	}
	
}

```
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
/* Global CSS start -----------------------------*/
*{
	margin:0;
	padding:0;
}
a{
	text-decoration: none;
	color:black;
}
ul,ol{
	list-style: none;
}
/* Global CSS end -----------------------------*/

/* Header CSS start -----------------------------*/
header{
	width:600px;
	height:100px;
	background: red;
	margin:0 auto;
}
header > h1 {
	text-align: center;
	line-height: 100px;
}
/* Header CSS end -----------------------------*/

/* Nav CSS start -----------------------------*/
nav{
	width:600px;
	height:30px;
	background:pink;
	margin:0 auto;	
}
nav > ul{
	width:500px;
	margin:0 auto;	
}
nav > ul > li{
	float:left;
	width:125px;
	line-height: 30px;
}
nav > ul > li > a{
	display: block;
	text-align: center;
}
/* Nav CSS end -----------------------------*/

/* Section CSS start -----------------------------*/
section{
	width:600px;
	height:500px;
	background:gray;
	margin:0 auto;
}
section > aside{
	float:left;
}
section #left_aside{
	width:100px;
	height:500px;
	background: yellow;
}
section #center_aside{
	width:400px;
	height:500px;
	background: white;
}
section #right_aside{
	width:100px;
	height:500px;
	background:gray;
}
/* Section CSS end -----------------------------*/

/* Footer CSS start -----------------------------*/
footer{
	width:600px;
	height:30px;
	background:black;
	margin:0 auto;
}
/* Footer CSS end -----------------------------*/

</style>
</head>
<body>
	<header>
	<h1>HTML5 & CSS3.0</h1>
	</header>
	<nav>
		<ul>
			<li><a href="">HTML5</a></li>
			<li><a href="">CSS3</a></li>
			<li><a href="">JavaScript</a></li>
			<li><a href="">AJAX</a></li>
		</ul>
	</nav>
	<section>
		<aside id="left_aside"></aside>
		<aside id="center_aside"></aside>
		<aside id="right_aside"></aside>
	</section>
	<footer></footer>
</body>
</html>
```

### day03ws 과제(애플 홈페이지 예제)
- MainController 
```java
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@RequestMapping("/")
	public ModelAndView main(ModelAndView mv) {
		mv.setViewName("main");
		return mv;
	}
}

```
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
/* Global CSS start -----------------------------*/
*{
	margin:0;
	padding:0;
}
a{
	text-decoration: none;
	color:black;
}
ul,ol{
	list-style: none;
}
/* Global CSS end -----------------------------*/

/* Header CSS start -----------------------------*/
header{
	width:600px;
	height:100px;
	background: red;
	margin:0 auto;
}
header > h1 {
	text-align: center;
	line-height: 100px;
}
/* Header CSS end -----------------------------*/

/* Nav CSS start -----------------------------*/
nav{
	width:600px;
	height:30px;
	background:pink;
	margin:0 auto;	
}
nav > ul{
	width:500px;
	margin:0 auto;	
}
nav > ul > li{
	float:left;
	width:125px;
	line-height: 30px;
}
nav > ul > li > a{
	display: block;
	text-align: center;
}
/* Nav CSS end -----------------------------*/

/* Section CSS start -----------------------------*/
section{
	width:600px;
	height:500px;
	background:gray;
	margin:0 auto;
}
section > aside{
	float:left;
}
section #left_aside{
	width:100px;
	height:500px;
	background: yellow;
}
section #center_aside{
	width:400px;
	height:500px;
	background: white;
}
section #right_aside{
	width:100px;
	height:500px;
	background:gray;
}
/* Section CSS end -----------------------------*/

/* Footer CSS start -----------------------------*/
footer{
	width:600px;
	height:30px;
	background:black;
	margin:0 auto;
}
/* Footer CSS end -----------------------------*/

</style>
</head>
<body>
	<header>
	<h1>HTML5 & CSS3.0</h1>
	</header>
	<nav>
		<ul>
			<li><a href="">HTML5</a></li>
			<li><a href="">CSS3</a></li>
			<li><a href="">JavaScript</a></li>
			<li><a href="">AJAX</a></li>
		</ul>
	</nav>
	<section>
		<aside id="left_aside"></aside>
		<aside id="center_aside"></aside>
		<aside id="right_aside"></aside>
	</section>
	<footer></footer>
</body>
</html>
```

- main css
```css
@charset "UTF-8";
@import url('https://fonts.googleapis.com/css2?family=Anton&family=Koulen&family=Oleo+Script+Swash+Caps:wght@700&family=Water+Brush&display=swap');

/* Global CSS start ------------------------------------- */
* {
	margin: 0;
	padding: 0;	
}

a {
	text-decoration: none;
	color: #0B615E;
	font-family: 'Oleo Script Swash Caps', cursive;
	font-size: 106.3%;
	
}

ul, ol {
	list-style: none;
}

button {
    margin: 10px;
    padding: 30px;
    margin: 20px 15px 20px 15px;
}
/* Global CSS end --------------------------------------- */

/* Header CSS start ------------------------------------- */
header {
	width: 100%;
	height: 100px;
	background: white;
	font-size: 200%;
	margin: 0 auto;
	text-align: center;
}
header>h1>img{
	height: 100px;
}
/* Header CSS end --------------------------------------- */

/* Nav CSS start ------------------------------------- */
nav {
	width: 100%;
	height: 50px;
	background: #FFFACD;
	margin: 0 auto;
}

nav>ul {
	width: 500px;
	margin: 0 auto;
}

nav>ul>li {
	float: left;
	width: 125px;
	line-height: 50px;
}

nav>ul>li>a {
	display: block;
	text-align: center;
}


nav>ul>li>a:hover {
	color: blue;
	font-size: 1.5em;
}
/* Nav CSS end --------------------------------------- */

/* Section CSS start ------------------------------------- */
section {
	width: 100%;
	height: 600px;
	background: gray;
	margin: 0 auto;
}

section > aside {
	float: left;
}

section > #left_aside {
	width: 16%;
	height: 600px;
	background: #8258FA;
}
section > #left_aside>.btn_1{
	position: relative;
    border: none;
    min-width: 300px;
    min-height: 50px;
    background: linear-gradient(
        90deg,
        rgba(129, 230, 217, 1) 0%,
        rgba(79, 209, 197, 1) 100%
    );
    border-radius: 1000px;
    color: darkslategray;
    cursor: pointer;
    box-shadow: 12px 12px 24px rgba(79, 209, 197, 0.64);
    font-weight: 700;
    transition: 0.3s;
    
}

section > #left_aside >.btn_1:hover{
	    transform: scale(1.2);
	}
section > #left_aside >.btn_1:hover:after{
	content: "";
    width: 30px;
    height: 30px;
    border-radius: 100%;
    border: 6px solid #00ffcb;
    position: absolute;
    z-index: -1;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    animation: ring 1.5s infinite;
	}

section > #left_aside>.btn_2{
	position: relative;
    border: none;
    min-width: 300px;
    min-height: 50px;
    background: linear-gradient(
        90deg,
        rgba(129, 230, 217, 1) 0%,
        rgba(79, 209, 197, 1) 100%
    );
    border-radius: 1000px;
    color: darkslategray;
    cursor: pointer;
     box-shadow: 12px 12px 24px rgba(79, 209, 197, 0.64);
    font-weight: 700;
    transition: 0.3s;
}

section > #left_aside >.btn_2:hover{
	    transform: scale(1.2);
	}
section > #left_aside >.btn_2:hover:after{
	content: "";
    width: 30px;
    height: 30px;
    border-radius: 100%;
    border: 6px solid #00ffcb;
    position: absolute;
    z-index: -1;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    animation: ring 1.5s infinite;
	}

section > #left_aside>.btn_3{
	position: relative;
    border: none;
    min-width: 300px;
    min-height: 50px;
    background: linear-gradient(
        90deg,
        rgba(129, 230, 217, 1) 0%,
        rgba(79, 209, 197, 1) 100%
    );
    border-radius: 1000px;
    color: darkslategray;
    cursor: pointer;
     box-shadow: 12px 12px 24px rgba(79, 209, 197, 0.64);
    font-weight: 700;
    transition: 0.3s;
}

section > #left_aside >.btn_3:hover{
	    transform: scale(1.2);
	}
section > #left_aside >.btn_3:hover:after{
	content: "";
    width: 30px;
    height: 30px;
    border-radius: 100%;
    border: 6px solid #00ffcb;
    position: absolute;
    z-index: -1;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    animation: ring 1.5s infinite;
	}

section > #center_aside {
	width: 68%;
	height: 600px;
	background: white;
}
section > #center_aside > div > img{
	width: 100%;
	height: 600px;
}

section > #right_aside {
	width: 16%;
	height: 600px;
	background: #0431B4;
}


/* Section CSS end --------------------------------------- */

/* Footer CSS start --------------------------------------- */
footer {
	height: 30px;
	background: #6E6E6E;
}
/* Footer CSS end --------------------------------------- */

```


# 반응형 웹에 대한 내용은 다음 SSCM27에서 다루도록 하자.