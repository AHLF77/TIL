# 0519 배운 내용 요약

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
			display(data);
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

- aj04
```html
<meta charset="UTF-8">
<style>
.reg_bt{
	margin-top: 50px;
	width: 100px;
  	height: 50px;
    border: 0;
  	outline: none;
  	border-radius: 40px;
  	background: linear-gradient(to left, rgb(255, 77, 46), rgb(255, 155, 47));
  	color: white;
  	font-size: 1.5em;
  	letter-spacing: 2px;
}
</style>

<script>
$(document).ready(function () {
	
	// 등록 버튼
	$("#register_bt").click(function(){
		
		var id = $('#id').val();
		var pwd2 = $('#pwd2').val();
		var named = $('#named').val();
		var genderse = $('#select').val();
		
		
		$("#register_form").attr({
			'action':'register_formimpl2',
			'method':'post'
		});
		$("#register_form").submit();
	
	});
	
	
	// 아이디 제약 조건 설정
	$('input[name="id"]').keyup(function(){
		var id = $('input[name="id"]').val();
		if(id.length < 7){
			$('#iid').text('아이디를 7자리 이상 설정해 주세요.');
		}else{
			$('#iid').hide();
		}
	});
	
	// 비밀번호 제약 조건 설정(특수문자, 영문, 숫자 8자리 이상)
	$('input[name="pwd1"]').keyup(function(){
		var pwd1 = $('input[name="pwd1"]').val();
		var pwdcheck = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,25}$/;
		if(false === pwdcheck.test(pwd1)){
			$('#pwd').text('비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
		}else if(true === pwdcheck.test(pwd1)){
			$('#pwd').text('양호');
		}else{
			$('#pwd').hide();
		}
		
	});
	
	// 비밀번호 확인
	$('input[name="pwd2"]').keyup(function(){
		var pwd1 = $('input[name="pwd1"]').val();
		var pwd2 = $('input[name="pwd2"]').val();
		if(pwd1 != pwd2){
			$('#recheck').text('비밀번호가 일치하지 않습니다.');
		}else{
			$('#recheck').hide();
		}
	});
	
});
</script>

<h1>AJ04 Register Main</h1>
<div class="container col-sm-8">
	<form id="register_form">
	
	<div class="form-group">
	<label for="id">아이디</label><br>
	<input type="text" name="id" placeholder="아이디를 입력하세요."><span id="iid"></span><br>
	</div>
	
	<div class="form-group">
	<label for="pwd1">비밀번호</label><br>
	<input type="password" name="pwd1" placeholder="비밀번호를 입력하세요."><span id="pwd"></span><br>
	</div>
	
	<div class="form-group">
	<label for="pwd2">비밀번호 재확인</label><br>
	<input type="password" name="pwd2"><span id="recheck"></span><br>
	</div>
	
	<div class="form-group">
	<label for="named">이름</label><br>
	<input type="text" name="named"><span id="nm"></span><br>
	</div>
	
	<div class="form-group">
	<label for="select">성별</label><br>
	<select class="form-control" id="select" name="gendersel">
	<option value="male">male</option>
	<option value="female">female</option>
	
	</select>
	</div>
	<button id="register_bt" class="reg_bt">등록</button>
	</form>
</div>
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