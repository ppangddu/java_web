package pack;

public class Jspex11Logic {
	// 총점과 평균 등을 계산하는 business logic. 클래스
	private JspEx11FormBean formBean;
	
	public void setFormBean(JspEx11FormBean formBean) {
		this.formBean = formBean;
	}
	
	public int getTot() {
		return formBean.getKor() + formBean.getEng();
	}
	
	public double getAvg() {
		double imsi = getTot() / 2.0;
		return imsi;
	}
}
