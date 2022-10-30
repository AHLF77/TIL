## 0420 배운 내용 요약
### 복습
- 추상: 실체들 간에 공통되는 특성을 추출한 것(ex: 새, 곤충, 물고기 -> 동물)
- 추상 클래스는 실체 클래스의 공통된 필드와 메소드의 이름 통일할 목적

### 인터페이스
- DB 연동과 관련이 있음.

#### 인터페이스 예제1
```java
package P344;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		System.out.println("Start...");
		OracleDAO dao = new OracleDAO(); // OracleDAO에 넣기위해서는 클래스 초기화 필요. 단, 오라클과 연동되는 프로그램으로 고착됨.

		Scanner sc = new Scanner(System.in);

		while(true) {
			
			System.out.println("Input cmd(i,d,s,a,q): ");
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
package P344;

public class CustomerVO {
	
	private String id;
	private String pwd;
	private String name;
	
	public CustomerVO() {
		
	}

	public CustomerVO(String id, String pwd, String name) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return "CustomerVO [id=" + id + ", pwd=" + pwd + ", name=" + name + "]";
	}
	
	
	
}

```

```java
package P344;

import java.util.ArrayList;

public class OracleDAO {
	
	public void connect() {
		System.out.println("Oracle Server Connected.......");
	}
	
	public void close() {
		System.out.println("Oracle Server Disconnected.......");
	}
	
	public void insert(CustomerVO c) {
	connect();
	System.out.println(c);
	System.out.println("...Inserted...");
	close();
	}
	
	public void delete(String id) {
		connect();
		System.out.println(id);
		System.out.println("...Deleted...");	
		close();
	}
	
	public CustomerVO select(String id) {
		connect();
		String pwd = "pwd01";
		String name = "james";
		CustomerVO c = new CustomerVO(id,pwd,name);
		close();
		return c;
	}
	
	public ArrayList<CustomerVO> select(){
		connect();
		ArrayList<CustomerVO> list = new ArrayList<>();
		list.add(new CustomerVO("id01","pwd01","james1"));
		list.add(new CustomerVO("id02","pwd02","james2"));
		list.add(new CustomerVO("id03","pwd03","james3"));	
		close();
		return list;
	}
	
}

```

```java
package P344;

import java.util.ArrayList;
import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		// Collection API
		
		// CustomerVO cs[] = new CustomerVO[5];

		ArrayList<CustomerVO> list = new ArrayList<>();
		list.add(new CustomerVO("id01","pwd01","james1"));
		list.add(new CustomerVO("id02","pwd02","james2"));
		list.add(new CustomerVO("id03","pwd03","james3"));		
		
		for (CustomerVO c : list) {
			System.out.println(c);
		}
	}

}

```

#### 인터페이스 예제2
```java
package P345;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		System.out.println("Start ...");
		DAO dao = new OracleDAO();
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Input cmd(i,d,s,a,q) ...");
			String cmd = sc.next();
			if(cmd.equals("q")) {
				System.out.println("Bye");
				break;
			}else if(cmd.equals("i")) {
				System.out.println("Input Customer info ..");
				System.out.println("Input Customer id:");
				String id = sc.next();
				System.out.println("Input Customer pwd:");
				String pwd = sc.next();			
				System.out.println("Input Customer name:");
				String name = sc.next();
				
				CustomerVO c = new CustomerVO(id, pwd, name);
				dao.insert(c);
			}else if(cmd.equals("d")) {
				System.out.println("Input Customer id:");
				String id = sc.next();
				dao.delete(id);
			}else if(cmd.equals("s")) {
				System.out.println("Input Customer id:");
				String id = sc.next();
				CustomerVO c= dao.select(id);
				System.out.println(c);
			}else if(cmd.equals("a")) {
				ArrayList<CustomerVO> list = dao.select();
				for (CustomerVO c : list) {
					System.out.println(c);
				}
			}
		}
		sc.close();
		System.out.println("End ...");
	}

}

```
```java
package P345;

public class CustomerVO {
	private String id;
	private String pwd;
	private String name;
	public CustomerVO() {
	}
	public CustomerVO(String id, String pwd, String name) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CustomerVO [id=" + id + ", pwd=" + pwd + ", name=" + name + "]";
	}
	
}

```
```java
package P345;

import java.util.ArrayList;

public interface DAO {
	static final int a = 1000;
	
	// 인터페이스는 기능만 정의하는곳에 일반함수를 넣기위해 default를 따로 정의함.
	public default void connect() {
		System.out.println("Connect .....");
	}
	public default void close() {
		System.out.println(" .....");
	}
	
	public void insert(CustomerVO c);
	public void delete(String id);
	public CustomerVO select(String id);
	public ArrayList<CustomerVO> select();
}
```
```java
package P345;

import java.util.ArrayList;

public class MariadbDAO implements DAO {

	@Override
	public void insert(CustomerVO c) {
		connect();
		System.out.println("Mariadb Inserted:"+c);
		close();
	}

	@Override
	public void delete(String id) {
		connect();
		System.out.println("Mariadb Deleted:"+id);
		close();
	}

	@Override
	public CustomerVO select(String id) {
		connect();
		System.out.println("Mariadb Selected:"+id);
		CustomerVO c = new CustomerVO("id01", "pwd01", "james");
		close();
		return c;
	}

	@Override
	public ArrayList<CustomerVO> select() {
		connect();
		System.out.println("Mariadb Selected ALL:");
		ArrayList<CustomerVO> list = new ArrayList<>();
		list.add(new CustomerVO("id01","pwd01","james1"));
		list.add(new CustomerVO("id02","pwd02","james2"));
		list.add(new CustomerVO("id03","pwd03","james3"));
		close();
		return list;
	}

}

```
```java
package P345;

import java.util.ArrayList;


public class OracleDAO implements DAO {

	@Override
	public void insert(CustomerVO c) {
		connect();
		System.out.println("Oracle Insered:"+c);
		close();
	}

	@Override
	public void delete(String id) {
		connect();
		System.out.println("Oracle Deleted:"+id);
		close();
	}

	@Override
	public CustomerVO select(String id) {
		connect();
		System.out.println("Oracle Selected:"+id);
		CustomerVO c = new CustomerVO("id01", "pwd01", "james");
		close();
		return c;
	}

	@Override
	public ArrayList<CustomerVO> select() {
		connect();
		System.out.println("Oracle Selected ALL:");
		ArrayList<CustomerVO> list = new ArrayList<>();
		list.add(new CustomerVO("id01","pwd01","james1"));
		list.add(new CustomerVO("id02","pwd02","james2"));
		list.add(new CustomerVO("id03","pwd03","james3"));
		close();
		return list;
	}

}

```

#### 인터페이스 예제3
```java
package P346;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		System.out.println("Start...");
		DAO dao = new OracleDAO();

		Scanner sc = new Scanner(System.in);

		while(true) {
			
			System.out.println("Input cmd(i,d,u,s,a,q): ");
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
				// customerVO 객체 생성
				CustomerVO c = new CustomerVO(id, pwd, name);
				// OracleDAO의 insert기능 실행.
				dao.insert(c);
				
			}else if(cmd.equals("d")) {
				
				System.out.println("Input Customer id: ");
				String id = sc.next();
				dao.delete(id);
				
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

public class CustomerVO {
	
	private String id;
	private String pwd;
	private String name;
	
	public CustomerVO() {
		
	}

	public CustomerVO(String id, String pwd, String name) {
		this.id = id; 
		this.pwd = pwd; 
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return "CustomerVO [id=" + id + ", pwd=" + pwd + ", name=" + name + "]";
	}
	
	
	
}

```
```java
package P346;

import java.util.ArrayList;

public interface DAO {
	
	static final int a = 1000; 

	// 인터페이스는 기능만 정의하는곳에 일반함수를 넣기위해 default를 따로 정의함.
	
	public default void connect() {
		System.out.println("Connect......");
	}
	public default void close() {
		System.out.println("Close......");
	}
	
	public void insert(CustomerVO c);
	public void delete(String id);
	public void update(CustomerVO c);
	public CustomerVO select(String id);
	public ArrayList<CustomerVO> select();

}

```
```java
package P346;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class OracleDAO implements DAO {

	HashMap<String, CustomerVO> map;
	
	public OracleDAO() {
		map = new HashMap<String, CustomerVO>();
	}
	
	@Override
	public void insert(CustomerVO c) {
		String key = c.getId();
		map.put(key, c);
	}

	@Override
	public void delete(String id) {
		map.remove(id);
	}

	@Override
	public CustomerVO select(String id) {
		CustomerVO c = null;
		c = map.get(id);
		return c;
	}

	@Override
	public ArrayList<CustomerVO> select() {
		Collection<CustomerVO> col = map.values();
		Iterator<CustomerVO> it = col.iterator();
		
		ArrayList<CustomerVO> list = new ArrayList<>();
		while(it.hasNext()) {
			CustomerVO cust = it.next();
			list.add(cust);
		}
		
		return list;
	}

	@Override
	public void update(CustomerVO c) {
		String key = c.getId();
		map.put(key, c);
		
	}

}

```
```java
package P346;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		// MAP - <Key, value>
		HashMap<String, CustomerVO> map = new HashMap<String, CustomerVO>();
		
		//insert
		CustomerVO c1 = new CustomerVO("id01","pwd01","james1");
		CustomerVO c2 = new CustomerVO("id02","pwd02","james2");
		CustomerVO c3 = new CustomerVO("id03","pwd03","james3");
		CustomerVO c4 = new CustomerVO("id04","pwd04","james4");
		CustomerVO c5 = new CustomerVO("id05","pwd05","james5");
		
		map.put("id01", c1);
		map.put("id02", c2);
		map.put("id03", c3);
		map.put("id04", c4);
		map.put("id05", c5);
		
		//delete
		map.remove("id02");
		
		//update
		CustomerVO cc = new CustomerVO("id03", "ppp03", "jj03");
		map.put("id03", cc);
		
		//select
		String id = "id03";
		CustomerVO c = map.get(id);
		System.out.println(c);
		
		//select all
		Collection<CustomerVO> col = map.values();
		Iterator<CustomerVO> it = col.iterator();
		
		ArrayList<CustomerVO> list = new ArrayList<>();
		while(it.hasNext()) {
			CustomerVO cust = it.next();
			list.add(cust);
		}
		
		for(CustomerVO customerVO : list) {
			System.out.println(customerVO);
		}
	}

}

```

#### 인터페이스 WorkShop
```java
package ws;

import java.util.ArrayList;
import java.util.Scanner;


public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Start...");
		// OracleDAO에 넣기위해서는 클래스 초기화 필요. 단, 오라클과 연동되는 프로그램으로 고착됨. 
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
				// customerVO 객체 생성
				ToDoVO c = new ToDoVO(id, pwd, name);
				// OracleDAO의 insert기능 실행.
				dao.insert(c);
			}else if (cmd.equals("d")) {
				System.out.println("Input order:");
				String id = sc.next();
				dao.delete(id);
			}else if (cmd.equals("s")) {
				System.out.println("Input order:");
				String id = sc.next();
				ToDoVO c = dao.select(id);
				System.out.println(c);
			}else if (cmd.equals("a")) {
				ArrayList<ToDoVO>list = dao.select();
				for (ToDoVO c : list) {
					System.out.println(c);
				}
			}else if (cmd.equals("u")) {
				System.out.println("Input order:");
				String id = sc.next();			
				ToDoVO c = dao.update(id);
				System.out.println(c);
			}else if (cmd.equals("f")) {
				System.out.println("Input when:");
				String when = sc.next();
				ArrayList<ToDoVO> list = search.search(when);
				for (ToDoVO c : list) {
					System.out.println(c);
				}
			}
		}
		
		sc.close();
		System.out.println("End ...");
		

	}

}
```

```java
package ws;

import java.util.ArrayList;


public interface DAO {
	

	public void insert(ToDoVO c);
	public void delete(String id);
	public ToDoVO update(String id);
	public ToDoVO select(String id);
	public ArrayList<ToDoVO> select();
	
	
}
```

```java
package ws;

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
	public void insert(ToDoVO c) {
		String key = c.getId();
		map.put(key, c);
	}

	@Override
	
	public void delete(String id) {	
		map.remove(id);
	}

	@Override
	public ToDoVO select(String id) {
		ToDoVO c = null;
		c = map.get(id);
		return c;
	}

	@Override
	public ArrayList<ToDoVO> select() {
		Collection<ToDoVO> col = map.values();
		Iterator<ToDoVO> it = col.iterator();		
		ArrayList<ToDoVO> list = new ArrayList<>();	
		while(it.hasNext()) {
			ToDoVO cust = it.next();
			list.add(cust);
		}
		return list;
	}

	@Override
	public ToDoVO update(String id) {
		ToDoVO c = null;
		c = map.get(id);	
		c.setDone(true);
		return c;
	}

	@Override
	public ArrayList<ToDoVO> search(String when) {
		ArrayList<ToDoVO> list = new ArrayList<>();	
		Collection<ToDoVO> col = map.values();
		Iterator<ToDoVO> it = col.iterator();
		while(it.hasNext()) {
            ToDoVO cust = it.next();
            if(cust.getWhen().equals(when)) {
                list.add(cust);
            }
		}
		return list;

	}

}
```

```java
package ws;

import java.util.ArrayList;


public interface Search {
	public ArrayList<ToDoVO> search(String name);
}
```

```java
package ws;

public class ToDoVO {
	private String id;
	private String when;
	private String what;
	private boolean done;
	public ToDoVO() {
	}
	
	public ToDoVO(String id, String when, String what) {
		this.id = id;
		this.when = when;
		this.what = what;
		this.done = false;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
		return "ToDoVO [id=" + id + ", when=" + when + ", what=" + what + ", done=" + done + "]";
	}


}
```