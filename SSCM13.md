## 0421 강의

### 웹 디자인
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