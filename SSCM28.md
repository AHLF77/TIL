# 0516강의(JavaScript)

- MainController
```java
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController{
	
	@RequestMapping("/")
	public String main(Model m) {
		return "main";
	}
	

}

```

- JsController
```java
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JsController {
	
	@RequestMapping("/js")
	public String js(Model m) {
		m.addAttribute("center", "js/center");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js01")
	public String js01(Model m) {
		m.addAttribute("center", "js/js01");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js02")
	public String js02(Model m) {
		m.addAttribute("center", "js/js02");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js03")
	public String js03(Model m) {
		m.addAttribute("center", "js/js03");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js04")
	public String js04(Model m) {
		m.addAttribute("center", "js/js04");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js05")
	public String js05(Model m) {
		m.addAttribute("center", "js/js05");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js06")
	public String js06(Model m) {
		m.addAttribute("center", "js/js06");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js07")
	public String js07(Model m) {
		m.addAttribute("center", "js/js07");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js08")
	public String js08(Model m) {
		m.addAttribute("center", "js/js08");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	@RequestMapping("/js09")
	public String js09(Model m) {
		m.addAttribute("center", "js/js09");
		m.addAttribute("left", "js/left");
		return "main";
	}
	
	
}

```


### template
- center
```html
<meta charset="UTF-8">

<h1>환영 합니다.</h1>
<p>Lorem consequat.</p>
<hr>
<h3>Test</h3>
<p>Lorem ipsum...</p>
```

- left
```html
<meta charset="UTF-8">
<p><a href="#">main1</a></p>
<p><a href="#">main2</a></p>
<p><a href="#">main3</a></p>
```

- login
```html
<div class="container">
  <div class="col-sm-offset-2 col-sm-10">
  	<h2>Horizontal form</h2>
  </div>
  <form class="form-horizontal" action="/action_page.php">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Email:</label>
      <div class="col-sm-6">
        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-6">          
        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <div class="checkbox">
          <label><input type="checkbox" name="remember"> Remember me</label>
        </div>
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">Submit</button>
      </div>
    </div>
  </form>
</div>

```