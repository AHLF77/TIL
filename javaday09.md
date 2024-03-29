## 0419 배운 내용 요약
### 복습
- 절차지향 프로그램 : C, Script
- 조건문을 통해 흐름제어 : if문, switch문
- 위의 것으로만 작성 시, 복잡해짐
- OOAD(UML로 설계) -> OOP
- class: 현실 세계의 사물을 프로그램으로 표현
- object: 클래스의 구조를 파악하고 설계
- 메모리 구조(heap, stack)를 잘 파악하기
- reference 타입임
- OOP의 특징: 캡슐화(외부에서 접근이 어려움, getter와 setter를 통해 통로 제공), 상속, 추상화 

### 상속
- 상속의 효과: 클래스를 재사용하여 개발 속도가 빨라짐, 반복된 코드 줄임, 객체 다형성의 구현, 유지보수 편리
- 부모 클래의 private 접근을 갖는 필드와 메소드 제외
- extends를 사용
- 메소드 재정의: 부모 클래스의 상속 메소드 수정해 자식 클래스에서 재정의하는 것
- 접근 제한을 더 강하게 오버라이딩 불가(public을 default private으로 수정 불가)
- final 메소드: 자식이 재정의할 수 없는 메소드
- 프로모션(자동 타입 변환): 프로그램 실행 도중에 자동 타입 변환이 일어나는 것

### 다형성
- 같은 타입이지만 실행결과가 다양한 객체 대입 가능한 성질

```java
package graphic;

public class App {

	public static void main(String[] args) {
		
		DrawPanel dw = new DrawPanel();
		Shape s1 = new Rectangle(5,5,10,10);
		dw.draw(s1);
		Shape s2 = new Circle(4,4,10);
		dw.draw(s2);
	}

}

```
```java
package graphic;

public class Triangle extends Shape {

	private int width;
	private int height;
	
	
	public Triangle() {

	}

	public Triangle(int x, int y) {
		super(x, y);
		
	}

	public Triangle(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Triangle [width=" + width + ", height=" + height + ", x=" + x + ", y=" + y + "]";
	}

	@Override
	public double getArea() {
		return (width * height) /2;
	}

	@Override
	public double getCircum() {
		return width + height + Math.hypot(width, height);
	}

}

```

```java
package graphic;

public class Circle extends Shape {

	private int radius;
	
	public Circle() {
		
	}

	public Circle(int x, int y) {
		super(x, y);
		
	}

	public Circle(int x, int y, int radius) {
		super(x, y);
		this.radius = radius;
	}
	
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void setColor(String color) {
		System.out.println(color);
	}
	
	@Override
	public String toString() {
		return "Circle [radius=" + radius + ", x=" + x + ", y=" + y + "]";
	}

	@Override
	public double getArea() {
		return radius * radius * Math.PI;
	}

	@Override
	public double getCircum() {
		return 2 * radius * Math.PI;
	}

}

```

```java
package graphic;

public class Rectangle extends Shape {
	
	private int width;
	private int height;
	
	public Rectangle() {
	}

	public Rectangle(int x, int y) {
		super(x, y);
	}

	
	
	public Rectangle(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Rectangle [width=" + width + ", height=" + height + ", x=" + x + ", y=" + y + "]";
	}

	@Override
	public double getArea() {
		return width * height;
	}

	@Override
	public double getCircum() {
		return (width + height)*2;
	}

}

```
```java
package graphic;

public abstract class Shape {
	
	protected int x;
	protected int y;
	
	public Shape() {
	
	}

	public Shape(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Shape [x=" + x + ", y=" + y + "]";
	}

	//Abstract Method
	public abstract double getArea();
	public abstract double getCircum();
	
}

```
```java
package graphic;

public class DrawPanel {
	
	public void draw(Shape shape) {
		
		System.out.println(shape.toString());
		if(shape instanceof Circle) {
			Circle c = (Circle)shape;
			c.setColor("blue");
		}
	}

}

```
```java
package graphic;

public class TestApp {

	public static void main(String[] args) {
	//	Shape s = new Shape();
	
		Shape s[] = new Shape[3];
		s[0] = new Rectangle(1,3,10,20);
		s[1] = new Triangle(2,2,20,20);
		s[2] = new Circle(3,3,15);
		
		for (Shape shape : s) {
			System.out.println(shape.getArea());
			System.out.println(shape.getCircum());
			if(shape instanceof Circle) {
				Circle circle = (Circle)shape;
				circle.setColor("red");
			}
		}
		
	}

}

```
