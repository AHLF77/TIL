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