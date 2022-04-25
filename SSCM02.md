
### UniCode 예제1
```java
package ch02;

public class P39 {

	public static void main(String[] args) {
		byte b1 = 127;
		System.out.println(b1);
		
//		char c1 = 'A';
		char c1 = 65;
//		char c1 = '\u0041'; // unicode
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
	 int v1 = 10; // 10진수
	 int v2 = 012;// 8진수
	 int v3 = 0xA;// 16진수
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