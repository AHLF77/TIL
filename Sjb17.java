public class Sjb17 {
	public static void main(String[] args) {
        int x = 5, y=0, z=0;
        System.out.println("1단계 x: "+x);
        System.out.println("1단계 y: "+y);
        System.out.println("1단계 z: "+z);
        y = x++;
        System.out.println("2단계 x: "+x);
        System.out.println("2단계 y: "+y);
        System.out.println("2단계 z: "+z);
        z = --x;
        System.out.println("3단계 x: "+x);
        System.out.println("3단계 y: "+y);
        System.out.println("3단계 z: "+z);
        System.out.println(x+","+y+","+z);
    }
   }
