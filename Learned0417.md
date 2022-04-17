```java
package bank;

public class Account {
	
	private String name; // 성함
	private String accNo; // 계좌번호 
	private double balance; // 잔고
	

	
	public Account() {
		
	}
	
	public Account(String name, String accNo, double balance) {
		this.name = name;
		this.accNo = accNo;
		this.balance = balance;
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getAccNo() {
		return accNo;
	}

	public double getBalance() {
		return balance;
	}
	
	
	@Override
	public String toString() {
		return "Account [name=" + name + ", accNo=" + accNo + ", balance=" + balance + "]";
	}

	public void deposit(double money) {
		if(money < 1) {
			System.out.println("입금 금액 오류");
			return;
		}
		this.balance += money;
		System.out.printf("Balance: %.2f \n", balance);
	}
	
	public void withdraw(double money) {
		if(money < 1) {
			System.out.println("입금 금액 오류");
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

   ```