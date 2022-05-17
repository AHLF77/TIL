# 0517강의(JavaScript)
## SSCM28 파일에서 이어짐.

- js08
```html
<meta charset="UTF-8">
<style>
	#result{
		width: 300px;
		border: 2px solid red;
	}
</style>
<script>
function display(d){
	var txt = '';
	for(var i in d){
		txt += '<h2>';
		txt += d[i].id+' '+d[i].name+' '+d[i].age;
		txt += '</h2>';
	};
	
	document.querySelector('#result').innerHTML = txt;
};

function getdata(){
	var a = [
		{id:'id01',name:'lee',age:10},
		{id:'id02',name:'kim',age:20},
		{id:'id03',name:'han',age:30},
		{id:'id04',name:'soo',age:40},
		{id:'id05',name:'koo',age:50}
	];
	display(a);
};
</script>

<h1>JS08 Main</h1>
<button onclick="getdata();">GET DATA</button>
<div id="result"></div>
```

- js09
```html
<meta charset="UTF-8">

<script>
window.onload = function(){
	var c1 = document.querySelector('#c1');
	var c2 = document.querySelector('#c2');
	var c3 = document.querySelector('#c3');
	var h1 = document.querySelector('h1');
	
	h1.onclick = function(){
		var c = prompt('숫자를 입력 하세요?',0);
		this.innerHTML = c;
	};
	
	c1.onclick = function(){
		h1.innerHTML = 'Clicked c1';
	};
	
	c2.onclick = function(){
		var c = confirm('회원가입 가시겠습니까?');
		if(c == true){
			location.href='/';
		}	
	};

	c3.onclick = function(){
		var c = confirm('네이버로 가시겠습니까?');
		if(c == true){
			location.href='http://www.naver.com';
		}
	};
	
};
</script>

<h1>JS09 Main</h1>
<button id="c1">Click1</button>
<button id="c2">Click2</button>
<h2><a id="c3" href="#">Click</a></h2>

```

- js10
```html
<meta charset="UTF-8">
<script>
window.onload = function(){
	var hh1 = document.querySelector('#hh1');	
	var s1 = setInterval(function(){
		var txt = hh1.innerHTML;
		txt += '*';
		hh1.innerHTML = txt;
	}, 500);
	setTimeout(function(){
		clearInterval(s1);
		location.href="http://www.naver.com"
	}, 5000);
};
</script>

<h1>JS10 Main</h1>

<h1 id="hh1"></h1>
```

# 0517강의(jQuery)
- jQController
```java
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JqController {
	
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
	
	
}

```

#### template/jq
- jq01
```html
<meta charset="UTF-8">
<script>
$(document).ready(function(){
	$('h1').css('color', 'red');
	$('#hh1').html('<a href ="#">Click</a>');
	var txt = $('h1)').eq(0).text();
	$('h1').eq(1).text(txt);
	$('h1 > a').click(function(){
		alert('a');
	});
});	
</script>

<h1>JQ01 Main</h1>

<h1>Header1</h1>
<h1 id="hh1">Header2</h1>
```

- jq02
```html
<meta charset="UTF-8">
<style>
 .b{
 color: gray;
 background: blue;
 }
</style>
<script>
$(document).ready(function(){
	
	
	$('button').on({
		'click':function(){
			$(this).css({'color':'gray', 'background':'blue'});
		},
		'mouseenter':function(){
			$('h1').text('mouse enter');
			$(this).addClass('b');
		},
		'mouseleave':function(){
			$('h1').text('mouse leave');
			$(this).removeClass('b');
		}
	});
	/*
	$('button').mouseenter(function(){
		$('h1').text('mouse enter');	
	});
	$('button').mouseleave(function(){
		$('h1').text('mouse leave');
	});
	$('button').click(function(){
		$(this).css({'color':'gray', 'background':'blue'});
	});
	*/
	
	$('h2').hover(function(){
		$(this).addClass('b');
	},function(){
		$(this).removeClass('b');
	});
	
	$('input[name="id"]').focus(function(){
		$(this).addClass('b');
		$('form > span:eq(0)').text("ID를 입력 하시오.");
	});
	$('input[name="id"]').blur(function(){
		$(this).removeClass('b');
		var txt = $(this).val();
		if(txt == ''){
			$(this).focus();
		};
		if(txt.length == 5){
			$('form > span:eq(0)').text('ID는 5개 이상이어야 합니다.');
		};
	});
	
});
</script>

<h1>JQ02 Main</h1>

<h2>Header2</h2>
<button>Button</button>
<form>
ID<input type="text" name="id"><span></span><br>
PWD<input type="password" name="pwd"><span></span><br>
<input type="submit" name="login"><br>
</form>

```

```html

```

```html

```

```html

```

```html

```

```html

```

```html

```

```html

```

```html

```