# 0603강의

## Springboot 2차
- product 테이블을 이용한 CRUD 작성

## day042

### src/main/java
#### com.multi.biz
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

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@Controller
public class MainController {
	
	@Autowired
	ProductBiz biz;
	
	@RequestMapping("/")
	public String main() {
		return "main";	
	}
	
	@RequestMapping("productadd")
	public String productadd() {
		return "productadd";	
	}
	
	@RequestMapping("productaddimpl")
	public ModelAndView productaddimpl(ModelAndView mv, ProductVO prod) {
		String next= "productaddok";
		try {
			biz.register(prod);
			mv.addObject("rproduct",prod);
		} catch (Exception e) {
			next = "productaddfail";
		}
		mv.setViewName(next);
		return mv;
	}
	
	@RequestMapping("productselect")
	public ModelAndView productselect(ModelAndView mv) {
		List<ProductVO> list = null;
		try {
			list = biz.get();
			mv.addObject("allproduct", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("productselect");
		return mv;	
	}
	
	@RequestMapping("productdetail")
	public ModelAndView productdetail(ModelAndView mv, Integer id) {
		ProductVO prod = null;
		try {
			prod = biz.get(id);
			mv.addObject("dproduct", prod);
		} catch (Exception e) {
			
		}
		mv.setViewName("productdetail");
		return mv;	
	}
	
	@RequestMapping("productdelete")
	public String productdelete(Integer id) {
		try {
			biz.remove(id);
		} catch (Exception e) {
			
		}
		return "redirect:productselect";	
	}
	
	@RequestMapping("productupdate")
	public ModelAndView productupdate(ModelAndView mv, Integer id) {
		ProductVO prod = null;
		try {
			prod = biz.get(id);
			mv.addObject("uproduct", prod);
		} catch (Exception e) {
			
		}
		mv.setViewName("productupdate");
		return mv;	
	}
	
	@RequestMapping("productupdateimpl")
	public String productupdateimpl(ProductVO prod) {
		String next= "productupdateok";
		try {
			biz.modify(prod);
		} catch (Exception e) {
			
		}
		return "redirect:productdetail?id="+prod.getId();
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
	public void insert(ProductVO prod) throws Exception;
	public void delete(int id) throws Exception;
	public void update(ProductVO prod) throws Exception;
	public ProductVO select(int id) throws Exception;
	public List <ProductVO> selectall() throws Exception;
}

```

#### com.multi.mapper
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
	<h3><a href="productadd">Product ADD</a></h3>
	<h3><a href="productselect">Product SELECT</a></h3>
</body>
</html>
```

- productadd
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Product Add Page</h1>
	<form action="productaddimpl" method="post">
	상품이름<input type="text" name="name"><br>
	가격<input type="number" name="price"><br>
	상품등급<input type="text" name="rate"><br>
	<input type="submit" value="제출"><br>
	</form>
</body>
</html>
```

- productaddfail
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Product Add Fail Page</h1>
</body>
</html>
```

- productaddok
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Product Add OK Page</h1>
	<h3 th:text="${rproduct.name}"></h3>
	<h3>상품이 등록 되었습니다.</h3>
</body>
</html>
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
	<h1>Product Detail Page</h1>
	<h3 th:text="${dproduct.id}"></h3>
	<h3 th:text="${dproduct.name}"></h3>
	<h3 th:text="${dproduct.price}"></h3>
	<h3 th:text="${#dates.format(dproduct.regdate, 'yyyy/MM/dd')}"></h3>
	<h3 th:text="${dproduct.rate}"></h3>
	<a href="" th:href="@{/productdelete(id=${dproduct.id})}">DELETE</a>
	<a href="" th:href="@{/productupdate(id=${dproduct.id})}">UPDATE</a>
</body>
</html>
```

- productselect
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Product Select Page</h1>
	<table>
		<thead>
			<tr><th>ID</th><th>NAME</th><th>PRICE</th><th>REGDATE</th><th>RATE</th></tr>
		</thead>
		<tbody>
			<tr th:each="p:${allproduct}">
				<td><a href="productdetail" th:href="@{/productdetail(id=${p.id})}" th:text="${p.id}">ID</a></td>
				<td th:text="${p.name}">NAME</td>
				<td th:text="${p.price}">PRICE</td>
				<td th:text="${#dates.format(p.regdate, 'yyyy/MM/dd')}">REGDATE</td>
				<td th:text="${p.rate}">RATE</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
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
	아이디: <span th:text="${uproduct.id}"></span><br>
	<input type="hidden" name="id" value="" th:value="${uproduct.id}">
	
	이름:<input type="text" name="name" value="" th:value="${uproduct.name}"><br>
	상품가격: <input type="text" name="price" value="" th:value="${uproduct.price}"><br>
	등록일짜: <span th:text="${#dates.format(uproduct.regdate, 'yyyy/MM/dd')}"></span><br>
	상품 등급: <input type="text" name="rate" value="" th:value="${uproduct.rate}"><br>
	
	<input type="submit" value="UPDATE"><br>
	</form>
</body>
</html>
```

### src/test/java
#### com.multi
- ProductDeleteTests
```java
package com.multi;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductDeleteTests {

	@Autowired
	ProductBiz biz;
	
	@Test
	void contextLoads() {
		try {
			biz.remove(8);
			System.out.println("Delete OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
```

- ProductInsertTests
```java
package com.multi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductInsertTests {

	@Autowired
	ProductBiz pbiz;
	
	@Test
	void contextLoads() {
		ProductVO p = new ProductVO(16,"sweater", 56000, null, 3.8);
		try {
			pbiz.register(p);
			System.out.println("Register OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
```

- ProductSelectAllTests
```java
package com.multi;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductSelectAllTests {

	@Autowired
	ProductBiz biz;
	
	@Test
	void contextLoads() {
		List<ProductVO> list = null;
		try {
			list = biz.get();
			System.out.println("Select All Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(ProductVO productvo : list) {
			System.out.println(productvo);
		}
		
	}

}
```

- ProductSelectTests
```java
package com.multi;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.ProductBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductSelectTests {

	@Autowired
	ProductBiz biz;
	
	@Test
	void contextLoads() {
		
		ProductVO prod = null;
		try {
			prod = biz.get(7);
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

```