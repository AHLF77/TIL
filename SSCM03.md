# 0406

## Primitive type 
- 기본형 타입
- 총 8가지
 1. 논리형: boolean(1byte)
 2. 정수형: byte(1 byte), short(2 byte), int(4 byte), long(8 byte)
 3. 실수형: float(4 byte),  double(8 byte)
 4. 문자형: char(2 byte)
 - 실제 값을 저장하는 공간으로 스택(Stack) 메모리에 저장
 - Not Null

## Reference type
- 참조형 타입
- NUll 존재
- 런타임 에러가 발생
- 값이 저장되어 있는곳의 주소값을 저장하는 공간으로 힙(heap) 메모리에 저장
- 배열(array), 열거(Enumeration), 클래스(Class), 인터페이스(Interface)

### Primitive type & Reference type 예제
```java
package ch03;

public class P90 {

	public static void main(String[] args) {
		// primitive type
		int a = 10;
		int b = 10;
		int c = a + b;
		// Reference type
		String s ="ABC"; //s는 위치 정보가 들어가 있습니다.
		
		System.out.println(c);
		System.out.println(s);
		
	}

}

```
### 계산기 예제
```java
package ch04;

import java.util.Scanner;

public class lastca0406 {

	public static void main(String[] args) {
		System.out.println("Start");
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Input 1 number: "); // 첫 번째 수
		String num1 = sc.next();
		System.out.println("Input 2 Number: "); // 두 번째 수
		String num2 = sc.next();
		System.out.println("연산자: "); // 연산자
		String op = sc.next();
		
		int nm1 = Integer.parseInt(num1);
		int nm2 = Integer.parseInt(num2);
		int result1 = 0;
		
		if(op.equals("+")) {
			result1 = nm1 + nm2;
			System.out.printf("결과 : %s\n", result1 );
		}else if(op.equals("-")) {
			result1 = nm1 - nm2;
			System.out.printf("결과 : %d\n", result1 );
		}else if(op.equals("/")){
			result1 = nm1 / nm2;
			System.out.printf("결과 : %d\n", result1 );
		}else if(op.equals("x")){
			result1 = nm1 * nm2;
			System.out.printf("결과 : %d\n", result1 );
		}else {
			System.out.printf("해당 연산은 존재하지 않음");
		}
		
		String result2 = "";
		result2 = (result1 >= 10) ? "큰 수" : (result1 >= 5) ? "중간수" : "작은수";
		System.out.println(result2);
		
		sc.close();
		System.out.println("END");
	}

}

```

### 숫자 입력을 받고, 삼항 연산자로 변경하는 예제
```java
package ch04;

import java.util.Scanner;

public class P108 {
	
	public static void main(String[]args) {
		System.out.println("START.......");
		
		Scanner sc = new Scanner(System.in);		
		System.out.println("Input number: ");
		String num = sc.next();
		
		// 입력한 숫자가 0이면 프로그램 종료하고, 그렇지 않으면 출력
		// 1보다 크고 10이하인 숫자가 아니면 종료
		int nm = Integer.parseInt(num);
		
		if(nm > 10 || nm < 1) {
			System.out.println("BYE");
			sc.close();
			return;
		}else {
			System.out.printf("입력한 값은 %s \n", nm);
		}
		
		// 삼항 연산자로 바꾸시오.
		String level = "";
		level = (nm >= 7) ? "대" : (nm >= 3) ? "중" : "소";
		System.out.println(level);
		
		
		sc.close(); // 파이프 클로즈하기
		System.out.println("END.......");
		
	}

}

```
### OP 검증 예제
```java
package ch04;

import java.util.Scanner;

public class P1099 {
// 
	public static void main(String[] args) {
		System.out.println("Start");
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Input Number 1..?");
		String n1 = sc.next();
		
		System.out.println("Input Number 2..?");
		String n2 = sc.next();
		
		System.out.println("Input Op..?");
		String op = sc.next();
				
		
		// OP 검증
		
		if (op.length() > 1 || 
				(!op.equals("+") && 
				!op.equals("-") && 
				!op.equals("/") &&
				!op.equals("x") )
				) {
			System.out.println("Bye");
			sc.close();
			return;
		}
		
		
		// 숫자만 입력 받는다.
		
		double num1 = 0.0;
		double num2 = 0.0;
		try {
			num1 = Double.parseDouble(n1);
			num2 = Double.parseDouble(n2);
		}catch(Exception e) {
			System.out.println("숫자를 입력 하세요. Bye...");
			sc.close();
			return;
		}
		
		
		//System.out.println(n1.length());
		System.out.printf("입력한 내용은 %s %s %s  입니다. \n", n1, op, n2);
		
		// 연산처리
		double result = 0.0;
		
		if(op.equals("+")) {
			result = num1 + num2;
		}else if(op.equals("-")) {
			result = num1 - num2;
		}else if(op.equals("/")) {
			result = num1 / num2;
		}else if(op.equals("x")) {
			result = num1 * num2;
		}else {
			System.out.println("아니야");
			sc.close();
			return;
		}
		System.out.printf("결과: %.2f\n", result);
		
		String str = "";
		str = (result < 0) ? "음수": "양수";
		System.out.println(str);
		
		String str2 = "";
		if(result >= 10) {
			str2 = "큰수";
		}else if(result >= 5) {
			str2 = "중간수";
		}else{
			str2 = "작은수";
		}
		System.out.println(str2);
		
		
		sc.close();
		System.out.println("End");
	}

}


```
### 영문자를 소문자로 전환하는 예제
```java
package ch05;

public class P145 {

	public static void main(String[] args) {
		String s1 = "ABC";
		String s2 = "ABC";
		String s3 = s2.toLowerCase(); // s2 문자를 소문자로 재탄생 시킴.
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		// 같은 번지를 참조한다.
		// 쓰레기를 찾아서 없애버린다.
		
		char c = s3.charAt(0);
		System.out.println(c);
	}

}

```

### 주소를 비교하는 예제
```java
package ch05;

public class P146 {

	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		if(a>b) {
			System.out.println("OK");
		}else {
			System.out.println("NO");
		}
		
		String s1 = "abc";
		String s2 = "abc";
		
		if(s1 == s2) {// 주소를 비교한다.
			System.out.println("Equal Reference ..");
		}
		if(s1.equals(s2)) { // 값을 비교한다.
			System.out.println("Equal String ..");
		}
	}

}

```

### 주소값 예제
```java
package ch05;

public class P147 {

	public static void main(String[] args) {
		String s1 = "ABC";
		String s2 = new String("ABC"); // heap에 새로운 주소가 만들어진다.
		String s3 = "ABC";
		String s4 = new String("ABC"); // heap에 새로운 주소가 만들어진다.
		// s1과 s3는 같은 주소 이지만, s1과 s2는 다르고, s2와 s3가 다름
		// 하지만 값은 다 같음.
		// (heap) string pool에는 new라는 키워드로 새로 만들어진 String은 안들어가고, new라는 키워드가 없는 거만 string pool로 들어감
	}

}

```

