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