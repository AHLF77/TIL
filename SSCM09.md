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

```java
package ch06;

public class App2 {

	public static void main(String[] args) {
		Car c1 = new Car();
		System.out.println(c1.toString());
		
		Car c2 = new Car("K1","red",1000,50,0);
		System.out.println(c2.toString());

	}

}

```

## 은행에 대한 클래스 예제
```java
package ch06;

public class BankApp {

	public static void main(String[] args) {
	
		Account acc1 = new Account("1111-2222");
		System.out.println(acc1.toString());

		acc1.deposit(10000);
		System.out.println(acc1.toString());
		
		acc1.withdraw(5000);
		System.out.println(acc1);
		
		acc1.setBalance(500000000.0);
		
		String accNo = acc1.getAccNo();
		double balance = acc1.getBalance();
		System.out.printf("계좌번호: %s %.2f \n",accNo, balance);
		
	}

}

```
```java
package ch06;

public class Account {
	String accNo;
	double balance;
	
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

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	
	public String toString() {
		return "Account [accNo" + accNo + ", balance=" + balance + "]";
	}
	
	public void deposit(double money) {
		if(money < 1) {
			System.out.println("입금 금액 오류");
			return;
		}
		this.balance += money;
	}
	
	// 출금 금액이 1보다 작으면 안된다.
	// 출금 금액이 잔액 보다 많으면 안된다.
	public void withdraw(double money) {
		if(money < 1) {
			System.out.println("출금 금액 오류");
			return;
		}
		if(money > this.balance) {
			System.out.println("잔액 초과");
			return;
		}
		this.balance -= money;
	}
	

}

```

## 자동차2에 대한 클래스 예제
```java
package car;

public class Car {
	private String name;
	private double fsize;
	private double cfsize;
	private String status;
	private double fe;
	
	public Car() {
		
	}

	public Car(String name, double fsize, double fe) {
		this.name = name;
		this.fsize = fsize;
		this.fe = fe;
		this.status = "STOP";
	}

	public Car(String name, double fsize, double cfsize, String status, double fe) {
		this.name = name;
		this.fsize = fsize;
		this.cfsize = cfsize;
		this.status = status;
		this.fe = fe;
	}

	public double getCfsize() {
		return cfsize;
	}

	public void setCfsize(double cfsize) {
		this.cfsize = cfsize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getFe() {
		return fe;
	}

	public void setFe(double fe) {
		this.fe = fe;
	}

	public String getName() {
		return name;
	}

	public double getFsize() {
		return fsize;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", fsize=" + fsize + ", cfsize=" + cfsize + ", status=" + status + ", fe=" + fe
				+ ", getCfsize()=" + getCfsize() + ", getStatus()=" + getStatus() + ", getFe()=" + getFe()
				+ ", getName()=" + getName() + ", getFsize()=" + getFsize() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	public void go(double km) {
		if(this.cfsize == 0) {
			System.out.println("기름이 없습니다.");
			return;
		}
		System.out.println(this.name+" : GO");
		this.status = "GO";
		this.cfsize -= (km/this.fe);
	}
	
	public void stop() {
		System.out.println(this.name+" : STOP");
		this.status = "STOP";
	}
	

}

```