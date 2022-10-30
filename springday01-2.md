# 0526 배운 내용 요약

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
