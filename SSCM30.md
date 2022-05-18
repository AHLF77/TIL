# 0518강의(jQuery 강의)

- jQController
```java
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JqController {
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model m, String id, String pwd) {
		System.out.println(id+" "+pwd);
		if(id.equals("qq") && pwd.equals("11")) {
			m.addAttribute("loginid",id);
			m.addAttribute("center", "jq/loginok");
		}else {
			m.addAttribute("center", "jq/loginfail");
		}
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/registerimpl")
	public String registerimpl(Model m, 
			String name, String pwd, String ch, String optradio, int sel) {
		System.out.printf("%s %s %s %s %d", name, pwd, ch, optradio, sel);
		m.addAttribute("rname", name);
		m.addAttribute("center", "jq/registerok");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/signimpl")
	public String signimpl(Model m, String id, String pwd) {
		System.out.println(id+" "+pwd);
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq")
	public String jq(Model m) {
		m.addAttribute("center", "jq/center");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq01")
	public String jq01(Model m) {
		m.addAttribute("center", "jq/jq01");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq02")
	public String jq02(Model m) {
		m.addAttribute("center", "jq/jq02");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq03")
	public String jq03(Model m) {
		m.addAttribute("center", "jq/jq03");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq04")
	public String jq04(Model m) {
		m.addAttribute("center", "jq/jq04");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq05")
	public String jq05(Model m) {
		m.addAttribute("center", "jq/jq05");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq06")
	public String jq06(Model m) {
		m.addAttribute("center", "jq/jq06");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq07")
	public String jq07(Model m) {
		m.addAttribute("center", "jq/jq07");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq08")
	public String jq08(Model m) {
		m.addAttribute("center", "jq/jq08");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq09")
	public String jq09(Model m) {
		m.addAttribute("center", "jq/jq09");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq10")
	public String jq10(Model m) {
		m.addAttribute("center", "jq/jq10");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq11")
	public String jq11(Model m) {
		m.addAttribute("center", "jq/jq11");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq12")
	public String jq12(Model m) {
		m.addAttribute("center", "jq/jq12");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq13")
	public String jq13(Model m) {
		m.addAttribute("center", "jq/jq13");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
	@RequestMapping("/jq14")
	public String jq14(Model m) {
		m.addAttribute("center", "jq/jq14");
		m.addAttribute("left", "jq/left");
		return "main";
	}

	@RequestMapping("/jq15")
	public String jq15(Model m) {
		m.addAttribute("center", "jq/jq15");
		m.addAttribute("left", "jq/left");
		return "main";
	}
	
}

```
- left
```html
<meta charset="UTF-8">

<p><a href="jq01">jq01</a></p>
<p><a href="jq02">jq02</a></p>
<p><a href="jq03">jq03</a></p>
<p><a href="jq04">jq04</a></p>
<p><a href="jq05">jq05</a></p>
<p><a href="jq06">jq06</a></p>
<p><a href="jq07">jq07</a></p>
<p><a href="jq08">jq08</a></p>
<p><a href="jq09">jq09</a></p>
<p><a href="jq10">jq10</a></p>
<p><a href="jq06">jq11</a></p>
<p><a href="jq07">jq12</a></p>
<p><a href="jq08">jq13</a></p>
<p><a href="jq09">jq14</a></p>
<p><a href="jq10">jq15</a></p>
```

- jq05
```html
<meta charset="UTF-8">
<style>
	#result{
		width:300px;
		border: 2px solid red;
	}

</style>
<script>
function display(d){
	var txt = '';
	$(d).each(function(index, item){
		txt += '<h3>';
		txt += item.id +' '+ item.name+' '+item.age;
		txt += '</h3>';
	});
	
	
	/*	자바 스크립트를 이용한 방식
	for(var i in d){
		txt += '<h3>';
		txt += d[i].id+' '+d[i].name+' '+d[i].age;
		txt += '</h3>';
	};
	*/
	$('#result').html(txt);
};

function getdata(){
	
	var data = [
		{id:'id01',name:'lee',age:10},
		{id:'id02',name:'kim',age:20},
		{id:'id03',name:'han',age:30},
		{id:'id04',name:'soo',age:40},
		{id:'id05',name:'koo',age:50}
	];
	display(data);
};

$(document).ready(function(){
	$('#getdata').click(function(){
		getdata();
	});
	$('#show').click(function(){
		$('#result').slideDown();
	});
	$('#hide').click(function(){
		$('#result').slideUp();
	});
});	
</script>

<h1>JQ05 Main</h1>

<button id="getdata">GET DATA</button>
<button id="show">Show</button>
<button id="hide">Hide</button>

<div id="result"></div>
```

- jq06
```html
<meta charset="UTF-8">
<script>
$(document).ready(function(){
	var txt = $('h1').text();
	txt += ':New';
	$('h1').text(txt);
	$('h2 > a').attr('href','http://www.naver.com');
});
</script>

<h1>JQ06 Main</h1>

<h1>Header1</h1>
<h2><a href="#">Click</a></h2>
<form>
	
</form>

```

- jq07
```html
<meta charset="UTF-8">
<script>
$(document).ready(function(){
	$('#name_help').hide();
	$('#pwd_help').hide();
	
	$('#name').keyup(function(){
		$('#name_help').hide();
	});
	
	$('#pwd').keyup(function(){
		$('#pwd_help').hide();
	});
	
	$("#register_bt").click(function(){
		
		var name = $('#name').val();
		var pwd = $('#pwd').val();
		
		if(name == ''){
			$('#name_help').show();
			$('#name').focus();
			return;
		}
		
		if(pwd == ''){
			$('#pwd_help').show();
			$('#pwd').focus();
			return;
		}
		
		$("#register_form").attr({
			'action':'registerimpl',
			'method':'get'
		});
		$("#register_form").submit();
	
	});
});
</script>

<div class="container col-sm-6">
	<h1>Register</h1>
	<form id="register_form">
	<div class="form-group">
	  <label for="name">Name:</label>
	  <input type="text" name="name" class="form-control" id="name" placeholder="Input Name">
	  <span id="name_help" class="help-block">아이디를 기입해주세요.</span>
	</div>
	<div class="form-group">
	  <label for="pwd">Password:</label>
	  <input type="password" name="pwd" class="form-control" id="pwd" placeholder="Input Password">
	  <span id="pwd_help" class="help-block">비밀번호를 기입해 주세요.</span>
	</div>
	<div class="form-group">
		<label class="checkbox-inline"><input type="checkbox" name="ch" value="op1">Option1</label>
		<label class="checkbox-inline"><input type="checkbox" name="ch" value="op2">Option2</label>
		<label class="checkbox-inline"><input type="checkbox" name="ch" value="op3">Option3</label>
	</div>
	<div class="radio">
  		<label><input type="radio" name="optradio" value="op1" checked>Option 1</label>
	</div>
	<div class="radio">
  		<label><input type="radio" name="optradio" value="op2">Option 2</label>
	</div>
		<div class="radio">
  		<label><input type="radio" name="optradio" value="op3">Option 3</label>
	</div>
	<div class="form-group">
  		<label for="sel1">Select list:</label>
  		<select class="form-control" id="sel1" name="sel">
    		<option value="1">1</option>
    		<option value="2">2</option>
    		<option value="3">3</option>
    		<option value="4">4</option>
 		 </select>
	</div>
	<button id="register_bt" type="button" class="btn btn-success">REGISTER</button>
	</form>
</div>
```

- jq08
```html
<meta charset="UTF-8">
<script>
function getdata(){
	$('#name').val('lee');
	$('#name').attr('disabled', 'disabled');
	$('#pwd').val('1111');
	$('input[name="ch"]:checkbox[value="op1"]').attr('checked',true);
	$('input[name="ch"]:checkbox[value="op2"]').attr('checked',true);
	$('input[name="ch"]:checkbox[value="op3"]').attr('checked',true);
	$('input[name="optradio"]:radio[value="op2"]').attr('checked',true);
	$('input[name="optradio"]:radio[value="op3"]').attr('checked',false);
	$('#sel1').val('3').attr('selected','selected');
};

$(document).ready(function(){
	
	getdata();
	
	$('#name_help').hide();
	$('#pwd_help').hide();
	
	$('#name').keyup(function(){
		$('#name_help').hide();
	});
	
	$('#pwd').keyup(function(){
		$('#pwd_help').hide();
	});
	
	$("#register_bt").click(function(){
		
		var name = $('#name').val();
		var pwd = $('#pwd').val();
		
		if(name == ''){
			$('#name_help').show();
			$('#name').focus();
			return;
		}
		
		if(pwd == ''){
			$('#pwd_help').show();
			$('#pwd').focus();
			return;
		}
		
		$("#register_form").attr({
			'action':'registerimpl',
			'method':'get'
		});
		$("#register_form").submit();
	
	});
});
</script>
<div class="container col-sm-6">
	<h1>JQ08 Register</h1>
	<form id="register_form">
	<div class="form-group">
	  <label for="name">Name:</label>
	  <input type="text" name="name" class="form-control" id="name" placeholder="Input Name">
	  <span id="name_help" class="help-block">아이디를 기입해주세요.</span>
	</div>
	<div class="form-group">
	  <label for="pwd">Password:</label>
	  <input type="password" name="pwd" class="form-control" id="pwd" placeholder="Input Password">
	  <span id="pwd_help" class="help-block">비밀번호를 기입해 주세요.</span>
	</div>
	<div class="form-group">
		<label class="checkbox-inline"><input type="checkbox" name="ch" value="op1">Option1</label>
		<label class="checkbox-inline"><input type="checkbox" name="ch" value="op2">Option2</label>
		<label class="checkbox-inline"><input type="checkbox" name="ch" value="op3">Option3</label>
	</div>
	<div class="radio">
  		<label><input type="radio" name="optradio" value="op1" checked>Option 1</label>
	</div>
	<div class="radio">
  		<label><input type="radio" name="optradio" value="op2">Option 2</label>
	</div>
		<div class="radio">
  		<label><input type="radio" name="optradio" value="op3">Option 3</label>
	</div>
	<div class="form-group">
  		<label for="sel1">Select list:</label>
  		<select class="form-control" id="sel1" name="sel">
    		<option value="1">1</option>
    		<option value="2">2</option>
    		<option value="3">3</option>
    		<option value="4">4</option>
 		 </select>
	</div>
	<button id="register_bt" type="button" class="btn btn-success">REGISTER</button>
	</form>
</div>
```