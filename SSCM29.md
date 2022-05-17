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