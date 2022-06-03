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

```

- productaddok
```html

```

- productdetail
```html

```

- productselect
```html

```

- productupdate
```html

```