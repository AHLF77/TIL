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