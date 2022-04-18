## 0418 강의

## 캡슐화
- 객체의 필드, 메소드를 하나로 묶고, 실제 구현 내용을 감추는 것

### 관계종류
- 집합 관계: 완성품과 부품의 관계(ex: 컴퓨터는 CPU가 있다.)
- 사용 관계: 객체가 다른 객체를 사용하는 관계(ex: 은행에서 일반적인 관계는 계좌를 가지고 있음)
- 상속 관계: 종류 객체와 구체적인 사물 객체 관계()

## 상속성
- 상위(부모)가 가지고 있는 제산을 자식(하위)에게 물려주는 것

## 다형성
- 같은 타입이지만 실행 결과가 다양한 객체를 대입할 수 있는 성질
- 효과: 유지보수 용이, 객체를 부품화 시키는 것 가능

## 객체와 클래스
- 설계도가 있어야 한다.(UML)
- new 연산자에 의해 리턴 된 객체의 번지 저장

### 생성자 오버로딩
- 자바는 다양한 방법으로 객체를 생성할 수 있도록 제공

### 다른 생성자 호출(this)
- 자신의 다른 생성자를 호출하는 코드로 반드시 생성자의 첫줄에서만 허용(주소를 의미)
- this가 없으면 아무것도 아님


## 패키지
- 클래스를 체계적으로 관리하기 위한 것
- 파일 시스템의 폴더
- 클래스를 유일하게 만들어주는 식별자 역할을 함
- 클래스의 이름이 동일하더라고 패키지가 다르면 완전히 다른 클래스 

## Car3 예제
```java
package ch06;
 // 현업에서는 Car2처럼 한다.
public class Car {
	private String name;
	private String color;
	private int size;
	
	public Car() { // default는 반드시 만들어 준다.
		
	}

	public Car(String name) {
		this(name, "red", 1000);
	}

	
	
	public Car(String name, String color) {
		this(name, color, 1000);// 같은 클래스 안에 동일한 컨스트럭터흫 호출
	}
	
	

	public Car(String name, String color, int size) { // 위의 this와는 다름
		this.name = name;
		this.color = color;
		this.size = size;
	}
	
	
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	
	public void go() {
		System.out.println(this.name+": GO!!!!!!!");
	}
	
	public void go(int a) { // 오버로딩이 안되면 go를 변경 시켜야 함.
		System.out.println(this.name+": GO!!!!!!!"+a);
	}
	
	public void go(double b) { // 오버로딩이 안되면 go를 변경 시켜야 함.
		System.out.println(this.name+": GO!!!!!!!" + b);
	}
	
	// 컴파일 언어는 변수명을 똑같이 쓰면 안된다.
	
	@Override
	public String toString() {
		return "Car [name=" + name + ", color=" + color + ", size=" + size + "]";
	}
	
	
}


```

```java
package ch06;

public class CarApp {

	public static void main(String[] args) {
		Car c = new Car("k1");
		c.setSize(2000);
		System.out.println(c);
		String color = c.getColor();
		System.out.println(c.getColor());
		
		c.go(7);
	}

}

```

## Static
- 고정된 의미를 가짐

```java
package ch06;

public class Car {
	private String name;
	private String color;
	private int size;
	private int serial;
	
	// class의 유일한 변수
	static int cnt = 1000;
	
	public Car() { // default는 반드시 만들어 준다.
		
	}

	public Car(String name) {
		this(name, "red", 1000);
	}

	
	public Car(String name, String color) {
		this(name, color, 1000);// 같은 클래스 안에 동일한 컨스트럭터흫 호출
	}
	
	
	public Car(String name, String color, int size) { // 위의 this와는 다름
		this.name = name;
		this.color = color;
		this.size = size;
		this.serial = cnt;
		cnt++;
	}
	
	
	public Car(String name, String color, int size, int serial) {
		this.name = name;
		this.color = color;
		this.size = size;
		this.serial = serial;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public int getSerial() {
		return serial;
	}

	public void go() {
		System.out.println(this.name+": GO!!!!!!!");
	}
	
	public void go(int a) { // 오버로딩이 안되면 go를 변경 시켜야 함.
		System.out.println(this.name+": GO!!!!!!!"+a);
	}
	
	public void go(double b) { // 오버로딩이 안되면 go를 변경 시켜야 함.
		System.out.println(this.name+": GO!!!!!!!" + b);
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", color=" + color + ", size=" + size + ", serial=" + serial + "]";
	}
	
	// 컴파일 언어는 변수명을 똑같이 쓰면 안된다.
	

	
}

```

```java
package ch06;

public class CarApp {

	public static void main(String[] args) {
		Car c = new Car("k1");
		c.setSize(2000);
		System.out.println(c);
		String color = c.getColor();
		System.out.println(c.getColor());
		
		c.go(7);
	}

}

```

```java
package ch06;

public class CarApp2 {

	public static void main(String[] args) {
		Car c1 = new Car("K1");
		Car c2 = new Car("K2");
		Car c3 = new Car("K3");
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c2);
	}

}

```


## 계산기 예제
```java

```
```java

```
```java

```

#### singleton 제외, final 제외