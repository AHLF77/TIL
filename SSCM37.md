# 0602강의
## Springboot
- Springboot를 통해 SQL문을 연동하고, CRUD를 작성하는 것이 목표

## day041

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

#### com.multi.controller
- MainController
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
public class MainController {
	
	@Autowired
	CustBiz biz;
	
	@RequestMapping("/")
	public String main() {
		return "main";	
	}
	
	@RequestMapping("custadd")
	public String custadd() {
		return "custadd";	
	}
	
	@RequestMapping("custaddimpl")
	public ModelAndView custaddimpl(ModelAndView mv, CustVO cust) {
		String next= "custaddok";
		try {
			biz.register(cust);
			mv.addObject("rcust",cust);
		} catch (Exception e) {
			next = "custaddfail";
		}
		mv.setViewName(next);
		return mv;
	}
	
	@RequestMapping("custselect")
	public ModelAndView custselect(ModelAndView mv) {
		List<CustVO> list = null;
		try {
			list = biz.get();
			mv.addObject("allcust", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("custselect");
		return mv;	
	}
	
	@RequestMapping("custdetail")
	public ModelAndView custdetail(ModelAndView mv, String id) {
		CustVO cust = null;
		try {
			cust = biz.get(id);
			mv.addObject("dcust", cust);
		} catch (Exception e) {
			
		}
		mv.setViewName("custdetail");
		return mv;	
	}
	
	@RequestMapping("custdelete")
	public String custdelete(String id) {
		try {
			biz.remove(id);
		} catch (Exception e) {
			
		}
		return "redirect:custselect";	
	}
	
	@RequestMapping("custupdate")
	public ModelAndView custupdate(ModelAndView mv, String id) {
		CustVO cust = null;
		try {
			cust = biz.get(id);
			mv.addObject("ucust", cust);
		} catch (Exception e) {
			
		}
		mv.setViewName("custupdate");
		return mv;	
	}
	
	@RequestMapping("custupdateimpl")
	public String custupdateimpl(CustVO cust) {
		String next= "custupdateok";
		try {
			biz.modify(cust);
		} catch (Exception e) {
			
		}
		return "redirect:custdetail?id="+cust.getId();
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

### src/main/resources
#### templates
- main
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Main Page</h1>
	<h3><a href="custadd">Cust ADD</a></h3>
	<h3><a href="custselect">Cust SELECT</a></h3>
</body>
</html>
```

- custadd
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Cust Add Page</h1>
	<form action="custaddimpl" method="post">
	ID<input type="text" name="id"><br>
	PWD<input type="text" name="pwd"><br>
	NAME<input type="text" name="name"><br>
	<input type="submit" value="REGISTER"><br>
	</form>
</body>
</html>
```

- custaddfail
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Cust Add Fail Page</h1>
</body>
</html>
```

- custaddok
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Cust Add OK Page</h1>
	<h3 th:text="${rcust.id}"></h3>
	<h3 th:text="${rcust.name}"></h3>
	<h3>님이 등록 되었습니다.</h3>
</body>
</html>
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
	<h1>Cust Detail Page</h1>
	<h3 th:text="${dcust.id}"></h3>
	<h3 th:text="${dcust.pwd}"></h3>
	<h3 th:text="${dcust.name}"></h3>
	<a href="" th:href="@{/custdelete(id=${dcust.id})}">DELETE</a>
	<a href="" th:href="@{/custupdate(id=${dcust.id})}">UPDATE</a>
</body>
</html>
```

- custselect
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Cust Select Page</h1>
	<table>
		<thead>
			<tr><th>ID</th><th>NAME</th></tr>
		</thead>
		<tbody>
			<tr th:each="c:${allcust}">
				<td><a href="custdetail" th:href="@{/custdetail(id=${c.id})}" th:text="${c.id}">ID</a></td>
				<td th:text="${c.name}">NAME</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
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


### src/test/java
#### com.multi
- CustTests
```java
package com.multi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.vo.CustVO;

@SpringBootTest
class CustTests {

	@Test
	void contextLoads() {
		CustVO cust = new CustVO("id01","pwd01","lee");
		System.out.println(cust);
		System.out.println(cust.getName());
	}

}
```

- CustDeleteTests
```java
package com.multi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@SpringBootTest
class CustDeleteTests {

	@Autowired
	CustBiz biz;
	
	@Test
	void contextLoads() {
		try {
			biz.remove("id23");
			System.out.println("Delete OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
```

- CustInsertTests
```java
package com.multi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@SpringBootTest
class CustInsertTests {

	@Autowired
	CustBiz biz;
	
	@Test
	void contextLoads() {
		CustVO c = new CustVO("id23","pwd23","han");
		try {
			biz.register(c);
			System.out.println("Register OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
```

- CustSelectAllTests
```java
package com.multi;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@SpringBootTest
class CustSelectAllTests {

	@Autowired
	CustBiz biz;
	
	@Test
	void contextLoads() {
		List<CustVO> list = null;
		try {
			list = biz.get();
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
package com.multi;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@SpringBootTest
class CustSelectTests {

	@Autowired
	CustBiz biz;
	
	@Test
	void contextLoads() {
		
		CustVO cust = null;
		try {
			cust = biz.get("id22");
			System.out.println("Select Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			System.out.println(cust);
		
	}


}
```

- CustUpdateTests
```java
package com.multi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@SpringBootTest
class CustUpdateTests {

	@Autowired
	CustBiz biz;
	
	@Test
	void contextLoads() {
		CustVO c = new CustVO("id22","pwd1752","malsook");
		try {
			biz.modify(c);
			System.out.println("Update OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
```