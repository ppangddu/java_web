package pack;

public class JspEx12FormBean {
	// 클라이언트로부터 수신되는 값이 복수일 경우 이를 한꺼번에 처리하기 위한 클래스, 폼빈을 생성 가능
	// 생성 조건 : 클라이언트의 querystring(?변수=값&변수=값...)에서 사용하는 매개변수명과 멤버 필드의 이름은 일치시킨다. 
	private String name;
	private int price;
	private int saleprice;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getSaleprice() {
		return saleprice;
	}
	
	public void setSaleprice(int saleprice) {
		this.saleprice = saleprice;
	}
}
