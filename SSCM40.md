# 0607 강의
## day051

### src/main/java
#### com.multi.biz
- CustBiz
```java
package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.CustMapper;
import com.multi.vo.CustVO;

@Service("custbiz")
public class CustBiz implements Biz<String, CustVO>{

	@Autowired
	CustMapper dao;
	
	@Override
	public void register(CustVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(CustVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(String k) throws Exception {
		dao.delete(k);
	}

	@Override
	public CustVO get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<CustVO> get() throws Exception {
		return dao.selectall();
	}
	
}
```

- ProductBiz
```java
package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.ProductMapper;
import com.multi.vo.ProductVO;

@Service("productbiz")
public class ProductBiz implements Biz<Integer, ProductVO>{

	@Autowired
	ProductMapper dao;
	
	@Override
	public void register(ProductVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(ProductVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public ProductVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<ProductVO> get() throws Exception {
		return dao.selectall();
	}
	
	public int getcnt() throws Exception{
		return dao.selectcnt();
	}
	
}
```

#### com.multi.controller
- AJAXController
```java
package com.multi.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@RestController
public class AJAXController {
	
	@Autowired
	ProductBiz pbiz;
	
	@RequestMapping("/chartimpl")
	public Object chartimpl() {
		
		JSONObject jo = new JSONObject();
		JSONArray ja1 = new JSONArray();
		JSONArray ja2 = new JSONArray();
		
		List<ProductVO> list = null;
		
		try {
			list= pbiz.get();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		for (ProductVO p : list) {
			ja1.add(p.getName());
			ja2.add(p.getPrice());
		}
		jo.put("cate", ja1);
		jo.put("data", ja2);
		
		return jo;
	}
}
```

- CustController
```java
package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@Controller
@RequestMapping("/cust")
public class CustController {

	@Autowired
	CustBiz biz;
	
	@RequestMapping("")
	public ModelAndView main(ModelAndView mv) {
		mv.setViewName("main");
		mv.addObject("left", "cust/left");
		mv.addObject("center", "cust/center");
		return mv;
	}
	@RequestMapping("/register")
	public ModelAndView register(ModelAndView mv) {
		mv.setViewName("main");
		mv.addObject("left", "cust/left");
		mv.addObject("center", "cust/register");
		return mv;
	}
	@RequestMapping("/registerimpl")
	public ModelAndView registerimpl(ModelAndView mv,CustVO obj) {
		System.out.println(obj);
		mv.setViewName("main");
		mv.addObject("left", "cust/left");
		try {
			biz.register(obj);
			mv.addObject("center", "cust/registerok");
			mv.addObject("rcust", obj);
		} catch (Exception e) {
			mv.addObject("center", "cust/registerfail");
		}
		return mv;
	}
	@RequestMapping("/custselect")
	public ModelAndView custselect(ModelAndView mv) {
		mv.setViewName("main");
		mv.addObject("left", "cust/left");
		List<CustVO> list = null;
		try {
			list = biz.get();
			mv.addObject("center", "cust/custselect");
			mv.addObject("allcust", list);
		} catch (Exception e) {
			mv.addObject("center", "cust/registerfail");
		}
		return mv;
	}
	@RequestMapping("/custdetail")
	public ModelAndView detail(ModelAndView mv, String id) {
		mv.setViewName("main");
		mv.addObject("left", "cust/left");
		CustVO obj = null;
		try {
			obj = biz.get(id);
			mv.addObject("center", "cust/custdetail");
			mv.addObject("dcust", obj);
		} catch (Exception e) {
			mv.addObject("center", "cust/registerfail");
		}
		return mv;
	}
	@RequestMapping("/custdelete")
	public String custdelete(String id) {
		try {
			biz.remove(id);
		} catch (Exception e) {
				
		}
		return "redirect:custselect";
	}
	@RequestMapping("/custupdate")
	public ModelAndView custupdate(ModelAndView mv, String id) {
		CustVO cust = null;
		mv.setViewName("main");
		mv.addObject("left", "cust/left");
		try {
			cust = biz.get(id);
			mv.addObject("ucust", cust);
			mv.addObject("center", "cust/custupdate");
		} catch (Exception e) {
				
		}
		return mv;
	}
	@RequestMapping("/custupdateimpl")
	public String custupdateimpl(CustVO cust) {
		try {
			biz.modify(cust);
		} catch (Exception e) {
				
		}
		return "redirect:custdetail?id="+cust.getId();
	}
}
```

- MainController
```java
package com.multi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@Controller
public class MainController {

	@Autowired
	CustBiz cbiz;
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/login")
	public String login(Model m) {
		m.addAttribute("center", "login");
		return "main";
	}
	@RequestMapping("/logout")
	public String logout(Model m, HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "main";
	}
	
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model m, String id, String pwd, HttpSession session) {
		String next = "";
		CustVO cust = null;
		try {
			cust = cbiz.get(id);
			if(cust != null) {
				if(cust.getPwd().equals(pwd)) {
					session.setAttribute("logincust", cust);
					m.addAttribute("logincust", cust);
					next = "loginok";
				}else {
					throw new Exception();
				}
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			next="loginfail";
		}
	
		m.addAttribute("center", next);
		return "main";
	}
	
	
	
}
```

- ProductController
```java
package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@Controller
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductBiz pbiz;
	
	@RequestMapping("")
	public ModelAndView main(ModelAndView mv) {
		mv.setViewName("main");
		mv.addObject("left","product/left");
		mv.addObject("center","product/center");
		return mv;
	}
	
	@RequestMapping("/register")
	public String register(Model m) {
		m.addAttribute("left","product/left");
		m.addAttribute("center","product/register");
		return "main";
	}
	
	@RequestMapping("/registerimpl")
	public ModelAndView registerimpl(ModelAndView mv, ProductVO obj) {
		mv.setViewName("main");
		mv.addObject("left","product/left");
		try {
			pbiz.register(obj);
			int cnt = pbiz.getcnt();
			mv.addObject("cnt", cnt);
			mv.addObject("center","product/registerok");
		} catch (Exception e) {
			mv.addObject("center","product/registerfail");
		}
		return mv;
	}
	
	@RequestMapping("/productselect")
	public ModelAndView productselect(ModelAndView mv) {
		mv.setViewName("main");
		mv.addObject("left","product/left");
		mv.addObject("center","product/productselect");

		List<ProductVO> list = null;
		try {
			list = pbiz.get();
			mv.addObject("allproduct", list);
		} catch (Exception e) {
			
		}
		return mv;
	}
	
	@RequestMapping("productdetail")
	public ModelAndView productdetail(ModelAndView mv, Integer id) {
		ProductVO prod = null;
		try {
			prod = pbiz.get(id);
			mv.addObject("dproduct", prod);
		} catch (Exception e) {
			
		}
		mv.setViewName("main");
		mv.addObject("left","product/left");
		mv.addObject("center","product/productdetail");
		return mv;
	}
	
	@RequestMapping("productupdate")
	public ModelAndView productupdate(ModelAndView mv, Integer id) {
		mv.setViewName("main");
		mv.addObject("left","product/left");
		mv.addObject("center","product/productupdate");
		
		ProductVO prod = null;
		try {
			prod = pbiz.get(id);
			mv.addObject("uproduct", prod);
		} catch (Exception e) {
			
		}
		return mv;	
	}
	
	@RequestMapping("productupdateimpl")
	public String productupdateimpl(ProductVO prod) {
		String next= "productupdateok";
		try {
			pbiz.modify(prod);
		} catch (Exception e) {
			
		}
		return "redirect:productdetail?id="+prod.getId();
	}
	
	@RequestMapping("productdelete")
	public String productdelete(Integer id) {
		try {
			pbiz.remove(id);
		} catch (Exception e) {
			
		}
		return "redirect:productselect";	
	}
}
```

#### com.multi.frame
- Biz(interface)
```java
package com.multi.frame;

import java.util.List;

public interface Biz<K,V> {
	public void register(V v) throws Exception;
	public void modify(V v) throws Exception;
	public void remove(K k) throws Exception;
	public V get(K k) throws Exception;
	public List<V> get() throws Exception;
}
```

#### com.multi.mapper
- CustMapper(interface)
```java
package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.CustVO;

@Repository
@Mapper
public interface CustMapper {
	public void insert(CustVO cust) throws Exception;
	public void delete(String id) throws Exception;
	public void update(CustVO cust) throws Exception;
	public CustVO select(String id) throws Exception;
	public List <CustVO> selectall() throws Exception;
}

```

- ProductMapper(interface)
```java
package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.ProductVO;

@Repository
@Mapper
public interface ProductMapper {
	public void insert(ProductVO cust) throws Exception;
	public void delete(int id) throws Exception;
	public void update(ProductVO cust) throws Exception;
	public ProductVO select(int id) throws Exception;
	public List <ProductVO> selectall() throws Exception;
	public int selectcnt() throws Exception;
}
```

#### com.multi.mybatis
- custmapper
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org/DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.multi.mapper.CustMapper">
	
	<select id="select" parameterType="String" resultType="custVO">
		SELECT * FROM CUST WHERE ID=#{id}
	</select>
	<select id="selectall" resultType="custVO">
		SELECT * FROM CUST
	</select>
	<insert id="insert" parameterType="custVO">
		INSERT INTO CUST VALUES (#{id},#{pwd},#{name})
	</insert>
	<update id="update" parameterType="custVO">
		UPDATE CUST SET PWD=#{pwd},NAME=#{name} WHERE ID=#{id}
	</update>
	<delete id="delete" parameterType="String">
		DELETE FROM CUST WHERE ID=#{id}
	</delete>
	
</mapper>
```

- productmapper
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org/DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.multi.mapper.ProductMapper">
	
	<select id="select" parameterType="int" resultType="productVO">
		SELECT * FROM PRODUCT WHERE ID=#{id}
	</select>
	<select id="selectcnt" resultType="int">
		SELECT last_insert_id() as cnt
	</select>
	<select id="selectall" resultType="productVO">
		SELECT * FROM PRODUCT ORDER BY 1
	</select>
	<insert id="insert" parameterType="productVO">
		INSERT INTO PRODUCT VALUES (NULL, #{name},#{price},SYSDATE(),#{rate})
	</insert>
	<update id="update" parameterType="productVO">
		UPDATE PRODUCT SET NAME=#{name}, PRICE=#{price}, RATE=#{rate} WHERE ID=#{id}
	</update>
	<delete id="delete" parameterType="int">
		DELETE FROM PRODUCT WHERE ID=#{id}
	</delete>
	
</mapper>
```

#### com.multi.vo
- CustVO
```java
package com.multi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustVO {

	private String id;
	private String pwd;
	private String name;
}

```

- ProductVO
```java
package com.multi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductVO {
	private int id;
	private String name;
	private int price;
	private Date regdate;
	private double rate;
	
	public ProductVO(int id, String name, int price, double rate) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.rate = rate;
	}
}

```

### src/main/resources
#### messages
<!-- 중국, 영어, 한국어 세 가지의 언어를 설정하면 각 나라에 맞게 설정되어 출력-->
- message_en_US
```properties
home.welcome= Welcome to My Shopping Mall
tel=010-7777-8982
```

- message_ko_KR
```properties
home.welcome= \uD658\uC601\uD569\uB2C8\uB2E4. {0} {1}
tel=010-9999-9898
```

- message_zn_CN
```properties
home.welcome= Ni hao... 
tel=010-5562-2312
```

- message
```properties
home.welcome= {0}\uB2D8 \uD658\uC601 \uD569\uB2C8\uB2E4.
tel=010-0000-0000
```

#### templates
- main
```html
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-more.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2b21c3f8da84a6df87f98d92e21da425&libraries=services"></script>



<style>
   /* Remove the navbar's default margin-bottom and rounded borders */ 
   .navbar {
     margin-bottom: 0;
     border-radius: 0;
   }
   
   /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
   .row.content {height: 700px}
   
   /* Set gray background color and 100% height */
   .sidenav {
     padding-top: 20px;
     background-color: #f1f1f1;
     height: 100%;
   }
   
   /* Set black background color, white text and some padding */
   footer {
     background-color: #555;
     color: white;
     padding: 15px;
   }
   
   /* On small screens, set height to 'auto' for sidenav and grid */
   @media screen and (max-width: 767px) {
     .sidenav {
       height: auto;
       padding: 15px;
     }
     .row.content {height:auto;} 
   }
</style>


</head>
<body>
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	      <a class="navbar-brand" href="#">한글</a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav">
	        <li><a href="/">Home</a></li>
	        <li><a href="/cust/">Cust</a></li>
	        <li><a href="/product/">Product</a></li>
	      </ul>
	    
	      <ul class="nav navbar-nav navbar-right"> 	
	      	<li th:if="${session.logincust == null}"><a href="/login">
	      	    <span class="glyphicon glyphicon-log-in">
	      	    </span> Login</a></li>
	      	<li th:unless="${session.logincust == null}"><a href="/logout">
				<span th:text="${session.logincust.name}"></span>
				<span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
	      </ul>
	    
	    
	    </div>
	  </div>
	</nav>
	  
	<div class="container-fluid text-center">    
	  <div class="row content">
	    <div class="col-sm-1 sidenav"
	    th:insert="${left} ? ${left} : left">
	    </div>
	    <div class="col-sm-9 text-left" 
	    th:insert="${center} ? ${center} : center"> 
	    </div>
	    <div class="col-sm-2 sidenav">
	      <div class="well">
	        <p>ADS</p>
	      </div>
	      <div class="well">
	        <p>ADS</p>
	      </div>
	    </div>
	  </div>
	</div>
	
	<footer class="container-fluid text-center">
	  <p>Footer Text</p>
	</footer>
</body>
</html>
```

- center
```html
<meta charset="UTF-8">

<h1 th:text="#{home.welcome('hi','han')}">환영 합니다.</h1>
<p th:text="#{tel}">Lorem consequat.</p>
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
<script>
$(document).ready(function(){
	$('#login_bt').click(function(){
		
		$('#login_form').attr({
			'method':'post',
			'action':'/loginimpl'
		});
		
		$('#login_form').submit();
	});
});
</script>

<div class="container">
  <div class="col-sm-offset-2 col-sm-10">
  	<h2>Login form</h2>
  </div>
  <form class="form-horizontal" id="login_form">
    <div class="form-group">
      <label class="control-label col-sm-2" for="id">ID:</label>
      <div class="col-sm-6">
        <input type="text" class="form-control" id="id" placeholder="Enter id" name="id">
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
        <button id="login_bt" class="btn btn-default">LOGIN</button>
    </div>
  </div>
  </form>
 
</div>

```

- loginfail
```html
<meta charset="UTF-8">

<h1>LOGIN Fail</h1>
<p>ID 또는 PWD가 틀립니다.</p>
<hr>
```

- loginok
```html
<meta charset="UTF-8">

<h1>환영 합니다.</h1>
<p>Login OK</p>
<hr>
<h3 th:text="${logincust.id} + '님 로그인 성공' ">Test</h3>

```

- logintop
```html
<meta charset="UTF-8">

<li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
```

- logouttop
```html
<meta charset="UTF-8">

<li><a href="/logout">
<span th:text="${session.logincust.name}"></span>
<span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
```


#### templates/cust
- center
```html
<meta charset="UTF-8">

<h1>Cust Main Page</h1>
<p>Lorem consequat.</p>
<p th:utext="#{home.welcome(han)}"> 
<p th:utext="#{tel}"> 
<hr>

```

- custdetail
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Cust Update Page</h1>
	<h3 th:text="${dcust.id}"></h3>
	<h3 th:text="${dcust.pwd}"></h3>
	<h3 th:text="${dcust.name}"></h3>
	<a href="" th:href="@{custdelete(id=${dcust.id})}">DELETE</a>
	<a href="" th:href="@{custupdate(id=${dcust.id})}">UPDATE</a>
</body>
</html>
```

- custselect
```html
<meta charset="UTF-8">

<h1>CUST Select Page</h1>
<style>
	#result{
		width:300px;
		border: 2px solid blue;
	}
</style>
<script>
	
</script>
<div id="result">
<table>
		<thead>
			<tr><th>ID</th><th>NAME</th></tr>
		</thead>
		<tbody>
		
		<tr th:each="c:${allcust}">
				<td><a href="custdetail" th:href="@{custdetail(id=${c.id})}" th:text="${c.id}">ID</a></td>
				<td th:text="${c.name}">NAME</td>
				</tr>
		
		</tbody>
		
	</table>
</div>
<hr>

```

- custupdate
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Cust Update Page</h1>
	<form action="custupdateimpl" method="post">
	ID: <span th:text="${ucust.id}"></span><br>
	<input type="hidden" name="id" value="" th:value="${ucust.id}">
	
	PWD<input type="text" name="pwd" value="" th:value="${ucust.pwd}"><br>
	NAME<input type="text" name="name" value="" th:value="${ucust.name}"><br>
	<input type="submit" value="UPDATE"><br>
	</form>
</body>
</html>
```

- left
```html
<meta charset="UTF-8">
<html lang="ko" xmlns:th="http://www.thymeleaf.org">

<p th:if="#{session.logincust != null}"><a href="register">Cust Register</a></p>
<p><a href="custselect">Cust Select</a></p>
```

- register
```html
<meta charset="UTF-8">

<h1>CUST Register Page</h1>
<form action="registerimpl" method="POST">
ID<br><input type="text" name="id"><br>
PWD<br><input type="text" name="pwd"><br>
NAME<br><input type="text" name="name"><br>
<input type="submit" name="REGISTER"><br>
</form>
<hr>

```

- registerfail
```html
<meta charset="UTF-8">

<h1>CUST Page</h1>
<p>Register Fail</p>
<hr>

```

- registerok
```html
<meta charset="UTF-8">

<h1>CUST Page</h1>
<p>Register OK</p>
<p th:text="${rcust.id} + '환영합니다.'"></p>
<hr>

```

#### templates/product
- center
```html
<meta charset="UTF-8">

<h1>Product Main Page</h1>
<hr>
```

- left
```html
<meta charset="UTF-8">
<html lang="ko" xmlns:th="http://www.thymeleaf.org">

<p><a href="register">Product Register</a></p>
<p><a href="productselect">Product Select</a></p>
```

- productdetail
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Product Update Page</h1>
	<h3 th:text="${dproduct.id}"></h3>
	<h3 th:text="${dproduct.name}"></h3>
	<h3 th:text="${dproduct.price}"></h3>
	<h3 th:text="${#dates.format(dproduct.regdate, 'yyyy/MM/dd')}"></h3>
	<h3 th:text="${dproduct.rate}"></h3>
	<a href="" th:href="@{productdelete(id=${dproduct.id})}">DELETE</a>
	<a href="" th:href="@{productupdate(id=${dproduct.id})}">UPDATE</a>
</body>
</html>
```

- productselect
```html
<meta charset="UTF-8">

<h1>Product Select Page</h1>
<style>
	#result{
		width:300px;
		border: 2px solid blue;
	}
</style>
<script>
	
</script>
<div id="result">
	<table>
		<thead>
			<tr><th>ID</th><th>NAME</th><th>PRICE</th><th>REGDATE</th><th>RATE</th></tr>
		</thead>
		<tbody>
			<tr th:each="p:${allproduct}">
				<td><a href="productdetail" th:href="@{productdetail(id=${p.id})}" th:text="${p.id}">ID</a></td>
				<td th:text="${p.name}">NAME</td>
				<td th:text="${p.price}">PRICE</td>
				<td th:text="${#dates.format(p.regdate, 'yyyy/MM/dd')}">REGDATE</td>
				<td th:text="${p.rate}">RATE</td>
			</tr>
		</tbody>
	</table>
</div>
<hr>
```

- productupdate
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Product Update Page</h1>
	<form action="productupdateimpl" method="post">
	ID: <span th:text="${uproduct.id}"></span><br>
	<input type="hidden" name="id" value="" th:value="${uproduct.id}">
	NAME:<input type="text" name="name" value="" th:value="${uproduct.name}"><br>
	PRODUCT: <input type="text" name="price" value="" th:value="${uproduct.price}"><br>
	REGDATE: <span th:text="${#dates.format(uproduct.regdate, 'yyyy/MM/dd')}"></span><br>
	RATE: <input type="text" name="rate" value="" th:value="${uproduct.rate}"><br>
	
	<input type="submit" value="UPDATE"><br>
	</form>
</body>
</html>
```

- register
```html
<meta charset="UTF-8">

<h1>Product Register Page</h1>
<form action="registerimpl" method="POST">
NAME<br><input type="text" name="name"><br>
PRICE<br><input type="text" name="price"><br>
RATE<br><input type="text" name="rate"><br>
<input type="submit" name="REGISTER"><br>
</form>
<hr>

```

- registerfail
```html
<meta charset="UTF-8">

<h1>Product Page</h1>
<p>Register Fail</p>
<hr>
```

- registerok
```html
<meta charset="UTF-8">

<h1>Product Page</h1>
<p>Register OK</p>
<p th:text="${cnt}+'번째 제품이 입력 되었습니다.'">
<hr>
```