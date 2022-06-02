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