# 0415 배운 내용 요약
## Class
- 객체들의 공통적으로 갖는 속성들을 모아서 놓은 것을 의미
- OOAD(UML) -> OOP
- Java(OOP) Design Pattern

## OOP 
- 정의: Object-Oriented Programming의 약자로, 객체 지향적인 프로그래밍임.
- ex: 사람 개인 한명 한명을 모두 객체라 할 수 있고, 책 한권을 객체라 할 수 있습니다.

### 첫번째 특징: Encapsulation(캡슐화 == 정보은닉)
- 관련이 있는 변수와 함수를 하나의 클래스로 묶고 외부에서 접근하지 못하도록 은닉하는 것이 핵심(정보처리기사에도 나옴)

### 접근제어자
- default
- private
- public
- protected

### getter 와 setter
- 사용이유: 클래스의 필드에 집적 접근하는 것을 막기 위함
- ex: 은행 업무를 볼 때 해당 인원의 정보만 볼 수 있도록 하는 것
- setter: 외부에서 메소드를 통해 접근하면, 메소는 매개 값을 검증해서 유효한 값만 데이터로 저장 가능
- getter: 데이터 복사본을 던져주고 있어, 데이터를 손상하지 않고, 생성자에게 데이터를 넣어 줄 수 있음

## 자동차1에 대한 클래스 예제(값 고정)
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
	//private : 해당 변수는 다른 클래스에서 수정을 못하도록 막음.
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
	
	
	// get은 타 클래스에서 정보만 볼 수 있는 것.
	public String getAccNo() {
		return accNo;
	}

	// set은 타 클래스에서 설정할 수 있는 것.
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	
	public String toString() { // 모양을 보기위해서 하는것 업무에서는 필요하지 않는 코드이다.
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

```java
package bank;

import java.util.Random;
import java.util.Scanner;

public class AccountApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Start...");
		Account acc = null;
		String accNo ="";
		Random r = new Random();	
		
		while(true) {
			System.out.println("Input cmd(c,d,w,s,q) ...");
			String cmd = sc.next();
			try {	
				if(cmd.equals("q")) {
					System.out.println("Bye");
					break;
				}else if (cmd.equals("c")) {
					System.out.println("계좌 생성합니다 ...");
					System.out.println("계좌 생성중 ...");
					// 000000-00-000000
					int accNo1 =r.nextInt(1000000);
					int accNo2 =r.nextInt(100);
					int accNo3 =r.nextInt(1000000);
					accNo = new String(accNo1 + "-" + accNo2 + "-"+ accNo3);
					acc = new Account(accNo);
					System.out.println("계좌가 생성되었습니다.");				
					System.out.println("당신의 계좌번호: " + accNo);			
				}else if (cmd.equals("d")) {
					System.out.println("입금 금액을 입럭하세요.");
					double dm = Double.parseDouble(sc.next());
					acc.deposit(dm);
					System.out.println("입금 금액 :" + dm ); 
					System.out.println("현재 잔액 :" + acc.getBalance()); 
				}else if (cmd.equals("w")) {
					System.out.println("출금 금액을 입럭하세요.");
					double wm = Double.parseDouble(sc.next());
					acc.withdraw(wm);
					System.out.println("출금 금액 :" + wm ); 
					System.out.println("현재 잔액 :" + acc.getBalance());
				}else if  (cmd.equals("s")) {
					System.out.println("내 계좌번호:" + accNo );
					System.out.println("현재 잔액 :" + acc.getBalance());			
				}
			
				} catch (Exception e) {
					System.out.println("숫자만 입력 가능합니다. 다시 선택하세요.");
					continue;
			}
			
		}
		
		sc.close();
		System.out.println("End ...");

	}

}
```

## 자동차2에 대한 클래스 예제(값 입력 받기)
```java
package car;

public class Car {
	// Attribute(속성, 필드)
	private String name;
	private double fsize;
	private double cfsize;
	private String status; //Go, Stop
	private double fe;
	
	public Car() { //Constructor(생성자)
		
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

```java
package car;

import java.util.Scanner;

public class CarApp {

	public static void main(String[] args) {
		
		System.out.println("Start...");
		Scanner sc = new Scanner(System.in);
		Car car = null;
		
		while(true) {
			System.out.println("Input cmd(c,a,s,g,t,q): ");
			String cmd = sc.next();
			
			if(cmd.equals("q")) {
				
				System.out.println("Bye");
				break;
				
			}else if(cmd.equals("a")) {
				
				System.out.println("Input Fuels Size: ");
				double fs = Double.parseDouble(sc.next());	
				car.setCfsize(fs);
				
			}else if(cmd.equals("c")) {
				
				System.out.println("Input name: ");
				String name = sc.next();
				System.out.println("Input fsize: ");
				double fsize = Double.parseDouble(sc.next());
				System.out.println("Input fe: ");
				double fe = Double.parseDouble(sc.next());
				
				car = new Car(name, fsize, fe);
				System.out.println(car);
				
			}else if(cmd.equals("g")) {
				
				System.out.println("Input Km: ");
				double km = Double.parseDouble(sc.next());
				car.go(km);;
				
			}else if(cmd.equals("t")) {
				
				car.stop();
				
			}else if(cmd.equals("s")) {
				
				System.out.println(car);
				
			}else {
				System.out.println("wrong text");
			}
		}
		sc.close();
		System.out.println("End...");

	}

}

```