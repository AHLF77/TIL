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

```