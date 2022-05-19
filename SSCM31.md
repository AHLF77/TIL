# 0519강의(jQuery AJAX 강의)

- AJAXController
```java
package com.shop.controller;

import java.util.Date;

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
}
```

- AjController
```java
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AjController {

	@RequestMapping("/ajax")
	public String ajax(Model m) {
		m.addAttribute("center", "ajax/center");
		m.addAttribute("left", "ajax/left");
		return "main";
	}
	
	@RequestMapping("/register_formimpl")
	public String register_formimpl(Model m,String id, String pwd) {
		System.out.println(id+" "+pwd);
		m.addAttribute("center", "ajax/registerok");
		m.addAttribute("left", "ajax/left");
		return "main";
	}
	
	@RequestMapping("/aj01")
	public String aj01(Model m) {
		m.addAttribute("center", "ajax/aj01");
		m.addAttribute("left", "ajax/left");
		return "main";
	}
	
	@RequestMapping("/aj02")
	public String aj02(Model m) {
		m.addAttribute("center", "ajax/aj02");
		m.addAttribute("left", "ajax/left");
		return "main";
	}
	
	@RequestMapping("/aj03")
	public String aj03(Model m) {
		m.addAttribute("center", "ajax/aj03");
		m.addAttribute("left", "ajax/left");
		return "main";
	}
	
	@RequestMapping("/aj04")
	public String aj04(Model m) {
		m.addAttribute("center", "ajax/aj04");
		m.addAttribute("left", "ajax/left");
		return "main";
	}
	
	@RequestMapping("/aj05")
	public String aj05(Model m) {
		m.addAttribute("center", "ajax/aj05");
		m.addAttribute("left", "ajax/left");
		return "main";
	}
	
	@RequestMapping("/aj06")
	public String aj06(Model m) {
		m.addAttribute("center", "ajax/aj06");
		m.addAttribute("left", "ajax/left");
		return "main";
	}
	
	@RequestMapping("/aj07")
	public String aj07(Model m) {
		m.addAttribute("center", "ajax/aj07");
		m.addAttribute("left", "ajax/left");
		return "main";
	}
	
	@RequestMapping("/aj08")
	public String aj08(Model m) {
		m.addAttribute("center", "ajax/aj08");
		m.addAttribute("left", "ajax/left");
		return "main";
	}
	
	@RequestMapping("/aj09")
	public String aj09(Model m) {
		m.addAttribute("center", "ajax/aj09");
		m.addAttribute("left", "ajax/left");
		return "main";
	}
	
	@RequestMapping("/aj10")
	public String aj10(Model m) {
		m.addAttribute("center", "ajax/aj10");
		m.addAttribute("left", "ajax/left");
		return "main";
	}
}
```

- center
```html
<meta charset="UTF-8">

<h1>AJAX Main</h1>

```

- left
```html
<meta charset="UTF-8">

<p><a href="aj01">aj01</a></p>
<p><a href="aj02">aj02</a></p>
<p><a href="aj03">aj03</a></p>
<p><a href="aj04">aj04</a></p>
<p><a href="aj05">aj05</a></p>
<p><a href="aj06">aj06</a></p>
<p><a href="aj07">aj07</a></p>
<p><a href="aj08">aj08</a></p>
<p><a href="aj09">aj09</a></p>
<p><a href="aj10">aj10</a></p>
```

- aj01
```html
<meta charset="UTF-8">

<script>
function display(data){
	$('h3').text(data);
};

function getdata(){
	
	$.ajax({
		url: 'gettime',
		 success: function(data){
			 display(data);
		 },
		 error:function(e){
			 alert('Error'+e.responseText);
		 }
	});
	
};

$(document).ready(function() {
	timer = setInterval(function () {
		getdata();
	}, 1000);
});
</script>
<h1>AJ01 Main</h1>

<h3></h3>
```

- aj02
```html
<meta charset="UTF-8">
<style>
	#result{
		width:500px;
		border:2px solid red;
	}
</style>

<script>
function display(data) {
	var txt = '<h2>'+data+'</h2>';
	$('#result').html(txt);
};

function getdata(txt) {
	$.ajax({
		url:'search',
		data:{'s':txt}, 
		success:function(data){
			alert(data);
		}
	});
};

$(document).ready(function(){
	$('button').click(function(){
		var txt = $('#txt').val();
		getdata(txt);		
	});
	
});
</script>

<h1>AJ02 Main</h1>
<input type="text" id="txt">
<button>GETDATA</button>
<div id= "result"></div>
```

- aj03
```html

```