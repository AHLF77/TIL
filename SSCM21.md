# LottoGame Mini Project

## lottoapp
### Application
```java

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