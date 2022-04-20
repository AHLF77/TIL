## 0420 강의
### 복습
- 추상: 실체들 간에 공통되는 특성을 추출한 것(ex: 새, 곤충, 물고기 -> 동물)
- 추상 클래스는 실체 클래스의 공통된 필드와 메소드의 이름 통일할 목적

### 인터페이스
- DB 연동과 관련이 있음.

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

```

```java

```