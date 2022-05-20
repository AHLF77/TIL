# 0520강의(jQuery AJAX 강의)

- aj05
```html
<meta charset="UTF-8">

<style>
	#result{
		width:300px;
		border: 2px solid red;
	}
</style>

<script>
function display(data){
	
};

function getdata(){
	$.ajax({
		url:'getdata',
		success:function(data){
			//[{},{},.....] = JSON 형태라고 함.
			display(data);
		}
	});
};

$(document).ready(function(){
	$('button').click(function(){
		getdata();
	});
});
</script>

<h1>AJ05 Main</h1>
<button>GET DATA</button>
<div id="result"></div>
```