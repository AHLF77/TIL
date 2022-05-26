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
- com.multi.controller

- com.multi.frame

- com.multi.user

- com.multi.vo


### Day011


### Day012


### Day013