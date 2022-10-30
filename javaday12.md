## 0422 배운 내용 요약

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

//VO = value object
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
		
		Object obj3 = 10; 
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
		// Object 객체를 CustromerVO 객체로 바꿔줘야함.
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
	
	// 인터페이스는 기능만 정의하는곳에 일반함수를 넣기위해 default를 따로 정의함.
	public default void connect() {
		System.out.println("Connect......");
	}
	public default void close() {
		System.out.println("Close......");
	}
	//CRUD
	// Object 클래스를 쓰면, 어떤 데이터를 집어넣어도 되는 표준 스펙이 됨.
	public void insert(Object obj) throws Exception;  // DuplicatedIDException
	public void delete(Object obj) throws Exception;  // NotFoundException
	public void update(Object obj) throws Exception; // NotFoundException
	public Object select(Object obj) throws Exception; // NotFoundException
	public ArrayList<Object> select() throws Exception; // NotFoundException

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
package oracle;

public class ItemTestApp {

	public static void main(String[] args) {
		DAO dao = new ItemDAO();
		
		try {
			ItemVO item = new ItemVO(100, "pants", 10000.0);
			dao.insert(item);
			
			dao.delete(100);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}

```
```java
package oracle;

public class ItemVO {
	private int id;
	private String name;
	private double price;
	
	public ItemVO() {
		
	}

	public ItemVO(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ItemVO [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
	

}

```

```java
package Oracle;

public class NotFoundException extends Exception {
	public NotFoundException() {
		
	}
	
	public NotFoundException(String msg) {
		super(msg);
	}
}
```

### 예제3(달력)
```java
package P496;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTest {

	public static void main(String[] args) {
		
		Date date = new Date();
		System.out.println(date);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY:MM:dd-hh:mm:ss");
		String str = sdf.format(date);
		System.out.println(str);

		TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles"); 
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY:MM:dd-hh:mm:ss");
		sdf2.setTimeZone(tz);
		System.out.println(sdf2.format(new Date()));
		
		Calendar cal = Calendar.getInstance(tz);
		int yy = cal.get(Calendar.YEAR);
		int mm = cal.get(Calendar.MONTH)+1;
		int dd = cal.get(Calendar.DAY_OF_MONTH);
		int hh = cal.get(Calendar.HOUR);
		int mi = cal.get(Calendar.MINUTE);
		int ss = cal.get(Calendar.SECOND);
		System.out.printf("%d %d %d %d %d %d", yy, mm, dd, hh, mi, ss);
		
	}

}


```

### 예제4(랜덤)
```java
package P496;

import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		double d = Math.random(); // 0.0 <= x < 1.0
		System.out.println(d);
		
		int i1 = (int)(Math.random() * 6) + 1;
		System.out.println(i1);
		
		int i2 = (int)(Math.random() * 45) + 1;
		System.out.println(i2);
		
		Random random = new Random();
		int i3 = random.nextInt(45)+1;
		
		double dd = (random.nextDouble() * 10000000000.0) +1;
		System.out.println(dd);
		System.out.printf("%.0f",dd);
		
	}

}
```

### 예제5(StringBuffer)

```java
package P496;

public class StringBufferTest {

	public static void main(String[] args) {
		
		// String은 변경 불가
		StringBuffer sb = new StringBuffer("abcdef");
		sb.append("ghi");
		System.out.println(sb);
		sb.reverse();
		System.out.println(sb);
		sb.delete(2,3);
		System.out.println(sb);

	}

}

```

### 예제6(String)
```java
package P496;

public class StringTest {

	public static void main(String[] args) {
		String str = "abcdef ";
		
		char c = str.charAt(3);
		System.out.println(c);
		
		int i = str.indexOf('c');
		System.out.println(i);
		
		String str2 = str.substring(1,3);
		System.out.println(str2);
		
		String str3 = str.trim();
	
		char cs[] = str.toCharArray();
		for(char d: cs) {
			System.out.println(d);
		}
		
		String ss = "1,2,3,4,5";
		String result[] = ss.split(","); //,를 기준으로 스플릿 하기(:을 기준으로 스플릿 하기)
	
		for (String st: result) {
			System.out.println(st);
		}
	}

}

```
### 예제7(Wrapper)
```java
package P496;

public class WrapperTest {

	public static void main(String[] args) {
		int a = 10;
		Integer i = 10;
		
		int result = a + i;
		System.out.println(result);
		
		if(a == i) {
			System.out.println("ok");
		}

	}

}

```

### 예제8(Format)
```java
package P496;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatTest {

	public static void main(String[] args) {
		double num = 1234567878.789;
		DecimalFormat df = new DecimalFormat("#,###");

		String str = df.format(num);
		System.out.println(str);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm:Sm E a");
		String str2 = sdf.format(new Date());
		System.out.println(str2);
	}

}

```

### P654
```java
package p654;

public class Box {
	Object obj;
	public Box() {
		
	}
	public void setBox(Object c) {
		obj = c;
	}
	public Object getBox(){
		return obj;
	}
	
	
}
```

```java
package p654;

import java.util.ArrayList;

public class BoxTest {

	public static void main(String[] args) {
		Box box = new Box();
		box.setBox(new ItemVO(100,"pants",10000));
		
		ItemVO item = (ItemVO)box.getBox();
		System.out.println(item);
	}

}
```

```java
package p654;

//VO = value object
public class CustomerVO {
	private String id;
	private String password;
	private String name;
	public CustomerVO() {
	}
	public CustomerVO(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CustomerVO [id=" + id + ", password=" + password + ", name=" + name + "]";
	}
	
	
	
}
```

```java
package p654;

public class ItemVO {
	private int id;
	private String name;
	private double price;
	public ItemVO() {
	}
	public ItemVO(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ItemVO [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
}
```

### P722
```java
package p722;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add(2,"d");
		
		
		for (String s : list) {
			System.out.println(s);
		}
	}

}
```
```java
package p722;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(100,"A");
		map.put(102,"B");
		map.put(103,"C");
		map.put(104,"D");
		map.put(105,"E");
		map.put(107,"f");
		
		System.out.println(map.toString());
		map.remove(102);
		System.out.println(map.toString());
		System.out.println(map.get(103));
		

	}

}
```
```java
package p722;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiTest {

	public static void main(String[] args) {
		Properties pr = new Properties();
		String path = PropertiTest.class.getResource("database properties.txt").getPath();
		try {
			pr.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String url = pr.getProperty("url");
		
		System.out.println(url);

	}
	

}
```
```java
package p722;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>();
		Random rd = new Random();
		while(set.size() <= 6) {
			int num = rd.nextInt(45) + 1;
			set.add(num);
			System.out.println("cnt");
		}
		
		for (Integer i : set) {
			System.out.println(i);
		}

	}

}
```
```java
package p722;

import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		Stack<String> st = new Stack<String>();
		st.push("a");
		st.push("b");
		st.push("c");
		st.push("d");
		st.push("e");
		
		while(! st.isEmpty()) {
			String s = st.pop();
			System.out.println(s);
		}
		
	}

}
```