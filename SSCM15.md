## 0425 강의

## 자바 언어의 특징
 - Exception
 - oop
 - Generic: 상속 받아 오버라이딩 함, 다형성을 쉽게 구현이 가능
 - Collection API
 - Multi thread
 - I/O

### Multi thread
- 여러 개의 크롬 프로세스가 생성된 것이다.
- 멀티 태스킹: 독립적으로 프로그램들을 실행하고 여러 가지 작업 처리
- 멀티 스레드: 한 개의 프로그램을 실행하고 내부적으로 여러 가지 작업 처리
- 스레드를 짤 일이 없음.

```java
package P576;

public class MainThread {

	public static void main(String[] args) {
		System.out.println("Start...............");
		MyThread1 t1= new MyThread1();
		t1.start();
		
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