# 0411 강의

## 1. switch 문
- if 문과 같은 제어문
- 특정 변수를 다양한 상황에서 비교할 수 있게 해줌
- if문에 비해 가독성이 좋음
- 컴파일러의 최적화를 쉽게 할 수 있어서 속도도 if문에 비해 빠름
-  문법 
```java
   switch(변수){
    case 값1 : 
        실행문; 
        break;
    case 값2 : 
        실행문; 
        break;  
    default :
        실행문;    
}
```

## 2. for문
- 반복 횟수를 정확히 알고 있을 때 사용
- while문 보다 가독성이 좋음
- if문에 비해 가독성이 좋음
- 문법
```java
  for(초기화식; 조건식; 증감식){
    실행문; 
}
```
  
### 3. while문
- 문장이 true면 실행, false이면 종료
- while문 빠져 나가기 break
- while문 조건으로 돌아가기 continue
- 문법
```java
 while (조건문) {
    <수행할 문장1>;
    <수행할 문장2>;
    ...
}
```
 
## P117
```java
package day04;

public class P117 {

	public static void main(String[] args) {
		// switch문
		String a = "10";
		//만약 double(실수)로 변경 시 사용 불가 하지만, String은 사용 가능
		// 따라서 int와 String으로 가능.
		switch (a) {
		case "10": // 조건문은 들어갈 수가 없음
			System.out.println("큰 수");
			break; // 더 이상 switch문을 실행하지 않는다고 이야기 하는 것.
		case "5": 
			System.out.println("중간 수");
			break;
		case "1": 
			System.out.println("작은 수");
			break;
		default:
			System.out.println("입력오류");
			break;
		} // switch 문 종료
		System.out.println("End.....");

	}

}

```

## P118
```java
package day04;

import java.util.Random;

public class P118 {

	public static void main(String[] args) {
		Random r = new Random();
		int n = r.nextInt(5)+1; // 1 ~ 5
	//	int n1 = r.nextInt(5); // 0 ~ 4
		System.out.println(n);
	//	System.out.println(n1);
		
		// 스위치문을 사용해야 하는 상황
		switch (n) {
		case 1:
			System.out.println("냉장고");
			break;
		case 2:
			System.out.println("세탁기");
			break;
		case 3:
			System.out.println("아이폰");
			break;
		case 4:
			System.out.println("애플워치");
			break;
		case 5:
			System.out.println("에어팟");
			break;
		default:
			break;
		}
		
	}

}

```

## P119
```java
package day04;

public class P119 {

	public static void main(String[] args) {
		System.out.println("start.....");
		
		// 1부터 10까지 반복하여
		for (int i = 1; i <= 10; i++) { // 항상 시작과 끝이 있다. 그리고 증가가 있다.
			System.out.println("For....." + i);
		}
		
		System.out.println("end.....");
		
		int  s = 1;
		while(s <= 10) {
			s++;
			System.out.println("While....."+s);
			
		} // end while
		// 증감연산자 때문에
	}

}
```

## P120
```java
package day04;

public class P120 {

	public static void main(String[] args) {
	 // 1~10까지의 합을 구하시오.	
		int sum = 0;
		int count = 0;
		for (int i = 1; i <= 10; i++) { 
			sum += i;
			count ++;
		}
		System.out.println("답1: " + sum);
		System.out.println(count);
		System.out.println("평균: " + sum/count);
		
		System.out.println("-------------------------------------");

		int s = 1;
		int sum2 = 0;
		while (s<=10) {
			sum2 += s;
			s++;
		}
		System.out.println("답2: " + sum2);
		System.out.println("" + s);
		System.out.println("평균: " + sum2/(s-1));
		
		System.out.println("-------------------------------------");	
		
	}

}

```

## P121
```java
package day04;

import java.util.Random;
import java.util.Scanner;

public class P121 {

	public static void main(String[] args) {
		// Random을 통해 1 ~ 99까지의 난수를 발생 시킴.
		
		Random r = new Random(); 
		int n = r.nextInt(98)+2;// 랜던 값
		System.out.println("랜덤값: " + n);
		
	// 1부터 난수까지의 합과 평균을 출력한다.
		double sum = 0.0; // 합
		int count = 0;
		
		for (int i = 1; i <= n; i++) { 
			sum += i;
			count++;
		}
		System.out.println("합계: " + sum);
		System.out.println("평균: " + sum/count);

	}

}

```

## P122
```java
package day04;

import java.util.Scanner;

public class P122 {

	public static void main(String[] args) {
		// Scanner를 이용하여 2 ~ 99까지의 정수를 입력 받음.
		Scanner sc = new Scanner(System.in);
		System.out.println("2~99까지 사이의 값 입력: "); // 숫자 입력 받기
		String snum = sc.next();
		System.out.println(snum);
		
		// 범위를 벗어나면 Bye 프로그램 종료
		// 1부터 입력 받은 수까지의 합과 평균을 출력하시오. while
		int num = 0;
		
		try {
			num = Integer.parseInt(snum);
			// 2 ~ 99 사이의 숫자가 맞는지 확인
			if(num < 2 || num > 99) {
				System.out.println("안녕히 계세요. 여러분 ~ season 1");
				sc.close();
				return;
			}
		} catch (Exception e) {
			System.out.println("안녕히 계세요. 여러분 ~ season 2");
			sc.close();
			return;
		}
		
		double sum = 0.0;
		int s = 1;
		
		while (s<=num) {
			sum += s;
			s++;
		 }
		    System.out.println("합계: " + sum);
		    System.out.println("평균: " + sum/(s-1));
		    sc.close();
		      
	}

}

```

## P127
```java
package day04;

import java.util.Scanner;

public class P127 {

	public static void main(String[] args) {
		
		System.out.println("Start...");
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Input cmd(w1,w2,q): ");
			String cmd = sc.next();
			if(cmd.equals("q")) {
				System.out.println("Bye");
				break;
			}else if(cmd.equals("w1")) {
				System.out.println("w1 Input Number: ");
				String snum = sc.next();
				System.out.println(snum);
			}else if(cmd.equals("w2")) {
				System.out.println("w2 Input Number: ");
				String snum = sc.next();
				System.out.println(snum);
			}else {
				System.out.println("wrong text");
			}
		}
		sc.close();
		System.out.println("End...");

	}

}
```

## P128
```java
package day04;

import java.util.Random;
import java.util.Scanner;

public class P128 {

	public static void main(String[] args) {
		
		System.out.println("Start...");
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Input cmd(w1,w2,q): ");
			String cmd = sc.next();
			
			if(cmd.equals("q")) {
				System.out.println("Bye");
				break;
			}else if(cmd.equals("w1")) {
				
				/*1. 두개의 숫자를 입력받는다.
				  조건: 두 수는 숫자 이어야 하며 1 ~ 99 까지의 숫자 이어야 한다.
				  첫 번째 숫자는 두 번째 숫자보다 작아야 한다.
				  2. 첫 번째 숫자와 두번 째 숫자의 합과 평균을 구하시오.
				 */
				
				System.out.println("첫 번째 값 입력(1~99): "); 
				String num1 = sc.next();
				System.out.println("두 번째 값 입력(1~99): "); 
				String num2 = sc.next();
				
				int nm1,nm2 = 0;
				
				try {
					nm1 = Integer.parseInt(num1);
					nm2 = Integer.parseInt(num2);
					
					if(nm1 > nm2) {
						System.out.println("첫 번째 숫자는 두 번째 숫자보다 큽니다.");
						continue;
					}if(nm1 < 1 || nm1 > 99) {
						System.out.println("안녕히 계세요. 여러분 ~");
						continue;
					}if(nm2 < 1 || nm2 > 99) {
						System.out.println("안녕히 계세요. 여러분 ~");
						continue;
					}
				} catch (Exception e) {
					System.out.println("안녕히 계세요. 여러분 ~");
					continue;
					
				}
				
				double sum = 0.0;
				int count = 0;
				for (int i = 1; i <= nm2; i++) {
					sum += i;
					count += 1;
				}
				
				    System.out.println("합계: " + sum);
				    System.out.println("평균: " + sum/count);
				    
				    
			}else if(cmd.equals("w2")) {
				/*
				 1. 10~99까지의 숫자를 입력 받는다.
		         조건: 숫자여야 하며 범위안에 숫자여야 한다.
		         2. 2부터 입력 받은 숫자 까지에서 난수를 발생 시킨다.
		 		 3. 1부터 난수까지의 합을 구한다.
		 		 단, 짝수의 값의 합만 구한다.
				 */
				
				System.out.println("10~99까지 사이의 값 입력: "); 
				String snum = sc.next();
				
				int num1 = 0;
				
				try {
					num1 = Integer.parseInt(snum);
					if(num1 < 10 || num1 > 99) {
						System.out.println("안녕히 계세요. 여러분");
						continue;
					}
				} catch (Exception e) {
					System.out.println("안녕히 계세요. 여러분");
					continue;
				}
				
					
				Random r = new Random(); 
				int n = r.nextInt(num1-1) + 2;
				System.out.println("난수: " + n);
				
				double sum = 0.0;
				for(int i=1; i<=n; i++) {
					if(i%2 == 1) {
						continue;
					}
					sum += i;
				}
				System.out.println("합계: " + sum);
				System.out.println("평균: " + sum/2);
			}else {
				System.out.println("wrong text");
			}
		}
		sc.close();

	}

}

```

## P130
```java
package day04;

public class P130 {

	public static void main(String[] args) {
		// 10부터 1까지 출력 하시오.
		
/*		for (int i = 10; i >= 1; i--) {
			System.out.println("For Loop1: "+i);
		}*/
		for (int i = 1; i <= 10; i++) {
			System.out.println("For Loop: "+i);
			if (i == 7) {
				break;
			}
		}// end for
		
		System.out.println("-------------------------------------");
		int s = 1;
		while(s <=10) {
			System.out.println("While Loop: "+s);
			if (s == 7) {
				break;
			}
			s++;
		}

	
	}

}

```

## P131
```java
package day04;

public class P131 {

	public static void main(String[] args) {
		// i가 짝수일때만 출력하시오.
		for (int i = 1; i <= 10; i++) {
			if (i%2 == 1) {
				continue;
			}
			
			System.out.println("For Loop: "+i);
			
		}// end for
		
		System.out.println("-------------------------------------");
		
		int s = 1; // s가 계속 1로 남음
		while(s <=10) {
			if (s%2 == 0) {
				System.out.println("While Loop: "+s);
			}
			s++;
		}

	
	}

}

```

## 느낀점
> 오늘은 평소와 다르게 빠르게 흘러가는 것 같았다. 갈길이 아직 멀었지만, 내 꿈을 위해 꾸준히 나아가자. 낙심하지 말자.