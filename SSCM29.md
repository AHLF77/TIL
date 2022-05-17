# 0517강의(JavaScript)
## SSCM28 파일에서 이어짐.

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


# 0517강의(Jquery)