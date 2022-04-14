# 0414 강의
## 1. 1번 과제
```java
package tw;

import java.util.Arrays;
import java.util.Random;

public class tp01 {

	public static void main(String[] args) {
		/*
		1. int 사이즈 5 배열을 만든다.
		2. 그 배열에 랜덤하게 값을 넣는다.
		3. 배열의 값들 중 최대값을 찾는다.
		4. 최대값을 출력한다. 
		*/
		
		int ar [] = new int[5];
		Random rn = new Random();
		int max = 0;
		int min = 99;
		
		for (int i = 0; i < ar.length; i++) {
			ar[i] = rn.nextInt(9)+1;
			if(ar[i]>max) {
				max = ar[i];
			}
			if(ar[i]<min) {
				min = ar[i];
			}
				
		}
		System.out.println("랜덤값 : "+ Arrays.toString(ar));
		System.out.println("최대값 : "+ max);
		System.out.println("최소값 : "+ min);
		

	}

}

```

## 2. 2번 과제
```java
package tw;

public abstract class tw01 {

	public static void main(String[] args) {
		/*
		 정수 1~100의 수 중 5의 배수 합을 구하시오.
		 */
		
		int num = 5; // 선언
		double sum = 0.0;
		
		for (int i = 1; i <= 100; i++) {
			if(i%num==0) {
				sum += i;
			}
		}
		System.out.println("5의 배수의 합: " + sum);
		
		
	}

}

```

## 3. 3번 과제
```java
package tw;

import java.util.Arrays;

public class Tw021 {

	public static void main(String[] args) {
		double ar[]= new double[9];
		double arr[] = new double[9];
		double sum = 0.0;
		int cnt = 0;
		for (int i = 2; i < 10; i++) {
			if(i%2 == 1) {
				continue;
			}
			System.out.println(i+"단 시작");
			for (int j = 1; j < 10; j++) {
				/*if((i*j) == 99) {
					break outter;
				}*/
				System.out.printf("%d x %d = %d \n ",i,j,(i*j));
				sum += i*j;
				cnt++;
				
			}
			ar[i-1] = sum;
			arr[i-1] = sum/cnt;
			System.out.println(i+"단의 합과 평균 : "+sum+", "+sum/cnt);
			
			System.out.println("~~~~~~~~~~~~~");
			sum = 0;
			cnt = 0;
		}
		System.out.println("각 단의 합 : "+Arrays.toString(ar));
		System.out.println("각 단의 평균 : "+Arrays.toString(arr));
	}

}

```

## 4. 4번 과제
```java
package tw;

import java.util.Random;
import java.util.Scanner;

public class Ws01 {

	public static void main(String[] args) {
		/*
		 * Number Guess Game 을 만드시오
		ㅇ"Game Start" 메시지를 출력하며 게임을 시작한다.
		ㅇ1에서 10까지의 정수 중에서 숫자 1개를 골라 정답을 정해놓는다.
		ㅇ숫자를 하나 입력받는다. 단, 입력횟수(시도횟수)도 5회로 정해져있다.(While)
		ㅇ입력받은 숫자가 정답보다 크면 down을 출력하고 다시 메시지를 출력하고 숫자를 입력받는다.
		ㅇ입력받은 숫자가 정답보다 작으면 up을 출력하고 다시 메시지를 출력하고 숫자를 입력받는다.
		ㅇ입력받은 숫자와 정답과 일치하면 "축하합니다! 정답입니다!"를 출력한다.
		ㅇ5회 모두 실패시, 정답을 출력하고 메시지 "Game End"를 출력한다. 
		ㅇ "종료" 를 입력하면 바로 프로그램을 종료한다.

*/
		Random rn = new Random();
		Scanner sc = new Scanner(System.in);
		System.out.println("Game Start...");
		int rd = rn.nextInt(10)+1;
		int try_count = 5;
		while(true) {
			
			System.out.println("1~10숫자를 입력하세요.");
			String num = sc.next();
			if(num.contentEquals("종료")) {
				sc.close();
				System.out.println("종료");
				break;
			}
			
			int n = Integer.parseInt(num);
			
			if (n <0 || n>10) {
				System.out.println("입력오류");
				continue;
			}
			if(n>rd) {
				System.out.println("down");
				try_count--;
				
			}else if(n<rd){
				System.out.println("up");
				try_count--;	
				
			}else if(n == rd) {
				sc.close();
				System.out.println("정답");
				return;
				
			}
			if(try_count == 0) {
				System.out.println("정답은"+rd+"끝");
				rd = rn.nextInt(10)+1;
				try_count = 5;
				continue;
			}
		}
		
	}

	
	}
```

## 5. 5번 과제
```java
package tw;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Ws02 {

	public static void main(String[] args) {
		/*
		Lotto Game 을 만드시오.
  		1에서 20까지의 정수 중에서 중복 없이 랜덤으로 숫자 6개를 뽑는다.(배열사용)
		입력받은 숫자들과 뽑힌 숫자를 비교한다. (이중for문)(배열사용)
		1등은 숫자 6개를 모두 맞춘 경우, 메시지 "1등입니다. 상금은 10만원입니다"출력함
		2등은 숫자 5개를 맞춘 경우, 메시지 "2등입니다. 상금은 7만원입니다"출력함
		3등은 숫자 4개를 맞춘 경우, 메시지 "3등입니다. 상금은 3만원입니다"출력함
		그 외는 메시지 " 꽝입니다" 출력함
		 */
				
		int ar[] = new int[6];
		int user[] = new int[6];
		int cnt = 0;
		
		Random r = new Random();
		Scanner sc = new Scanner(System.in);
		
	//	로또 번호 산정(1~20)
		for (int i = 0; i < ar.length; i++) {
			int a = r.nextInt(20)+1;
			ar[i] = a;
			for (int j = 0; j < i; j++) {
				if(ar[i] == ar[j]) {
					i--;
				}
				
			}
		}
		
		
		int nm = 0;
		for (int i = 0; i < ar.length; i++) {
			
			System.out.println("숫자를 입력하세요.");
			String num = sc.next();
			
			try {
				nm = Integer.parseInt(num);
				user[i] = nm;
				if(nm < 1 || nm >20) {
					System.out.println("입력 오류.");
					i--;
					continue;
				}	
				
			} catch (Exception e) {
				System.out.println("문자를 입력 하셨습니다.");
				i--;
				continue;
			}
			
			for (int j = 0; j < i; j++) {
				if(user[i] == user[j]) {
					System.out.println("중복 숫자.");
					i--;
					continue;
					}			
	
		}
			
		
	}
		
		
		for (int i = 0; i < user.length; i++) {
			if(ar[i] == user[i]) {
				cnt++;
			}
		}
		
		
		System.out.println("맞힌 숫자 갯수 : "+cnt);
		System.out.println(Arrays.toString(ar));
		System.out.println(Arrays.toString(user));
		
		if(cnt == 6) {
			System.out.println("1등에 당첨되셨습니다. 상금 10만원입니다.");
		}else if(cnt == 5) {
			System.out.println("2등에 당첨되셨습니다. 상금 7만원입니다.");
		}else if(cnt == 4) {
			System.out.println("3등에 당첨되셨습니다. 상금 5만원입니다.");
		}else {
			System.out.println("꽝");
		}
		
		
		sc.close();
	}

	}


```