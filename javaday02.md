# 0405 배운 내용 요약

### UniCode 예제1
```java
package ch02;

public class P39 {

	public static void main(String[] args) {
		byte b1 = 127; // byte는 -128 ~ 127 까지만 처리할 수 있다.
		System.out.println(b1);
		
//		char c1 = 'A';  //character는 한글자만 가능하다. 작은따옴표 ' ' 안에 있어야만 한다.
		char c1 = 65; 
//		char c1 = '\u0041'; // unicode 한글 '가'
		System.out.println(c1);
		
//		char c2 = '\uAC00';
		char c2 = 44032;
		System.out.println(c2);
	}

}

```

### UniCode 예제2
```java
package ch02;

public class P44 {

	public static void main(String[] args) {
	 int v1 = 10; // 10진수 10
	 int v2 = 012;// 8진수 10
	 int v3 = 0xA;// 16진수 10
	 System.out.println(v1);
	 System.out.println(v2);
	 System.out.println(v3);
	 
	 byte b1 = 100;
	 byte b2 = 100;
	 byte b3 = (byte)(b1 + b2);
	 // byte로 강제로 잘라냄
	 // 빨간 줄이 뜨는 이유는 산술연산자가 들어갈 경우, 안에서는 int 형으로 변환이 된다 
	 System.out.println(b1+b2);
	 
	 // 은행은 long을 주로 사용한다.(but, 소수점 이하 표현을 못한다.)
	 // 그래서 실수형인 float을 사용합니다.
	 // 0이 8자리 이상시 L을 넣어 사용한다.
	 long vi1 = 1500000000L;
	 long vi2 = 1500000000L;
	 long result = vi1 + vi2;
	 System.out.println(result);
	}

}

```

### Double 최대값 예제
```java
package ch02;

public class P46 {

	public static void main(String[] args) {
		double d1 = 10000000000000000000000000000000000000000000.0;
		double d2 = 10;
		int a = (int)100.1;
		System.out.println(d1);

	}

}

```

### Double값 출력
```java
package ch02;

public class P47 {

	public static void main(String[] args) {
		int a = 10;
		double b = 0.0;
		
		double result = a/b;
		System.out.println(result);
		
		System.out.printf("진짜 double 값: %1.50f\n",b);
		// 0.0이 끝이 아니라 뒤에 0이 계속 붙어 있습니다. 하지만 실제 나눌때는 0.000000~이 됨으로 무한대(Infinity)로 출력이 된다.
		
		double b2 = 14.23543;
		System.out.printf("%5.2f",b2);
		
		
	}

}

```

### JDK 예제
```java
package ch02;

public class P48 {

	public static void main(String[] args) {
		boolean b1 = false; //false
		System.out.println(b1); 
		
		if(b1) {
			System.out.println("First");
		}else {
			System.out.println("Second");
		}
		
		System.out.println(Byte.MAX_VALUE); //jdk에서 제공을 해주고 있음.
		System.out.println(Byte.MIN_VALUE);
	}

}

```

### Float 변환 예제
```java
package ch02;

public class P60 {

	public static void main(String[] args) {
		byte b1 =100;
		float f1 = 2.5F;
		double d2 = 2.5;
		
		byte bb = (byte)(b1 + b1);
		System.out.println(bb);
		
		int result = 5 + b1;
		
		float ff = 500 + f1; // 실수로 변환이 되어 연산이 된다.
		double dd = 50000000000000000000000000000.0 + d2;
		
		int result2 = 100 + (int)d2; // 정수와 실수를 같이 계산 시 실수가 계산이 된다.
									// double로 바꿀 경우 전체에 잘못하면 영향을 받을 수가 있다.
		System.out.println(result2);
		
		//실제로는 타이핑을 하여 계산을 해보아야 한다.

	}

}

```

### 단항 연산자, 이항 연산자, 비교 연산, 논리 연산
```java
package ch03;

public class P64 {

	public static void main(String[] args) {
		/*int a =10;
		int b = 20;
		
		// 단항 연산자
		int aa = -a;
		int bb = b++;
		System.out.println(bb);
		System.out.println(b);
		*/
		int c = 10;
		int d = 10;
		int sum = c++ + ++d;
		System.out.println(sum);
		System.out.println(c);
		System.out.println(d);
				
		/*
		// 이항 연산자
		int result = a % b;
		System.out.println(result);
		
		int data = 100;
		// data = data + 1;
		data += 1; // 위보다 훨씬 빠름(대입만 하면 끝나기 때문에)
		
		
		//비교연산
		int a1 =10;
		int a2 = 20;
		
		if(a1 <= a2) {
			System.out.println("OK");
		}else {
			System.out.println("FAIL");
		}
		
		// 논리연산
		int b1 = 30;
		int b2 = 20;
		int d1 = 10;
		int d2 = 20;
		
		// || or
		// && and
		// (&&,&)두개는 두개를 먼저 비교하지만, 하나의 경우는 앞에 있는 것이 fail이면 fail로 뜬다.
		// && 하면은 성능이 느려지는 단점이 있다.
		
		if( b1 > b2 && d1 > d2++) {
			System.out.println("OK");
		}else {
			System.out.println("FAIL");
		}
		
		//!는 NOT이다.
		// not이 아니면 
		if(!(b1 > b2)) {
			System.out.println("b2이 크다.");
		}else {
			System.out.println("b1이 크다.");
		}
		
		*/
	}

}

```

### Math.max(최대값)
```java
package ch03;

public class P100 {

	public static void main(String[] args) {
		int a = 100;
		int b = 200;
		int result = Math.max(a, b);
		System.out.println(result);

	}

}

```

### IF, ELSE 문(삼항연산자)
```java
package ch03;

public class P101 {

	public static void main(String[] args) {
		int sum = 80;
		char result = 0;
		
		// 90이상이면 'A'
		// 80이상이면 'B'
		// 70이상이면 'C'
		// 70미만이면 'D'
	
	/*	if(sum >= 90) {
			result = 'A';
		}else if(sum >= 80) {
			result = 'B';
		}else {
			result = 'C';
		}*/
		
		// 삼항 연산자로 구현 하시오.
			result = (sum >= 90) ? 'A' : (sum >= 80) ? 'B' : 'D';
			System.out.println(result);
	}

}

```

### IF문 예제
```java
package ch03;

public class P84 {

	public static void main(String[] args) {
		int a = 10;
		int b = 0;
		int result = 0;
		if(b!=0) {
		result = a/b;
		}
// double result = (double)a/(double)b;
		System.out.println(result);
// 괄호 안에 변수 선언을 할 경우 밖에서는 인식을 못합니다.
		
	}

}

```

### Infinity 연산
- 무한대라는 의미
- 정수 0은 정확한 0이지만 double이나 float의 경우는 0.0이어서 정확한 0이 아님
- 그래서 double, float의 경우 Infinity가 나옴

```java
package ch03;

public class P85 {

	public static void main(String[] args) {
		int a = 100;
		double b = 0.0;
		double result = 0.0;
		
		result = a/b;
		if(Double.isInfinite(result)) {
		System.out.println("분모가 0.0 입니다.");
		}else {
		System.out.println(result);
		}
	}

}

```

### 입력값의 NaN 검사
- 잘못된 입력으로 인해 계산을 할 수 없음을 나타내는 기호
- Not a Number
- 반드시 검사를 거쳐야만 한다.

```java
package ch03;

public class P86 {

	public static void main(String[] args) {
		// 객체형: 문자열, 객체
		String str = "NaN";
		char c ='A';
		double a = Double.valueOf(str); // String을 정수로 바꾸어주는 함수
		double result = 0.0;
		
		if(Double.isNaN(a)) {
			System.out.println("입력 오류");
		}else {
			result = a * 100;
		}
		
		int b = (int)c ; // int에는 정수형값이 들어갈 수 있다. 결과값 : 65
		// int a (int)str; // int type 에는 "" type이 들어올수 없어 에러가난다.

		System.out.println(b);

		System.out.println(result);
		
		
	}

}

```

### 직각삼각형의 빗변 길이 예제
```java
package ch03;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ws0405 {
	public static final DecimalFormat df = new DecimalFormat("0.00");
	
	public static void main(String[] args) {
        int a = 5;
		int b = 7;
		double result = Math.hypot(a, b);
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		System.out.println(df.format(result));
	}

}
```