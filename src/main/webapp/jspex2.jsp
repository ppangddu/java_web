<%-- page 지시어 : 현재 문서의 속성이나 정보 등을 선언 또는 지시하는 역할 --%>
<%@page import="java.time.ZoneId"%>
<%@ page 
language="java" 
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDate"
import="java.sql.Connection, java.sql.ResultSet"
session="true"
buffer="8kb"
isThreadSafe="true"
info="jsp 문서 정보 적기"
errorPage="jspex2err.jsp"
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>page 지시어</h2>
현재 날짜 :
<%
LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul")); // 페이지 지시어 안에 임포트를 해준다. 
out.println(now.getYear() + "년" + now.getMonthValue() + "월");
%>
<%= now.getDayOfMonth() + "일" %>
<hr>
<%= 10 / 1 %> <%-- 문법에러, 런타임에러(처음부터 0으로 나눈 경우) 등은 개발자가 수정한다.
하지만 정상적으로 적용하면 페이지가 예상치 못한 에러를 만난 경우는 별도의 화면을 제공해주는 것이 효과적 --%>

</body>
</html>