# 0425-2 로또 프로그램 중간 평가
## Java의 기본 문법과 OOP를 적용한 프로그래밍

## 7조 구성원: 김O준, 한O정, 강O성, 유O아

## 로또 프로그램 제작하기
1. 1~45까지의 숫자중 중복되지 않은 6개의 숫자를 배열에 저장 출력한다. 
2. 로또 맞출 숫자 6번 입력받는다. 
3. 입력된 값은 중복시 다시 입력받는다. 
4. 범위에 벗어난 값은 다시 입력받는다. 
5. count 변수 선언 -> 몇개 맞췃는지
6. 각 등수 별 맞춘 숫자의 개수를 장하여 출력 한다

#### lottoapp package
```java
package lottoapp;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import lottofunction.InsertNumber;
import lottofunction.Lottonum;
import lottofunction.Reward;


public class Application{

   public static void main(String[] args) {
	   	  
      Reward m = new Reward();
      Lottonum w = new Lottonum();
      InsertNumber in = new InsertNumber();
      
      DecimalFormat df = new DecimalFormat(",###"); // 랜덤 상금 3자리로 끊기
      Scanner sc = new Scanner(System.in);
      
      int ar[] = new int[6]; // 6개의 입력 배열
      ArrayList<Integer> cn = new ArrayList<Integer>(); // 정답을 담을 배열
      Arrays.toString(w.getWnn());
      boolean loop = true; // 무한 반복
      int cnt = 0; // 정답 횟수 변수
      
      Arrays.toString(w.getWn());
  
      while(loop) {
          try {
             in.getSc();
             
             for (int i = 0; i < in.ar.length; i++) {
                 for (int j = 0; j < in.ar.length; j++) {
                    if(w.getWnn()[i] == in.ar[j]) {
                       cnt++;
                       cn.add(in.ar[j]);
                    }
                 }
              }
             System.out.println("당첨번호 : "+Arrays.toString(w.getWnn()));
             System.out.println("입력한 번호 : "+Arrays.toString(in.ar));
             System.out.println("총 상금 : "+df.format(m.getMoney())+"원");
             
         	Collections.sort(cn);
         	
             if(cnt == 6) {
                System.out.println("맞힌 숫자는"+cn+", 맞힌 숫자 갯수는 "+cnt+"개, 1등에 당첨되셨습니다!");
                m.getFirst();
                
                loop = false;
             }else if(cnt == 5) {
                System.out.println("맞힌 숫자는 "+cn+", 맞힌 숫자의 갯수는 "+cnt+"개, 2등에 당첨되셨습니다!");
                m.getSecond();
                loop = false;
             }else if(cnt == 4) {
                System.out.println("맞힌 숫자는 "+cn+", 맞힌 숫자의 갯수는 "+cnt+"개, 3등에 당첨되셨습니다!");
                m.getThird();
                loop = false;
             }else if(cnt < 4 && cnt > 0) {
                System.out.println("맞힌 숫자는 "+cn+", 맞힌 숫자의 갯수 : "+cnt+"개, 꽝입니다...");
                loop = false;
             }else if(cnt == 0) {
            	 System.out.println("맞힌 숫자의 갯수 : "+cnt+"개, 꽝입니다...");
                 loop = false; 
             }
          }catch (Exception e) {
             System.out.println("숫자가 아닙니다. 다시 입력하세요.");
          }
          }
   }
}
```

#### lottofunction package
```java
package lottofunction;

import java.util.Arrays;
import java.util.Scanner;

public class InsertNumber {
	public Scanner sc = new Scanner(System.in);
    public int ar[] = new int[6];
    public int lc = 0;
    public int cnt = 0;
	public InsertNumber() {
	}
	public InsertNumber(Scanner sc, int[] ar, int lc, int cnt) {
		this.sc = sc;
		this.ar = ar;
		this.lc = lc;
		this.cnt = cnt;
	}
	public Scanner getSc() {
		for (int i = lc; i < ar.length; i++) {
            System.out.println((lc+1)+"번째 숫자를 입력하세요.");
            String num = sc.next();
            ar[i] = Integer.parseInt(num);
            lc++;
            if(ar[i]<0 || ar[i]>45) {
               System.out.println("입력 오류, 1~45까지의 숫자를 입력하세요.");
               i--;
               lc--;
            }
            for (int j = 0; j < i; j++) {
               if(ar[i] == ar[j]) {
                  System.out.println("숫자가 중복되었습니다. 다시 입력하세요.");
                  i--;
                  lc--;
               }
            }
         }
	
		return sc;
	}
	public void setSc(Scanner sc) {
		this.sc = sc;
	}
	public int[] getAr() {
		
		return ar;
	}
	public void setAr(int[] ar) {
		this.ar = ar;
	}
	public int getLc() {
		return lc;
	}
	public void setLc(int lc) {
		this.lc = lc;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}


```
```java
package lottofunction;

import java.util.Arrays;
import java.util.Random;

public class Lottonum{ // 캡슐화
	private int wn[] = new int[6];
	private int wnn[] = new int[6];

	public Lottonum() {
	
	}

	public Lottonum(int[] wn, int[] wnn) {
		this.wn = wn;
		this.wnn = wnn;
	}

	public int[] getWn() {
		for (int i = 0; i < wn.length; i++) {
			Random r= new Random();
			int rr = r.nextInt(45)+1;
			wn[i] = rr;
			for (int j = 0; j < i; j++) {
				if(wn[i] == wn[j]) {
					i--;
				}
			}
		}
		for (int i = 0; i < wnn.length; i++) {
			wnn[i] = wn[i];
		}
		return wn;
	}

	public void setWn(int[] wn) {
		this.wn = wn;
	}

	public int[] getWnn() {
		return wnn;
	}

	public void setWnn(int[] wnn) {
		this.wnn = wnn;
	}


	
	
}

```
```java
package lottofunction;

public class Reward {
	private double r =  Math.random();
	
	private long money = Math.round(r*1000000000)*10;
	private long first = money/100*50;
	private long second = money/100*30;
	private long third = money/100*20;
	
	public Reward() {
		
	}
	
	public Reward(double r, long money, long first, long second, long third) {
		this.r = r;
		this.money = money;
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	public double getR() {
		return r;
	}
	
	public void setR(double r) {
		this.r = r;
	}
	
	public long getMoney() {
		return money;
	}
	
	public void setMoney(long money) {
		this.money = money;
	}
	
	public long getFirst() {
		return first;
	}
	
	public void setFirst(long first) {
		this.first = first;
	}
	
	public long getSecond() {
		return second;
	}
	public void setSecond(long second) {
		this.second = second;
	}
	public long getThird() {
		return third;
	}
	public void setThird(long third) {
		this.third = third;
	}
}
```