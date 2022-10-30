# 0526 배운 내용 요약

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
- UserDeleteTest
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
	
		service.remove("id01");
	}

}

```

- UserSelectTest
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
		user = service.get("id01");
		
			System.out.println(user);

	}

}

```

- UserSelectAllTest
```java
package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.frame.Service;
import com.vo.UserVO;

public class UserSelectAllTest {

	public static void main(String[] args) {
		ApplicationContext factory =
				new ClassPathXmlApplicationContext("spring.xml");
	
		Service<String, UserVO> service = 
				(Service<String, UserVO>) factory.getBean("uservice");
		
		List<UserVO> list = null;
		list = service.get();
		
		for(UserVO userVO : list) {
			System.out.println(userVO);
		}
	}

}

```

- UserSelectTest
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
		user = service.get("id01");
		
			System.out.println(user);

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