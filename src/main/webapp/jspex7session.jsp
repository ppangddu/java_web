<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true" 
    %>
    
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");

if (!id.equalsIgnoreCase("aa")) {
	response.sendRedirect("jspex7main.html");
}
// request.setAttribute("mykey", id); // 현재 jsp에서만 유요한 객체, 다른 jsp에서 사욯하려면 전송 필요
session.setAttribute("mykey", id); //session : 내장객체 - 로그인 한 사용자에 한해 모든 jsp 사용 가능
session.setMaxInactiveInterval(10); // 유효시간 기본값은 30분

// application.setAttribute("mykey", id); // 로그인과 상관없이 모든 사용자에게 public
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>세션 연습</h2>
좋아하는 영화는?
<form action="jspex7show.jsp"> <!-- method는 안 쓰면 get이다. -->
	<input type="radio" name="movie" value="미키17" checked="checked">미키17&nbsp;&nbsp;&nbsp;
	<input type="radio" name="movie" value="백설공주">백설공주&nbsp;&nbsp;&nbsp;
	<input type="radio" name="movie" value="타짜">타짜&nbsp;&nbsp;&nbsp;
	<br><br>
	<input type="submit" value="결과보기">
	</form>
</body>
</html>