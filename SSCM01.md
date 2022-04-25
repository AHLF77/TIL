## 0404 강의 내용

### 1. 프로그래밍 언어
   - 사람과 컴퓨터 대화에 도움
   - 프로그램 언어 구조 파악
   - 고급 언어/ 저급 언어로 구분

### 2. What is Java
  - 초기 이름은 오크 
   · 인터넷과 웹의 엄청난 발전에 힘입어 퍼지게 됨
   · 웹 브라우저 Netscape에서 실행
  - 1991년 그린 프로젝트
  - 선 마이크로시스템즈의 제임스 고슬링에 의해 시작
  - 1996년 1월에 공식적으로 자바 발표
  - 현재 선 마이크로시스템즈가 오라클과 인수 합병됨

### 3. 자바의 특징
 - 객체지향 언어
  · 객체(부품)을 먼저 제작하여, 탑 쌓아 올리듯 객체를 조합하여 프로그램을 완성해 나감
 - 클래스, 상속, 캡슐화, 다형성 등의 개념이 잘 적용되어 있음
 - 운영체제의 독립적
  · JVM이 구축된 컴퓨터에서는 어디에서든지 실행이 가능
 - 개발 환경은 무료(단. jdk 버전 8 버전에 한해서)
  · 개발된 형태를 취함

### 4. eclipse
 - 통합개발환경
 - 자바 개발을 편리하게 해주는 툴
 - 안드로이드 어플
 - 자유 소프트웨어

```java
public class Ch01 {

	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		int result = a * b;
		System.out.println(a);
		System.out.println(b);
		System.out.println("result: "+result);
	}

}
   ```

   ```java
   public class Variable1 {

	public static void main(String[] args) {
		int a = 2000000000;
		a = 20;
		int maxSpeed = 200;
		System.out.println(a);
	}

}
   ```

   ```java
  public class Variable2 {

	// class variable
	int a = 20;
	
	public static void main(String[] args) { // 이 문장 안에서만 변수로 취급이 된다.
		// local variable
		int a;
		a = 10;
		
		int result;
		result = a + 100;
		System.out.println(result);
	}

}

   ```
