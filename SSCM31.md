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
	
	@RequestMapping("/register_formimpl2")
	public String register_formimpl2(Model m, 
			String id, String pwd2, String named, String genderse) {
		System.out.printf("%s %s %s %s", id, pwd2, named, genderse);
		m.addAttribute("rid",id);
		m.addAttribute("rpwd2",pwd2);
		m.addAttribute("rnamed",named);
		m.addAttribute("rgenderse",genderse);
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
<meta charset="UTF-8">
<style>
	#result{
		width:500px;
		border:2px solid red;
	}
</style>

<script>

function checkid(id){
	$.ajax({
		url:'checkid',
		data:{'id': id},
		success:function(data){
			if(data == '1'){
				$('#iid').text('사용가능 합니다.');
			}else{
				$('#iid').text('사용 불가능 합니다.');
			}
		}
	});
};

function checkpwd(pwd){
	$.ajax({
		url:'checkpwd',
		data:{'pwd': pwd},
		success:function(data){
			if(data == '1'){
				$('#secondpwd').text('비밀번호가 일치합니다.');
			}else{
				$('#secondpwd').text('비밀번호가 일치하지 않습니다.');
			}
		}
	});
	
};

$(document).ready(function(){
	
	$('button').attr('disabled','disabled');
	
	$('button').click(function(){
		
		$('register_form').attr({
			'action':'register_formimpl',
			'method':'post'
		});
		$('register_form').submit();
	});
	
	$('input[name="pwd2"]').keyup(function(){
		var pwd = $('input[name="pwd"]').val();
		var pwd2 = $('input[name="pwd2"]').val();
		if(pwd == pwd2){
			$('#secondpwd').text('비밀번호가 일치합니다.');
			$('button').removeAttr('disabled');
		}else{
			$('#secondpwd').text('비밀번호가 일치하지 않습니다.');
		}
	});
	
	$('input[name="id"]').keyup(function(){
		var id = $(this).val();
		// 길이가 3자리 미만이면 span에 "3자리 이상이어야 합니다." 출력
		if(id.length < 3){
			$('#iid').text('3자리 이상이어야 합니다.')
		}else{
			$('#iid').text('');
			checkid(id);
		}
	});
	
	
});
</script>
<h1>AJ03 Main</h1>
<form id="register_form">
ID<input type="text" name="id"><span id="iid"></span><br>
PWD1<input type="password" name="pwd"><span id="firstpwd"></span><br>
PWDCheck<input type="password" name="pwd2"><span id="secondpwd"></span><br>
<button>REGISTER</button>
</form>
```

- jq04
```html

```

- registerok
```html
<meta charset="UTF-8">

<h1>Register SUCCESS</h1>
<h2><span th:text="${rnamed}"></span>님 가입을 환영합니다.</h2>
<h2><span th:text="${rid}"></span></h2>
<h2><span th:text="${rpwd2}"></span></h2>
<h2><span th:text="${rgenderse}"></span></h2>
```