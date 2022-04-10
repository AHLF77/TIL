public class  Ch02va2{

	// class variable
	int a = 20;
	
	public static void main(String[] args) { // 이 문장 안에서만 변수로 취급이 된다.
		// local variable
		int a;
		a = 10;
		
		int result;
		result = a + 100;
		System.out.println(result);
	}

}