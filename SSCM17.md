# MySQL연동을 위한 JDBC Programming과 OOP개념을 적용한 시스템 구축

## 7조 구성원: 김O준, 한O정, 강O성, 유O아

### 놀이동산 관리 프로그램
1. MySQL 데이터베이스 서버에 두 개의 테이블을 구축한다. 
2. 각 테이블의 CRUD SQL문을 작성 한다
3. Java Application을 이용하여 CRUD 프로그램을 작성한다
4. VO(Value Object)를 설계 한다. 
5. 각 테이블의 CRUD 기능을 설계 한다. 
6. 각 테이블의 DAO(Data Access Object)를 구현한다. 
7. CRUD 기능에 대한 테스트 프로그램을 작성 한다. 
8. Java Application을 구현 하여 각 기능을 통합 테스트 한다.

#### MySQL
```sql
DROP TABLE Employee;
DROP TABLE Arpart;

CREATE TABLE Employee(
	eid VARCHAR(50) primary KEY,
	ename VARCHAR(50),
    position VARCHAR(50),
    ridespart VARCHAR(50),
    Salary Double,
    regdate VARCHAR(30)
);

CREATE TABLE Arpart(
	apid VARCHAR(30),
	apname VARCHAR(30),
    installdate VARCHAR(30),
    recentdate VARCHAR(30)
);

-- CRUD
-- Create
INSERT INTO Employee VALUES ('l7824', '이순신', '매니저', '롤러코스터', 3500000, '2019-08-05'); 
INSERT INTO Employee VALUES ('u7824', '을지문덕', '과장', '바이킹', 4500000, '2016-09-01'); 
INSERT INTO Employee VALUES ('k7824', '강감찬', '대리', '회전목마', 4000000, '2018-07-05'); 

INSERT INTO Arpart VALUES ('R185374', '롤러코스터','2010-08-03','2021-09-06');
INSERT INTO Arpart VALUES ('B234868', '바이킹','2010-09-03','2021-10-10');
INSERT INTO Arpart VALUES ('H894653', '회전목마','2010-10-03','2021-11-15'); 

-- Update
UPDATE Employee SET ename='을지문덕', position='과장', ridespart='후룸라이드', Salary=4500000, regdate='2016-09-01' WHERE eid='u7824';  

UPDATE Arpart SET apname='후룸라이드',  installdate='2010-08-07',  recentdate= '2021-10-01' WHERE apid='B234868'; 

-- Delete
DELETE FROM Employee WHERE eid='k7824';

DELETE FROM Arpart WHERE apid='B234868';

-- Read
SELECT * FROM Employee;
SELECT * FROM Employee WHERE eid = 'l4962';

SELECT * FROM Arpart;
SELECT * FROM Arpart WHERE apid = 'H894653';
```

#### app package
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

import dao.ArpartDao;
import dao.EmployeeDao;
import frame.Dao;
import vo.ArpartVo;
import vo.EmployeeVo;

public class ApUI {

   Dao<String,EmployeeVo> dao1;
   Dao<String,ArpartVo> dao2;
   java.util.List<EmployeeVo> elist1;
   java.util.List<ArpartVo> elist2;
   
   Frame f; 
   Button b1, b2, b3, b4, b5, b6, b7, b8; 
   Panel p1, p2, p3, p4; 
   Panel main;
   TextField tf1, tf2, tf3, tf4, tf5, tf6, tf8, tf9, tf10, tf11; 
   TextField maintf; 
   List list1, list2; 
   Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl8, lbl9, lbl10, lbl11; 
   
   public ApUI() {
      
      dao1 = new EmployeeDao();
      dao2 = new ArpartDao();
      init();
      make();
      addEvent();
   }
   
    private void init() {
      f = new Frame("놀이동산 직원 관리 프로그램");
      b1 = new Button("직원 등록");
      b2 = new Button("직원 조회");
      b3 = new Button("놀이기구 등록");
      b4 = new Button("놀이기구 조회");
      b5 = new Button("직원 삭제");
      b6 = new Button("놀이기구 삭제");
      b7 = new Button("직원 업데이트");
      b8 = new Button("놀이기구 업데이트");
      p1 = new Panel();
      p2 = new Panel();
      p3 = new Panel();
      p4 = new Panel();

      
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
      
      lbl8 = new Label("놀이기구ID"); 
      tf8 = new TextField();
      lbl9 = new Label("놀이기구명"); 
      tf9 = new TextField();
      lbl10 = new Label("등록날짜"); 
      tf10 = new TextField();
      lbl11 = new Label("최근점검날짜"); 
      tf11 = new TextField();
      
      list1 = new List();
      list2 = new List();
      
      
      
   }

   private void make() {
      
      p1.setBackground(Color.gray); // 백그라운드 색 회색으로 변경.
      p1.setLayout(null);// 패널 p1의 배치관리자 제거
      
      // 직원ID 입력값
      p1.add(lbl1);
      lbl1.setBounds(20, 10, 80, 20);//x : x 좌표, y : y 좌표, w : 가로 크기, h : 세로 크기
      p1.add(tf1);
      tf1.setBounds(120, 10, 120, 20);
      
      // 직원 이름 입력값
      p1.add(lbl2);
      lbl2.setBounds(20, 40, 80, 20);
      p1.add(tf2);
      tf2.setBounds(120, 40, 120, 20);
      
      // 직책 입력값
      p1.add(lbl3);
      lbl3.setBounds(20, 70, 80, 20); 
      p1.add(tf3);
      tf3.setBounds(120, 70, 120, 20);
      
      // 놀이기구 부서 입력값
      p1.add(lbl4);
      lbl4.setBounds(20, 100, 100, 20);
      p1.add(tf4);
      tf4.setBounds(120, 100, 120, 20);
      
      // 연봉 입력값
      p1.add(lbl5);
      lbl5.setBounds(20, 130, 80, 20);
      p1.add(tf5);
      tf5.setBounds(120, 130, 120, 20);
      
      // 입사날짜 입력값
      p1.add(lbl6);
      lbl6.setBounds(20, 160, 80, 20);
      p1.add(tf6);
      tf6.setBounds(120, 160, 120, 20);
      
      // 직원 등록 버튼
      p1.add(b1);
      b1.setBounds(120, 190, 120, 30);
      
      // 직원 업데이트 버튼
      p1.add(b7);
      b7.setBounds(120, 230, 120, 30);
      
      // 직원 삭제 버튼
      p1.add(b5);
      b5.setBounds(120, 270, 120, 30);
      
      
      p2.setBackground(Color.yellow);
      p2.setLayout(null);// 패널 p2의 배치관리자 제거
      
      // 놀이기구ID 입력값
      p2.add(lbl8);
      lbl8.setBounds(20, 10, 80, 20);
      p2.add(tf8);
      tf8.setBounds(120, 10, 120, 20);
      
      // 놀이기구명 입력값
      p2.add(lbl9);
      lbl9.setBounds(20, 40, 80, 20);
      p2.add(tf9);
      tf9.setBounds(120, 40, 120, 20);
      
      // 등록날짜 입력값
      p2.add(lbl10);
      lbl10.setBounds(20, 70, 80, 20);
      p2.add(tf10);
      tf10.setBounds(120, 70, 120, 20);
      
      // 최근점검날짜 입력값
      p2.add(lbl11);
      lbl11.setBounds(20, 100, 80, 20);
      p2.add(tf11);
      tf11.setBounds(120, 100, 120, 20);
      
      // 놀이기구 등록 버튼
      p2.add(b3);
      b3.setBounds(120, 130, 120, 30);
      
      // 놀이기구 업데이트 버튼
      p2.add(b8);
      b8.setBounds(120, 170, 120, 30);
      
      // 놀이기구 삭제 버튼
      p2.add(b6);
      b6.setBounds(120, 210, 120, 30);
      
      p3.setLayout(new BorderLayout());
      p3.add(list1,"Center");
      p3.add(b2,"South");
      
      p4.setLayout(new BorderLayout());
      p4.add(list2,"Center");
      p4.add(b4,"South");
      
      
      main.setLayout(new GridLayout(1, 2));
      main.add(p1);
      main.add(p2);
      main.add(p3);
      main.add(p4);
      
      f.add(main,"Center");
      f.add(maintf,"South");
      
      f.setSize(1300, 800);
      f.setVisible(true);
        
      
   }
    
    private void addEvent() {
       list1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int t1 = list1.getSelectedIndex();
            EmployeeVo emp1 = elist1.get(t1);
            String s = emp1.getEid()+" "+emp1.getEname()+" "+emp1.getPosition()+" "+emp1.getRidespart()+" "+emp1.getSalary()+" "+emp1.getRegdate();
            maintf.setText(s);
         }
      });
       
       list2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int t2 = list2.getSelectedIndex();
            ArpartVo ap = elist2.get(t2);
            String s = ap.getApid()+" "+ap.getApname()+" "+ap.getInstalldate()+" "+ap.getRecentdate()+" ";
            maintf.setText(s);
         }
      });
      
      b1.addActionListener(new ActionListener() { // 직원 insert
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
               dao1.create(v);
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
      
      b7.addActionListener(new ActionListener() { //직원 update
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
            dao1.update(v);
            maintf.setText(eid + " Update OK");   
         } catch (Exception e1) {
            maintf.setText("Update Error .... !!!" + e1.getMessage());
         }
         }
      });
      
      b5.addActionListener(new ActionListener() { //직원 delete
         @Override
         public void actionPerformed(ActionEvent e) {
            String eid = tf1.getText();
            try {
               dao1.delete(eid);
               System.out.println("Delete Success");
            } catch (Exception e1) {
               System.out.println(e1.getMessage());
               e1.printStackTrace();
            }
            }
      });
      
      b2.addActionListener(new ActionListener() {// 직원 list
         @Override
         public void actionPerformed(ActionEvent e) {
            try {
               elist1 = dao1.select();
               list1.removeAll();
               for (EmployeeVo c : elist1) {
                  String str = c.getEid()+" "+c.getEname()+" "+c.getPosition()+" "+c.getRidespart()+" "+c.getSalary()+" "+c.getRegdate();
                  list1.add(str);
                  maintf.setText(elist1.size()+": Completed !");
               }
            } catch (Exception e1) {
               e1.printStackTrace();
            }
         }
      });
      
      b3.addActionListener(new ActionListener() { //놀이기구 insert
         @Override
         public void actionPerformed(ActionEvent e) {
            String apid = tf8.getText();
            String apname = tf9.getText();
            String installdate = tf10.getText();
            String recentdate = tf11.getText();
            
            ArpartVo v = new ArpartVo(apid, apname, installdate, recentdate);
            try {
               dao2.create(v);
               tf8.setText("");
               tf9.setText("");
               tf10.setText("");
               tf11.setText("");
         
               maintf.setText(apid+"  Insert OK");
               
            } catch (Exception e1) {
               tf8.setText("");
               tf9.setText("");
               tf10.setText("");
               tf11.setText("");
               
               maintf.setText("Insert Error ...  !!!");
            }
         }
      });
      
      b8.addActionListener(new ActionListener() { //놀이기구 update
         @Override
         public void actionPerformed(ActionEvent e) {
            String apid = tf8.getText();
            String apname = tf9.getText();
            String installdate = tf10.getText();
            String recentdate = tf11.getText();
            
            ArpartVo v = new ArpartVo(apid, apname, installdate, recentdate);
            try {
               dao2.update(v);
            } catch (Exception e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      });
      
      b6.addActionListener(new ActionListener() { // 놀이기구 delete
         @Override
         public void actionPerformed(ActionEvent e) {
            String apid = tf8.getText();
            try {
               dao2.delete(apid);
               System.out.println("Delete Success");
            } catch (Exception e1) {
               System.out.println(e1.getMessage());
               e1.printStackTrace();
            }
            }
      });
      
      b4.addActionListener(new ActionListener() {//놀이기구 list   
         @Override
         public void actionPerformed(ActionEvent e) {
            try {
               elist2 = dao2.select();
               list2.removeAll();
               for (ArpartVo d : elist2) {
                  String str = d.getApid()+" "+d.getApname()+" "+d.getInstalldate()+" "+d.getRecentdate()+" ";
                  list2.add(str);
                  maintf.setText(elist2.size()+": Completed !");
               }
            } catch (Exception e1) {
               e1.printStackTrace();
            }
         }
      });
      
      f.addWindowListener(new WindowAdapter() { // 시스템 종료
         @Override
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
         
      });
      
   }
   
 
 

} 
```
```java
package app;

import java.util.List;

import java.util.Scanner;

import dao.ArpartDao;
import frame.Dao;
import vo.ArpartVo;


public class ArpApp {

	public static void main(String[] args) {
		
		Dao<String, ArpartVo> dao = new ArpartDao();
		
		System.out.println("Start...");
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Input cmd(i,d,u,l,s,q): ");
			String cmd = sc.next();
			if(cmd.equals("q")) {
				System.out.println("Bye");
				break;
			}else if(cmd.equals("i")) { // 삽입
				System.out.println("--Input amusement park Ridepart--");
				System.out.println("Input Ridepart id: ");
				String apid = sc.next();
				System.out.println("Input Ridepart name: ");
				String apname = sc.next();
				System.out.println("Input Ridepart Installdate: ");
				String installdate = sc.next();
				System.out.println("Input Ridepart Recentdate: ");
				String recentdate = sc.next();
				
				ArpartVo c = new ArpartVo(apid, apname, installdate, recentdate);
				
				try {
					dao.create(c);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
			}else if(cmd.equals("d")) { // ID를 통한 삭제
				System.out.println("Input amusement park Ridepart ID: ");
				String apid = sc.next();
	
				try {
					dao.delete(apid);
					System.out.println("Delete Success");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
			}else if(cmd.equals("u")) { // 업데이트
				
				System.out.println("Update Ridepart id: ");
				String apid = sc.next();
				System.out.println("Update Ridepart name: ");
				String apname = sc.next();
				System.out.println("Update Ridepart Installdate: ");
				String installdate = sc.next();
				System.out.println("Update Ridepart Recentdate: ");
				String recentdate = sc.next();
				
				ArpartVo e = new ArpartVo(apid, apname, installdate, recentdate);
				try {
					dao.update(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}else if(cmd.equals("l")) {// 전체출력
				List<ArpartVo> list = null;
				try {
					list = dao.select();
					for (ArpartVo c : list) {
						System.out.println(c);
					}
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}else if(cmd.equals("s")) { // ID를 통한 검색
				System.out.println("Input apid value: ");
				String apid = sc.next();
				
				ArpartVo c = null;
				
				try {
					c=dao.select(apid);
					if((c.getApid()).equals(apid)) {
						System.out.println("Success");
						System.out.println(c);
					}else {
						throw new Exception();
					}
				} catch (Exception e) {
					System.out.println("Fail");
				}
			}else {
				System.out.println("wrong text"); // i,d,u,l,s,q를 제외한 문자를 입력하였을 경우
			}
		}
		sc.close();
		System.out.println("End...");

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


public class EmApp {

	public static void main(String[] args) throws Exception {
		
		Dao<String, EmployeeVo> dao = new EmployeeDao();
		
		System.out.println("Start...");
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Input cmd(i,d,u,l,s,q): ");
			String cmd = sc.next();
			if(cmd.equals("q")) {
				System.out.println("Bye");
				break;
			}else if(cmd.equals("i")) {// 삽입
				System.out.println("--Input amusement park Employee--");
				
				System.out.println("Input Employee id: ");
				String eid = sc.next();
				System.out.println("Input Employee name: ");
				String ename = sc.next();
				System.out.println("Input Employee Position: ");
				String position = sc.next();
				System.out.println("Input Employee Ridepart: ");
				String ridespart = sc.next();
				System.out.println("Input Employee Salary: ");
				double Salary = sc.nextDouble();
				System.out.println("Input Employee Register Date: ");
				String regdate = sc.next();
				
				
				EmployeeVo c = new EmployeeVo(eid, ename, position, ridespart,Salary,regdate);
				try {
					dao.create(c);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
			}else if(cmd.equals("d")) {// ID를 통한 삭제
				System.out.println("Input amusement park Employee ID: ");
				String eid = sc.next();
	
				try {
					dao.delete(eid);
					System.out.println("Delete Success");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
			}else if(cmd.equals("u")) {// 업데이트
				
				System.out.println("Update Employee id: ");
				String eid = sc.next();
				System.out.println("Update Employee name: ");
				String ename = sc.next();
				System.out.println("Update Employee Position: ");
				String position = sc.next();
				System.out.println("Update Employee Ridepart: ");
				String ridespart = sc.next();
				System.out.println("Update Employee Salary: ");
				double Salary = sc.nextDouble();
				System.out.println("Update Employee Register Date: ");
				String regdate = sc.next();
				
				EmployeeVo e = new EmployeeVo(eid,ename,position,ridespart,Salary,regdate);
				try {
					dao.update(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}else if(cmd.equals("l")) {// 전체출력
				List<EmployeeVo> list = null;
				try {
					list = dao.select();
					for (EmployeeVo c : list) {
						System.out.println(c);
					}
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}else if(cmd.equals("s")) {// ID를 통한 검색
				System.out.println("Input eid value: ");
				String eid = sc.next();
				
				EmployeeVo c = null;
				
				try {
					c=dao.select(eid);
					if((c.getEid()).equals(eid)) {
						System.out.println("Success");
						System.out.println(c);
					}else {
						throw new Exception();
					}
				} catch (Exception e) {
					System.out.println("Fail");
				}
			}else {
				System.out.println("wrong text");// i,d,u,l,s,q를 제외한 문자를 입력하였을 경우
			}
		}
		sc.close();
		System.out.println("End...");

	}

}

```

#### dao package
```java
package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import frame.Dao;
import frame.Sql;
import vo.ArpartVo;


public class ArpartDao extends Dao<String, ArpartVo> {
	
	@Override
	public void create(ArpartVo v) throws Exception {
		// INSERT 구문
				Connection con2 = null;
				PreparedStatement ps2 = null;
				try {
				con2 = getConnection();
				ps2 = con2.prepareStatement(Sql.createArpart);
				ps2.setString(1, v.getApid());
				ps2.setString(2, v.getApname());
				ps2.setString(3, v.getInstalldate());
				ps2.setString(4, v.getRecentdate());
				ps2.executeUpdate();
				}catch(Exception e) {
					throw new Exception("사용자 입력 오류");
				}finally {
					close(ps2);
					close(con2);
				}
		
	}

	@Override
	public void update(ArpartVo v) throws Exception {
		// UPDATE 구문
		Connection con2 = null;
		PreparedStatement ps2 = null;
		try {
		con2 = getConnection();
		ps2 = con2.prepareStatement(Sql.updateArpart);
		ps2.setString(1, v.getApname());
		ps2.setString(2, v.getInstalldate());
		ps2.setString(3, v.getRecentdate());
		ps2.setString(4, v.getApid());
		ps2.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			close(ps2);
			close(con2);
		}
		
	}

	@Override
	public void delete(String k) throws Exception {
		// DELETE 구문
		Connection con2 = null;
		PreparedStatement ps2 = null;
		try {
		con2 = getConnection();
		ps2 = con2.prepareStatement(Sql.deleteArpart);
		ps2.setString(1, k);
		int result = ps2.executeUpdate();
		if(result != 1) {
			throw new Exception("삭제 항목이 없습니다.");
		}
		}catch(Exception e) {
			throw e;
		}finally {
			close(ps2);
			close(con2);
		}
		
	}

	@Override
	public ArpartVo select(String k) throws Exception {
		// SELECT 구문
		ArpartVo ap = null;
		
		Connection con2 = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		try {
		con2 = getConnection();
		ps2 = con2.prepareStatement(Sql.selectArpart);
		ps2.setString(1, k);
		rs2=ps2.executeQuery();
		rs2.next();
		String apid = rs2.getString("apid");
		String apname = rs2.getString("apname");
		String installdate = rs2.getString("installdate");
		String recentdate= rs2.getString("recentdate");
		ap = new ArpartVo(apid, apname, installdate,recentdate);
		
		}catch(Exception e) {
			throw e;
		}finally {
			close(rs2);
			close(ps2);
			close(con2);
		}
		
		return ap;
	}

	@Override
	public List<ArpartVo> select() throws Exception {
		// ListAll 구문
		List<ArpartVo> elist2 = new ArrayList<ArpartVo>();
		Connection con2 = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		try {
			con2 = getConnection();
			ps2 = con2.prepareStatement(Sql.selectALLArpart);
			rs2=ps2.executeQuery();
			while(rs2.next()) {
			String apid = rs2.getString("apid");
			String apname = rs2.getString("apname");
			String installdate = rs2.getString("installdate");
			String recentdate= rs2.getString("recentdate");
			ArpartVo ap = new ArpartVo(apid, apname, installdate,recentdate);
			elist2.add(ap);
			}
			}catch(Exception e) {
				throw e;
			}finally {
				close(rs2);
				close(ps2);
				close(con2);
			}
		
		return elist2;
	}

}

```
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
		// INSERT 구문
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
		// UPDATE 구문
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
		// DELETE 구문
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
		// SELECT 구문
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
		// ListAll 구문
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

#### frame package
```java
package frame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import vo.EmployeeVo;

public abstract class Dao<K,V> {
	
	// MySQL Connect
	String url = "jdbc:mysql://172.30.1.12:3306/amusementparkdb?serverTimezone=Asia/Seoul";
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
	
	public Connection getConnection() throws SQLException { 
		// 한 번 연결된 객체를 사용한다는 것 
		// 즉, 연결되지 않은 경우에만 연결을 시도하겠다는 의미
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
	public static String createEmployee = "INSERT INTO Employee VALUES (?,?,?,?,?,?)"; // 직원 ID,직원 이름, 직책, 놀이기구 부서, 급여, 등록일을 삽입하는 SQL문
	public static String deleteEmployee = "DELETE FROM Employee WHERE eid=?"; // 직원 아이디를 통한 삭제하는 SQL문 
	public static String updateEmployee = "UPDATE Employee SET ename=?, position=?, ridespart=?, Salary=?, regdate=? WHERE eid=?"; // 현재 직원 정보를 업데이트하기 위한 SQL문
	public static String selectEmployee = "SELECT * FROM Employee WHERE eid = ?"; // 직원 아이디로 조회 하는 SQL문
	public static String selectALLEmployee = "SELECT * FROM Employee"; // 모든 직원을 조회하는 SQL문
	
	// 놀이기구 sql 구문
	public static String createArpart = "INSERT INTO Arpart VALUES (?,?,?,?)"; // 놀이기구 ID, 놀이기구 이름, 놀이기구 설치 날짜, 놀이기구 등록일짜를 삽입하는 SQL문
	public static String deleteArpart = "DELETE FROM Arpart WHERE apid=?"; // 놀이기구 아이디를 통한 삭제하는 SQL문 
	public static String updateArpart = "UPDATE Arpart SET apname=?, installdate=? , recentdate=? WHERE apid=?"; // 현재 놀이기구 정보를 업데이트하기 위한 SQL문
	public static String selectArpart = "SELECT * FROM Arpart WHERE apid = ?"; // 놀이기구 아이디로 조회 하는 SQL문
	public static String selectALLArpart = "SELECT * FROM Arpart"; // 모든 놀이기구를 조회하는 SQL문	

}

```

#### test package
- Create문
```java
package test;

import dao.ArpartDao;
import frame.Dao;
import vo.ArpartVo;


public class CreateArpart {

	public static void main(String[] args) {
		Dao<String, ArpartVo> dao = new ArpartDao();
		
		ArpartVo ap = new ArpartVo("J21894","자이로드롭","2009-08-05","2022-01-15");
		
		try {
			dao.create(ap);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}

```
```java
package test;

import dao.EmployeeDao;

import frame.Dao;
import vo.EmployeeVo;

public class CreateEmployee {

	public static void main(String[] args) {
		Dao<String, EmployeeVo> dao = new EmployeeDao();
		
		EmployeeVo emp = new EmployeeVo("s1289", "신짱구", "대리", "범퍼카", (double) 123486, "2020-08-23");
		
		try {
			dao.create(emp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}

```

- Delete문
```java
package test;

import dao.ArpartDao;
import frame.Dao;
import vo.ArpartVo;


public class DeleteArpart {

	public static void main(String[] args) {
		
		Dao<String, ArpartVo> dao = new ArpartDao();
		
		try {
			dao.delete("R185374");// 놀이기구 아이디를 삽입하여 해당 직원 정보를 DELETE함
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}

```
```java
package test;

import dao.EmployeeDao;
import frame.Dao;
import vo.EmployeeVo;


public class DeleteEmployee {

	public static void main(String[] args) {
		
		Dao<String, EmployeeVo> dao = new EmployeeDao();
		
		try {
			dao.delete("");// 직원 아이디를 삽입하여 해당 직원 정보를 DELETE함
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}

```

- SelectAll문
```java
package test;

import java.util.List;

import dao.EmployeeDao;
import frame.Dao;
import vo.EmployeeVo;


public class SelectAllArpart { // 모든 놀이기구 정보들을 조회하는 테스트

	public static void main(String[] args) {
		Dao<String, EmployeeVo> dao = new EmployeeDao();
		List<EmployeeVo> elist = null;
		try {
			elist = dao.select();
			for(EmployeeVo empVo : elist) {
			System.out.println(empVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}

```
```java
package test;

import java.util.List;

import dao.ArpartDao;
import frame.Dao;
import vo.ArpartVo;

public class SelectAllEmployee { // 모든 직원들을 조회하는 테스트

	public static void main(String[] args) {
		Dao<String, ArpartVo> dao = new ArpartDao();
		List<ArpartVo> elist = null;
		try {
			elist = dao.select();
			for(ArpartVo empVo : elist) {
			System.out.println(empVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}

```

- Select문
```java
package test;

import dao.ArpartDao;
import frame.Dao;
import vo.ArpartVo;


public class SelectArpart {

	public static void main(String[] args) { // 해당 놀이기구 ID를 조회하는 테스트
		Dao<String, ArpartVo> dao = new ArpartDao();
		
		ArpartVo p = null;
		try {
			p = dao.select("H894653");
			System.out.println(p);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

```
```java
package test;

import dao.EmployeeDao;
import frame.Dao;
import vo.EmployeeVo;


public class SelectEmployee {

	public static void main(String[] args) { // 해당 놀이기구 직원을 조회하는 테스트
		Dao<String, EmployeeVo> dao = new EmployeeDao();
		
		EmployeeVo p = null;
		try {
			p = dao.select("u7824");
			System.out.println(p);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

```

- Update문
```java
package test;


import dao.EmployeeDao;
import frame.Dao;
import vo.EmployeeVo;

public class UpdateArpart {

	public static void main(String[] args) { // 해당 직원의 ID를 찾아 정보를 업데이트하는 테스트 
		
		Dao <String,EmployeeVo> dao = new EmployeeDao();
	      
	      EmployeeVo ev = new EmployeeVo("s1289", "신짱구", "과장", "회전그네", (double) 4000000, "2020-08-23");
	      
			try {
				dao.update(ev);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

}
```
```java
package test;


import dao.ArpartDao;
import frame.Dao;
import vo.ArpartVo;

public class UpdateEmployee {

	public static void main(String[] args) { // 해당 놀이기구의 ID를 찾아 정보를 업데이트하는 테스트 
		
		Dao <String,ArpartVo> dao = new ArpartDao();
	      
		ArpartVo ev = new ArpartVo("B234868", "부메랑", "2015-08-09", "2022-03-09");
	      
			try {
				dao.update(ev);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

}
```
