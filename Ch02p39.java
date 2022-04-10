public class Ch02p39 {
    public static void main(String[] args) {
		byte b1 = 127;
		System.out.println(b1);
		
// A에 대한 유니코드 및 이진수        
//		char c1 = 'A';
//		char c1 = 65;
		char c1 = '\u0041'; // unicode
		System.out.println(c1);
		
//		char c2 = '\uAC00';
		char c2 = 44032;
		System.out.println(c2);
	}

}
