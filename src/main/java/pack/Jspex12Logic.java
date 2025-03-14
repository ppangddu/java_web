package pack;

public class Jspex12Logic {
	// 총점과 평균 등을 계산하는 business logic. 클래스
	private JspEx12FormBean formBean;
	
	public void setFormBean(JspEx12FormBean formBean) {
		this.formBean = formBean;
	}
	
	public int getTot() {
		return formBean.getPrice() - formBean.getSaleprice();
	}
	
}
