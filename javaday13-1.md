## 0425-1 배운 내용 요약

### Multi thread
- 여러 개의 크롬 프로세스가 생성된 것이다.
- 멀티 태스킹: 독립적으로 프로그램들을 실행하고 여러 가지 작업 처리
- 멀티 스레드: 한 개의 프로그램을 실행하고 내부적으로 여러 가지 작업 처리
- 스레드를 짤 일이 없음.
- 업데이트를 생각하면 됨
- 여기까지가 시험 범위.

```java
package P576;

public class MainThread {

	public static void main(String[] args) {
		System.out.println("Start...............");
		MyThread1 t1= new MyThread1();
		t1.start();
		

		// 인터페이스 안에서 생성한 객체는 thread 객체 안에 넣어서 사용
		MyThread2 t2 = new MyThread2();
		Thread tt2 = new Thread(t2);
		tt2.start();
		
		System.out.println("End..................");
		
		// End까지 한 번 찍고 메인 쓰레드가 돌면서, 다른 쓰레드 들이 돈다.
	}

}

```

```java
package P576;

public class MyThread1 extends Thread{

	@Override
	public void run() {
		int i = 0;
		while(i <= 100){
			i++;
			System.out.println("MyThread1: "+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
}
}

```
```java
package P576;

public class MyThread2 implements Runnable{

	@Override
	public void run() {
		int i = 0;
		while(i <= 100){
			i++;
			System.out.println("MyThread2: "+i);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}

```

```java
package P576;

public class MainThread2 { // 가장 많이 쓰이는 방법이다.

	public static void main(String[] args) {
		System.out.println("Start.................");
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while(i <= 100){
					i++;
					System.out.println("MyThread1: "+i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while(i <= 100){
					i++;
					System.out.println("MyThread2: "+i);
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}				
			}
		});
		
		t1.start();
		t2.start();
		
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				int i = 0;
				while(i <= 100){
					i++;
					System.out.println("MyThread3: "+i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}		
				
			}
		};
		
		Thread t3 = new Thread(r1);
		t3.start();
		
		System.out.println("End.................");
	}

}

```

## 데이터 베이스
- 데이터의 집합
- 데이터베이스를 관리 · 운영하는 역할

### 데이터베이스의 특징
- 데이터의 무결성
- 데이터의 독립성
- 보안
- 데이터 중복의 최소화
- 응용 프로그램 제작 및 수정이 쉬워짐
- 데이터의 안전성 향상

### Mysql
- Oracle사에서 제작한 DBMS 소프트웨어로 오픈 소스로 제공
- 무료(단. 상업용 사용 시 별도 라이선스 취득 필요)
- 버전: Mysql Community 8.0

### DBA
- Database Administration 해석
- 개발단계 역할
  1. 기획안 분석 후 데이터베이스 관계모델 설계
  2. 테이블 생성 및 프로시저 작성
  3. 인덱스, 정규화, 비정규화 등의 튜닝작업
- 운영단계 역할
  1. 데이터베이스 관리
  2. 백업
- 데이터베이스 관리
- 개발 하는 단계에서는 전체 허가, 실무에서는 각각마다 권한 다르게 부여
- Delete, Update시 항상 주의 할 것.
- Update와 Insert를 혼돈하지 말자
※ Update는 올라와 있는 데이터를 수정하고, Insert는 값을 처음부터 생성하는 것.


### Eclipse JAVA MYSQL 연동(CRUD)
#### 데이터 삽입 구문
```java
package cust;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCust {

	public static void main(String[] args) {
		// JDBC(Java Database Connectivity) Program
		
		// 변수 선언
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO CUST VALUES (?,?,?)";
		
		// MySQL JDBC Driver Loading
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Loading.....");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// MySQL Connect
		String url = "jdbc:mysql://개인IP:3306/shopdb?serverTimezone=Asia/Seoul";
		// root로 접속을 보안상이유로 막아놓은다.
		String mid = "admin1";
		String mpwd = "111111";
		try {
			con = DriverManager.getConnection(url,mid,mpwd);
			System.out.println("MySQL JDBC Driver Connected.....");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// SQL을 이용한 요청
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "id12");
			ps.setString(2, "pwd11");
			ps.setString(3, "오말숙");
			
			// 요청 결과를 확인
			int result = ps.executeUpdate();
			System.out.println(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//Close
			// 예외 상황이 발생하여도 close를 해주어야함.
			// MySQL Close -> !!! close를 안해주면 MYSQL에 쓰레기가 남아 DB가 터질 수 있다.!!!
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
```
#### 데이터 업데이트 구문
```java
package cust;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCust {

	public static void main(String[] args) {
		// JDBC(Java Database Connectivity) Program
		// java projcet에 mysql connector apply하기.
		
		// 변수 선언
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE CUST SET pwd=?, name =? WHERE id =?";
		
		// MySQL JDBC Driver Loading
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Loading.....");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// MySQL Connect
		String url = "jdbc:mysql://개인IP:3306/shopdb?serverTimezone=Asia/Seoul";
		String mid = "admin1";
		String mpwd = "111111";
		try {
			con = DriverManager.getConnection(url,mid,mpwd);
			System.out.println("MySQL JDBC Driver Connected.....");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// SQL을 이용한 요청
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "1111");
			ps.setString(2, "오말숙");
			ps.setString(3, "id01");
			
			// 요청 결과를 확인
			int result = ps.executeUpdate();
			System.out.println(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//Close
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}

```

#### 데이터 삭제 구문
```java
package cust;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteCust {

	public static void main(String[] args) {
		// JDBC(Java Database Connectivity) Program
		
		// 변수 선언
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM CUST WHERE id=?"; // id로 삭제
	//	String sql = "DELETE FROM CUST WHERE pwd=?"; //pwd로 삭제
	//	String sql = "DELETE FROM CUST WHERE name=?"; // 이름으로 삭제
		
		// MySQL JDBC Driver Loading
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Loading.....");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// MySQL Connect
		String url = "jdbc:mysql://개인IP:3306/shopdb?serverTimezone=Asia/Seoul";
		String mid = "admin1";
		String mpwd = "111111";
		try {
			con = DriverManager.getConnection(url,mid,mpwd);
			System.out.println("MySQL JDBC Driver Connected.....");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// SQL을 이용한 요청
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "id12");
        //  ps.setString(1, "pwd10");
		//  ps.setString(1, "id12");
			
			// 요청 결과를 확인
			int result = ps.executeUpdate();
			System.out.println(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//Close
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}

```
- Finally의 필요성
 1. 항상 실행되는 것이 아니라, finally와 연결되어 있는 try 블록으로 일단 진입을 하면, 무조건 실행되는 영역
 2. 예외 발생 또는 미발생 일지라도 무조건 수행을 한다.
 3. Database에서는 매우 중요: Database를 열람하고 닫고 난 후 프로그램이 종료되는 부분을 수행해주기 때문이다.
  - 무한 반복으로 인해 장애가 발생할 수 있음.

### MYSQL 문법

#### 테이블 생성
```SQL
CREATE TABLE CUST(
   id varchar(20) PRIMARY KEY,
   pwd VARCHAR(20),
   name VARCHAR(30)
);
```

#### 값 삽입
```SQL
INSERT INTO table명 VALUES ();
```

#### 값 업데이트
```SQL
UPDATE CUST SET pwd='', name ='' WHERE id =''; 
```
- String 값으로 넣어야 함.

#### 값 삭제
```SQL
DELETE FROM CUST WHERE id='';
```
- 삭제할 값

#### 값 읽기
```SQL
SELECT * FROM table명;
```