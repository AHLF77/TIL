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
package bank;

import java.util.Scanner;

import bank.Account;
import car.Car;

public class BankApp {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		Account bank = null;
		
		System.out.println("*****Start*****");
		
		while(true) {
			System.out.println("Input cmd(c. create | d. deposit | w. withdraw | l. look | q. quit): ");
			String cmd = sc.next();
			
			if(cmd.equals("q")) { // 종료
				
				System.out.println("~~~ Good Bye ~~~");
				break;
				
			}else if(cmd.equals("c")) { // 계좌 개설
				
				System.out.println("*****create mode*****");
				System.out.println("insert name: ");
				String name = sc.next();
				System.out.println("insert accNo: ");
				String accNo = sc.next();
				
				bank = new Account(name, accNo, 0);
				System.out.println(bank);
				System.out.println("complete");
				
				
			}else if(cmd.equals("d")) { // 돈 입금
				
				System.out.println("*****deposit mode*****");
				
				System.out.println(bank.toString());
				
				if (bank == null) {
					
					System.out.println("account empty");
				
				} else {
					
					System.out.println("Amount: ");
					System.out.println(bank.toString());
					
					double deposit = 0.0;
					
					while (true) {
						try {
							 deposit = Double.parseDouble(sc.next());
						} catch (Exception e) {
							System.out.println("Number X");
							continue;
						}
						break;
					}
					bank.deposit(deposit);
					
			
				System.out.println("complete");
				
				}
				}else if(cmd.equals("w")) { // 돈 출금
				
				System.out.println("*****balnace mode*****");
				
				System.out.println(bank.toString());
				
				if (bank == null) {
					
					System.out.println("account empty");
				
				} else {
					
					System.out.println("Amount: ");
					
					double withdraw = 0.0;
					
						while (true) {
							try {
								withdraw = Double.parseDouble(sc.next());
							} catch (Exception e) {
								System.out.println("Number X");
								continue;
							}
							break;
						}
						bank.withdraw(withdraw);
						
					}
				}else if(cmd.equals("l")) { // 계좌 정보 출력
					if (bank == null) {
						
						System.out.println("account empty");
					
					} else {
				System.out.println(bank);
					}
			}
			else {
				System.out.println("wrong text");
			}
		}
		sc.close();
		System.out.println("***** END *****");

	}

}



   ```