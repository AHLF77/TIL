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

#### com.test
- Delete
```java
package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;

public class ProductDeleteTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<Integer, ProductVO> service = 
				(Service<Integer, ProductVO>) factory.getBean("pservice");
	
		service.remove(100);
	}

}

```
- Insert
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
	
		ProductVO p = new ProductVO(100,"pants", 10000);
		service.register(p);
	}

}

```
- Select
```java
package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;
import com.vo.UserVO;

public class ProductSelectTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<Integer, ProductVO> service = 
				(Service<Integer, ProductVO>) factory.getBean("pservice");
		
		ProductVO product = null;
		product = service.get(300);
		
			System.out.println(product);

	}

}

```
- SelectAll
```java
package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;

public class ProductSelectAllTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<Integer, ProductVO> service = 
				(Service<Integer, ProductVO>) factory.getBean("pservice");
		
		List<ProductVO> list = null;
		list = service.get();
		
		for(ProductVO productVO : list) {
			System.out.println(productVO);
		}
	}

}

```

- Update
```java
package com.test;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;

public class ProductUpdateTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<Integer, ProductVO> service = 
				(Service<Integer, ProductVO>) factory.getBean("pservice");
	
		ProductVO p = new ProductVO(100,"shirts", 40000);
		service.modify(p);
	}

}

```

#### com.user
- UserDao
```java
package com.user;

import java.util.ArrayList;
import java.util.List;

import com.frame.Dao;
import com.vo.UserVO;

public class UserDao implements Dao<String, UserVO> {

	@Override
	public void insert(UserVO v) {
		System.out.println("Inserted: "+v);
	}

	@Override
	public void delete(String k) {
		System.out.println("Deleted: "+k);
	}

	@Override
	public void update(UserVO v) {
		System.out.println("Updated: "+v);
	}

	@Override
	public UserVO select(String k) {
		UserVO user = new UserVO(k,"pwd02","kim");
		return user;
	}

	@Override
	public List<UserVO> select() {
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		list.add(new UserVO("id01","pwd01","lee"));
		list.add(new UserVO("id02","pwd02","kim"));
		list.add(new UserVO("id03","pwd03","han"));
		list.add(new UserVO("id04","pwd04","yang"));
		list.add(new UserVO("id05","pwd05","james"));
		return list;
	}

}

```
- UserService
```java
package com.user;

import java.util.List;

import com.frame.Dao;
import com.frame.Service;
import com.vo.UserVO;

public class UserService implements Service<String, UserVO> {

	Dao<String, UserVO> dao;
	
	public Dao<String, UserVO> getDao() {
		return dao;
	}

	public void setDao(Dao<String, UserVO> dao) {
		this.dao = dao;
	}

	@Override
	public void register(UserVO v) {
		dao.insert(v);
	}

	@Override
	public void remove(String k) {
		dao.delete(k);
	}

	@Override
	public void modify(UserVO v) {
		dao.update(v);
	}

	@Override
	public UserVO get(String k) {
		return dao.select(k);
	}

	@Override
	public List<UserVO> get() {
		return dao.select();
	}

}

```

#### com.vo
- ProductVO
```java
package com.vo;

public class ProductVO {
	private int id;
	private String name;
	private int price;
	
	public ProductVO() {
		
	}

	public ProductVO(int id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
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

	@Override
	public String toString() {
		return "ProductVO [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
	
}

```
- UserVO
```java
package com.vo;

public class UserVO {
	private String id;
	private String pwd;
	private String name;
	
	public UserVO() {
	
	}

	public UserVO(String id, String pwd, String name) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pwd=" + pwd + ", name=" + name + "]";
	}
	
}

```

- spring
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" 
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
 		
 		<bean id="udao" class="com.user.UserDao"></bean>
 		<bean id="uservice" class="com.user.UserService">
 			<property name="dao" ref="udao"></property>
 		
 		</bean>
 		
 		<bean id="pdao" class="com.product.ProductDao"></bean>
 		<bean id="pservice" class="com.product.ProductService">
 			<constructor-arg name="dao" ref="pdao"/>
 		</bean>
 		
</beans>
```

- pom
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>day02</groupId>
  <artifactId>day02</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <build>
    <sourceDirectory>src</sourceDirectory>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
  
  <properties>
		<java-version>1.8</java-version>
        <org.springframework-version>4.2.5.RELEASE</org.springframework-version>
		<org.aspectj-version>1.6.5</org.aspectj-version>
		<org.slf4j-version>1.7.18</org.slf4j-version>
  </properties>
  
  <dependencies>
		<!-- Spring -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${org.springframework-version}</version>
			<exclusions>
				<!-- Exclude Commons Logging in favor of SLF4j -->
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				 </exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
				<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-aop</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-beans</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-expression</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-messaging</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-tx</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<!-- AspectJ -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>	
		
		
		  <dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjtools</artifactId>
			<version>${org.aspectj-version}</version>
		 </dependency>
		             
		  <dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>${org.aspectj-version}</version>
		  </dependency>
		
		
		
		
		<!-- Logging -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${org.slf4j-version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>jcl-over-slf4j</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${org.slf4j-version}</version>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.15</version>
			<exclusions>
				<exclusion>
					<groupId>javax.mail</groupId>
					<artifactId>mail</artifactId>
				</exclusion>
				<exclusion>
					<groupId>javax.jms</groupId>
					<artifactId>jms</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jdmk</groupId>
					<artifactId>jmxtools</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jmx</groupId>
					<artifactId>jmxri</artifactId>
				</exclusion>
			</exclusions>
			<scope>runtime</scope>
		</dependency>

		<!-- @Inject -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>1</version>
		</dependency>
				
		<!-- Servlet -->
       <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.0.1</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
		<!-- Tiles3 -->
		<dependency>
            <groupId>org.apache.tiles</groupId>
            <artifactId>tiles-core</artifactId>
            <version>3.0.3</version>
        </dependency>
        <dependency>
            <groupId>org.apache.tiles</groupId>
            <artifactId>tiles-extras</artifactId>
            <version>3.0.3</version>
        </dependency>

		  <!-- MyBatis -->
		 <dependency>
		  <groupId>org.mybatis</groupId>
		  <artifactId>mybatis</artifactId>
		  <version>3.2.3</version>
		 </dependency>
		
		 <dependency>
		  <groupId>org.mybatis</groupId>
		  <artifactId>mybatis-spring</artifactId>
		  <version>1.2.2</version>
		 </dependency>
		 <!-- DBCP -->
		
		 <dependency>
		  <groupId>commons-dbcp</groupId>
		  <artifactId>commons-dbcp</artifactId>
		  <version>1.2.2</version>
		 </dependency>
		<!--  
		 <dependency>
		     <groupId>com.oracle</groupId>
		     <artifactId>ojdbc6</artifactId>
		     <version>11.2.0</version>
		 </dependency>
		 -->
			<!-- AOP Alliance -->
		<!-- Test -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.7</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>1.3.1</version>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jms</artifactId>
			<version>4.2.5.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>aopalliance</groupId>
			<artifactId>aopalliance</artifactId>
			<version>1.0</version>
		</dependency>
		<dependency>
			<groupId>cglib</groupId>
			<artifactId>cglib</artifactId>
			<version>2.2</version>
		</dependency>
		<!-- json request -->   
		<dependency>
		   <groupId>com.fasterxml.jackson.core</groupId>
		   <artifactId>jackson-databind</artifactId>
		   <version>2.3.3</version>
		  </dependency>     
		 <dependency>
            <groupId>org.codehaus.jackson</groupId>
            <artifactId>jackson-mapper-asl</artifactId>
            <version>1.9.11</version>
        </dependency>
		 <dependency>
		 	<groupId>taglibs</groupId>
		 	<artifactId>standard</artifactId>
		 	<version>1.1.2</version>
		 </dependency>

		<dependency>
			<groupId>com.googlecode.json-simple</groupId>
			<artifactId>json-simple</artifactId>
			<version>1.1</version>
  		</dependency>
	</dependencies>
  
</project>
```

### day021
#### com.frame
- Dao
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

- Service
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

import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.vo.ProductVO;

@Repository("pdao")
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

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Service;
import com.vo.ProductVO;
import com.frame.Dao;

@org.springframework.stereotype.Service("pservice")
public class ProductService implements Service<Integer, ProductVO>{

	@Autowired
	Dao<Integer, ProductVO> dao;
	
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

#### com.test
- Delete
```java
package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;

public class ProductDeleteTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<Integer, ProductVO> service = 
				(Service<Integer, ProductVO>) factory.getBean("pservice");
	
		service.remove(100);
	}

}

```
- Insert
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
	
		ProductVO p = new ProductVO(100,"pants", 10000);
		service.register(p);
	}

}

```
- Select
```java
package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;
import com.vo.UserVO;

public class ProductSelectTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<Integer, ProductVO> service = 
				(Service<Integer, ProductVO>) factory.getBean("pservice");
		
		ProductVO product = null;
		product = service.get(300);
		
			System.out.println(product);

	}

}

```
- SelectAll
```java
package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;

public class ProductSelectAllTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<Integer, ProductVO> service = 
				(Service<Integer, ProductVO>) factory.getBean("pservice");
		
		List<ProductVO> list = null;
		list = service.get();
		
		for(ProductVO productVO : list) {
			System.out.println(productVO);
		}
	}

}

```

- Update
```java
package com.test;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;

public class ProductUpdateTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<Integer, ProductVO> service = 
				(Service<Integer, ProductVO>) factory.getBean("pservice");
	
		ProductVO p = new ProductVO(100,"shirts", 40000);
		service.modify(p);
	}

}

```

#### com.user
- UserDao
```java
package com.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.vo.UserVO;

@Repository("userDao")
public class UserDao implements Dao<String, UserVO> {

	@Override
	public void insert(UserVO v) {
		System.out.println("Inserted: "+v);
	}

	@Override
	public void delete(String k) {
		System.out.println("Deleted: "+k);
	}

	@Override
	public void update(UserVO v) {
		System.out.println("Updated: "+v);
	}

	@Override
	public UserVO select(String k) {
		UserVO user = new UserVO(k,"pwd02","kim");
		return user;
	}

	@Override
	public List<UserVO> select() {
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		list.add(new UserVO("id01","pwd01","lee"));
		list.add(new UserVO("id02","pwd02","kim"));
		list.add(new UserVO("id03","pwd03","han"));
		list.add(new UserVO("id04","pwd04","yang"));
		list.add(new UserVO("id05","pwd05","james"));
		return list;
	}

}

```

- UserService
```java
package com.user;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Dao;
import com.frame.Service;
import com.vo.UserVO;
@org.springframework.stereotype.Service("uservice")
public class UserService implements Service<String, UserVO> {

	@Autowired
	Dao<String, UserVO> dao;
	
	@Override
	public void register(UserVO v) {
		dao.insert(v);
	}

	@Override
	public void remove(String k) {
		dao.delete(k);
	}

	@Override
	public void modify(UserVO v) {
		dao.update(v);
	}

	@Override
	public UserVO get(String k) {
		return dao.select(k);
	}

	@Override
	public List<UserVO> get() {
		return dao.select();
	}

}

```
#### com.vo
- ProductVO
```java
package com.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.vo.UserVO;

@Repository("userDao")
public class UserDao implements Dao<String, UserVO> {

	@Override
	public void insert(UserVO v) {
		System.out.println("Inserted: "+v);
	}

	@Override
	public void delete(String k) {
		System.out.println("Deleted: "+k);
	}

	@Override
	public void update(UserVO v) {
		System.out.println("Updated: "+v);
	}

	@Override
	public UserVO select(String k) {
		UserVO user = new UserVO(k,"pwd02","kim");
		return user;
	}

	@Override
	public List<UserVO> select() {
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		list.add(new UserVO("id01","pwd01","lee"));
		list.add(new UserVO("id02","pwd02","kim"));
		list.add(new UserVO("id03","pwd03","han"));
		list.add(new UserVO("id04","pwd04","yang"));
		list.add(new UserVO("id05","pwd05","james"));
		return list;
	}

}

```
- UserVO
```java
package com.user;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Dao;
import com.frame.Service;
import com.vo.UserVO;
@org.springframework.stereotype.Service("uservice")
public class UserService implements Service<String, UserVO> {

	@Autowired
	Dao<String, UserVO> dao;
	
	@Override
	public void register(UserVO v) {
		dao.insert(v);
	}

	@Override
	public void remove(String k) {
		dao.delete(k);
	}

	@Override
	public void modify(UserVO v) {
		dao.update(v);
	}

	@Override
	public UserVO get(String k) {
		return dao.select(k);
	}

	@Override
	public List<UserVO> get() {
		return dao.select();
	}

}

```

- spring
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" 
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
       xmlns:context="http://www.springframework.org/schema/context" 
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans 
       http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
       http://www.springframework.org/schema/context 
       http://www.springframework.org/schema/context/spring-context-3.0.xsd
       ">
	<context:component-scan base-package="com.*"/>
</beans>
```
### day022
#### com.frame
- Dao
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
- LoggerAdvice
```java
package com.frame;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class LoggerAdvice {
	public void beforeLog(JoinPoint jp){
		Object [] arg = jp.getArgs();
		Signature si = jp.getSignature();
		
		Date d = new Date();
		System.out.println(d.toString()+
				" Before Log:"+si.getName()+":"+
				":"+arg[0]);
	}
	public void afterLog(JoinPoint jp){
		Signature si = jp.getSignature();
		System.out.println("After Log:"+si.getName());
	}
	public void afterReturnLog(JoinPoint jp,Object returnVal){
		Signature si = jp.getSignature();
		System.out.println("afterReturnLog:"+si.getName()+":"+returnVal);
	}
	public void afterEx(JoinPoint jp, Exception exObj){
		Signature si = jp.getSignature();
		System.out.println("afterEx Log:"+si.getName());
		System.out.println("Exception:"+exObj.getMessage());
	}
	public Object around(ProceedingJoinPoint process){
		Object result = null;
		Signature si = process.getSignature();	
		String className = process.getTarget().toString(); 
		long start = System.currentTimeMillis();
		//System.out.println("Before:"+si.getName()+" "+className);
		try {
			result = process.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(
				si.getName()+" 함수 실행 시간 "+(end-start)+"ms");
		//System.out.println("After:"+si.getName()+" "+className);
		return result;
	}
}
```

#### com.product
- ProductDao
```java
package com.product;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.vo.ProductVO;

@Repository("pdao")
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

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Service;
import com.vo.ProductVO;
import com.frame.Dao;

@org.springframework.stereotype.Service("pservice")
public class ProductService implements Service<Integer, ProductVO>{

	@Autowired
	Dao<Integer, ProductVO> dao;
	
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

- Service
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

#### com.user
- UserDao
```java
package com.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.frame.Dao;
import com.vo.UserVO;

@Repository("userDao")
public class UserDao implements Dao<String, UserVO> {

	@Override
	public void insert(UserVO v) {
		System.out.println("Inserted: "+v);
	}

	@Override
	public void delete(String k) {
		System.out.println("Deleted: "+k);
	}

	@Override
	public void update(UserVO v) {
		System.out.println("Updated: "+v);
	}

	@Override
	public UserVO select(String k) {
		UserVO user = new UserVO(k,"pwd02","kim");
		return user;
	}

	@Override
	public List<UserVO> select() {
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		list.add(new UserVO("id01","pwd01","lee"));
		list.add(new UserVO("id02","pwd02","kim"));
		list.add(new UserVO("id03","pwd03","han"));
		list.add(new UserVO("id04","pwd04","yang"));
		list.add(new UserVO("id05","pwd05","james"));
		return list;
	}

}

```
- UserService
```java
package com.user;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.frame.Dao;
import com.frame.Service;
import com.vo.UserVO;
@org.springframework.stereotype.Service("uservice")
public class UserService implements Service<String, UserVO> {

	@Autowired
	Dao<String, UserVO> dao;
	
	@Override
	public void register(UserVO v) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		dao.insert(v);
	}

	@Override
	public void remove(String k) {
		dao.delete(k);
	}

	@Override
	public void modify(UserVO v) {
		dao.update(v);
	}

	@Override
	public UserVO get(String k) {
		return dao.select(k);
	}

	@Override
	public List<UserVO> get() {
		return dao.select();
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
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans 
       http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
       http://www.springframework.org/schema/context 
       http://www.springframework.org/schema/context/spring-context-3.0.xsd
       http://www.springframework.org/schema/aop 
       http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
       ">
	<context:component-scan base-package="com.*"/>

	<bean id="logAdvice" class="com.frame.LoggerAdvice"/>
 	
	<aop:config>
		<aop:pointcut expression="execution(* com.user.*Service.*(..))" 
		id="mypoint"/>
		<aop:aspect id="MyAspect" ref="logAdvice">
			<aop:after method="afterLog"  pointcut-ref="mypoint"/>
		</aop:aspect>
	</aop:config>
 	
</beans>
```
- <aop:before method="beforeLog"  pointcut-ref="mypoint"/>
- <aop:around method="around"  pointcut-ref="mypoint"/>

### day023


### day024