# Java

### 1. switch 문
   - if 문과 같은 제어문
   - 특정 변수를 다양한 상황에서 비교할 수 있게 해줌
   - if문에 비해 가독성이 좋음
   - 컴파일러의 최적화를 쉽게 할 수 있어서 속도도 if문에 비해 빠름
   - 문법 
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

### 2. for 문
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

# Git 특강 1일차 정리

## 1. CLI

#### 경로

- 파일이나 폴더의 고유한 위치를 나타내는 문자열 (주소)

- 운영체제에 따라서 다르게 표현된다.

  windows - `C:\\Users\\Document`

  macOS - `/Users/Document`

#### 루트 디렉토리

- 모든 파일/폴더를 담고 있는 폴더
- windows의 경우 보통 C 드라이브를 의미함

#### 상대 경로

- 현재 워킹 디렉토리를 기준으로 계산된 경로
- 현재 워킹 디렉토리가 `C:\\Users\\User\\바탕 화면` 라면 `C:\\Users\\USER\\바탕 화면\\code`의 상대 경로는 `code` 가 된다.
- 간결해서 좋지만 만약 현재 워킹 디렉토리가 변한다면 상대 경로는 변경된다. (다시 계산해야함)
- 현재 워킹 디렉토리가`C:\\Users\\` 로 변한다면, `C:\\Users\\USER\\바탕 화면\\code` 상대경로는 `Users\\code` 가 된다.



### Command

#### **cd**

- Change Directory
- 현재 워킹 디렉토리를 변경할 때 사용한다.
- `cd ..` 하면 부모 디렉토리로 이동 할 수 있다.

#### **ls**

- 현재 워킹디렉토리의 폴더/파일 확인하기
- `ls -a -l` 을 활용하면 숨김 폴더와 자세한 정보를 알 수 있다.

#### **touch**

- `touch a.txt` 형식으로 파일을 만들 수 있다.

#### **mkdir**

- `mkdir 디렉토리이름` 형식으로 폴더를 만들 수 있다.
- 만약 폴더 이름(happy hacking)에 공백이 있다면? `mkdir happy hacking` 이라고 명령어를 사용하면 `happy` 와 `hacking` 2개의 폴더가 생긴다. 이를 방지하기 위해서는 따옴표를 사용해서 폴더 이름을 묶어줘야 한다. `mkdir 'happy hacking'`



## 2. Markdown

#### 마크다운의 장점

1. 문법이 쉽다.
2. 관리가 쉽다.
3. 지원 가능한 플랫폼과 프로그램이 다양하다.

#### 마크다운의 단점

1. 표준이 없어 사용자마다 문법이 상이할 수 있다.
2. 모든 HTML 마크업을 대신하지 못한다.



#### 2-1) 제목

# 제목 1

## 제목 2

### 제목 3

#### 제목 4

##### 제목 5

###### 제목 6



---



#### 2-2) 목록

**순서가 없는 목록**

- 목록 1
- 목록 2
- 과일
  - 수박
  - 참외

`-, *, +` ->  순서가 없는 목록



**순서가 있는 목록**

1. 목록 1
2. 목록 2
   1. 목록 2-1
   2. 목록 2-2



---



#### 2-3) 강조 (스타일링)

1. 기울임(이탤릭체) : *글자*, _글자_
2. 굵게(볼드체) : **글자**, __글자__
3. 취소선 : ~~글자~~



---



#### 2-4) 코드

인라인 코드 (= 한줄) ->  백틱(backtick) `

 파이썬에서는 `print("Hello World!")` 라고 쓸수 있습니다.



블록 코드 (= 여러줄)

```python
for i in range(10):
	print(i)
```



---

 

#### 2-5) 수평선

`-, *, _` 3번 연속 작성

---

***

___



---



#### 2-6) 표(table)

| 동물   | 다리 개수 | 종     |
| ------ | --------- | ------ |
| 사자   | 4개       | 포유류 |
| 원숭이 | 2개       | 포유류 |
| 앵무새 | 2개       | 조류   |
|        |           |        |

