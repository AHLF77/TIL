# 0531강의



### day031
#### com.config
- mybatis
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org/DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<typeAliases>
		<typeAlias type="com.vo.UserVO" alias="user"/>
		<typeAlias type="com.vo.ProductVO" alias="product"/>
	</typeAliases>
	
	<mappers>
		<mapper resource="com/config/usermapper.xml"/>
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
		SELECT * FROM PRODUCT WHERE ID=#{obj}
	</select>
	<select id="selectall" resultType="product">
		SELECT * FROM PRODUCT
	</select>
	<insert id="insert" parameterType="product">
		INSERT INTO PRODUCT VALUES (NULL, #{name},#{price},SYSDATE(),#{rate})
	</insert>
	<update id="update" parameterType="product">
		UPDATE PRODUCT SET NAME=#{name}, PRICE=#{price}, RATE=#{rate} WHERE ID=#{id}
	</update>
	<delete id="delete" parameterType="int">
		DELETE FROM PRODUCT WHERE ID=#{obj}
	</delete>
	<select id="searchname" parameterType="String" resultType="product">
		SELECT * FROM PRODUCT WHERE NAME LIKE CONCAT('%',#{name},'%')
	</select>
	<select id="getrate" parameterType="double" resultType="product">
		SELECT * FROM PRODUCT WHERE RATE > #{rate}
	</select>
	
</mapper>
```
- usermapper
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org/DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mapper.UserMapper">
	
	<select id="select" parameterType="String" resultType="user">
		SELECT * FROM CUST WHERE ID=#{obj}
	</select>
	<select id="selectall" resultType="user">
		SELECT * FROM CUST
	</select>
	<insert id="insert" parameterType="user">
		INSERT INTO CUST VALUES (#{id},#{pwd},#{name})
	</insert>
	<update id="update" parameterType="user">
		UPDATE CUST SET PWD=#{pwd},NAME=#{name} WHERE ID=#{id}
	</update>
	<delete id="delete" parameterType="String">
		DELETE FROM CUST WHERE ID=#{obj}
	</delete>
	<select id="searchname" parameterType="String" resultType="user">
		SELECT * FROM CUST WHERE NAME LIKE CONCAT('%',#{name},'%')
	</select>
	
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
	public void insert(ProductVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(ProductVO obj) throws Exception;
	
	public ProductVO select(int obj) throws Exception;
	public List<ProductVO> selectall() throws Exception;
	public List<ProductVO> searchname(String name) throws Exception;
	public List<ProductVO> getrate(double rate) throws Exception;

}
```

- UserMapper(interface)
```java
package com.mapper;

import java.util.List;

import com.vo.UserVO;

public interface UserMapper {
	public void insert(UserVO obj) throws Exception;
	public void delete(String obj) throws Exception;
	public void update(UserVO obj) throws Exception;
	
	public UserVO select(String obj) throws Exception;
	public List<UserVO> selectall() throws Exception;
	public List<UserVO> searchname(String name) throws Exception;
}
```

#### com.test
- ProductDelete
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
	
		try {
			service.remove(1);
		} catch (Exception e) {
			// e.printStackTrace();
			
		}
	}

}
```
- ProductGetRate
```java
package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.ProductService;
import com.vo.ProductVO;

public class ProductGetRateTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
//		Service<String, ProductVO> service = 
//				(Service<String, ProductVO>) factory.getBean("pservice");

		
		ProductService service = 
				(ProductService) factory.getBean("pservice");
		
		List<ProductVO> list = null;
		try {
			list = service.getrate(3.0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(ProductVO productVO : list) {
			System.out.println(productVO);
		}
	}

}

```

- ProductInsert
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
	
		Service<String, ProductVO> service = 
				(Service<String, ProductVO>) factory.getBean("pservice");
	
		ProductVO p = new ProductVO("shirts", 35000, 1.6);
		try {
			service.register(p);
		} catch (Exception e) {
			// e.printStackTrace();
			
		}
	}

}
```

- ProductSearchName
```java
package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.ProductService;
import com.vo.ProductVO;

public class ProductSearchNameTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
			
		ProductService service = 
				(ProductService) factory.getBean("pservice");
		
		List<ProductVO> list = null;
		try {
			list = service.searchname("p");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(ProductVO productVO : list) {
			System.out.println(productVO);
		}
	}

}
```

- ProductSelectAll
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
	
		Service<String, ProductVO> service = 
				(Service<String, ProductVO>) factory.getBean("pservice");
		
		List<ProductVO> list = null;
		try {
			list = service.get();
		} catch (Exception e) {
			// e.printStackTrace();
			
		}
		
		for(ProductVO productVO : list) {
			System.out.println(productVO);
		}
	}

}
```

- ProductSelect
```java
package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.ProductVO;

public class ProductSelectTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<Integer, ProductVO> service = 
				(Service<Integer, ProductVO>) factory.getBean("pservice");
		
		ProductVO product = null;
		try {
			product = service.get(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			System.out.println(product);

	}

}
```

- ProductUpdate
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
	
		ProductVO p = new ProductVO(2, "jeans2", 10000, 2.8);
		try {
			service.modify(p);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
```

- UserDelete
```java
package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.UserVO;

public class UserDeleteTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<String, UserVO> service = 
				(Service<String, UserVO>) factory.getBean("uservice");
	
		try {
			service.remove("id88");
		} catch (Exception e) {
			System.out.println("해당 아이디는 존재하지 않습니다.");
			//e.printStackTrace();
		}
	}

}
```

- UserInsert
```java
package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.UserVO;

public class UserInsertTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<String, UserVO> service = 
				(Service<String, UserVO>) factory.getBean("uservice");
	
		UserVO u = new UserVO("id89","pwd89","lee");
		try {
			service.register(u); // 등록 시 주의 해야해 라는 문구
		} catch (Exception e) {
			System.out.println("입력 오류가 발생하였습니다.");
			//e.printStackTrace();
		}
	}

}
```

- UserSearchName
```java
package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.UserService;
import com.vo.UserVO;

public class UserSearchNameTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
			
		UserService service = 
				(UserService) factory.getBean("uservice");
		
		List<UserVO> list = null;
		try {
			list = service.searchname("말");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(UserVO userVO : list) {
			System.out.println(userVO);
		}
	}

}
```

- UserSelectAll
```java
package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.UserService;
import com.vo.UserVO;

public class UserSelectAllTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		//Service<String,UserVO> service = 
		//(Service<String, UserVO>) factory.getBean("uservice");
		
		UserService service = 
				(UserService) factory.getBean("uservice");
		
		List<UserVO> list = null;
		try {
			list = service.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(UserVO userVO : list) {
			System.out.println(userVO);
		}
	}

}
```

- UserSelect
```java
package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.UserVO;

public class UserSelectTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<String, UserVO> service = 
				(Service<String, UserVO>) factory.getBean("uservice");
		
		UserVO user = null;
		try {
			user = service.get("id89");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
			System.out.println(user);

	}

}

```

- UserUpdate
```java
package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.UserVO;

public class UserUpdateTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<String, UserVO> service = 
				(Service<String, UserVO>) factory.getBean("uservice");
	
		UserVO u = new UserVO("id89", "pwd45","yang");
		try {
			service.modify(u);
		} catch (Exception e) {
			e.printStackTrace();	
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
	private double rate;
	
	public ProductVO() {
	
	}

	public ProductVO(String name, int price, double rate) {
		this.name = name;
		this.price = price;
		this.rate = rate;
	}
	

	public ProductVO(int id, String name, int price, double rate) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.rate = rate;
	}

	public ProductVO(int id, String name, int price, Date regdate, double rate) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.regdate = regdate;
		this.rate = rate;
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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "ProductVO [id=" + id + ", name=" + name + ", price=" + price + ", regdate=" + regdate + ", rate=" + rate
				+ "]";
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
 		<property name="url" value="jdbc:mysql://127.0.0.1:3306/shopdb?serverTimezone=Asia/Seoul"/>
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
		
		<!-- MySQL Driver -->	
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.17</version>
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

- ItemSelect
```java

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

```

#### com.service
- ProductService
```java

```

#### com.test
- ProductInsertTest
```java

```

- ProductSelectAllTest
```java

```

#### com.vo
- ProductVO
```java

```

- spring
```xml

```