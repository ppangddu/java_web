package pack;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/irum.go")
public class JspEx5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String data = request.getParameter("data");
		System.out.println("data : " + data);
		// 넘어온 자료로 이런저런 작업(출력이나 DB처리 등등...)을 하다가
		
		// jsp 또는 servlet 파일 호출하기 1: redirect - client를 통해 파일 호출
		// response.sendRedirect("jspex5jsp.jsp?mydata=" + data); // 서버는 클라이언트에게 받은 데이터를 포함한 url로 이동하라고 지시함. data는 문자열만 가능
		
		// jsp 또는 servlet 파일 호출하기 2: forward - server에서 바로 파일 호출
		request.setAttribute("mydata", data); // forward 방식에서 파일 호출 시 값을 주려면 request를 이동
		// request.setAttribute("키", 값); // 값은 자바의 모든 객체 가능
		RequestDispatcher dispatcher = request.getRequestDispatcher("jspex5jsp.jsp"); //jsp는 service 메서드를 오버라이드 하고 있다.
		dispatcher.forward(request, response); // service 메서드엔 argument가 두 개가 있어서 파라미터 2개가 필요한 것이다.
		
	}

}
