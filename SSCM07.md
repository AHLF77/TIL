# 0412 강의

## 2차원 배열


## 2중 for문 구구단
```java
package ch04;

public class P124 {

	public static void main(String[] args) { // 알고리즘 테스트해서 나옴.
		outter: // 밖에 상위 for문에서 멈추게 할 수 있음
			// 위에는 outter라고 적었지만, abc라고 적어도 무관
		for (int i = 2; i < 10; i++) {
			if(i%2 == 1) {
				continue;
			}
			System.out.println(i+"단 시작");
			for (int j = 1; j < 10; j++) {
				if((i*j) == 99) {
					break outter;
				}
				System.out.printf("%d x %d = %d \t",i,j,(i*j));
				
			}
			System.out.println("~~~~~~~~~~~~~");
		}

	}

}

```

## 2중 for문을 활용한 표 배열
```java
package ch04;

public class P125 {

	public static void main(String[] args) {
		 
		for (int i = 0; i < 5; i++) {
			
			for (int j = 0; j < 5; j++) {
			
				System.out.printf("[%d, %d]\t",i,j);
				
			}
			System.out.println("");
		}

	}

}

```

```java
package ch04;

public class P126 {

	public static void main(String[] args) {
		 int i = 0;
		
		while(i<5) {
			
			for (int j = 0; j < 5; j++) {
				System.out.printf("[%d, %d]\t",i,j);	
			}
			
			i++;
			System.out.println("");
		}
		

	}

}

```

## 은행계좌 이체 문제
```java
package ch04;

import java.util.Scanner;

public class P135 {

	public static void main(String[] args) {
		System.out.println("Start...");
		Scanner sc = new Scanner(System.in);
		
		double balance = 0.0;
		
		while(true) {
			System.out.println("Input cmd(d,w,s,i,q): ");
			String cmd = sc.next();
			
			if(cmd.equals("q")) {
				System.out.println("Bye");
				break;
				
			}else if(cmd.equals("d")) {
				System.out.println("deposit.. ");
				System.out.println("Amount: ");
				String snum = sc.next();
				System.out.println(snum);
				
				int amount = Integer.parseInt(snum);
				if(amount < 0) {
					System.out.println("음수입니다. 다시 진행하세요.");
					continue;
				}
					balance += amount;
					System.out.printf("Balance: %.2f \n", balance);
				
			}else if(cmd.equals("w")) {
				System.out.println("Withdraw...");
				System.out.println("Amount: ");
				String snum = sc.next();
				System.out.println(snum);
				
				int amount = Integer.parseInt(snum);
				if(amount < 0) {
					System.out.println("음수입니다. 다시 진행하세요.");
					continue;
				}else if(amount > balance) {
					System.out.println("잔액 부족입니다. 다시 진행하세요.");
					continue;
				}
					balance -= amount;
					System.out.printf("Balance: %.2f \n", balance);
				
			}else if(cmd.equals("s")) {
				System.out.println("Select Balance...");
				System.out.printf("Balance: %.2f \n", balance);	
			
			}else if(cmd.equals("i")) {
				// 이자율을 입력하면 이자가 얼마 인지를 출력 하시오.
				// 원금과 이자 이자율을 같이 출력하시오.
				System.out.println("이자율 입력:");
				String sinterest = sc.next();
				double interestRate = Double.parseDouble(sinterest);
				double interest = balance * (interestRate / 100);	
				System.out.printf("%.2f %.2f %.2f %% \n", balance, interest, interestRate);
				
			}else {
				System.out.println("wrong text");
			}
		}
		sc.close();
		System.out.println("End...");

	}

}


```