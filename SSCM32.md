# 0520강의(jQuery AJAX 강의)

- AJAXController
```java
package com.shop.controller;

import java.util.Date;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	
	@RequestMapping("/getdata")
	public Object getdata() {
		//[{},{},.....] = JSON 형태라고 함.
		JSONArray ja = new JSONArray();
		for(int i = 0; i<6; i++){
			JSONObject jo = new JSONObject();
			jo.put("id", "id0"+i);
			jo.put("name", "james"+i);
			Random r = new Random();
			int a = r.nextInt(50)+1;			
			jo.put("age", a);
			ja.add(jo);
		}
		return ja;	
	}
}

```

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