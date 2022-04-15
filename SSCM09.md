# 0415 강의
## Class
- OOAD(UML) -> OOP
- Java(OOP) Design Pattern

## OOP 특징 - Java
1. Encapsulation
2. Inheritance
3. Polymorpism
4. Abstraction


## 자동차에 대한 클래스 예제
```java
package ch06;

public class Car { // 객체를 생성하기 위한 툴이다.

	// 속성
	String name;
	String color;
	int size;
	int fsize;
	int cfsize;

	// Constructor
	public Car() {
	
	}
	
	public Car(String name, String color, int size, int fsize, int cfsize) {
		this.name = name;
		this.color = color;
		this.size = size;
		this.fsize = fsize;
		this.cfsize = cfsize;
	}


	//오퍼레이션
	public void go() {
	System.out.println("Go !");
	}

	public void stop() {
	System.out.println("STOP !");
	}
	public void addFuels(int f) {
	cfsize += f;	
	}
	@Override
	public String toString() {
		return "Car [name=" + name + ", color=" + color + ", size=" + size + ", fsize=" + fsize + ", cfsize=" + cfsize
				+ "]";
	}
	
	
}

```

```java
package ch06;

public class App {

	public static void main(String[] args) {
//		int a =100;
//		String s = new String("ABC");
//		int ar[] = {1,2,3,4};
		
		Car c1 = new Car();
		String result1= c1.toString();
		System.out.println(c1.toString());
		
		Car c2 = new Car();
		System.out.println(c2.toString());
	}

}

```