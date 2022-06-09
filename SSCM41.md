# 0608~0610 강의
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.mapper.MainMapper;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.biz.CateBiz;
import com.multi.biz.CustBiz;
import com.multi.vo.CateVO;
import com.multi.vo.CustVO;

@RestController
public class AJAXController {

	@Autowired
	CustBiz cubiz;
	
	@Autowired
	CateBiz cabiz;
	
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
		
	@RequestMapping("/cartselect")
	public String cartselect(Model m) {
		List<CartVO> list = null;
		try {
			list = biz.get();
			m.addAttribute("cartlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center","cart/cartselect");
		return "index";
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
@RequestMapping("cate")
public class CateController {
	
	@Autowired
	CateBiz biz;
	
	@RequestMapping("cateadd")
	public String add(Model m) {
		m.addAttribute("center","cate/cateadd");
		return "index";
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
		return "index";
	}
	
	@RequestMapping("/catedetail")
	public String catedetail(Model m, Integer id) {
		CateVO obj = null;
		try {
			obj = biz.get(id);
			m.addAttribute("dcate", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center","cate/catedetail");
		return "index";
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.MainBiz;

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
		return "index";
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

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MainMapper {

	public int getcustcnt() throws Exception;
	public int getproductcnt() throws Exception;
	public int getcatecnt() throws Exception;
	public int getcartcnt() throws Exception;
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