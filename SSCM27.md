# 0514강의(반응형 웹)

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

```

- html5
```jsp

```

- css3
```jsp

```

- center
```jsp

```