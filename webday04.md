# 0513 배운 내용 요약

## 반응형 웹이란?
 - 웹 페이지 하나로도 데스크톱, 태블릿PC, 스마트폰에 맞게 디자인이 자동으로 반응해서 변경되는 웹 페이지를 의미
 - 개발: 미디어 쿼리를 사용

## 반응형 웹을 위한 설정
 1. 뷰포트 설정
  - meta 태그는 웹 페이지에 투가 정보를 제공할 때 사용
  - name 속성에 viewport가 입력된 meta 태그를 viewport meta태그 라고 함.
<meta name="viewport" content="user-scalable=no,initial-scale=1, maximum-scale=1">
 - 입력할 수 있는 태그 값
 | 값   | 설명   | 예 |
 | ------ | ------ | --------- |
 | width  | 화면너비 | width=240 |
 | height | 화면 높이 | height=800 |
 | initial-scale | 초기 확대 비율 | initial-scale=2.0 |
 | user-scalable | 확대 및 축소 가능 여부 | user-scalable=no |
 | minimum-scale | 최소 축소 비율 | minimum-scale=1.0 |
 | maximum-scale | 최대 확대 비율 | maximum-scale=2.0 |
 | target-densitydpi | DPI 지정 | target-densitydpi=medium-dpi |

 2. 미디어 쿼리 설정
  - 웹 페이지가 표시되는 장치에 '반응'하도록 하여 반응형 웹을 구현할 수 있도록 함
  - 이때 사용하는 것이 미디어 쿼리임.
  ① @-규칙@-rule이란 스타일시트 내부에서 특정한 규칙을 표현하는데 사용
  ② 외부 스타일을 가져오는 @import규칙, 글똘을 추가로 정의하는 @font-face 규칙 등이 있음
  - @media 규칙
  @media (<미디어 쿼리>){
    <CSS 코드>
  }

  - media 속성
  <link rel="stylesheet" href="<파일이름>" media="<미디어 쿼리>">

## 반응형 웹 패턴
 - 부트스트랩 웹 사이트를 이용하여 설계 가능.
  1. 너비가 큰 화면에서는 메뉴가 왼쪽, 너비가 좁은 화면에서는 메뉴가 위쪽
  2. 너비가 큰 화면에서는 메뉴가 오른쪽, 너비가 좁은 화면에서는 메뉴가 위쪽
  3. 너비가 큰 화면에서는 메뉴가 왼쪽, 너비가 좁은 화면에서는 메뉴가 아래쪽
  4. 너비가 큰 화면에서는 메뉴가 오른쪽, 너비가 좁은 화면에서는 메뉴가 오른쪽

 - 위치를 잡기 위한 규칙
  1. HTML 태그 구성은 모바일 장치를 기준으로 함.
  2. float 태그의 left와 right를 활용해 적당한 위치에 놓아둠

### 반응형 웹 예제
 - main
```java
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@RequestMapping("/")
	public ModelAndView main(ModelAndView mv) {
		mv.addObject("center", "center");
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/media")
	public ModelAndView media(ModelAndView mv) {
		mv.setViewName("media");
		return mv;
	}
	
	@RequestMapping("/html5")
	public ModelAndView html5(ModelAndView mv) {
		mv.addObject("center", "html5");
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/js")
	public ModelAndView js(ModelAndView mv) {
		mv.addObject("center", "js");
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/css3")
	public ModelAndView css3(ModelAndView mv) {
		mv.addObject("center", "css3");
		mv.setViewName("main");
		return mv;
	}
}

```

- main
```css
@charset "EUC-KR";

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
section > .center{
	width:550px;
	height:450px;
	background:white;
	display: inline-block;
	margin:25px;
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
```

- media
- 화면 크기가 바뀔 때 마다, 색깔이 변함
- 빨강: 스마트폰
- 초록: 태블릿PC 세로 화면
- 파랑: 태블릿PC 가로 화면

```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport" content="user-scalable=no, initial-scale=1, maximun-scale=1">
<title>Insert title here</title>
<style>
	@media screen and (max-width: 767px){
		body{
			background: red;
		}
	}
	@media screen and (min-width: 768px) and (max-width: 959px){
		body{
			background: green;
		}
	}
	@media screen and (min-width: 960px){
		body{
			background: blue;
		}
	}
</style>
</head>
<body>
	<h1>Media Page</h1>
	<p>이순신(李舜臣, 1545년 4월 28일 ~ 1598년 12월 16일 
	(음력 11월 19일))은 조선 중기의 무신이었다. 본관은 덕수(德水), 
	자는 여해(汝諧), 시호는 충무(忠武)였으며, 한성 출신이었다. 
	문반 가문 출신으로 1576년(선조 9년) 무과(武科)에 급제하여 그 관직이 동구비보 권관, 훈련원 봉사, 발포진 
	수군만호, 조산보 만호, 
	전라좌도 수군절도사를 거쳐 정헌대부 삼도수군통제사에 이르렀다.
	</p>
</body>
</html>
```

- main
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="css/main.css">
<style>
	
</style>
</head>
<body>
	<header>
	<h1><a href="/">HTML5 & CSS3.0</a></h1>
	</header>
	<nav>
		<ul>
			<li><a href="html5">HTML5</a></li>
			<li><a href="css3">CSS3</a></li>
			<li><a href="js">JavaScript</a></li>
			<li><a href="media">Media</a></li>
		</ul>
	</nav>
	<section>
		<jsp:include page="${center }.jsp"></jsp:include>
	</section>
	<footer></footer>
</body>
</html>
```

- js
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<div class="center">
	<h1>JavaScript Page</h1>
</div>
```

- html5
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<div class="center">
	<h1>HTML5 Page</h1>
</div>
```

- css3
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<div class="center">
	<h1>CSS3 Page</h1>
</div>
```

- center
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<div class="center">
	<h1>Main Center</h1>
</div>
```

## Spring을 통한 반응형 웹
- MainController
```java
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@RequestMapping("/")
	public String main(Model m) {
		m.addAttribute("center", "center");
        /* /라는 url에 매핑되도록 하였고 특이한 점은 Model을 매개변수로 전달받고 있습니다. 
        매개변수로 전달받은 m.addAttribute("key", "value"); 메소드를 이용하여 view 전달할 데이터를 key, value쌍으로하여 전달할 수 있습니다.*/
        // m은 크게 신경을 안써도 된다.
		return "main";
	}
}

```

- WearController
```java
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WearController {

	@RequestMapping("/wear")
	public String wear(Model m) {
		m.addAttribute("center", "wear");
		return "main";
	}
	
	@RequestMapping("/wear1")
	public String wear1(Model m) {
		m.addAttribute("center", "wear");
		m.addAttribute("scenter", "wear/wear1");
		return "main";
	}
	
	@RequestMapping("/wear2")
	public String wear2(Model m) {
		m.addAttribute("center", "wear");
		m.addAttribute("scenter", "wear/wear2");
		return "main";
	}
	
	@RequestMapping("/wear3")
	public String wear3(Model m) {
		m.addAttribute("center", "wear");
		m.addAttribute("scenter", "wear/wear3");
		return "main";
	}
}

```

- HomeController
```java
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public String home(Model m) {
		m.addAttribute("center", "home");
		return "main";
	}
	
	@RequestMapping("/home1")
	public String home1(Model m) {
		m.addAttribute("center", "home");
		m.addAttribute("scenter", "home/home1");
		return "main";
	}

	@RequestMapping("/home2")
	public String home2(Model m) {
		m.addAttribute("center", "home");
		m.addAttribute("scenter", "home/home2");
		return "main";
	}
	
	@RequestMapping("/home3")
	public String home3(Model m) {
		m.addAttribute("center", "home");
		m.addAttribute("scenter", "home/home3");
		return "main";
	}
}

```

- FoodController
```java
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FoodController {
	
	@RequestMapping("/food")
	public String food(Model m) {
		m.addAttribute("center", "food");
		return "main";
	}
	
	@RequestMapping("/food1")
	public String food1(Model m) {
		m.addAttribute("center", "food");
		m.addAttribute("scenter", "food/food1");
		return "main";
	}
	
	@RequestMapping("/food2")
	public String food2(Model m) {
		m.addAttribute("center", "food");
		m.addAttribute("scenter", "food/food2");
		return "main";
	}
	
	@RequestMapping("/food3")
	public String food3(Model m) {
		m.addAttribute("center", "food");
		m.addAttribute("scenter", "food/food3");
		return "main";
	}

}

```

- main
```css
@charset "EUC-KR";

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
section > #left_aside{
	width:100px;
	height:500px;
	background: #FFA500;
}
section > .center{
	width:550px;
	height:450px;
	background:white;
	display: inline-block;
	margin:25px;
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
```

### template
- template/food
<div>
	<h1>Food1 Main</h1>
</div>
<div>
	<h1>Food2 Main</h1>
</div>
<div>
	<h1>Food3 Main</h1>
</div>
<div>
	<h1>Food Main</h1>
</div>

- template/home
<div>
	<h1>Home Main</h1>
</div>
<div>
	<h1>Home1 Main</h1>
</div>
<div>
	<h1>Home2 Main</h1>
</div>
<div>
	<h1>Home3 Main</h1>
</div>

- template/wear
<div>
	<h1>Wear Main</h1>
</div>
<div>
	<h1>Wear1 Main</h1>
</div>
<div>
	<h1>Wear2 Main</h1>
</div>
<div>
	<h1>Wear3 Main</h1>
</div>

- main
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
<header>
	<h1><a href="/">Shoping Mall</a></h1>
	</header>
	<nav>
		<ul>
			<li><a href="home">home</a></li>
			<li><a href="food">food</a></li>
			<li><a href="wear">wear</a></li>
			<li><a href="media">Media</a></li>
		</ul>
	</nav>
	<section th:include="${center}">
	</section>
	<footer></footer>
</body>
</html>

- home
<aside id="left_aside">
	<ul>
		<li><a href="home1">home1</a></li>
		<li><a href="home2">home2</a></li>
		<li><a href="home3">home3</a></li>
	</ul>
</aside>
<aside id="right_aside" th:include="${scenter} ? ${scenter} : @{home/homemain}">
	
</aside>

- center
<div class="center">
	<h1>Main Center</h1>
</div>

- food
<aside id = "left_aside">
	<ul>
		<li><a href="food1">food1</a></li>
		<li><a href="food2">food2</a></li>
		<li><a href="food3">food3</a></li>
	</ul>
</aside>
<aside id="right_aside" th:include="${scenter} ? ${scenter} : @{food/foodmain}">

</aside>

- wear
<aside id="left_aside">
	<ul>
		<li><a href="wear1">wear1</a></li>
		<li><a href="wear2">wear2</a></li>
		<li><a href="wear3">wear3</a></li>
	</ul>
</aside>
<aside id="right_aside" th:include="${scenter} ? ${scenter} : @{wear/wearmain}">
</aside>

