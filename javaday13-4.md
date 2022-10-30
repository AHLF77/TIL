# 0425-4 로또 프로그램 중간 평가

## lottoapp
### Application
```java
package lottoapp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import lottofunction.Lottonum;
import lottofunction.Reward;


public class Application{

   public static void main(String[] args) {
      Reward m = new Reward();
      Lottonum w = new Lottonum();
      
      DecimalFormat df = new DecimalFormat(",###"); // 랜덤 상금 3자리로 끊기
      Scanner sc = new Scanner(System.in);
      
      int ar[] = new int[6]; // 6개의 입력 배열
      ArrayList<Integer> cn = new ArrayList<Integer>(); // 정답을 담을 배열
      Arrays.toString(w.getWn());
      boolean loop = true; // 무한 반복
      int cnt = 0; // 정답 횟수 변수
      int lc = 0; // 입력 숫자 횟수 조절 변수

      while(loop) {
      try {
         for (int i = 0; i < ar.length; i++) { 
            System.out.println((i+1)+"번째 숫자를 입력하세요.");
            String num = sc.next();
            int n = Integer.parseInt(num);
            ar[i] = n;
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
         
         for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar.length; j++) {
               //wnn배열을 다시 사용.(이유는 wn배열은 랜덤값이므로 계속 바뀌기 때문)
               //따라서 첫번째 랜덤 배열을 입력할 새로운 배열이 필요.
               if(w.getWnn()[i] == ar[j]) {
                  cnt++;
                  cn.add(ar[j]); //정답배열에 정답 숫자 입력
               }
            }
         }
         
      // 오름차순으로 숫자정렬
         Arrays.sort(w.getWnn());
         Arrays.sort(ar);
         Collections.sort(cn); 
         
         System.out.println("당첨번호 : "+Arrays.toString(w.getWnn()));
         System.out.println("입력한 번호 : "+Arrays.toString(ar));
         System.out.println("총 상금 : "+df.format(m.getMoney())+"원");
         
         if(cnt == 6) {
             System.out.println("맞힌 숫자는"+cn+", 맞힌 숫자 갯수는 "+cnt+"개, 1등에 당첨되셨습니다!");
             m.getFirst(); //Reward class 활용
            loop=false; // 결과 발생 후 while문 빠져나감.
          }else if(cnt == 5) {
             System.out.println("맞힌 숫자는 "+cn+", 맞힌 숫자의 갯수는 "+cnt+"개, 2등에 당첨되셨습니다!");
             m.getSecond();
             loop=false;
          }else if(cnt == 4) {
             System.out.println("맞힌 숫자는 "+cn+", 맞힌 숫자의 갯수는 "+cnt+"개, 3등에 당첨되셨습니다!");
             m.getThird();
             loop=false;
          }else {
             System.out.println("맞힌 숫자는 "+cn+", 맞힌 숫자의 갯수 : "+cnt+"개, 꽝입니다...");
             loop=false;
          }
         
      }catch (Exception e) { // 문자 입력시 다시 숫자입력을 위해 return,break,continue 문을 사용하지 않음.
         System.out.println("숫자가 아닙니다. 다시 입력하세요.");
         
      }
   }
   
      sc.close();
   }
}
```

## lottofunction
### Lottonum
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

### Reward
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
			int rr = r.nextInt(10)+1;
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