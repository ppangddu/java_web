<%@page import="pack.Jspex9Gugudan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<b>현재 내 능력으로 할 수 있는 방법</b>
<%
int dan = Integer.parseInt(request.getParameter("dan"));
out.println(dan + "단 출력<br>");

// Jspex9Gugudan gugudan = new Jspex9Gugudan(); // 클래스의 포함 관계 : 객체가 클래스의 요청 수만큼 계속 생성됨
Jspex9Gugudan gugudan = Jspex9Gugudan.getInstance(); // 클래스의 포함 관계 : 싱글톤 패턴 적용

int[] result = gugudan.computeGugudan(dan);
for (int a = 0; a < result.length; a++) {
	out.println(dan + "*" + (a + 1) + "=" + result[a] + "&nbsp;&nbsp;<br>");
}
%>
<hr>
<b>beans를 사용하는 방법</b>
<br>
<jsp:useBean id="gugudan2" class="pack.Jspex9Gugudan"/>
<%
int[] result2 = gugudan2.computeGugudan(dan);
for (int a = 0; a < result2.length; a++) {
	out.println(dan + "*" + (a + 1) + "=" + result2[a] + "&nbsp;&nbsp;<br>");
}
%>
</body>
</html>