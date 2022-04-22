## 0422 강의

### 자바 API란
- 자바에서 기본적으로 제공하는 라이브러리
- 프로그램 개발에 자주 사용되는 클래스 및 인터페이스 모음

### API 도큐먼트
- 쉽게 API 찾아 이용할 수 있도록 문서화한 것

### java.lang 패키지
- 자바 프로그램의 기본적인 클래스를 담은 패키지
- 포함된 클래스와 인터페이스는 import 없이 사용


### 예제1
```java
package P457;

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
package P457;

public class TestApp {

	public static void main(String[] args) {
		
		Object obj1 = new String("abc");
		Object obj2 = new CustomerVO("id01","pwd01","lee");

		CustomerVO c = (CustomerVO)obj2;
		System.out.println(c.getName());
		
		Object obj3 = 10; //왜 될까?
		Object obj4 = new Integer(10);
		
	}

}

```

### 예제2
```java
package oracle;

import java.util.ArrayList;

public class CustomerDAO implements DAO {

	@Override
	public void insert(Object obj) throws Exception {
		connect();
		CustomerVO c = (CustomerVO)obj;
		System.out.println(c);
		System.out.println("Inserted...");
		close();
	}

	@Override
	public void delete(Object obj) throws Exception {
		connect();
		String id = (String)obj;
		System.out.println(id);
		System.out.println("Deleted...");
		close();

	}

	@Override
	public void update(Object obj) throws Exception {
		

	}

	@Override
	public Object select(Object obj) throws Exception {
		connect();
		String id = (String)obj;
		CustomerVO c= new CustomerVO(id,"pwd01","lee");
		close();
		return c;
	}

	@Override
	public ArrayList<Object> select() throws Exception {
		connect();
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(new CustomerVO("id01", "pwd01", "lee"));
		list.add(new CustomerVO("id02", "pwd02", "kim"));
		list.add(new CustomerVO("id03", "pwd03", "koo"));
		close();
		return list;
	}

}

```
```java
package oracle;

import java.util.ArrayList;

public class CustomerTestApp {

	public static void main(String[] args) {
		DAO dao = new CustomerDAO();
		
		try {
			CustomerVO c= new CustomerVO("id01","pwd01","lee");
			dao.insert(c);
			
			ArrayList<Object> list = dao.select();
			
			for (Object obj : list) {
				System.out.println(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

```
```java
package oracle;

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
package oracle;

import java.util.ArrayList;

public interface DAO {
	
	public default void connect() {
		System.out.println("Connect......");
	}
	public default void close() {
		System.out.println("Close......");
	}
	//CRUD
	public void insert(Object obj) throws Exception;
	public void delete(Object obj) throws Exception; 
	public void update(Object obj) throws Exception; 
	public Object select(Object obj) throws Exception; 
	public ArrayList<Object> select() throws Exception;

}

```
```java
package oracle;

import java.util.ArrayList;

public class ItemDAO implements DAO {

	@Override
	public void insert(Object obj) throws Exception {
		connect();
		ItemVO item = (ItemVO)obj;
		System.out.println("Inserted....");
		close();
	}

	@Override
	public void delete(Object obj) throws Exception {
		connect();
		Integer ii = (Integer) obj; //Integer는 오브젝트 타입이다. 
		int id = ii.intValue();
		System.out.println(id+"값이 삭제 되었습니다.");
		close();
	}

	@Override
	public void update(Object obj) throws Exception {
		

	}

	@Override
	public Object select(Object obj) throws Exception {
		
		return null;
	}

	@Override
	public ArrayList<Object> select() throws Exception {
		
		return null;
	}

}

```
```java
```
