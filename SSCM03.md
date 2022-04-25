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