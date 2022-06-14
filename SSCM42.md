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

```

- ProductBiz
```java

```