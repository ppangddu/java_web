package pack;

public class Jspex9Gugudan { // 비즈니스 로직을 클래스, jsp에서 포함 관계로 호출됨
	// 싱글톤 패턴 코드
	private static Jspex9Gugudan gugudan = new Jspex9Gugudan();
	
	public static Jspex9Gugudan getInstance() {
		return gugudan;
	}
	
	public int[] computeGugudan(int dan) {
		int[] gu = new int[9];
		for (int i = 1; i < 10; i++) {
			gu[i - 1] = dan * i;
		}
		return gu;
	}
}
