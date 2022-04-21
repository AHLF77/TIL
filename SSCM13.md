## 0421 강의

### serach
```java

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