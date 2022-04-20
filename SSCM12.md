## 0420 강의
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
		OracleDAO dao = new OracleDAO();

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

```
```java

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

