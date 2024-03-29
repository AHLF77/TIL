# 0526 배운 내용 요약
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

- Dao
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
		// Security,
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