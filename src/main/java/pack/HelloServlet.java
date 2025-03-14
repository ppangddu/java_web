package pack;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet(urlPatterns = {"/hello", "/HelloServlet"})
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}


	public void destroy() {
		// TODO Auto-generated method stub
	}


	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청 성공");
		
		response.setContentType("text/html;charset=utf-8"); // Mime type과 문자코드 설정
		// MIME TYPE은 인터넷에서 전송되는 다양한 종류의 데이터를 식별하기 위한 형식
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>안녕하세요 서블릿 세상입니다.</h2>");
		out.println("</body></html>");
		out.close(); // 클라이언트 브라우저로 전송
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
