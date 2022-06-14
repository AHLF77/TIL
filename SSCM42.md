# 0614~0617 강의
## shop

### src/main/java
#### com.multi.biz
- CartBiz
```java
package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.CartMapper;
import com.multi.vo.CartVO;
@Service
public class CartBiz implements Biz<Integer, CartVO>{
	@Autowired
	CartMapper dao;
	
	@Override
	public void register(CartVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(CartVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public CartVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<CartVO> get() throws Exception {
		return dao.selectall();
	}

}
```

- CateBiz
```java
package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.CateMapper;
import com.multi.vo.CateVO;
@Service
public class CateBiz implements Biz<Integer,CateVO>{

	@Autowired
	CateMapper dao;
	
	
	@Override
	public void register(CateVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(CateVO v) throws Exception {
		dao.update(v);		
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);		
		
	}

	@Override
	public CateVO get(Integer k) throws Exception {
		return 	dao.select(k);		
	}

	@Override
	public List<CateVO> get() throws Exception {
		return dao.selectall();
	}

	public List<CateVO> getmain() throws Exception {
		return dao.selectmain();
	}
	
	public List<CateVO> selectsub(int id) throws Exception {
		return dao.selectsub(id);
	}
	
}
```

- CustBiz
```java
package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.frame.Biz;
import com.multi.mapper.CustMapper;
import com.multi.vo.CustVO;
@Service
public class CustBiz implements Biz<String,CustVO>{

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
import com.multi.vo.ProductAVGVO;
import com.multi.vo.ProductVO;
@Service
public class ProductBiz implements Biz<Integer, ProductVO> {

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

	public List<ProductVO> get2() throws Exception {
		return dao.selectall2();
	}
	
	public List<ProductAVGVO> get3() throws Exception {
		return dao.selectall3();
	}
	
	public List<ProductVO> selectproduct(int id) throws Exception {
		return dao.selectproduct(id);
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

import com.multi.biz.CateBiz;
import com.multi.biz.CustBiz;
import com.multi.biz.ProductBiz;
import com.multi.vo.CateVO;
import com.multi.vo.CustVO;
import com.multi.vo.ProductAVGVO;
import com.multi.vo.ProductVO;

@RestController
public class AJAXController {

	@Autowired
	CustBiz cbiz;
	
	
	@RequestMapping("checkcustid")
	public String checkcustid(String id) {
		String result = "";
		CustVO c = null;
		
		if(id.equals("")|| id == null) {
			return "1";
		}
		
		try {
			c = cbiz.get(id);
			if(c == null) {
				result = "0";
			}else {
				result = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
		
	
}
```

- MainController
```java
package com.multi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CateBiz;
import com.multi.biz.CustBiz;
import com.multi.vo.CateVO;
import com.multi.vo.CustVO;

@Controller
public class MainController {
	
	@Autowired
	CustBiz cbiz;
	
	@Autowired
	CateBiz catebiz;
	
	@ModelAttribute("catelist")
	public List<CateVO> makemenu(){
		System.out.println("makemenu");
		List<CateVO> catelist = null;
		try {
			catelist = catebiz.getmain();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return catelist;
	}
	
	@RequestMapping("/")
	public String main(Model m) {
	
		return "main";
	}
	
	@RequestMapping("getcate")
	public String getcate(Model m, int id) {
		List<CateVO> catelist = null;
		try {
			catelist = catebiz.selectsub(id);
			m.addAttribute("leftcatelist",catelist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "main";
	}
	
	@RequestMapping("/login")
	public String login(Model m, String msg) {
		if(msg != null && msg.equals("f")) {
			m.addAttribute("msg","Login Fail Retry!!!");
		}
		m.addAttribute("center", "login");
		return "/main";
	}
	
	@RequestMapping("/logout")
	public String logout(Model m, HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "/main";
	}	
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model m, String id, String pwd, HttpSession session) {
		CustVO cust = null;
		try {
			cust = cbiz.get(id);
			if(cust == null) {
				throw new Exception();
			}if(cust.getPwd().equals(pwd)) {
				session.setAttribute("logincust", cust);
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			return "redirect:/login?msg=f";
		}
		return "/main";
	}
	
	@RequestMapping("/register")
	public String register(Model m) {
		m.addAttribute("center", "/register");
		return "/main";
	}
	
	@RequestMapping("/registerimpl")
	public String registerimpl(Model m,CustVO cust, HttpSession session) {
		try {
			cbiz.register(cust);
			session.setAttribute("logincust", cust);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "/register");
		return "/main";
	}
}
```

#### com.multi.frame
- Biz(interface)
```java
package com.multi.frame;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface Biz<K,V> {
	@Transactional
	public void register(V v) throws Exception;
	public void modify(V v) throws Exception;
	public void remove(K k) throws Exception;
	public V get(K k) throws Exception;
	public List<V> get() throws Exception;
}

```

#### com.multi.mapper
- CartMapper(interface)
```java
package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.CartVO;
import com.multi.vo.CateVO;

@Repository
@Mapper
public interface CartMapper {

	public void insert(CartVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(CartVO obj) throws Exception;
	public CartVO select(int obj) throws Exception;
	public List<CartVO> selectall() throws Exception;
}
```

- CateMapper(interface)
```java
package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.CateVO;

@Repository
@Mapper
public interface CateMapper {

	public void insert(CateVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(CateVO obj) throws Exception;
	public CateVO select(int obj) throws Exception;
	public List<CateVO> selectall() throws Exception;
	public List<CateVO> selectmain() throws Exception;
	public List<CateVO> selectsub(int id) throws Exception;
}
```

- CustMapper(interface)
```java
package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.CateVO;
import com.multi.vo.CustVO;

@Repository
@Mapper
public interface CustMapper {

	public void insert(CustVO obj) throws Exception;
	public void delete(String obj) throws Exception;
	public void update(CustVO obj) throws Exception;
	public CustVO select(String obj) throws Exception;
	public List<CustVO> selectall() throws Exception;
	
}
```

- ProductMapper(interface)
```java
package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.ProductAVGVO;
import com.multi.vo.ProductVO;

@Repository
@Mapper
public interface ProductMapper {

	public void insert(ProductVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(ProductVO obj) throws Exception;
	public ProductVO select(int obj) throws Exception;
	public List<ProductVO> selectall() throws Exception;
	public List<ProductVO> selectall2() throws Exception;
	public List<ProductAVGVO> selectall3() throws Exception;
	public List<ProductVO> selectproduct(int id) throws Exception;
}
```

#### com.multi.mybatis
- cartmapper
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org/DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.multi.mapper.CartMapper">
	
	<select id="select" parameterType="int" resultType="cartVO">
		SELECT c.id, cu.id AS uid, ca.name AS catename, 
		p.id AS pid, p.name AS pname, p.price as pprice, c.regdate, c.cnt FROM cart c
		INNER JOIN cust cu ON c.uid = cu.id
		INNER JOIN product p ON c.pid = p.id
		INNER JOIN cate ca ON ca.id = p.cid
		WHERE c.id = #{id}
	</select>
	<select id="selectall" resultType="cartVO">
		SELECT c.id, cu.id AS uid, ca.name AS catename, 
		p.id AS pid, p.name AS pname, p.price as pprice, c.regdate, c.cnt FROM cart c
		INNER JOIN cust cu ON c.uid = cu.id
		INNER JOIN product p ON c.pid = p.id
		INNER JOIN cate ca ON ca.id = p.cid
	</select>
	<insert id="insert" parameterType="cartVO">
		INSERT INTO CART VALUES (NULL, #{uid},#{pid},CURDATE(),#{cnt});
	</insert>
	<update id="update" parameterType="cartVO">
		UPDATE CART SET CNT=#{cnt} WHERE ID=#{id}
	</update>
	<delete id="delete" parameterType="int">
		DELETE FROM CART WHERE ID=#{id}
	</delete>
	
</mapper>
```

- catemapper
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org/DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.multi.mapper.CateMapper">
	
	<select id="select" parameterType="int" resultType="cateVO">
		SELECT * FROM CATE WHERE ID=#{id}
	</select>
	<select id="selectsub" parameterType="int" resultType="cateVO">
		SELECT * FROM CATE WHERE PID=#{pid}
	</select>
	<select id="selectall" resultType="cateVO">
		SELECT * FROM CATE
	</select>
	<select id="selectmain" resultType="cateVO">
		SELECT * FROM CATE WHERE pid IS NULL
	</select>
	<insert id="insert" parameterType="cateVO">
		<if	test="pid != 0"> 
			INSERT INTO CATE VALUES (#{id},#{name},#{pid})
		</if>
		<if	test="pid == 0"> 
			INSERT INTO CATE VALUES (#{id},#{name},NULL)
		</if>
	</insert>
	<update id="update" parameterType="cateVO">
		<if	test="pid != 0"> 
		 	UPDATE CATE SET NAME=#{name},PID=#{pid} WHERE ID=#{id}
		</if>
		<if	test="pid == 0"> 
		 	UPDATE CATE SET NAME=#{name} WHERE ID=#{id}
		</if>
	</update>
	<delete id="delete" parameterType="int">
		DELETE FROM CATE WHERE ID=#{id}
	</delete>
	
</mapper>
```

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
		INSERT INTO CUST VALUES (#{id},#{name},#{addr},SYSDATE(), #{pwd})
	</insert>
	<update id="update" parameterType="custVO">
		UPDATE CUST SET NAME=#{name},ADDR=#{addr},PWD=#{pwd} WHERE ID=#{id}
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
		SELECT p.id, p.name, p.regdate, p.imgname,
		p.cid, p.price, c.name as catename
		FROM product p
		INNER JOIN cate c ON p.cid = c.id
		WHERE p.id = #{id}
	</select>
	<select id="selectproduct" parameterType="int" resultType="productVO">
		SELECT p.id, p.name, p.regdate, p.imgname,
		p.cid, p.price, c.name as catename
		FROM product p
		INNER JOIN cate c ON p.cid = c.id
		WHERE p.cid = #{id}
	</select>
	<select id="selectall" resultType="productVO">
		SELECT p.id, p.name, p.regdate, p.imgname,
		p.cid, p.price, c.name as catename
		FROM product p
		INNER JOIN cate c ON p.cid = c.id
	</select>
	<select id="selectall2" resultType="productVO">
		SELECT p.id, p.name, p.regdate, p.imgname,
		p.cid, p.price, c.name as catename, c2.name as maincatename
		FROM product p
		INNER JOIN cate c ON p.cid = c.id
		INNER JOIN cate c2 ON c.pid = c2.id
	</select>
	<select id="selectall3" resultType="productAVGVO">
		SELECT c.name as catename, AVG(p.price) as avg
		FROM product p
		INNER JOIN cate c ON p.cid = c.id
		GROUP BY catename
	</select>
	<insert id="insert" parameterType="productVO">
		INSERT INTO PRODUCT VALUES (NULL, #{name},#{price},curdate(),#{cid},#{imgname})
	</insert>
	<update id="update" parameterType="productVO">
		UPDATE PRODUCT SET NAME=#{name},PRICE=#{price},CID=#{cid},IMGNAME=#{imgname} 
		WHERE ID=#{id}
	</update>
	<delete id="delete" parameterType="int">
		DELETE FROM PRODUCT WHERE ID=#{id}
	</delete>
	
</mapper>
```

#### com.multi.vo
- CartVO
```java
package com.multi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartVO {
	private int id;
	private String uid;
	private int pid;
	private Date regdate;
	private int cnt;
	
	private String pname;
	private int pprice;
	private String catename;
		
	public CartVO(String uid, int pid, int cnt) {
		this.uid = uid;
		this.pid = pid;
		this.cnt = cnt;
	}
	
}
```

- CateVO
```java
package com.multi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CateVO {
	private int id;
	private String name;
	private int pid;
	
	public CateVO(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	
}
```

- CustVO
```java
package com.multi.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustVO {
	private String id;
	private String name;
	private String addr;
	private Date regdate;
	private String pwd;
	
	
	public CustVO(String id, String name, String addr, String pwd) {
		this.id = id;
		this.name = name;
		this.addr = addr;
		this.pwd = pwd;
	}

	public CustVO(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}

		
}

```

- ProductAVGVO
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
public class ProductAVGVO {
	private String catename;
	private double avg;
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

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductVO {
	private int id;
	private String name;
	private int price;
	private Date regdate;
	private int cid;
	private String imgname;
	private String catename;
	private String maincatename;
	
	public ProductVO(String name, int price, int cid, String imgname) {
		this.name = name;
		this.price = price;
		this.cid = cid;
		this.imgname = imgname;
	}
	
	public ProductVO(int id, String name, int price, int cid, String imgname) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.cid = cid;
		this.imgname = imgname;
	}
	
}
```


### src/main/resource
#### templates
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

<p th:each="c:${leftcatelist}"><a href="#" th:text="${c.name}">main1</a></p>
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
  	<h2>Customer Login</h2>
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
        <button id="login_bt" class="btn btn-primary">LOGIN</button>
        <h5 th:text="${msg}"></h5>
      </div>
  	</div>
  </form>
  
</div>
```

- loginok
```html
<meta charset="UTF-8">

<h1>환영 합니다.</h1>
<p>LOGIN OK</p>
<hr>
<h3 th:text="${logincust.id} + '님 로그인 성공' ">Test</h3>
```

- loginfail
```html
<meta charset="UTF-8">

<h1>LOGIN Fail</h1>
<p>ID 또는 PWD가 틀립니다.</p>
<hr>
```

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
	      <a class="navbar-brand" href="/">HOME</a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav">
	        <li th:each="c:${catelist}"><a th:href="@{/getcate(id=${c.id})}" th:text="${c.name}">Home</a></li>
	      </ul>
	    
	      <ul class="nav navbar-nav navbar-right">
	      <li th:if="${session.logincust == null}"><a href="/register">
	      <span class="glyphicon glyphicon-list-alt"></span>Register</a></li> 	
	      	<li th:if="${session.logincust == null}"><a th:href="@{/login}">
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

- register
```html
<meta charset="UTF-8">
<script>
$(document).ready(function(){
	
	$('#id').keyup(function(){
		var inputid = $(this).val();
		$.ajax({
			url:'/checkcustid',
			data:{id:inputid},
			success:function(result){
				if(result == '1'){
					$('#iid').text('사용불가능');
				}else{
					$('#iid').text('사용가능');
				}
			}
		});
	});	
	
	$('#register_bt').click(function(){
		
		$('#register_form').attr({
			'method':'post',
			'action':'/registerimpl'
		});
		
		$('#register_form').submit();
	});
});
</script>

<div class="container">
  <div class="col-sm-offset-2 col-sm-10">
  	<h2>Customer Register Page</h2>
  </div>
  <form class="form-horizontal" id="register_form">
    <div class="form-group">
      <label class="control-label col-sm-2" for="id">ID:</label>
      <div class="col-sm-6">
        <input type="text" class="form-control" id="id" placeholder="Enter id" name="id">
      	<h5 id="iid"></h5>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-6">          
        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="addr">Address:</label>
      <div class="col-sm-6">          
        <input type="text" class="form-control" id="addr" placeholder="Enter Address" name="addr">
      </div>
    </div>
        <div class="form-group">
      <label class="control-label col-sm-2" for="name">Name:</label>
      <div class="col-sm-6">          
        <input type="text" class="form-control" id="name" placeholder="Enter Name" name="name">
      </div>
    </div>
    
      	<div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button id="register_bt" class="btn btn-primary" name="REGISTER">REGISTER</button>
      </div>
  	</div>
  </form>
  
</div>
```

### src/test/java
#### com.multi.cate
- CateDeleteTests
```java
package com.multi.cate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CateBiz;

@SpringBootTest
class CateDeleteTests {

	@Autowired
	CateBiz cabiz;
	
	@Test
	void contextLoads() {
		try {
			cabiz.remove(41);
			System.out.println("Delete OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
```

- CateInsertTests
```java
package com.multi.cate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CateBiz;
import com.multi.vo.CateVO;

@SpringBootTest
class CateInsertTests {

	@Autowired
	CateBiz cabiz;
	
	@Test
	void contextLoads() {
		CateVO obj = new CateVO(41, "small shoes", 40);
		try {
			cabiz.register(obj);
			System.out.println("Register OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
```

- CateSelectAllTests
```java
package com.multi.cate;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CateBiz;
import com.multi.vo.CateVO;
import com.multi.vo.CustVO;

@SpringBootTest
class CateSelectAllTests {

	@Autowired
	CateBiz cubiz;
	
	@Test
	void contextLoads() {
		List<CateVO> list = null;
		try {
			list = cubiz.get();
			System.out.println("Select All Success");
			for(CateVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

```

- CateSelectMainTests
```java
package com.multi.cate;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CateBiz;
import com.multi.vo.CateVO;
import com.multi.vo.CustVO;

@SpringBootTest
class CateSelectMainTests {

	@Autowired
	CateBiz cubiz;
	
	@Test
	void contextLoads() {
		List<CateVO> list = null;
		try {
			list = cubiz.getmain();
			System.out.println("Select All Success");
			for(CateVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

```

- CateSelectSubTests
```java
package com.multi.cate;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CateBiz;
import com.multi.vo.CateVO;
import com.multi.vo.CustVO;

@SpringBootTest
class CateSelectSubTests {

	@Autowired
	CateBiz cubiz;
	
	@Test
	void contextLoads() {
		List<CateVO> list = null;
		try {
			list = cubiz.selectsub(20);
			System.out.println("Select Sub Success");
			for(CateVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

```

- CateSelectTests
```java
package com.multi.cate;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CateBiz;
import com.multi.biz.CustBiz;
import com.multi.vo.CateVO;
import com.multi.vo.CustVO;

@SpringBootTest
class CateSelectTests {

	@Autowired
	CateBiz cabiz;
	
	@Test
	void contextLoads() {
		
		CateVO obj = null;
		try {
			obj = cabiz.get(70);
			System.out.println("Select Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			System.out.println(obj);
		
	}


}

```

- CateUpdateTests
```java
package com.multi.cate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CateBiz;
import com.multi.biz.CustBiz;
import com.multi.vo.CateVO;
import com.multi.vo.CustVO;

@SpringBootTest
class CateUpdateTests {

	@Autowired
	CateBiz cabiz;
	
	@Test
	void contextLoads() {
		CateVO c = new CateVO(62, "small jeans",60);
		try {
			cabiz.modify(c);
			System.out.println("Update OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

```

#### com.multi.cust
- CustDeleteTests
```java
package com.multi.cust;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@SpringBootTest
class CustDeleteTests {

	@Autowired
	CustBiz cubiz;
	
	@Test
	void contextLoads() {
		try {
			cubiz.remove("id04");
			System.out.println("Delete OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

```

- CustInsertTests
```java
package com.multi.cust;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@SpringBootTest
class CustInsertTests {

	@Autowired
	CustBiz cubiz;
	
	@Test
	void contextLoads() {
		CustVO c = new CustVO("id05","lee","Ulsan","pwd05");
		try {
			cubiz.register(c);
			System.out.println("Register OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

```

- CustSelectAllTests
```java
package com.multi.cust;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@SpringBootTest
class CustSelectAllTests {

	@Autowired
	CustBiz cubiz;
	
	@Test
	void contextLoads() {
		List<CustVO> list = null;
		try {
			list = cubiz.get();
			System.out.println("Select All Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(CustVO custvo : list) {
			System.out.println(custvo);
		}
		
	}

}

```

- CustSelectTests
```java
package com.multi.cust;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@SpringBootTest
class CustSelectTests {

	@Autowired
	CustBiz cubiz;
	
	@Test
	void contextLoads() {
		
		CustVO obj = null;
		try {
			obj = cubiz.get("id01");
			System.out.println("Select Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			System.out.println(obj);
		
	}


}

```

- CustUpdateTests
```java
package com.multi.cust;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@SpringBootTest
class CustUpdateTests {

	@Autowired
	CustBiz cubiz;
	
	@Test
	void contextLoads() {
		CustVO c = new CustVO("id05", "choi","Gyeonggi","pwd55");
		try {
			cubiz.modify(c);
			System.out.println("Update OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

```

#### com.multi.product
- ProductDeleteTests
```java
package com.multi.product;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductDeleteTests {

	@Autowired
	ProductBiz pbiz;
	
	@Test
	void contextLoads() {
		try {
			pbiz.remove(1016);
			System.out.println("Delete OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

```

- ProductInsertTests
```java
package com.multi.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CateBiz;
import com.multi.biz.ProductBiz;
import com.multi.vo.CateVO;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductInsertTests {

	@Autowired
	ProductBiz biz;
	
	@Test
	void contextLoads() {
		ProductVO obj = new ProductVO("UNIQLO",19000, 21,"6.png");
		System.out.println("Register OK");
		try {
			biz.register(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}



```

- ProductSelect2Tests
```java
package com.multi.product;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.CustVO;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductSelect2Tests {
	
	@Autowired
	ProductBiz pbiz;

	@Test
	void contextLoads() {
		List<ProductVO> list = null;
		try {
			list = pbiz.get2();
			for (ProductVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
```

- ProductSelect3Tests
```java
package com.multi.product;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.CustVO;
import com.multi.vo.ProductAVGVO;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductSelect3Tests {
	
	@Autowired
	ProductBiz pbiz;

	@Test
	void contextLoads() {
		List<ProductAVGVO> list = null;
		try {
			list = pbiz.get3();
			for (ProductAVGVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
```

- ProductSelectAllTests
```java
package com.multi.product;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.CustVO;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductSelectAllTests {
	
	@Autowired
	ProductBiz pbiz;

	@Test
	void contextLoads() {
		List<ProductVO> list = null;
		try {
			list = pbiz.get();
			for (ProductVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
```

- ProductSelectTests
```java
package com.multi.product;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductSelectTests {

	@Autowired
	ProductBiz pbiz;
	
	@Test
	void contextLoads() {
		
		ProductVO prod = null;
		try {
			prod = pbiz.get(1006);
			System.out.println("Select Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			System.out.println(prod);
		
	}


}
```

- ProductUpdateTests
```java
package com.multi.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductUpdateTests {

	@Autowired
	ProductBiz pbiz;
	
	@Test
	void contextLoads() {
		ProductVO p = new ProductVO(1006,"zara",25000,11,"2.jpg");
		try {
			pbiz.modify(p);
			System.out.println("Update OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
```

- SelectProductTests
```java
package com.multi.product;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class SelectProductTests {

	@Autowired
	ProductBiz pbiz;
	
	@Test
	void contextLoads() {
		
		List<ProductVO> list = null;
		try {
			list = pbiz.selectproduct(21);
			for (ProductVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


}

```
