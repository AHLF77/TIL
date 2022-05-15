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