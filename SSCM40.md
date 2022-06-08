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