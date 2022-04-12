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


## 참조 타입
- 객체형: 문자열, 객체

## Substring, IndexOf
```java
package ch05;

public class P148 {

	public static void main(String[] args) {
		String str = "abcde";
		
		int c = str.indexOf("b");
		String c1 = str.substring(c);
		System.out.println(c1);
		
		String str2 = str.substring(0,3); // 0번 째서부터 2번째까지 출력 필요
		System.out.println(str2);
		
		//substring(시작위치,끝위치)
		
		String mail = "gjhan777@gmail.com";
		// id와 domain을 출력 하시오.
		
		int idx = mail.indexOf("@");
		String id = mail.substring(0,idx);
		String domain = mail.substring(idx); 
		
		System.out.printf("%s %s \n", id, domain);	
		
		String ss = " abc ";
		System.out.println(ss.trim()); // trim은 공백을 지워준다.
	}

}

```

## 배열
- 참조타입
- for문을 돌려서 값을 추출
- int 선언하면은 String 사용 못함
- Arrays.toString(ar) : // 안에 내용을 볼 때만 사용
- 배열의 고정된 값은 시험 에서만 나온다.
```java
package ch05;

import java.util.Arrays;

public class P149 {

	public static void main(String[] args) {
		int a = 10;
		
		int ar [] = new int[4]; // ar 뒤에 [] 사용해도 문제는 없음., new int[4] 선언 
		ar[0] = 10;
		ar[1] = 11;
		ar[2] = 12;
		ar[3] = 13;
		
		System.out.println(a);
		System.out.println(ar);
		System.out.println(Arrays.toString(ar)); // 안에 내용을 볼 때만 사용
		
		for (int i = 0; i < ar.length; i++) {
			System.out.println(ar[i]);
		}

	}

}

```
