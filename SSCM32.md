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
	var str = '';
	$(data).each(function(index,item){
		str += '<h3>';
		str += item.id+' '+item.name+' '+item.age;
		str += '</h3>';
	});
	$('#result').html(str);
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
	getdata();
	setInterval(() => {
		getdata();
	}, 3000);
});
</script>

<h1>AJ05 Main</h1>
<button>GET DATA</button>
<div id="result"></div>
```