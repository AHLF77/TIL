public class CBT220305Ch0473 {
    static void rs(char a[]) {
		for(int i = 0; i< a.length;i++)
			if(a[i]== 'B') { // 해당 배열이 문자 'B'와 일치 시, 아래 'C'문자를 반환한다.
				a[i] = 'C';
		       System.out.println(a[i]);
				}
			else if (i==a.length-1) {
				a[i]=a[i-1];// 문자열 갯수와 interger i가 같을 경우, 그 전에 출력한 문자를 출력
				System.out.println(a[i]);
			}
				else {
				a[i]= a[i+1]; // 위의 2 사항이 맞지 않을 경우, 다음 배열에 있는 문자를 출력
				System.out.println(a[i]);
				}
	}
	static void pca(char a[]) {
		for(int i = 0; i< a.length;i++)
		System.out.print(a[i]);
//		System.out.println();
	}
	public static void main(String[] args) {
		char c[] = {'A','B','D','D','A','D','F'};
		rs(c);
		pca(c);
	}
}
