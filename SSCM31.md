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