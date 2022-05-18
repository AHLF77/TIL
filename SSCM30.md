# 0518강의(jQuery 강의)

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