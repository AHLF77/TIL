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