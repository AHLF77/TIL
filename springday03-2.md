# 0531 배운 내용 요약

### day032
#### com.config
- mybatis
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org/DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<typeAliases>
		<typeAlias type="com.vo.ItemVO" alias="item"/>
	</typeAliases>
	
	<mappers>
		<mapper resource="com/config/itemmapper.xml"/>
	</mappers>
</configuration>
```

- itemmapper
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org/DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mapper.ItemMapper">
	
	<select id="select" parameterType="int" resultType="item">
		SELECT * FROM ITEM WHERE ID=#{obj}
	</select>
	<select id="selectall" resultType="item">
		SELECT * FROM ITEM
	</select>
	<insert id="insert" parameterType="item">
		INSERT INTO ITEM VALUES (NULL, #{name}, #{price}, #{imgname}, SYSDATE())
	</insert>
	<update id="update" parameterType="item">
		UPDATE ITEM SET NAME=#{name}, PRICE=#{price}, IMGNAME=#{imgname} WHERE ID=#{id}
	</update>
	<delete id="delete" parameterType="int">
		DELETE FROM ITEM WHERE ID=#{obj}
	</delete>
	<select id="searchname" parameterType="String" resultType="item">
		SELECT * FROM ITEM WHERE NAME LIKE CONCAT('%',#{name},'%')
	</select>
	<select id="getprice" parameterType="int" resultType="item">
		SELECT * FROM ITEM WHERE PRICE BETWEEN #{param1} AND #{param2}
	</select>
	<select id="getrdate" parameterType="date" resultType="item">
		SELECT * FROM ITEM WHERE regdate >= #{date}
	</select>
	
</mapper>
```

#### com.frame
- Service(interface)
```java
package com.frame;

import java.util.List;

public interface Service<K,V> {
	/* 느슨하게 하기 위해*/
	public void register(V v) throws Exception;
	public void remove(K k) throws Exception;
	public void modify(V v) throws Exception;
	public V get(K k) throws Exception;
	public List<V> get() throws Exception;
}
```

#### com.mapper
- ItemMapper(interface)
```java
package com.mapper;

import java.util.Date;
import java.util.List;

import com.vo.ItemVO;

public interface ItemMapper {

	public void insert(ItemVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(ItemVO obj) throws Exception;
	public ItemVO select(int obj) throws Exception;
	public List<ItemVO> selectall() throws Exception;
	public List<ItemVO> searchname(String name) throws Exception;
	public List<ItemVO> getprice(Integer price1, Integer price2) throws Exception;
	public List<ItemVO> getrdate(String date) throws Exception;
}
```
#### com.service
- ItemService
```java
package com.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Service;
import com.mapper.ItemMapper;
import com.vo.ItemVO;

@org.springframework.stereotype.Service("iservice")
public class ItemService implements Service<Integer, ItemVO>{

	@Autowired
	ItemMapper dao;
	
	@Override
	public void register(ItemVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public void modify(ItemVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public ItemVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<ItemVO> get() throws Exception {
		return dao.selectall();
	}
	
	public List<ItemVO> searchname(String name) throws Exception{
		return dao.searchname(name);
	}

	public List<ItemVO> getprice(int price1, int price2) throws Exception{
		return dao.getprice(price1, price2);
	}
	
	public List<ItemVO> getrdate(String date) throws Exception{
		return dao.getrdate(date);
	}
}
```

#### com.test
- ItemDelete
```java
package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ItemVO;

public class ItemDeleteTest {
	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<Integer, ItemVO> service = 
				(Service<Integer, ItemVO>) factory.getBean("iservice");
	
		try {
			service.remove(1003);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
```
- ItemGetPrice
```java
package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.ItemService;
import com.vo.ItemVO;

public class ItemGetPriceTest {
	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
		
		ItemService service = 
				(ItemService) factory.getBean("iservice");
		
		List<ItemVO> list = null;
		try {
			list = service.getprice(10000, 30000); // 제품 금액이 특정 구간(10000~30000)인 item을 검색
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(ItemVO itemVO : list) {
			System.out.println(itemVO);
		}
	}

}
```

- ItemGetRdate
```java
package com.test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.ItemService;
import com.vo.ItemVO;


public class ItemGetRdateTest {
	public static void main(String[] args) {
		
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
		
		ItemService service = 
				(ItemService) factory.getBean("iservice");
		
		
		List<ItemVO> list = null;
		try {
			list = service.getrdate("2022-05-29");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(ItemVO itemVO : list) {
			System.out.println(itemVO);
		}
	}

}
```

- ItemInsert
```java
package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ItemVO;

public class ItemInsertTest {
	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<Integer, ItemVO> service = 
				(Service<Integer, ItemVO>) factory.getBean("iservice");
	
		ItemVO i = new ItemVO("shirts1", 80000,"shirts1.png");
		try {
			service.register(i);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
```

- ItemSearchName
```java
package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.ItemService;
import com.vo.ItemVO;

public class ItemSearchNameTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
			
		ItemService service = 
				(ItemService) factory.getBean("iservice");
		
		List<ItemVO> list = null;
		try {
			list = service.searchname("i"); //Item 이름에 알파벳 검색
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(ItemVO itemVO : list) {
			System.out.println(itemVO);
		}
	}
}
```

- ItemSelectAll
```java
package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ItemVO;

public class ItemSelectAllTest {
	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<Integer, ItemVO> service = 
				(Service<Integer, ItemVO>) factory.getBean("iservice");
		
		List<ItemVO> list = null;
		try {
			list = service.get();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		for(ItemVO itemVO : list) {
			System.out.println(itemVO);
		}
	}
}

```

- ItemUpdate
```java
package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ItemVO;

public class ItemUpdateTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<Integer, ItemVO> service = 
				(Service<Integer, ItemVO>) factory.getBean("iservice");
	
		ItemVO i = new ItemVO(1000, "pants6", 10000, "pants6.jpg");
		try {
			service.modify(i);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
```

#### com.vo
- ItemVO
```java
package com.vo;

import java.util.Date;

public class ItemVO {
	private int id;
	private String name;
	private int price;
	private String imgname;
	private Date regdate;
	
	public ItemVO() {
	
	}

	public ItemVO(String name, int price, String imgname) {
		this.name = name;
		this.price = price;
		this.imgname = imgname;
	}
	
	public ItemVO(int id, String name, int price, String imgname) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.imgname = imgname;
	}

	public ItemVO(int id, String name, int price, String imgname, Date regdate) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.imgname = imgname;
		this.regdate = regdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "ItemVO [id=" + id + ", name=" + name + ", price=" + price + ", imgname=" + imgname + ", regdate="
				+ regdate + "]";
	}
	
	
}
```

### workbench
#### shopdb(item)
```sql
SELECT * FROM ITEM;
CREATE TABLE item(
   id INT,
   name VARCHAR(20) NOT NULL,
   price INT  NOT NULL,
   imgname VARCHAR(20),
   regdate DATE  NOT NULL
);
ALTER TABLE item ADD PRIMARY KEY (id);
ALTER TABLE item MODIFY id INT AUTO_INCREMENT;
ALTER TABLE item AUTO_INCREMENT = 1000;

INSERT INTO item (name,price,imgname,regdate) VALUES('pants1',10000,'pants1.jpg',CURRENT_DATE());
INSERT INTO item (name,price,imgname,regdate) VALUES('pants2',20000,'pants2.jpg',CURRENT_DATE());
INSERT INTO item (name,price,imgname,regdate) VALUES('pants3',30000,'pants3.jpg',CURRENT_DATE());
INSERT INTO item (name,price,imgname,regdate) VALUES('pants4',40000,'pants4.jpg',CURRENT_DATE());
INSERT INTO item VALUES(NULL,'pants5',50000,'pants5.jpg',CURRENT_DATE());

SELECT * FROM ITEM WHERE PRICE BETWEEN 10000 AND 30000;
```