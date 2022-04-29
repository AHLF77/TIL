## 0429 미니 프로젝트

## 7조 구성원: 김O준, 한O정, 강O성, 유O아

## 놀이동산 직원 관리 프로그램

### app 패키지
```java
package app;

public class App {

	public static void main(String[] args) {
		ApUI ui = new ApUI();

	}

}

```
```java
package app;

import java.util.List;
import java.util.Scanner;

import dao.EmployeeDao;
import frame.Dao;
import vo.EmployeeVo;


public class App2 {

	public static void main(String[] args) {
		
		Dao<String, EmployeeVo> dao = new EmployeeDao();
		
		System.out.println("Start...");
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Input cmd(i,s,l,q): ");
			String cmd = sc.next();
			if(cmd.equals("q")) {
				System.out.println("Bye");
				break;
			}else if(cmd.equals("i")) {
				System.out.println("Input amusement park Employee Info: ");
				String eid = sc.next();
				String ename = sc.next();
				String position = sc.next();
				String ridespart = sc.next();
				double Salary = sc.nextDouble();
				String regdate = sc.next();
				
				
				EmployeeVo c = new EmployeeVo(eid, ename, position, ridespart,Salary,regdate);
				try {
					dao.create(c);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
			}else if(cmd.equals("s")) {
				List<EmployeeVo> list = null;
				try {
					list = dao.select();
					for (EmployeeVo c : list) {
						System.out.println(c);
					}
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}else if(cmd.equals("l")) {
				System.out.println("Input eid value: ");
				String eid = sc.next();
				
				EmployeeVo c = null;
				
				try {
					c=dao.select(eid);
					if((c.getEid()).equals(eid)) {
						System.out.println("Success");
					}else {
						throw new Exception();
					}
				} catch (Exception e) {
					System.out.println("Fail");
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
package app;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import dao.EmployeeDao;
import frame.Dao;
import vo.EmployeeVo;

public class ApUI {

	Dao<String,EmployeeVo> dao;
	java.util.List<EmployeeVo> elist;
	
	Frame f;
	Button b1, b2;
	Panel p1, p2;
	Panel main;
	TextField tf1, tf2, tf3, tf4, tf5, tf6;
	TextField maintf;
	List list;
	Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6;
	
	public ApUI() {
		
		dao = new EmployeeDao();
		init();
		make();
		addEvent();
	}
	
 	private void init() {
		f = new Frame("놀이동산 직원 관리 프로그램");
		b1 = new Button("등록");
		b2 = new Button("조회");
		p1 = new Panel();
		p2 = new Panel();
		
		main = new Panel();
		maintf = new TextField();
		lbl1 = new Label("직원ID "); 
		tf1 = new TextField();
		lbl2 = new Label("직원 이름"); 
		tf2 = new TextField();
		lbl3 = new Label("직책"); 
		tf3 = new TextField();
		lbl4 = new Label("놀이기구 부서"); 
		tf4 = new TextField();
		lbl5 = new Label("연봉"); 
		tf5 = new TextField();
		lbl6 = new Label("입사날짜"); 
		tf6 = new TextField();
		list = new List();
		
	}

	private void make() {
		
		p1.setBackground(Color.gray);
		p1.setLayout(null);
		
		// 첫번째 입력값
		p1.add(lbl1);
		lbl1.setBounds(20, 10, 80, 20);
		p1.add(tf1);
		tf1.setBounds(120, 10, 160, 20);
		
		// 두번 째 입력값
		p1.add(lbl2);
		lbl2.setBounds(20, 40, 80, 20);
		p1.add(tf2);
		tf2.setBounds(120, 40, 160, 20);
		
		// 세번 째 입력값
		p1.add(lbl3);
		lbl3.setBounds(20, 70, 80, 20);
		p1.add(tf3);
		tf3.setBounds(120, 70, 160, 20);
		
		// 네번 째 입력값
		p1.add(lbl4);
		lbl4.setBounds(20, 100, 100, 20);
		p1.add(tf4);
		tf4.setBounds(120, 100, 160, 20);
		
		// 다섯번 째 입력값
		p1.add(lbl5);
		lbl5.setBounds(20, 130, 80, 20);
		p1.add(tf5);
		tf5.setBounds(120, 130, 160, 20);
		
		// 여섯번 째 입력값
		p1.add(lbl6);
		lbl6.setBounds(20, 160, 80, 20);
		p1.add(tf6);
		tf6.setBounds(120, 160, 160, 20);
		
		p1.add(b1);
		b1.setBounds(100, 200, 120, 40);
	
		p2.setLayout(new BorderLayout());
		p2.add(list,"Center");
		p2.add(b2,"South");
		
		main.setLayout(new GridLayout(1, 2));
		main.add(p1);
		main.add(p2);
		
		f.add(main,"Center");
		f.add(maintf,"South");
		
		f.setSize(800, 800);
		f.setVisible(true);
 		 
		
	}
 	
 	private void addEvent() {
 		list.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int t = list.getSelectedIndex();
				EmployeeVo emp = elist.get(t);
				String s = emp.getEid()+" "+emp.getEname()+" "+emp.getPosition()+" "+emp.getRidespart()+" "+emp.getSalary()+emp.getRegdate();
				maintf.setText(s);
			}
		});
		
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String eid = tf1.getText();
				String ename = tf2.getText();
				String position = tf3.getText();
				String ridespart = tf4.getText();
				String Salary = tf5.getText();
				double i= Double.parseDouble(Salary);
				String regdate = tf6.getText();
				EmployeeVo v = new EmployeeVo(eid,ename,position,ridespart,i,regdate);
				try {
					dao.create(v);
					tf1.setText("");
					tf2.setText("");
					tf3.setText("");
					tf4.setText("");
					tf5.setText("");
					tf6.setText("");
					maintf.setText(eid+"  Insert OK");
					
				} catch (Exception e1) {
					tf1.setText("");
					tf2.setText("");
					tf3.setText("");
					tf4.setText("");
					tf5.setText("");
					tf6.setText("");
					maintf.setText("Insert Error ...  !!!");
				}
			}
		});
		
		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					elist = dao.select();
					for (EmployeeVo c : elist) {
						String str = c.getEid()+" "+c.getEname()+" "+c.getPosition()+" "+c.getRidespart()+" "+c.getSalary()+c.getRegdate();
						list.add(str);
						maintf.setText(elist.size()+": Completed !");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
	}
	
 
 

} 
```

### dao 패키지

```java
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import frame.Dao;
import frame.Sql;
import vo.EmployeeVo;

public class EmployeeDao extends Dao<String, EmployeeVo> {

	@Override
	public void create(EmployeeVo v) throws Exception {
		// Connection
				Connection con = null;
				PreparedStatement ps = null;
				try {
				con = getConnection();
				ps = con.prepareStatement(Sql.createEmployee);
				ps.setString(1, v.getEid());
				ps.setString(2, v.getEname());
				ps.setString(3, v.getPosition());
				ps.setString(4, v.getRidespart());
				ps.setDouble(5, v.getSalary());
				ps.setString(6, v.getRegdate());
				ps.executeUpdate();
				}catch(Exception e) {
					throw new Exception("사용자 입력 오류");
				}finally {
					close(ps);
					close(con);
				}
		
	}

	@Override
	public void update(EmployeeVo v) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		try {
		con = getConnection();
		ps = con.prepareStatement(Sql.updateEmployee);
		ps.setString(1, v.getEname());
		ps.setString(2, v.getPosition());
		ps.setString(3, v.getRidespart());
		ps.setDouble(4, v.getSalary());
		ps.setString(5, v.getRegdate());
		ps.setString(6, v.getEid());
		ps.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(ps);
			close(con);
		}
		
	}

	@Override
	public void delete(String k) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		try {
		con = getConnection();
		ps = con.prepareStatement(Sql.deleteEmployee);
		ps.setString(1, k);
		int result = ps.executeUpdate();
		if(result != 1) {
			throw new Exception("삭제 항목이 없습니다.");
		}
		}catch(Exception e) {
			throw e;
		}finally {
			close(ps);
			close(con);
		}
		
	}

	@Override
	public EmployeeVo select(String k) throws Exception {
		
		EmployeeVo emp = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
		con = getConnection();
		ps = con.prepareStatement(Sql.selectEmployee);
		ps.setString(1, k);
		rs=ps.executeQuery();
		rs.next();
		String eid = rs.getString("eid");
		String ename = rs.getString("ename");
		String position = rs.getString("position");
		String ridespart = rs.getString("ridespart");
		double Salary = rs.getDouble("Salary");
		String regdate = rs.getString("regdate");
		emp = new EmployeeVo(eid,ename,position,ridespart,Salary,regdate);
		}catch(Exception e) {
			throw e;
		}finally {
			close(rs);
			close(ps);
			close(con);
		}
		
		return emp;
	}

	@Override
	public List<EmployeeVo> select() throws Exception {
		List<EmployeeVo> elist = new ArrayList<EmployeeVo>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			ps = con.prepareStatement(Sql.selectALLEmployee);
			rs=ps.executeQuery();
			while(rs.next()) {
			String eid = rs.getString("eid");
			String ename = rs.getString("ename");
			String position = rs.getString("position");
			String ridespart = rs.getString("ridespart");
			double Salary = rs.getDouble("Salary");
			String regdate = rs.getString("regdate");
			EmployeeVo emp = new EmployeeVo(eid,ename,position,ridespart,Salary,regdate);
			elist.add(emp);
			}
			}catch(Exception e) {
				throw e;
			}finally {
				close(rs);
				close(ps);
				close(con);
			}
		
		return elist;
	}

}
```

### frame 패키지
```java
package frame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class Dao<K,V> {
	
	// MySQL Connect
	String url = "jdbc:mysql://172.30.1.41:3306/amusementparkdb?serverTimezone=Asia/Seoul";
	String mid = "admin1";
	String mpwd = "111111";
	
	
	public Dao() {
	// MySQL JDBC Driver Loading
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("MySQL JDBC Driver Loading.....");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	}
	
	public Connection getConnection() throws SQLException { // 던진다.
		Connection con = null;
		con = DriverManager.getConnection(url,mid,mpwd);
		return con;
	}
	
	public void close(Connection con) {
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
    public void close(PreparedStatement ps) {
    	if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    public void close(ResultSet rset) {
    	if(rset != null) {
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public abstract void create(V v) throws Exception;
    public abstract void update(V v) throws Exception;
    public abstract void delete(K k) throws Exception;
    public abstract V select(K k) throws Exception;
    public abstract List<V> select() throws Exception;
    

}

```
```java
package frame;

public class Sql { 
	
	// 직원 sql 구문
	public static String createEmployee = "INSERT INTO Employee VALUES (?,?,?,?,?,?)";
	public static String deleteEmployee = "DELETE FROM Employee WHERE eid=?";
	public static String updateEmployee = "UPDATE Employee SET ename=?, position=?, ridespart=?, Salary=?, regdate=? WHERE eid=?";
	public static String selectEmployee = "SELECT * FROM Employee WHERE eid = ?";
	public static String selectALLEmployee = "SELECT * FROM Employee";
	
}

```

### test 패키지
```java
```
```java
```
```java
```
```java
```
```java
```

### vo 패키지