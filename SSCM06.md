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
 
## Switch문
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