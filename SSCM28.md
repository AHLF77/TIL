# 0516강의(JavaScript)

### src/main/java(com.shop.controller)
- MainController
```java
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController{
	
	@RequestMapping("/")
	public String main(Model m) {
		return "main";
	}
	

}

```

- JsController
```java
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JsController {
	
	@RequestMapping("/js")
	public String js(Model m) {
		m.addAttribute("center", "js/center");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js01")
	public String js01(Model m) {
		m.addAttribute("center", "js/js01");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js02")
	public String js02(Model m) {
		m.addAttribute("center", "js/js02");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js03")
	public String js03(Model m) {
		m.addAttribute("center", "js/js03");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js04")
	public String js04(Model m) {
		m.addAttribute("center", "js/js04");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js05")
	public String js05(Model m) {
		m.addAttribute("center", "js/js05");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js06")
	public String js06(Model m) {
		m.addAttribute("center", "js/js06");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js07")
	public String js07(Model m) {
		m.addAttribute("center", "js/js07");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js08")
	public String js08(Model m) {
		m.addAttribute("center", "js/js08");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js09")
	public String js09(Model m) {
		m.addAttribute("center", "js/js09");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	
}

```


### template
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
<script>

</script>
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
	    >

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

- center
```html
<meta charset="UTF-8">

<h1>환영 합니다.</h1>
<p>Lorem consequat.</p>
<hr>
<h3>Test</h3>
<p>Lorem ipsum...</p>
```

- left
```html
<meta charset="UTF-8">
<p><a href="#">main1</a></p>
<p><a href="#">main2</a></p>
<p><a href="#">main3</a></p>
```

- login
```html
<div class="container">
  <div class="col-sm-offset-2 col-sm-10">
  	<h2>Horizontal form</h2>
  </div>
  <form class="form-horizontal" action="/action_page.php">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Email:</label>
      <div class="col-sm-6">
        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-6">          
        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <div class="checkbox">
          <label><input type="checkbox" name="remember"> Remember me</label>
        </div>
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">Submit</button>
      </div>
    </div>
  </form>
</div>

```

#### template/js
- center
```html
<meta charset="UTF-8">

<h1>JS Main</h1>
```

- js01
```html
<meta charset="UTF-8">
<style>
	#hh2{
		background-color: black;
	}
</style>

<script>

function h2(){
	alert('Alert.............');
	var h2 = document.querySelector('#hh2');	
	h2.innerHTML = 'replace text';
};
window.onload = function(){
	var h3 = document.querySelector('#hh3');
	var i = 10;
	var j = 20;
	var result = i + j;
	console.log("i+j = " + result);
	h3.innerHTML = result;
};

</script>


<h1>JS01 Main</h1>
<button onclick="h2();">CLick</button>
<h2 id="hh2">Header2</h2>
<h3 id="hh3">Header3</h3>
```