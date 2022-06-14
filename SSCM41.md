# 0608~0613 강의
## shopadmin

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

- MainBiz
```java
package com.multi.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.mapper.MainMapper;
import com.multi.vo.AdminVO;
import com.multi.vo.ProductVO;

@Service
public class MainBiz {
	
	@Autowired
	MainMapper dao;
	
	public int getCustCnt() throws Exception {
		return dao.getcustcnt();
	}
	
	public int getProductCnt() throws Exception {
		return dao.getproductcnt();
	}
	
	public int getCateCnt() throws Exception{
		return dao.getcatecnt();
	}
	
	public int getCartCnt() throws Exception{
		return dao.getcartcnt();
	}
	
	public List<ProductVO> searchProduct(String txt) throws Exception{
		return dao.searchproduct(txt);
	}
	
	public AdminVO getAdmin(String id) throws Exception{
		return dao.getadmin(id);
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
	CustBiz cubiz;
	
	@Autowired
	CateBiz cabiz;
	
	@Autowired
	ProductBiz pbiz;
	
	@RequestMapping("/chart1")
	public Object chart1() {
		// [{},{}]
		
		JSONArray ja = new JSONArray();
		List<ProductAVGVO> list = null;
		try {
			list = pbiz.get3();
			for(ProductAVGVO p : list) {
				JSONObject jo = new JSONObject();
				jo.put("name", p.getCatename());
				jo.put("y", p.getAvg());
				ja.add(jo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ja;
	}
	
	@RequestMapping("checkcustid")
	public String checkcustid(String id) {
		String result = "";
		CustVO c = null;
		
		try {
			c = cubiz.get(id);
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
	
	@RequestMapping("checkcateid")
	public String checkcateid(Integer id) {
		String result = "";
		CateVO ca = null;
		
		if(id.equals("") || id == null) {
			return "1";
		}
		try {
			ca = cabiz.get(id);
			if(ca == null) {
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

- CartController
```java
package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CartBiz;
import com.multi.biz.CateBiz;
import com.multi.vo.CartVO;
import com.multi.vo.CateVO;

@Controller
@RequestMapping("cart")
public class CartController {
	
	@Autowired
	CartBiz biz;
		
	@RequestMapping("cartselect")
	public String cartselect(Model m) {
		List<CartVO> list = null;
		try {
			list = biz.get();
			m.addAttribute("cartlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center","cart/cartselect");
		return "/index";
	}
	
}
```

- CateController
```java
package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CateBiz;
import com.multi.vo.CateVO;

@Controller
@RequestMapping("/cate")
public class CateController {
	
	@Autowired
	CateBiz biz;
	
	@RequestMapping("cateadd")
	public String add(Model m) {
		List<CateVO> list = null;
		
		try {
			list=biz.getmain();
			m.addAttribute("slist",list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center","cate/cateadd");
		return "/index";
	}
	
	@RequestMapping("/cateaddimpl")
	public String cateaddimpl(Model m, CateVO obj) {
		try {
			biz.register(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:catedetail?id="+obj.getId();

	}

	
	@RequestMapping("/cateselect")
	public String cateselect(Model m) {
		List<CateVO> list = null;
		try {
			list = biz.get();
			m.addAttribute("catelist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center","cate/cateselect");
		return "/index";
	}
	
	@RequestMapping("/catedetail")
	public String catedetail(Model m, Integer id) {
		
		CateVO obj = null;
		List<CateVO> list = null;
		try {
			obj = biz.get(id);
			list = biz.getmain();
			m.addAttribute("dcate", obj);
			m.addAttribute("slist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center","cate/catedetail");
		return "/index";
	}
	
	@RequestMapping("/cateupdate")
	public String cateupdate(Model m, CateVO obj) {
		try {
			biz.modify(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:catedetail?id="+obj.getId();

	}
	
	@RequestMapping("/catedelete")
	public String catedelete(int id,Model m) {
		try {
			biz.remove(id);
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("msg","삭제불가");
			return "redirect:catedetail?id="+id;
		}
		
		return "redirect:cateselect";

	}
	
	

}
```

- CustController
```java
package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@Controller
@RequestMapping("/cust")
public class CustController {

	@Autowired
	CustBiz biz;
	
	@RequestMapping("add")
	public String add(Model m) {
		m.addAttribute("center", "cust/add");
		return "index";
	}
	
	@RequestMapping("addimpl")
	public String addimpl(Model m, CustVO obj) {
		
		try {
			biz.register(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:custdetail?id="+obj.getId();
	}
	
	@RequestMapping("/select")
	public String select(Model m) {
		List<CustVO> list = null;
		try {
			list = biz.get();
			m.addAttribute("clist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "cust/custselect");
		return "index";
	}
	
	@RequestMapping("/custdetail")
	public String custdetail(Model m, String id) {
		CustVO obj = null;
		try {
			obj = biz.get(id);
			m.addAttribute("dcust", obj);
		} catch (Exception e) {
					
			e.printStackTrace();
		}
		
		m.addAttribute("center","cust/custdetail");
		return "/index";
	}
	
	@RequestMapping("/update")
	public String update(Model m, CustVO obj) {
		
		try {
			biz.modify(obj);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		
		return "redirect:custdetail?id="+obj.getId();
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.MainBiz;
import com.multi.vo.AdminVO;
import com.multi.vo.ProductVO;

@Controller
public class MainController {
	
	@Autowired
	MainBiz biz;

	@RequestMapping("/")
	public String main(Model m) {
		int custcnt = 0;
		int productcnt = 0;
		int catecnt = 0;
		int cartcnt = 0;
		try {
			custcnt = biz.getCustCnt();
			productcnt = biz.getProductCnt();
			catecnt = biz.getCateCnt();
			cartcnt = biz.getCartCnt();
			m.addAttribute("custcnt", custcnt);
			m.addAttribute("productcnt", productcnt);	
			m.addAttribute("catecnt", catecnt);
			m.addAttribute("cartcnt", cartcnt);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/index";
	}
	
	@RequestMapping("search")
	public String search(Model m, String txt) {
		List<ProductVO> list = null;
		
		try {
			list = biz.searchProduct(txt);
			m.addAttribute("sproduct", list);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		m.addAttribute("center", "search");
		return "/index";
	}
	
	@RequestMapping("/login")
	public String login(Model m, String msg) {
		if(msg != null && msg.equals("f")) {
			m.addAttribute("msg", "LOGIN FAIL !!");
		}
		m.addAttribute("center", "login");
		return "/index";
	}
	
	@RequestMapping("/logout")
	public String logout(Model m, HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "/index";
	}
	
	@RequestMapping("loginimpl")
	public String loginimpl(Model m, String id, String pwd, HttpSession session) {	
		AdminVO ad = null;
		try {
			ad = biz.getAdmin(id);
			if(ad == null) {
				throw new Exception();
				
			}if(ad.getPwd().equals(pwd)){
					session.setAttribute("loginadmin", ad);
					
				}else {
					throw new Exception();
				}
		} catch (Exception e) {
			return "redirect:/login?msg=f";
		}
		return "/index";
	}
	
}
```

- ProductController
```java
package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CateBiz;
import com.multi.biz.ProductBiz;
import com.multi.frame.Util;
import com.multi.vo.CateVO;
import com.multi.vo.ProductVO;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Value("${admindir}")
	String admindir;
	
	@Value("${userdir}")
	String userdir;
	
	@Autowired
	ProductBiz pbiz;
	
	@Autowired
	CateBiz cbiz;
	
	@RequestMapping("/add")
	public String add(Model m) {
		List<CateVO> list = null;
		try {
			list = cbiz.get();
			m.addAttribute("clist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "product/add");
		return "/index";
	}
	
	@RequestMapping("/productselect")
	public String productselect(Model m) {
		List<ProductVO> list = null;
		try {
			list = pbiz.get();
			m.addAttribute("plist",list);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		m.addAttribute("center", "product/productselect");
		return "/index";
	}
	
	@RequestMapping("/detail")
	public String detail(Model m, Integer id) {
		ProductVO obj = null;
		List<CateVO> list = null;
		try {
			list = cbiz.get();
			m.addAttribute("clist", list);
			obj = pbiz.get(id);
			m.addAttribute("dproduct", obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("center","product/detail");
		return "/index";
	}
	
	@RequestMapping("/addimpl")
	public String addimpl(Model m, ProductVO p) {
		// name, price, cid, mf(->imgname)
		String imgname = p.getMf().getOriginalFilename();
		p.setImgname(imgname);
		
		try {
			pbiz.register(p);
			Util.saveFile(p.getMf(),admindir,userdir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:productselect";
	}
	
	@RequestMapping("/update")
	public String update(Model m, ProductVO p) {
		
		String iname = p.getMf().getOriginalFilename();
		if(!(iname.equals(""))){
			p.setImgname(iname);
			Util.saveFile(p.getMf(),admindir,userdir);
		}		
		try {
			pbiz.modify(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:productselect";
	}
	
	@RequestMapping("/delete")
	public String delete(int id, Model m) {
		try {
			pbiz.remove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:productselect?id="+id;
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

- Util
```java
package com.multi.frame;

import java.io.FileOutputStream;
import org.springframework.web.multipart.MultipartFile;

public class Util {
	public static void saveFile(MultipartFile mf, String admindir, String userdir) {
		
		byte [] data;
		String imgname = mf.getOriginalFilename();
		try {
			data = mf.getBytes();
			FileOutputStream fo = 
					new FileOutputStream(admindir+imgname);
			fo.write(data);
			fo.close();
			
			FileOutputStream fo2 = 
					new FileOutputStream(userdir+imgname);
			fo2.write(data);
			fo2.close();
		}catch(Exception e) {
			
		}
		
	}
	
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

- MainMapper(interface)
```java
package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.vo.AdminVO;
import com.multi.vo.ProductVO;

@Repository
@Mapper
public interface MainMapper {

	public int getcustcnt() throws Exception;
	public int getproductcnt() throws Exception;
	public int getcatecnt() throws Exception;
	public int getcartcnt() throws Exception;
	public List<ProductVO> searchproduct(String txt) throws Exception;
	public AdminVO getadmin(String id) throws Exception;
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

- mainmapper
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org/DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.multi.mapper.MainMapper">
	
	<select id="getadmin" parameterType="string" resultType="adminVO">
		SELECT * FROM ADMIN WHERE ID=#{id}
	</select>
	
	<select id="getcustcnt" resultType="int">
		SELECT COUNT(*) FROM CUST
	</select>
	<select id="getproductcnt" resultType="int">
		SELECT COUNT(*) FROM PRODUCT
	</select>
	<select id="getcatecnt" resultType="int">
		SELECT COUNT(*) FROM CATE
	</select>
	<select id="getcartcnt" resultType="int">
		SELECT COUNT(*) FROM CART
	</select>
	<select id="searchproduct" parameterType="string" resultType="productVO">
	SELECT p.id, p.name, p.regdate, p.imgname,
	p.cid, p.price, c.name as catename
	FROM product p
	INNER JOIN cate c ON p.cid = c.id
	WHERE p.name LIKE CONCAT('%',#{txt},'%')
	</select>
	
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
- AdminVO
```java
package com.multi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdminVO {
	private String id;
	private String pwd;
}
```

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



	public CartVO(int id, int cnt) {
		this.id = id;
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
 <style>
	#container{
		width:500px;
		height:300px;
	}
</style>
<script>
function display(data){
	Highcharts.chart('container', {
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false,
	        type: 'pie'
	    },
	    title: {
	        text: 'Browser market shares in January, 2018'
	    },
	    tooltip: {
	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	    },
	    accessibility: {
	        point: {
	            valueSuffix: '%'
	        }
	    },
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            dataLabels: {
	                enabled: true,
	                format: '<b>{point.name}</b>: {point.percentage:.1f} %'
	            }
	        }
	    },
	    series: [{
	        name: 'Brands',
	        colorByPoint: true,
	        data: data
	    }]
	});
};

function getdata(){
	$.ajax({
		url:'/chart1',
		success:function(data){
			//alert(data);
			display(data);
		}
	});	
};

$(document).ready(function(){
	getdata();
});
</script>

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
    </div>

    <!-- Content Row -->
    <div class="row">

        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Customer Count</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">
                            	<span th:text="${custcnt}"></span>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-calendar fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                Product Count</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">
                            <span th:text="${productcnt}"></span>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
     <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                Category</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">
                            <span th:text="${catecnt}"></span>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Pending Requests Card Example -->
        <div class="col-xl-3 col-md-6 mb-4">
            <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                Cart</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800"> <span th:text="${cartcnt}"></span></div>
                        </div>
                        <div class="col-auto">
                            <i class="fa fa-shopping-cart fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



    <!-- Content Row -->
    
    <div class="row">

    <!-- Area Chart -->
    <div class="col-xl-12 col-lg-12">
        <div class="card shadow mb-4">
            <!-- Card Header - Dropdown -->
            <div
                class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Earnings Overview</h6>
              
            </div>
            <!-- Card Body -->
             <div class="card-body">
                <div id="container"></div>
             </div>
         </div>
     </div>
     <!-- Area Chart End -->
     
	</div>
```

- index
```html
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shopping - Dashboard</title>

    <!-- Custom fonts for this template-->
    <link href="/vendor/fontawesome-free//css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
	<script src="/vendor/jquery/jquery.min.js"></script>
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script src="https://code.highcharts.com/modules/export-data.js"></script>
	<script src="https://code.highcharts.com/modules/accessibility.js"></script>

	<script>
	$(document).ready(function(){
		$('#searchbtn').click(function(){
			$('#searchform').attr({
				'action':'/search',
				'method':'get'
			});
			$('#searchform').submit();
		});
	});
	</script>

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3" href="/">
                Shop Management Tool
                </div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="/">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>HOME</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Management
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#custmenu"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Cust</span>
                </a>
                <div id="custmenu" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Cust Management:</h6>
                        <a class="collapse-item" href="/cust/add">ADD</a>
                        <a class="collapse-item" href="/cust/select">SELECT</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#productmenu"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>Product</span>
                </a>
                <div id="productmenu" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Product Management:</h6>
                        <a class="collapse-item" href="/product/add">ADD</a>
                        <a class="collapse-item" href="/product/productselect">SELECT</a>
                    </div>
                </div>
            </li>

 			<!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#categorymenu"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>Category</span>
                </a>
                <div id="categorymenu" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Category Management:</h6>
                        <a class="collapse-item" href="/cate/cateadd">ADD</a>
                        <a class="collapse-item" href="/cate/cateselect">SELECT</a>
                    </div>
                </div>
            </li>
            
			<!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#cartmenu"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>Cart</span>
                </a>
                <div id="cartmenu" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Cart Management:</h6>
                        <a class="collapse-item" href="/cart/cartselect">SELECT</a>
                    </div>
                </div>
            </li>
            
            <!-- Divider -->
            <hr class="sidebar-divider">
        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Search -->
                    <form id="searchform"
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" name="txt" class="form-control bg-light border-0 small" placeholder="Search for..."
                                aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button id="searchbtn" class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Alerts Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-primary">
                                            <i class="fas fa-file-alt text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 12, 2019</div>
                                        <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-success">
                                            <i class="fas fa-donate text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 7, 2019</div>
                                        $290.29 has been deposited into your account!
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-warning">
                                            <i class="fas fa-exclamation-triangle text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 2, 2019</div>
                                        Spending Alert: We've noticed unusually high spending for your account.
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                            </div>
                        </li>

                        <!-- Nav Item - Messages -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw"></i>
                                <!-- Counter - Messages -->
                                <span class="badge badge-danger badge-counter">7</span>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="messagesDropdown">
                                <h6 class="dropdown-header">
                                    Message Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="/img/undraw_profile_1.svg"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div class="font-weight-bold">
                                        <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                            problem I've been having.</div>
                                        <div class="small text-gray-500">Emily Fowler · 58m</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="/img/undraw_profile_2.svg"
                                            alt="...">
                                        <div class="status-indicator"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">I have the photos that you ordered last month, how
                                            would you like them sent to you?</div>
                                        <div class="small text-gray-500">Jae Chun · 1d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="/img/undraw_profile_3.svg"
                                            alt="...">
                                        <div class="status-indicator bg-warning"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Last month's report looks great, I am very happy with
                                            the progress so far, keep up the good work!</div>
                                        <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                            told me that people say this to all dogs, even if they aren't good...</div>
                                        <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow" th:if="${session.loginadmin == null}">
                        	<a th:href="@{/login}">
	      	    			<span class="glyphicon glyphicon-log-in">
	      	    			</span> Login</a></li>
                        </li>
                        
                        <li class="nav-item dropdown no-arrow" th:unless="${session.loginadmin == null}">
                            <a class="nav-link dropdown-toggle" href="logout" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small" 
                                th:text="${session.loginadmin.id}"></span>
                                <img class="img-profile rounded-circle" src="/img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Activity Log
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/logout"data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid"
                th:insert="${center} ? ${center} : center">
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2022</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
               
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" th:unless="${session.loginadmin == null}" href="logout">Logout</a>
                  
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="/vendor/bootstrap//js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="/vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/js/demo/chart-area-demo.js"></script>
    <script src="/js/demo/chart-pie-demo.js"></script>
    
     <!-- Page level plugins -->
    <script src="/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/js/demo/datatables-demo.js"></script>

</body>

</html>
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
  	<h2>Login</h2>
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
<h3 th:text="${loginadmin.id} + '님 로그인 성공' ">Test</h3>
```

- search
```html
<style>
	#main{
		width:200px;
	}
</style>

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Search Page</h1>
    </div>

    <!-- Content Row -->
    <div class="row" th:each="p:${sproduct}">

        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-12 col-md-12 mb-12">
            <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col-xl-4">
                        <a th:href="@{/product/detail(id=${p.id})}"> 
                            <img id="main" th:src="@{'/img/'+${p.imgname}}">
                        </a>
                        </div>
                        <div class="col-xl-8">
	                        <div class="h5 font-weight-bold text-primary mb-0"
	                        th:text="${p.catename}">
                                Category</div>  
                            <div class="h5 font-weight-bold text-primary mb-0"
                            th:text="${p.name}">
                                Customer Count</div>
                            <div class="h6 mb-0 font-weight-bold text-gray-800"
                            th:text="${p.price}">
                            	30000
                            </div>
                            <div class="h6 mb-0 font-weight-bold text-gray-800"
                            th:text="${#dates.format(p.regdate,'yyyy/MM/dd')}">
                            	2020-06-10
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
     
    </div>
```

#### templates/cart
- cartselect
```html
<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Cart Tables</h1>

<!-- DataTales Example -->
<div class="card shadow mb-4">

    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>CUST ID</th>
                        <th>PRODUCT ID</th>
                        <th>PRODUCT NAME</th>
                        <th>PRODUCT PRICE</th>
                        <th>CATEGORY</th>
                        <th>REGDATE</th>
                        <th>COUNT</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>CUST ID</th>
                        <th>PRODUCT ID</th>
                        <th>PRODUCT NAME</th>
                        <th>PRODUCT PRICE</th>
                        <th>CATEGORY</th>
                        <th>REGDATE</th>
                        <th>COUNT</th>
                    </tr>
                </tfoot>
                <tbody>
                    <tr th:each="car : ${cartlist}">
                        <td th:text="${car.id}">id</td>
                        <td th:text="${car.uid}">uid</td>
                        <td th:text="${car.pid}">pid</td>
                        <td th:text="${car.pname}">product name</td>
                        <td th:text="${car.pprice}">product price</td>
                        <td th:text="${car.catename}">categoty name</td>
                        <td th:text="${#dates.format(car.regdate,'yyyy/MM/dd')}">regdate</td>
                        <td th:text="${car.cnt}">count</td>
                    </tr> 
                </tbody>
            </table>
        </div>
    </div>
</div>
```

#### templates/cate
- cateadd
```html
<meta charset="UTF-8">

<script>

function sendId(id){
	$.ajax({
		url:'/checkcateid',
		data:{'id':id},
		success:function(data){
			if(data == '1'){
				$('#idspan').text('사용 불가능한 ID');
			}else{
				$('#idspan').text('사용 가능한 ID');
			}
		}
	});
};

$(document).ready(function(){
	$('input[name="id"]').blur(function(){
		var id = $(this).val();
		//alert(id);
		sendId(id);
	});
	
	$('#registerbtn').click(function(){
		$('.user').attr({
			'method':'post',
			'action':'cateaddimpl' 
		});
		$('.user').submit();
	});
});

</script>


  <div class="col-lg-6">
      <div class="p-5">
          <div class="text-center">
              <h1 class="h4 text-gray-900 mb-4">Category Register</h1>
          </div>
          <form class="user">
              <div class="form-group">
                  ID: <span id="idspan"></span><input type="number" class="form-control form-control-item" name="id">
              </div>
              <div class="form-group">
                  NAME: <input type="text" class="form-control form-control-item" name="name">
              </div>
              <div class="form-group">
                  PID: 
                  <select name="pid" class="form-control form-control-item">
                  <option value="0">TOP</option>
                  <option th:each="c : ${slist}" th:value="${c.id}" th:text="${c.id} + ${c.name}">:</option>
                  
                  </select>
              </div>
              <a id="registerbtn" href="#" class="btn btn-primary btn-user btn-block">
                  REGISTER
              </a>
             
          </form>
         
      </div>
  </div>
```

- catedetail
```html
<meta charset="UTF-8">

<script>
$(document).ready(function(){
	$('#deletebtn').click(function(){
		var id = $('input[name="id"]').val();
		var c = confirm('삭제 하시겠습니까?');
		if(c == true){
			location.href='catedelete?id='+id;
		}
		
	});
	
	$('#updatebtn').click(function(){
		$('.user').attr({
			'method':'post',
			'action':'cateupdate'
		});
		$('.user').submit();
	});
});

</script>

<div class="col-lg-6">
    <div class="p-5">
        <div class="text-center">
            <h1 class="h4 text-gray-900 mb-4">Category Information</h1>
        	<h1 th:if = "${msg != null}" th:text="${msg}"></h1>
        </div>
        <form class="user">
            <div class="form-group">
                <input type="text" class="form-control form-control-user" name="id" th:value="${dcate.id}" readonly="readonly">
            </div>
            
            <div class="form-group">
                <input type="text" class="form-control form-control-user" name="name" th:value="${dcate.name}">
            </div>
            
            <div class="form-group">
				<select name="pid" class="form-control form-control-item">
                  <option value="0">TOP</option>
                  <option th:each="s : ${slist}" 
                  th:value="${s.id}" 
                  th:text="${s.name}"
                  th:selected="${dcate.pid}==${s.id}">
                  </option>
                  </select>
            </div>
            
            <a id="updatebtn" href="#" class="btn btn-primary btn-user btn-block">
                UPDATE
            </a>
            
            <a id="deletebtn" href="#" class="btn btn-primary btn-user btn-block">
                DELETE
            </a>
            
        </form>
        
    </div>
</div>
```

- cateselect
```html
<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Category Tables</h1>

<!-- DataTales Example -->
<div class="card shadow mb-4">

    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>PID</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>PID</th>
                    </tr>
                </tfoot>
                <tbody>
                    <tr th:each="ca : ${catelist}">
                        <td><a href="" th:text="${ca.id}" th:href="@{catedetail(id=${ca.id})}">id</a></td>
                        <td th:text="${ca.name}">name</td>
                        <td th:if="${ca.pid == 0}" th:text="TOP">pid</td>
                        <td th:unless="${ca.pid == 0}" th:text="${ca.pid}">pid</td>
                    </tr> 
                </tbody>
            </table>
        </div>
    </div>
</div>
```

#### templates/cust
- add
```html
<meta charset="UTF-8">

<script>

function sendId(id){
	$.ajax({
		url:'/checkcustid',
		data:{'id':id},
		success:function(data){
			if(data == '1'){
				$('#ispan').text('사용 불가능한 ID');
			}else{
				$('#ispan').text('사용 가능한 ID');
			}
		}
	});
};

$(document).ready(function(){
	$('input[name="id"]').keyup(function(){
		var id = $(this).val(); 
		sendId(id);
	});
	
	$('#registerbtn').click(function(){
		$('.user').attr({
			'method':'post',
			'action':'addimpl' 
		});
		$('.user').submit();
	});
});

</script>


  <div class="col-lg-6">
      <div class="p-5">
          <div class="text-center">
              <h1 class="h4 text-gray-900 mb-4">Customer Register</h1>
          </div>
          <form class="user">
              <div class="form-group">
                  ID: <span id="ispan"></span><input type="text" class="form-control form-control-item" name="id">
              </div>
              <div class="form-group">
                 PWD: <input type="password" class="form-control form-control-item" name="pwd">
              </div>
              <div class="form-group">
                  NAME: <input type="text" class="form-control form-control-item" name="name">
              </div>
              <div class="form-group">
                 ADDR: <input type="text" class="form-control form-control-item" name="addr">
              </div>
              
              <a id="registerbtn" href="#" class="btn btn-primary btn-user btn-block">
                  REGISTER
              </a>
             
          </form>
         
      </div>
  </div>
```

- custdetail
```html
<script>
$(document).ready(function(){
	$('#deletebtn').click(function(){
		var id = $('input[name="id"]').val();
		var c = confirm('삭제 하시겠습니까?');
		if(c == true){
			location.href='delete?id='+id;
		}
		
	});
	
	$('#updatebtn').click(function(){
		$('.user').attr({
			'method':'post',
			'action':'update'
		});
		$('.user').submit();
	});
});

</script>

<div class="col-lg-6">
    <div class="p-5">
        <div class="text-center">
            <h1 class="h4 text-gray-900 mb-4">Customer Information</h1>
        </div>
        <form class="user">
            <div class="form-group">
                <input type="text" class="form-control form-control-user"
                    name="id" th:value="${dcust.id}" readonly="readonly">
            </div>
            
            <div class="form-group">
                <input type="text" class="form-control form-control-user"
                    name="pwd"  th:value="${dcust.pwd}">
            </div>
            
            <div class="form-group">
                <input type="text" class="form-control form-control-user"
                    name="name"  th:value="${dcust.name}">
            </div>
           
            <div class="form-group">
                <input type="text" class="form-control form-control-user"
                    name="addr"  th:value="${dcust.addr}">
            </div>
            
            
            <div class="form-group">
                <input type="text" class="form-control form-control-user"
                    name="regdate" th:value="${#dates.format(dcust.regdate,'yyyy/MM/dd')}" readonly="readonly">
            </div>
             
            <a id="updatebtn" href="#" class="btn btn-primary btn-user btn-block">
                UPDATE
            </a>
            
             <a id="deletebtn" href="#" class="btn btn-primary btn-user btn-block">
                DELETE
              </a>
            
        </form>
        
    </div>
</div>
```

- custselect
```html
<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800"> Cust Tables</h1>

<!-- DataTales Example -->
<div class="card shadow mb-4">

    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>PWD</th>
                        <th>NAME</th>
                        <th>ADDRESS</th>
                        <th>REGDATE</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>PWD</th>
                        <th>NAME</th>
                        <th>ADDRESS</th>
                        <th>REGDATE</th>
                    </tr>
                </tfoot>
                <tbody>
                    <tr th:each="c : ${clist}">
                        <td><a href="" th:text="${c.id}" th:href="@{custdetail(id=${c.id})}">id</a></td>
                        <td th:text="${c.pwd}">pwd</td>
                        <td th:text="${c.name}">name</td>
                        <td th:text="${c.addr}">addr</td>
                        <td th:text="${#dates.format(c.regdate,'yyyy/MM/dd')}">regdate</td>
                    </tr> 
                </tbody>
            </table>
        </div>
    </div>
</div>
```

#### templates/product
- add
```html
<meta charset="UTF-8">

<script>

$(document).ready(function(){

	$('#registerbtn').click(function(){
		$('.user').attr({
			'enctype':'multipart/form-data',
			'method':'post',
			'action':'addimpl' 
		});
		$('.user').submit();
	});
});

</script>


  <div class="col-lg-6">
      <div class="p-5">
          <div class="text-center">
              <h1 class="h4 text-gray-900 mb-4">Product Register</h1>
          </div>
          <form class="user">
              <div class="form-group">
                  NAME: <input type="text" class="form-control form-control-item" name="name">
              </div>
              <div class="form-group">
                 PRICE: <input type="number" class="form-control form-control-item" name="price">
              </div>
              <div class="form-group">
                  CATEGORY:
                  <select name = "cid" class="form-control form-control-item">
                  <option th:each="c:${clist}" 
                  th:if="${c.pid != 0}"
                  th:value="${c.id}" th:text="${c.id}+${c.name}">
                  </option>
                  </select>
              </div>
              <div class="form-group">
                 IMG: <input type="file" class="form-control form-control-item" name="mf">
              </div>
              
              <a id="registerbtn" href="#" class="btn btn-primary btn-user btn-block">
                  REGISTER
              </a>
             
          </form>
         
      </div>
  </div>
```

- detail
```html
<script>
$(document).ready(function(){
	$('#deletebtn').click(function(){
		var id = $('input[name="id"]').val();
		var c = confirm('삭제 하시겠습니까?');
		if(c == true){
			location.href='delete?id='+id;
		}
		
	});
	
	$('#updatebtn').click(function(){
		$('.user').attr({
			'enctype':'multipart/form-data',
			'method':'post',
			'action':'update' 
		});
		$('.user').submit();
	});
});

</script>

<style>
.form-group > img{
	width:200px; 
}
</style>

  <div class="col-lg-6">
      <div class="p-5">
          <div class="text-center">
              <h1 class="h4 text-gray-900 mb-4">Product Information</h1>
          </div>

          <form class="user">
         	  <div class="form-group">
         	  	  <img th:src="@{'/img/'+${dproduct.imgname}}">
         	  	  <input type="hidden" name="imgname" th:value="${dproduct.imgname}">
          	  </div>
              <div class="form-group">
                  <input type="text" class="form-control form-control-item"
                      name="id" value="id01" th:value="${dproduct.id}" readonly="readonly">
              </div>
              <div class="form-group">
                  <input type="text" class="form-control form-control-item"
                      name="name" value="pwd01" th:value="${dproduct.name}">
              </div>
              <div class="form-group">
                  <input type="text" class="form-control form-control-item"
                      name="price" value="number" th:value="${dproduct.price}">
              </div>
              <div class="form-group">
                  <input type="text" class="form-control form-control-item"
                      name="regdate" value="number" th:value="${#dates.format(dproduct.regdate,'yyyy/MM/dd')}">
              </div>
              <div class="form-group">
                 <select name="cid" class="form-control form-control-item">
                 	<option th:each="c:${clist}" 
                 	th:if="${c.pid != 0}"
                 	th:value="${c.id}" 
                 	th:text="${c.name}"
                 	th:selected="${c.id} == ${dproduct.cid}"
                 	>
                 	</option>
                 </select>
              </div>
              <div class="form-group">
                  <input type="file" class="form-control form-control-item"
                      name="mf">
              </div>
              <a id="updatebtn" href="#" class="btn btn-primary btn-user btn-block">
                  UPDATE
              </a>
              <a id="deletebtn" href="#" class="btn btn-primary btn-user btn-block">
                DELETE
              </a>
          </form>
         
      </div>
  </div>
```

- productselect
```html
<style>
	td>img{
		width: 50px;
	}
</style>
<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800"> Product Tables</h1>

<!-- DataTales Example -->
<div class="card shadow mb-4">

    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>REGDATE</th>
                        <th>CATEGORY</th>
                        <th>ImgName</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>REGDATE</th>
                        <th>CATEGORY</th>
                        <th>ImgName</th>
                    </tr>
                </tfoot>
                <tbody>
 						<tr th:each="p:${plist}">
                        <td><a href="" th:text="${p.id}" th:href="@{detail(id=${p.id})}">ID</a></td>
                        <td th:text="${p.name}">NAME</td>
                        <td th:text="${p.price}">PRICE</td>
                        <td th:text="${#dates.format(p.regdate,'yyyy/MM/dd')}">REGDATE</td>
                        <td th:text="${p.catename}">CATEGORY</td>
                        <td>
                        	<img th:src="@{'/img/'+${p.imgname}}">
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
```

- application properties
```properties
server.port=8088

spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/shoppingdb?serverTimezone=Asia/Seoul

spring.datasource.username=admin
spring.datasource.password=

mybatis.type-aliases-package=com.multi.vo
mybatis.mapper-locations=com/multi/mybatis/*.xml

spring.servlet.multipart.max-file-size=10MB
```


### src/test/java
#### com.multi.cart
- CartDeleteTests
```java
package com.multi.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CartBiz;

@SpringBootTest
class CartDeleteTests {

	@Autowired
	CartBiz cabiz;
	
	@Test
	void contextLoads() {
		try {
			cabiz.remove(1013);
			System.out.println("Delete OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

```

- CartInsertTests
```java
package com.multi.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CartBiz;
import com.multi.vo.CartVO;
import com.multi.vo.ProductVO;

@SpringBootTest
class CartInsertTests {

	@Autowired
	CartBiz biz;
	
	@Test
	void contextLoads() {
		//(NULL, 'id01',1006,CURDATE(),3);
		CartVO obj = new CartVO("id06",1016,2);
		try {
			biz.register(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
```

- CartSelectAllTests
```java
package com.multi.cart;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CartBiz;
import com.multi.biz.CateBiz;
import com.multi.vo.CartVO;
import com.multi.vo.CateVO;
import com.multi.vo.CustVO;

@SpringBootTest
class CartSelectAllTests {

	@Autowired
	CartBiz carbiz;
	
	@Test
	void contextLoads() {
		List<CartVO> list = null;
		try {
			list = carbiz.get();
			System.out.println("Select All Success");
			for(CartVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

```

- CartSelectTests
```java
package com.multi.cart;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CartBiz;
import com.multi.vo.CartVO;
import com.multi.vo.ProductVO;

@SpringBootTest
class CartSelectTests {

	@Autowired
	CartBiz biz;
	
	@Test
	void contextLoads() {
		List<CartVO> list = null;
		try {
			list = biz.get();
			for (CartVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
```

- CartUpdateTests
```java
package com.multi.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.CartBiz;
import com.multi.biz.CateBiz;
import com.multi.biz.CustBiz;
import com.multi.vo.CartVO;
import com.multi.vo.CateVO;
import com.multi.vo.CustVO;

@SpringBootTest
class CartUpdateTests {

	@Autowired
	CartBiz cabiz;
	
	@Test
	void contextLoads() {
		CartVO c = new CartVO(1005, 9);
		try {
			cabiz.modify(c);
			System.out.println("Update OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
```

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
			cabiz.remove(61);
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
		CateVO obj = new CateVO(31, "big hat", 30);
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
			cubiz.remove("id02");
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
		CustVO c = new CustVO("id04","choi","Busan","pwd04");
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
		CustVO c = new CustVO("id02", "han","Gyeonggi","pwd22");
		try {
			cubiz.modify(c);
			System.out.println("Update OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

```

#### com.multi.main
- CartCountTests
```java
package com.multi.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.MainBiz;

@SpringBootTest
class CartCountTests {

	@Autowired
	MainBiz biz;
	
	@Test
	void contextLoads() {
		
		int count;
		try {
			count = biz.getCartCnt();
			System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

}
```

- CateCountTests
```java
package com.multi.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.MainBiz;

@SpringBootTest
class CateCountTests {

	@Autowired
	MainBiz biz;
	
	@Test
	void contextLoads() {
		
		int count;
		try {
			count = biz.getCateCnt();
			System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
		
	}


}

```

- CustCountTests
```java
package com.multi.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.MainBiz;

@SpringBootTest
class CustCountTests {

	@Autowired
	MainBiz biz;
	
	@Test
	void contextLoads() {
		
		int count;
		try {
			count = biz.getCustCnt();
			System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}

}

```

- GetAdminTests
```java
package com.multi.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.MainBiz;
import com.multi.vo.AdminVO;

@SpringBootTest
class GetAdminTests {

	@Autowired
	MainBiz biz;
	
	@Test
	void contextLoads() {
		
		AdminVO admin = null;
		try {
			admin = biz.getAdmin("admin");
			System.out.println(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
}
```

- ProductCountTests
```java
package com.multi.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.MainBiz;

@SpringBootTest
class ProductCountTests {

	@Autowired
	MainBiz biz;
	
	@Test
	void contextLoads() {
		
		int count;
		try {
			count = biz.getProductCnt();
			System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
		
	}


}
```

- ProductSearchTests
```java
package com.multi.main;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.biz.MainBiz;
import com.multi.vo.ProductVO;

@SpringBootTest
class ProductSearchTests {

	@Autowired
	MainBiz biz;
	
	@Test
	void contextLoads() {
		
		List<ProductVO> list = null;;
		try {
			list = biz.searchProduct("pa");
			for(ProductVO p: list) {
				System.out.println(p);
			};
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
			pbiz.remove(1013);
			System.out.println("Delete OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
```