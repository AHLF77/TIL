# 0526 배운 내용 요약

### Day012
#### com.multi.controller
- controller
```java
package com.multi.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.multi.frame.Service;
import com.multi.user.UserService;
import com.multi.vo.UserVO;

public class Controller {

	public static void main(String[] args) {
		System.out.println("Spring Start .."); //스프링이 시작되는 곳 
		
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml"); 
		
		//  Ioc ( Injection Of Control) 제어 반전 loosely coupled -----> 거꾸로 스프링에서 만들어줌.
		// Service service = new UsesrService(); -----> 순방향 제어(tightly coupled) 
		Service service = (Service)factory.getBean("uservice");
		
		UserVO user = new UserVO("id01", "pwd01", "lee");
		service.register(user);
		
	}

}
```

#### com.multi.frame
- Dao(interface)
```java
package com.multi.frame;

public interface Dao<V> {
	public void insert(V v);
}

```

- Service(interface)
```java
package com.multi.frame;

public interface Service<V> {
	public void register(V v);
}

```

#### com.multi.user
- UserDao
```java
package com.multi.user;

import com.multi.frame.Dao;
import com.multi.vo.UserVO;

public class UserDao implements Dao<UserVO> {

	@Override
	public void insert(UserVO v) {
		System.out.println("Inserted: "+v);
	}
	
	
}

```

- UserOracleDao
```java
package com.multi.user;

import com.multi.frame.Dao;
import com.multi.vo.UserVO;

public class UserOracleDao implements Dao<UserVO> {

	@Override
	public void insert(UserVO v) {
		System.out.println("Inserted Oracle: "+v);
	}

}

```

- UserService
```java
package com.multi.user;

import com.multi.frame.Dao;
import com.multi.frame.Service;
import com.multi.vo.UserVO;

public class UserService implements Service<UserVO> {

	Dao<UserVO> dao;
	
	public Dao<UserVO> getDao() {
		return dao;
	}

	public void setDao(Dao<UserVO> dao) {
		this.dao = dao;
	}

	public UserService() {
		dao = new UserDao();
	}
	
	@Override
	public void register(UserVO v) {
		dao.insert(v);
	}
	
	
}

```

#### com.multi.vo
- UserVO
```java
package com.multi.vo;

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
 
 	<!-- DI (Dependency Injection): 의존성 주입 -->
 	<bean id="uservice" class="com.multi.user.UserService">
 		<property name="dao" ref="uodao"/>
 	</bean>
    <bean id="udao" class="com.multi.user.UserDao"/>
    <bean id="uodao" class="com.multi.user.UserOracleDao"/> 
     
</beans>
```