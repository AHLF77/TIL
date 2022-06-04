## day0413

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
	
}

```

#### com.multi.controller
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
		mv.addObject("left","cust/left");
		mv.addObject("center","cust/center");
		return mv;
	}
	
	@RequestMapping("/register")
	public ModelAndView register(ModelAndView mv) {
		mv.setViewName("main");
		mv.addObject("left","cust/left");
		mv.addObject("center","cust/register");
		return mv;
	}
	
	@RequestMapping("/registerimpl")
	public ModelAndView registerimpl(ModelAndView mv,CustVO obj) {
		mv.setViewName("main");
		mv.addObject("left","cust/left");
		try {
			biz.register(obj);
			mv.addObject("center","cust/registerok");
			mv.addObject("rcust", obj);
		} catch (Exception e) {
			mv.addObject("center","cust/registerfail");
		}
		return mv;
	}
	
	@RequestMapping("/custselect")
	public ModelAndView custselect(ModelAndView mv) {
		mv.setViewName("main");
		mv.addObject("left","cust/left");
		mv.addObject("center","cust/custselect");

		List<CustVO> list = null;
		try {
			list = biz.get();
			mv.addObject("allcust", list);
		} catch (Exception e) {
			
		}
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
		mv.setViewName("main");
		mv.addObject("left","cust/left");
		mv.addObject("center","cust/custdetail");
		return mv;
	}

	
	@RequestMapping("custupdate")
	public ModelAndView custupdate(ModelAndView mv, String id) {
		mv.setViewName("main");
		mv.addObject("left","cust/left");
		mv.addObject("center","cust/custupdate");
		
		CustVO cust = null;
		try {
			cust = biz.get(id);
			mv.addObject("ucust", cust);
		} catch (Exception e) {
			
		}
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
	
	@RequestMapping("custdelete")
	public String custdelete(String id) {
		try {
			biz.remove(id);
		} catch (Exception e) {
			
		}
		return "redirect:custselect";	
	}
	
}
```

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
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	
}
```