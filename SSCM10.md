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
package ch06;

public class Cal1 {

	private int a;
	private int b;
	
	public Cal1(){
		
	}

	public Cal1(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public int sum() {
		return (this.a + this.b);
	}
	
	public int div() {
		return (this.a / this.b);
	}
	
	public int avg() {
		return (this.a + this.b) / 2;
	}
	
	
	
}

```
```java
package ch06;

public class Cal2 {
	
	public static int sum(int a, int b) {
		return a + b;
	}
	
	public static int div(int a, int b) {
		return a / b;
	}
	
	public static double sum(double a, double b) { // 오버로딩(arguments가 달라서)
		return a + b;
	}
	
	public static double div(double a, double b) { // 오버로딩(arguments가 달라서)
		return a / b;
	}
	
}

```
```java
package ch06;

public class CalApp {

	public static void main(String[] args) {
		
		Cal1 c1 = new Cal1(10,2);
		int c1avg = c1.avg();
		System.out.println(c1avg);
		
		int c2sum = Cal2.sum(10, 2);
		System.out.println(c2sum);
		
		double c2sumd = Cal2.sum(10.1,2.1);
		System.out.println(c2sumd);
		
		
	}

}

```

## 접근 제한자
- default: 같은 패키지에 소속된 클래스에서만 사용할 수 있음
- private: 외부 클래스에서 자유롭게 사용 불가하며, 개인 적인, 그러니까 해당 클래스에서만 사용 가능.
- protected: 같은 패키지 또는 자식 클래스에서 사용할 수 있음
- public: 외부 클래스에서 자유롭게 사용이 가능


## 은행3 예제
```java
package bank;

import java.util.Arrays;

public class Customer {
	
	private String name; // 고객명
	private Account[] accs; 
	
	public Customer() {
		
	}

	public Customer(String name) {
		this.name = name;
	}

	public Customer(String name, Account[] accs) {
		this.name = name;
		this.accs = accs;
	}

	public String getName() {
		return name;
	}

	public Account[] getAccs() {
		return accs;
	}
	
	public void setAccs(Account[] accs) {
		this.accs = accs;
	}
	
	//고객계좌 잔액의 합을 구하는 기능
	public double getBalanceSum() {
		double sum = 0.0;
		for (int i = 0; i < accs.length; i++) {
			sum += accs[i].getBalance();
		}
		return sum;
	}
	
	public void deposit(String accNo, double money) {
	
		for (int i = 0; i < accs.length; i++) {
			if(accs[i].getAccNo().equals(accNo)){
				accs[i].deposit(money);;
				}
			}
		
	}
	
	//"1111"인 계좌를 리턴 한다.
	public Account getAc(String accNo) {
		Account acc = null;
		for (int i = 0; i < accs.length; i++) {
			if(accs[i].getAccNo().equals(accNo)){
				acc = accs[i];
				}
			}
		return acc;
	} // 일일이 하나 하나 계좌번호를 대조해 가며 해야함
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", accs=" + Arrays.toString(accs) + "]";
	}
	
}

```
```java
package bank;

public class Account {
	private String accNo;
	private double balance;
	
	public Account() {
	}

	
	public Account(String accNo) {
		this.accNo = accNo;
	}


	public Account(String accNo, double balance) {
		this.accNo = accNo;
		this.balance = balance;
	}

	
	
	
	public String getAccNo() {
		return accNo;
	}


	public double getBalance() {
		return balance;
	}


	@Override
	public String toString() {
		return "Account [accNo=" + accNo + ", balance=" + balance + "]";
	}
	
	public void deposit(double money) {
		if(money < 1) {
			System.out.println("입금 금액 오류");
			return;
		}
		this.balance += money;
	}
	
	
	public void withdraw(double money) {
		if(money < 1) {
			System.out.println("입금 금액 오류");
			return;
		}
		if(this.balance < money) {
			System.out.println("잔액 부족 오류");
			return;
		}
		this.balance -= money;
	}
	
}

```
```java
package bank;

public class BankApp {

	public static void main(String[] args) {
		Customer c = new Customer("Kim");
		System.out.println(c);

		Account [] accs = new Account[3];
		accs[0] = new Account("1111",10000);
		accs[1] = new Account("2222",20000);
		accs[2] = new Account("3333",30000);
		
		c.setAccs(accs);
		System.out.println(c);
		
		Account myacc = c.getAc("2222");
		System.out.println(myacc); // 2222 계좌를 끄집어 내는 것
		
		c.deposit("2222", 50000);
		
		System.out.println(c);
		
		double sum = c.getBalanceSum();
		System.out.println(sum);
		
	}

}

```

## 상속
- 부모가 자식에게 물려주는 행위를 말함.
- 프로그램에서 자식이 부모를 선택함.
- 상위 클래스의 필드나 정보를 하위 클래스에서 정상적으로 사용을 가능하게 해줌

### 회사 상속 예제
```java
package company;

public class Employee {
	private String id;
	private String name;
	protected double salary;
	
	public Employee() {
		
	}

	public Employee(String id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double annsalary() {	
	return this.salary * 12;
	}
	
	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}
}

```
```java
package company;

public class Manager extends Employee{
	private double bonus;

	public Manager() {
	}

	public Manager(String id, String name, double salary, double bonus) {
		super(id, name, salary);
		this.bonus = bonus;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	// 재정의 - overriding(overloading: 하나의 클래스 안에 동일한 이름이 같이 있어도 문제가 없음)	
	@Override
	public double annsalary() {
		double sum = 0.0;
	  //sum = getSalary() * 12 + this.bonus;
		sum = super.annsalary()+ this.bonus;
		return sum;
	}
	
	public double getBonusTex() {
		double tex = 0.0;
		tex = this.bonus - (this.bonus/ 1.1);
		return tex;
	}
	
	@Override
	public String toString() {
		return "Manager [bonus=" + bonus + ", toString()=" + super.toString() + "]";
	}

}

```
```java
package company;

public class CompanyApp {

	public static void main(String[] args) {
		
		Employee e = new Employee("100","james", 300);
		System.out.println(e);
		System.out.println(e.annsalary());
		
		Manager m = new Manager("101", "lee", 300, 500);
		System.out.println(m);
		System.out.println(m.annsalary());
		System.out.println(m.getBonusTex());
		
	}

}

```
```java
package company;

public class CompanyApp2 {

	public static void main(String[] args) {
		
		// heterogeneous collection
		Employee e[] = new Employee[4];
		e[0] = new Employee("100", "kim", 1000);
		e[1] = new Employee("101", "lee", 1000);
		e[2] = new Manager("102", "hong", 1000, 500);
		e[3] = new Manager("103", "jin", 1000, 800);
		
		for (int i = 0; i < e.length; i++) {
			System.out.println(e[i]);
		}
		for (int i = 0; i < e.length; i++) {
			System.out.println(e[i].annsalary());
		}
		for (int i = 0; i < e.length; i++) { // equals는 주소값으로 비교함
			if(e[i] instanceof Manager) {
				Manager m = (Manager)e[i];
				System.out.println(m.getBonusTex());
			}
		}
	}

}

```
- Manager is a Employee

#### singleton, final, annotation 제외(※ 나중에 따로 독학해야 함.)