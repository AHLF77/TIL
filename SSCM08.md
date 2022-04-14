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

## 2. 2번 과제
```java

```

## 2. 2번 과제
```java

```

## 2. 2번 과제
```java

```