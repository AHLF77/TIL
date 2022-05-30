# 0530강의(AOP 강의)

## AOP(Aspect Oriented Programming)
- 관점 지향 프로그래밍
- 해킹 관련
- 각 클래스 마다, 시큐어 코딩을 적용하면 지루한 반복작업을 계속 해주어야 합니다.
- 메서드 안의 주기능과 보조 기능을 분리하여 선택적으로 메서드에 적용해서 사용한다는 개념입니다.
- 장점은 전체 코드에 흩어져 있는 보조 기능을 하나의 장소에 모아서 관리가 가능.
- 또 보조 기능을 자신이 원하는 주 기능에 선택적으로 적용할 수 있어 코드가 단순해지고 가독성도 향상됨.
 
### day02
#### com.frame
- Dao(interface)
```java
package com.frame;

import java.util.List;

public interface Dao<K,V> {
	public void insert(V v);
	public void delete(K k);
	public void update(V v);
	public V select(K k);
	public List select();
}

```
- Service(interface)
```java
package com.frame;

import java.util.List;

public interface Service<K,V> {
	public void register(V v);
	public void remove(K k);
	public void modify(V v);
	public V get(K k);
	public List<V> get();
}
```

#### com.product
- ProductDao
```java
package com.product;

import java.util.ArrayList;
import java.util.List;

import com.frame.Dao;
import com.vo.ProductVO;

public class ProductDao implements Dao<Integer, ProductVO>{

	@Override
	public void insert(ProductVO v) {
		System.out.println("Inserted: " +v);
	}

	@Override
	public void delete(Integer k) {
		System.out.println("Deleted: " +k);
	}

	@Override
	public void update(ProductVO v) {
		System.out.println("Updated: " +v);
	}

	@Override
	public ProductVO select(Integer k) {
		ProductVO p = new ProductVO(k, "pants", 10000);
		return p;
	}

	@Override
	public List<ProductVO> select() {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		list.add(new ProductVO(100,"pants1",10000));
		list.add(new ProductVO(101,"pants2",20000));
		list.add(new ProductVO(102,"pants3",30000));
		list.add(new ProductVO(103,"pants4",40000));
		list.add(new ProductVO(104,"pants5",50000));
		return list;
	}
	
	
}

```
- ProductService
```java
package com.product;

import java.util.List;

import com.frame.Service;
import com.vo.ProductVO;
import com.frame.Dao;

public class ProductService implements Service<Integer, ProductVO>{

	Dao<Integer, ProductVO> dao;
	
	
	public ProductService(Dao<Integer, ProductVO> dao) {
		this.dao = dao;
	}

	@Override
	public void register(ProductVO v) {
		dao.insert(v);
	}

	@Override
	public void remove(Integer k) {
		dao.delete(k);
	}

	@Override
	public void modify(ProductVO v) {
		dao.update(v);
	}

	@Override
	public ProductVO get(Integer k) {
		
		return dao.select(k);
	}

	@Override
	public List<ProductVO> get() {
		return dao.select();
	}

}

```

### day021


### day022


### day023


### day024