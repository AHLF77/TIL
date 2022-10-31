# 0531 배운 내용 요약

### day033
#### com.config
- mybatis
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org/DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<typeAliases>
		<typeAlias type="com.vo.ProductVO" alias="product"/>
	</typeAliases>
	
	<mappers>
		<mapper resource="com/config/productmapper.xml"/>
	</mappers>
</configuration>
```

- productmapper
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org/DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mapper.ProductMapper">
	
	<select id="select" parameterType="int" resultType="product">
		SELECT p.id, p.name, p.price, p.regdate, p.cid, c.name as catename
		FROM PRODUCT p
		INNER JOIN cate c ON p.cid = c.id
		WHERE p.id=#{id}
	</select>
	<select id="selectall" resultType="product">
		SELECT p.id, p.name, p.price, p.regdate, p.cid, c.name as catename
		FROM PRODUCT p
		INNER JOIN cate c ON p.cid = c.id
	</select>
	<insert id="insert" parameterType="product">
		INSERT INTO PRODUCT VALUES (NULL,#{name},#{price}, SYSDATE(), #{cid})
	</insert>
	<update id="update" parameterType="product">
		UPDATE CUST SET name=#{name}, price=#{price}, cid=#{cid} WHERE ID=#{id}
	</update>
	<delete id="delete" parameterType="int">
		DELETE FROM PRODUCT WHERE ID=#{id}
	</delete>

</mapper>
```

#### com.frame
- Service(interface)
```java
package com.frame;

import java.util.List;

public interface Service<K,V> {
	public void register(V v) throws Exception;
	public void remove(K k) throws Exception;
	public void modify(V v) throws Exception;
	public V get(K k) throws Exception;
	public List<V> get() throws Exception;
}
```

#### com.mapper
- ProductMapper(interface)
```java
package com.mapper;

import java.util.List;

import com.vo.ProductVO;

public interface ProductMapper {
	public void insert(ProductVO p) throws Exception;
	public void delete(int id) throws Exception;
	public void update(ProductVO p) throws Exception;
	public ProductVO select(int id) throws Exception;
	public List<ProductVO> selectall() throws Exception;
}
```

#### com.service
- ProductService
```java
package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Service;
import com.mapper.ProductMapper;
import com.vo.ProductVO;

@org.springframework.stereotype.Service("pservice")
public class ProductService implements Service<Integer, ProductVO>{

	@Autowired
	ProductMapper dao;
	
	@Override
	public void register(ProductVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public void modify(ProductVO v) throws Exception {
		dao.update(v);
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

#### com.test
- ProductInsertTest
```java
package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;

public class ProductInsertTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<Integer, ProductVO> service = 
				(Service<Integer, ProductVO>) factory.getBean("pservice");
	
		ProductVO p = new ProductVO("pants05",10000, 11);
		try {
			service.register(p); 
		} catch (Exception e) {
			System.out.println("입력 오류가 발생하였습니다.");
			e.printStackTrace();
		}
	}

}
```

- ProductSelectAllTest
```java
package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.ProductService;
import com.vo.ProductVO;

public class ProductSelectAllTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		ProductService service = 
				(ProductService) factory.getBean("pservice");
		
		List<ProductVO> list = null;
		try {
			list = service.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(ProductVO productVO : list) {
			System.out.println(productVO);
		}
	}

}
```

#### com.vo
- ProductVO
```java
package com.vo;

import java.util.Date;

public class ProductVO {
	private int id;
	private String name;
	private int price;
	private Date regdate;
	private int cid;
	private String catename;
	
	public ProductVO() {
		
	}
	
	public ProductVO(String name, int price, int cid) {
		this.name = name;
		this.price = price;
		this.cid = cid;
	}

	public ProductVO(int id, String name, int price, Date regdate, int cid) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.regdate = regdate;
		this.cid = cid;
	}

	public ProductVO(int id, String name, int price, Date regdate, int cid, String catename) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.regdate = regdate;
		this.cid = cid;
		this.catename = catename;
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

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCatename() {
		return catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}

	@Override
	public String toString() {
		return "ProductVO [id=" + id + ", name=" + name + ", price=" + price + ", regdate=" + regdate + ", cid=" + cid
				+ ", catename=" + catename + "]";
	}
	
}
```

- spring
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" 
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
       xmlns:context="http://www.springframework.org/schema/context" 
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx" 
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans 
       http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
       http://www.springframework.org/schema/context 
       http://www.springframework.org/schema/context/spring-context-3.0.xsd
       http://www.springframework.org/schema/aop 
       http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
       http://www.springframework.org/schema/tx 
       http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
       ">
	
	<context:component-scan base-package="com.*"/>
 	<tx:annotation-driven transaction-manager="txManager"/> 
 	
 	<!-- 1. Database Setting -->
 	<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
 		<property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
 		<property name="url" value="jdbc:mysql://127.0.0.1:3306/shoppingdb?serverTimezone=Asia/Seoul"/>
 		<property name="username" value="admin1"/>
 		<property name="password" value="111111"/>
 	</bean>

 	<!-- 2. Transaction Setting -->
 	<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
 		<property name="dataSource" ref="dataSource"/>
 	</bean>
 	
 	<!-- 3. MyBatis Setting -->
 	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
 		<property name="dataSource" ref="dataSource"/>
 		<property name="configLocation" value="classpath:com/config/mybatis.xml"/>
 	</bean>
 	
 	<!-- 3-1. Mapper Setting -->
 	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
 		<property name="basePackage" value="com.mapper"/>
 	</bean>
 	
 	<!-- 4. Spring Mybatis Connect -->
 	<bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
 		<constructor-arg ref="sqlSessionFactory"/>
 	</bean>

 	
</beans>
```