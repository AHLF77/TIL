# day03-1ws 과제(애플 홈페이지)

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
- main
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/main.css">


</head>

<body>

	<header>
		<h1><img src="img/logo.png" alt="No img"></h1>
	</header>

	<nav>
		<ul>
			<li><a href="macbook.html">MacBook</a></li>
			<li><a href="ipad.html">iPad</a></li>
			<li><a href="iphone.html">iPhone</a></li>
			<li><a href="watch.html">Watch</a></li>
		</ul>
	</nav>

	<section>
		<aside id="left_aside">
		
			<button class="btn_1",
			onclick="window.open('https://www.apple.com/kr/retail/')">
			Market Location</button>
		
			<button class="btn_2",
			onclick="window.open('https://www.apple.com/kr/privacy/')">
			Privacy</button>
			
			<button class="btn_3", 
			onclick="window.open('https://www.youtube.com/user/Apple')">
			Youtube</button>
			
		</aside>
		<aside id="center_aside">
		 <div>
		 <img src="img/applesupport.jpg" alt="No img">
		 </div>
		</aside>
		<aside id="right_aside">
			<div>
				<img src="https://via.placeholder.com/330X600">
			</div>
		</aside>
	</section>

	<footer> Copyright © 2022 Apple.Inc.fd.h </footer>
</body>
</html>
```

- main
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

- ipad.html
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" bordercolor="blue" align = "center" >
    	<tr bgcolor="blue" align ="center" width = 600px;>
		<p><td colspan = "5" span style="color:white"><img src="img/ipad.png" alt="No img" width ="1380" height="300"></td></p>
    	</tr>
    	<tr align = "center" bgcolor="skybule">
    	<td width="276"><b>모델종류</b></td>
		<td width="276"><b>아이패드 프로</b></td>
		<td width="276"><b>아이패드 에어</b></td>
		<td width="276"><b>아이패드 9세대</b></td>
		<td width="276"><b>아이패드 미니 6세대</b></td>
    	</tr>
    	<tr>
		<td>프로세서</td>
		<td>M1</td>
		<td>M1</td>
		<td>A13</td>
		<td>A15</td>
    	</tr>
    	<tr>
		<td>디스플레이</td>
		<td>12.9inch, 11inch</td>
		<td>10.9inch</td>
		<td>10.2inch</td>
		<td>8.3inch</td>
    	</tr>
    	<tr>
		<td>카메라</td>
		<td>1300만 화소</td>
		<td>1000만 화소</td>
		<td>800만 화소</td>
		<td>700만 화소</td>
    	</tr>
    	<tr>
		<td>무게</td>
		<td>450g, 420g</td>
		<td>410g</td>
		<td>460g</td>
		<td>350g</td>	
    	</tr>
    	<tr>
		<td>배터리</td>
		<td>10,000mAh, 9,000mAh</td>	
		<td>8000mAh</td>
		<td>7000mAh</td>
		<td>5000mAh</td>
		<tr>
		<td>단자정보</td>
		<td>C type</td>	
		<td>C type</td>
		<td>Lightning 8-pin</td>
		<td>C type</td>
    	</tr>
    	<tr>
		<td>가격</td>
		<td>120만원, 100만원</td>	
		<td>70만원</td>
		<td>50만원</td>
		<td>60만원</td>
    	</tr>
    	<tr>
		<td>색상</td>
		<td>실버, 스페이스 그레이</td>	
		<td>블루, 스타라이트</td>
		<td>실버, 스페이스 그레이</td>
		<td>블루, 스타라이트</td>
    	</tr>
		</table>
</body>
</html>
```

- iphone.html
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" bordercolor="blue" align = "center" >
    	<tr bgcolor="blue" align ="center" width = 600px;>
		<p><td colspan = "5" span style="color:white"><img src="img/iphone.jpg" alt="No img" width ="1380" height="300"></td></p>
    	</tr>
    	<tr align = "center" bgcolor="skybule">
    	<td width="276"><b>모델종류</b></td>
		<td width="276"><b>아이폰13 Pro MAX, 아이폰13 Pro</b></td>
		<td width="276"><b>아이폰13, 아이폰13 mini</b></td>
		<td width="276"><b>아이폰se 3세대</b></td>
		<td width="276"><b>아이폰12, 아이폰12 mini</b></td>
    	</tr>
    	<tr>
		<td>프로세서</td>
		<td>A15</td>
		<td>A15</td>
		<td>A15</td>
		<td>A14</td>
    	</tr>
    	<tr>
		<td>디스플레이</td>
		<td>6.7inch, 6.1inch</td>
		<td>6.1inch, 5.4inch</td>
		<td>4.7inch</td>
		<td>6.1inch, 5.4inch<//td>
    	</tr>
    	<tr>
		<td>카메라</td>
		<td>1600만 화소</td>
		<td>1200만 화소</td>
		<td>800만 화소</td>
		<td>1200만 화소</td>
    	</tr>
    	<tr>
		<td>무게</td>
		<td>250g, 200g</td>
		<td>180g, 170g</td>
		<td>112g</td>
		<td>160g, 140g</td>	
    	</tr>
    	<tr>
		<td>배터리</td>
		<td>4500mAh, 4000mAh</td>	
		<td>3600mAh, 3000mAh</td>
		<td>2000mAh</td>
		<td>3400mAh, 2800mAh</td>
		<tr>
		<td>단자정보</td>
		<td>Lightning 8-pin</td>	
		<td>Lightning 8-pin</td>
		<td>Lightning 8-pin</td>
		<td>Lightning 8-pin</td>
    	</tr>
    	<tr>
		<td>가격</td>
		<td>160만원, 120만원</td>	
		<td>95만원, 85만원</td>
		<td>55만원</td>
		<td>70만원, 60만원</td>
    	</tr>
    	<tr>
		<td>색상</td>
		<td>그래파이트, 블루, 그린, 레드, 골드, 실버, 퍼플</td>	
		<td>블루, 그린, 스타라이트, 화이트, 퍼플, 블랙</td>
		<td>실버, 스페이스 그레이</td>
		<td>그린, 스타라이트, 화이트, 퍼플, 블랙</td>
    	</tr>
		</table>
		<div>
      <button onclick="history.back()">뒤로가기</button>
      </div>
</body>
</html>
```
- macbook.html
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" bordercolor="blue" align = "center" >
    	<tr bgcolor="blue" align ="center" width = 600px;>
		<p><td colspan = "3" span style="color:white"><img src="img/macbook.jpg" alt="No img" width ="1380" height="300"></td></p>
    	</tr>
    	<tr align = "center" bgcolor="skybule">
    	<td width="276"><b>모델종류</b></td>
		<td width="276"><b>맥북 프로 16inch</b></td>
		<td width="276"><b>맥북 프로 14inch</b></td>
    	</tr>
    	<tr>
		<td>프로세서</td>
		<td>M1 MAX</td>
		<td>M1 PRO</td>
    	</tr>
    	<tr>
		<td>디스플레이</td>
		<td>16.2inch</td>
		<td>14.2inch</td>
    	</tr>
    	<tr>
		<td>카메라</td>
		<td>1080만 화소</td>
		<td>1080만 화소</td>
    	</tr>
    	<tr>
		<td>무게</td>
		<td>2.3kg</td>
		<td>1.6kg</td>
    	</tr>
    	<tr>
		<td>배터리</td>
		<td>Li-Polymer 100 Wh</td>	
		<td>Li-Polymer 70 Wh</td>
		<tr>
		<td>단자정보</td>
		<td>C type, HDMI</td>	
		<td>C type, HDMI</td>
    	</tr>
    	<tr>
		<td>가격</td>
		<td>350만원</td>	
		<td>260만원</td>
    	</tr>
    	<tr>
		<td>색상</td>
		<td>실버, 스페이스 그레이</td>	
		<td>실버, 스페이스 그레이</td>
    	</tr>
		</table>
		<div>
      <button onclick="history.back()">뒤로가기</button>
      </div>
</body>
</html>
```

- watch.html
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" bordercolor="blue" align = "center" >
    	<tr bgcolor="blue" align ="center" width = 600px;>
		<p><td colspan = "4" span style="color:white"><img src="img/watch.png" alt="No img" width ="1380" height="300"></td></p>
    	</tr>
    	<tr align = "center" bgcolor="skybule">
    	<td width="276"><b>모델종류</b></td>
		<td width="276"><b>애플워치7</b></td>
		<td width="276"><b>애플워치SE</b></td>
		<td width="276"><b>애플워치3</b></td>
    	</tr>
    	<tr>
		<td>프로세서</td>
		<td>S7+U1</td>
		<td>S6</td>
		<td>S4</td>
    	</tr>
    	<tr>
		<td>디스플레이</td>
		<td>2inch</td>
		<td>1.9inch</td>
		<td>1.7inch</td>
    	</tr>
    	<tr>
		<td>카메라</td>
		<td>없음</td>
		<td>없음</td>
		<td>없음</td>
    	</tr>
    	<tr>
		<td>무게</td>
		<td>38g</td>
		<td>36g</td>
		<td>40g</td>
    	</tr>
    	<tr>
		<td>배터리</td>
		<td>303mAh</td>	
		<td>8000mAh</td>
		<td>7000mAh</td>
		<tr>
		<td>단자정보</td>
		<td>없음(마그네틱)</td>	
		<td>없음(마그네틱)</td>
		<td>없음(마그네틱)</td>
    	</tr>
    	<tr>
		<td>가격</td>
		<td>47만원</td>	
		<td>37만원</td>
		<td>25만원</td>
    	</tr>
    	<tr>
		<td>색상</td>
		<td>미드나이트, 스타라이드, 그린, 블루, 레드</td>	
		<td>스페이스 그레이, 실버, 골드</td>
		<td>스페이스 그레이, 실버, 골드</td>
    	</tr>
		</table>
		<div>
      <button onclick="history.back()">뒤로가기</button>
      </div>
</body>
</html>
```