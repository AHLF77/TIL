## 0421 배운 내용 요약

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

## 예외처리 예제3
```java
package bank;

public class Account {
	//private : 해당 변수는 다른 클래스에서 수정을 못하도록 막음.
	private String accNo;
	private double balance;
	
	public Account() {
	}

	
	public Account(String accNo) {
		this.accNo = accNo;
	}


	public Account(String accNo, double balance) {
		this.accNo = accNo;
		this.balance = balance;
	}

	
	
	// get은 타 클래스에서 정보만 볼 수 있는 것.
	public String getAccNo() {
		return accNo;
	}


	public double getBalance() {
		return balance;
	}


	@Override
	public String toString() {
		return "Account [accNo=" + accNo + ", balance=" + balance + "]";
	}
	
	public void deposit(double money) throws MinusException {
		if(money < 1) {
		throw new MinusException("EX0001");
		}
		this.balance += money;
	}
	
	// 출금 금액이 1보다 작으면 안된다.
	// 출금 금액이 잔액 보다 많으면 안된다.
	public void withdraw(double money) throws MinusException, OverdrawnException {
		if(money < 1) {
			throw new MinusException("음수입니다.");
		}
		if(this.balance < money) {
			throw new OverdrawnException("잔액부족");
		}
		this.balance -= money;
	}
	
}
```
```java
package bank;

public class BankApp {

	public static void main(String[] args) {
		Account acc = new Account("1111",10000);

		try {
			acc.deposit(-100);	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			acc.withdraw(1000);
		} catch (MinusException | OverdrawnException e) {
			System.out.println(e.getMessage());
		}
	
		System.out.println(acc);
	
	}

}

```
```java
package bank;

public class MinusException extends Exception {
	
	public MinusException() {
		
	}
	public MinusException(String msg) {
		super(msg);
	}
	
}

```
```java
package bank;

public class OverdrawnException extends Exception {
	
	public OverdrawnException() {
		
	}
	public OverdrawnException(String msg) {
		super(msg);
	}
	
}

```

### WorkShop
```java
package workshop;

import java.util.ArrayList;
import java.util.Scanner;


public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Start..."); 
		OracleDAO oracledao = new OracleDAO();
		DAO dao = oracledao;
		Search search = oracledao;
		while(true) {
			System.out.println("Input cmd(i,d,s,u,a,f,q) ...");
			String cmd = sc.next();
			if(cmd.equals("q")) {
				System.out.println("Bye");
				break;
			}else if (cmd.equals("i")) {
				System.out.println("Input todolist ..");
				System.out.println("Input order..");
				String id = sc.next();
				System.out.println("Input when..");
				String pwd = sc.next();
				System.out.println("Input what..");
				String name = sc.next();
				ToDoVO c = new ToDoVO(id, pwd, name);
				try {
					dao.insert(c);
				} catch (DuplicatedIDException e) {
					System.out.println(e.getMessage());
				}
			}else if (cmd.equals("d")) {
				System.out.println("Input order:");
				String id = sc.next();
				try {
					dao.delete(id);
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
			}else if (cmd.equals("s")) {
				System.out.println("Input order:");
				String id = sc.next();
				ToDoVO c;
				try {
					c = dao.select(id);
					System.out.println(c.toString());
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
			}else if (cmd.equals("a")) {
				ArrayList<ToDoVO> list;
				try {
					list = dao.select();
					for (ToDoVO c : list) {
						System.out.println(c.toString());
					}
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
			}else if (cmd.equals("u")) {
				System.out.println("Input order:");
				String id = sc.next();			
				ToDoVO c;
				try {
					c = dao.update(id);
					System.out.println(c);
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}

			}else if (cmd.equals("f")) {
				System.out.println("Input when:");
				String when = sc.next();
				ArrayList<ToDoVO> list;
				try {
					list = search.search(when);
					for (ToDoVO c : list) {
						System.out.println(c);
					}
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());;
				}

			}
		}
		
		sc.close();
		System.out.println("End ...");
		

	}

}
```
```java
package workshop;

import java.util.ArrayList;

public interface DAO {
	public void insert(ToDoVO c)  throws DuplicatedIDException;
	public void delete(String id) throws NotFoundException;
	public ToDoVO update(String id)throws NotFoundException;
	public ToDoVO select(String id)throws NotFoundException;
	public ArrayList<ToDoVO> select()throws NotFoundException;
	
}
```
```java
package workshop;

public class DuplicatedIDException extends Exception {
	public DuplicatedIDException() {
		
	}
	
	public DuplicatedIDException(String msg) {
		super(msg);
	}
}
```
```java
package workshop;

public class NotFoundException extends Exception {
	public NotFoundException() {
		
	}
	
	public NotFoundException(String msg) {
		super(msg);
	}
}
```
```java
package workshop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class OracleDAO implements DAO, Search {

	HashMap<String, ToDoVO> map;
	
	public OracleDAO() {
		map = new HashMap<String, ToDoVO>();
	}
	
	@Override
	public void insert(ToDoVO c) throws DuplicatedIDException {
		String key = c.getOrder();
		if(map.containsKey(key)) {
			throw new DuplicatedIDException("중복된 번호 입니다.");
		}		
		map.put(key, c);
	}

	@Override
	// 1. true가 된 것은 삭제
	public void delete(String id) throws NotFoundException {		

		// 1. false를 찾는다.
		// 2. key를 뽑는다.
		// 3. 지운다.
		if(! map.containsKey(id)) {
			throw new NotFoundException("해당 번호를 찾을 수 없습니다.");
		}
		map.remove(id);
	}

	@Override
	public ToDoVO select(String id) throws NotFoundException {
		ToDoVO c = null;
		if(! map.containsKey(id)) {
			throw new NotFoundException("해당 번호를 찾을 수 없습니다.");
		}
		c = map.get(id);
		return c;
	}

	@Override
	public ArrayList<ToDoVO> select() throws NotFoundException {
		ArrayList<ToDoVO> list = new ArrayList<>();	
		if(map.size()==0) {
			throw new NotFoundException("현재 목록이 없습니다.");
		}
		Collection<ToDoVO> col = map.values();
		Iterator<ToDoVO> it = col.iterator();		
		
		while(it.hasNext()) {
			ToDoVO cust = it.next();
			list.add(cust);
		}
		return list;
	}

	@Override
	public ToDoVO update(String id) throws NotFoundException {
		// 해당 ID는 완료처리해서 true값으로 바꾸어줌.
		ToDoVO c = null;
		if(! map.containsKey(id)) {
			throw new NotFoundException("해당 번호를 찾을 수 업습니다.");
		}
		c = map.get(id);
		if(c.isDone() == true) {
			throw new NotFoundException("이미 완료한 항목입니다.");
		}
		c.setDone(true);
		return c;
	}

	@Override
	public ArrayList<ToDoVO> search(String when) throws NotFoundException {
		ArrayList<ToDoVO> list = new ArrayList<>();	
		Collection<ToDoVO> col = map.values();
		Iterator<ToDoVO> it = col.iterator();
		while(it.hasNext()) {
            ToDoVO cust = it.next();
            if(cust.getWhen().equals(when)) {
                list.add(cust);
            }else {
            	throw new NotFoundException("해당 날짜를 찾을 수 없습니다.");
            }
            
		}
		return list;

	}

}
```
```java
package workshop;

import java.util.ArrayList;


public interface Search {
	public ArrayList<ToDoVO> search(String name) throws NotFoundException;
}
```
```java
package workshop;

public class ToDoVO {
	private String order;
	private String when;
	private String what;
	private boolean done;
	public ToDoVO() {
	}
	public ToDoVO(String order, String when, String what) {
		this.order = order;
		this.when = when;
		this.what = what;
		this.done = false;			
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getWhen() {
		return when;
	}
	public void setWhen(String when) {
		this.when = when;
	}
	public String getWhat() {
		return what;
	}
	public void setWhat(String what) {
		this.what = what;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	@Override
	public String toString() {
		return "ToDoVO [order=" + order + ", when=" + when + ", what=" + what + ", done=" + done + "]";
	}


	
}
```