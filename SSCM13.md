## 0421 강의

### serach
```java
package P346;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		System.out.println("Start...");
		OracleDAO oracleDao = new OracleDAO();
		
		//CRUD
		DAO dao = oracleDao;
		
		//Search
		Search search = oracleDao;

		Scanner sc = new Scanner(System.in);

		while(true) {
			
			System.out.println("Input cmd(i,d,u,s,a,c,q): ");
			String cmd = sc.next();
			
			if(cmd.equals("q")) {
				
				System.out.println("Bye");
				break;
				
			}else if(cmd.equals("i")) {
				
				System.out.println("Input Customer info... ");
				System.out.println("Input Customer id: ");
				String id = sc.next();
				System.out.println("Input Customer pwd: ");
				String pwd = sc.next();
				System.out.println("Input Customer name: ");
				String name = sc.next();
				
				CustomerVO c = new CustomerVO(id, pwd, name);
				dao.insert(c);
				
			}else if(cmd.equals("d")) {
				
				System.out.println("Input Customer id: ");
				String id = sc.next();
				dao.delete(id);
				
			}else if(cmd.equals("c")) {
				
				System.out.println("Input Customer name: ");
				String name = sc.next();
				search.search(name);
				ArrayList<CustomerVO> list = search.search(name);
				for (CustomerVO c : list) {
					System.out.println(c);	
				}
				
			}else if(cmd.equals("s")) {
				
				System.out.println("Input Customer id: ");
				String id = sc.next();
				CustomerVO c = dao.select(id);
				System.out.println(c);
				
			}else if(cmd.equals("a")) {
				ArrayList<CustomerVO> list= dao.select();
				for (CustomerVO c : list) {
					System.out.println(c);
				}
			}else if(cmd.equals("u")) {
				
				System.out.println("Input Customer id: ");
				String id = sc.next();
				System.out.println("Input Customer pwd: ");
				String pwd = sc.next();
				System.out.println("Input Customer name: ");
				String name = sc.next();
				
				CustomerVO c = new CustomerVO(id, pwd, name);
				dao.update(c);
				
			}else {
				System.out.println("wrong text");
			}
		}
		sc.close();
		System.out.println("End...");

	}

}

```
```java
package P346;

public class TestApp {

	public static void main(String[] args) {
		OracleDAO oracleDao = new OracleDAO();
		
		DAO dao = oracleDao;
		
		CustomerVO c = new CustomerVO("id01","pwd01","lee");
		dao.insert(c);
		
		CustomerVO sc = dao.select("id01");
		System.out.println(sc);
		
	}

}

```
```java
package P346;

import java.util.ArrayList;

public interface Search {
	
	public ArrayList<CustomerVO> search(String name);

}

```

### 웹 어플리케이션 디자인
```java
package awt;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame {
	Frame f;
	Button b;
	
	public MyFrame() {
		f = new Frame("My Frame");
		b = new Button("click");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				b.setLabel("Clicked");
				
			}
		});
	}
	public void setView() {
		f.add(b,"North");
		f.setSize(300,200);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		
		});
		
	}

}

```

```java
package awt;

public class App {

	public static void main(String[] args) {
		MyFrame m = new MyFrame();
		m.setView();
	}

}

```

## 10장 예외처리
- 사용자의 잘못된 조작 또는 개발자의 잘못된 코딩으로 인해 발생하는 프로그램 오류
- 개발자가 잘못 코딩 시, 빨리 고쳐서 배포를 해야함
- 예외 종류: 일반에러, 실행 에외

## 예외처리 예제1
```java
package P422;

import java.util.Scanner;

public class Ex2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input number");
		String num = sc.next();
		int n = 0;
		
		try {
			n = Integer.parseInt(num);	
		} catch (Exception e) {
			//System.out.println(e);	
			e.printStackTrace();
			System.out.println("숫자가 아닙니다.");
		
		}
		
		System.out.println(n);
		int result = 0;
		try {
			result = 100/n;	
		} catch (Exception e) {
			System.out.println("분모가 0입니다.");
		}
		
		System.out.println(result);
		sc.close();
	}

}

}

```

```java
package P422;

import java.util.Scanner;

public class Ex1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input number");
		String num = sc.next();
		int n = 0;
		int result = 0;
				
		try {
			n = Integer.parseInt(num);	
			result = 100/n;	
			System.out.println(result);
			sc.close();
			System.out.println("end");
		} catch (ArithmeticException e) {
			System.out.println("분모가 0입니다.");
		} catch(NumberFormatException e) {
			System.out.println("숫자가 아닙니다.");
		}finally {
			sc.close(); // 주의
			System.out.println("end");
		
		}
	}

}

```
```java
package P422;

import java.util.Iterator;

public class Ex3 {

	public static void main(String[] args) {
		String str = "ABC";
		System.out.println(str.toLowerCase());
		
		int a[] = new int[3];
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		
		for (int i : a) {
			System.out.println(i);
		}

	}

}

```

## 예외처리 예제2
```java
package P440;

public class Calc {
	public int div(int a, int b) throws Exception{
		int result = 0;
		try {
			result = a/b;	
		} catch (Exception e) {
			throw e;
		}
		return result;
		
	}
	
}

```
```java
package P440;

public class App {

	public static void main(String[] args) {
		
		Calc calc = new Calc();
		
		int a = 10;
		int b = 0;
		int result = 0;
		try {
			result = calc.div(a,b);
		} catch (Exception e) {
			System.out.println("분모가 0 입니다.");
		}
		
		System.out.println(result);	
	
	}

}

```