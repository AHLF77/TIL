# 0526강의(Spring 강의)
- 프레임워크: 기능을 미리 클래스나 인터페이스 등으로 만들어 제공하는 반제품 정도로 해석할 수 있습니다. 
- 즉, 어느 정도 완성된 상태로 제공하는 기능인 셈.
- 스프링 프레임워크: 자바 웹 어플리케이션 개발을 위한 오픈 소스 프레임워크(경량 프레임워크)

## 스프링 프레임워크의 특징
- 의존성 주입(DI)
- POJO 방식 프레임워크
- 제어 반전(IoC)
- 관점 지향 프로그래밍(AOP)
- 경량 컨테이너
- 가벼운 경량 프레임워크

### Day01
#### com.multi
- controller
```java
package com.multi;

public class Controller {

	public static void main(String[] args) {
		Service service = new Service();
		UserVO user = new UserVO("id01", "pwd01", "han");
		
		service.register(user);

	}

}
````

- dao
```java
package com.multi;

public class Dao {
	public void insert(UserVO user) {
		//SQL DB Insert
		System.out.println("Inserted: "+user);
	}
}
```

- Service
```java
package com.multi;

public class Service {
	Dao dao;
	
	public Service() {
		dao = new Dao();
	}
	public void register(UserVO user){
		dao.insert(user);
	}
}
```

- UserVo
```java
package com.multi;

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

#### com.multi.controller
- Controller
```java
package com.multi.controller;

import com.multi.frame.Service;
import com.multi.user.UserService;
import com.multi.vo.UserVO;

public class Controller {

	public static void main(String[] args) {
		Service service = null;
		
		service = new UserService();
		
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

- UserService
```java
package com.multi.user;

import com.multi.frame.Dao;
import com.multi.frame.Service;
import com.multi.vo.UserVO;

public class UserService implements Service<UserVO> {

	Dao<UserVO> dao;
	
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

### Day011
#### app
- Controller
```java
package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import frame.TV;

public class Controller {

	public static void main(String[] args) {
		System.out.println("App Start ...."); // 프로그램 실행
		
		System.out.println("Spring Start .."); //스프링이 시작되는 곳 
		ApplicationContext factory =
		new ClassPathXmlApplicationContext("spring.xml"); // xml 파일을 가지고
	
		TV tv = (TV) factory.getBean("ss"); // LG를 달라고 요청
		tv.on();
		tv.off();
		
		System.out.println("App End ....");
	}

}
```

#### bean
- LTV
```java
package bean;

import frame.TV;

public class LTV implements TV {

	public LTV() {
		System.out.println("Create LG TV Bean ....");
	}
	
	@Override
	public void on() {
		System.out.println("LG TV ON ....");
	}

	@Override
	public void off() {
		System.out.println("LG TV OFF ....");
	}

}

```
- STV
```java
package bean;

import frame.TV;

public class STV implements TV {

	public STV() {
		System.out.println("Create SAMSUNG TV Bean ....");
	}
	
	@Override
	public void on() {
		System.out.println("SAMSUNG TV ON ....");
	}

	@Override
	public void off() {
		System.out.println("SAMSUNG TV OFF ....");
	}

}

```

#### frame
- TV(interface)
```java
package frame;

public interface TV {
	
	public void on();
	public void off();

}
```

- spring
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" 
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
 
    <bean id="lg" class="bean.LTV"/> 
    <bean id="ss" class="bean.STV"/> 
    
</beans>
```

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
		new ClassPathXmlApplicationContext("spring.xml"); // xml 파일을 가지고
		
		//IoC (Inversion Of Control) 제어 역행 loosely coupled
		//Service service = new UserService(); tightly coupled
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

### Day013
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

#### com.test
- Service
```java

```

- Service
```java

```

- Service
```java

```

- Service
```java

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
 		
</beans>
```