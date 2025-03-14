<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
//String name = request.getParameter("name"); // 지금까지는 이렇게 함. 이제 빈즈를 사용해 본다.
%>
<jsp:useBean id="formBean" class="pack.JspEx12FormBean" />

<%-- 낱개로 받는다면 이렇게 --%>
<%--
<jsp:setProperty property="name" name="formBean" />
 --%>
<jsp:setProperty property="*" name="formBean" /> <%-- 자동으로 모든 값이 setter를 통해 저장됨 --%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
자료 출력<br>
과일 <jsp:getProperty property="name" name="formBean" />의
정가는 <jsp:getProperty property="price" name="formBean" /><br>
<%-- 할인가는 <jsp:getProperty property="saleprice" name="formBean" /><br> --%>
<%-- 계산을 위한 클래스를 Beans 사용 --%>
<jsp:useBean id="logic" class="pack.Jspex12Logic" />
<jsp:setProperty property="formBean" name="logic" value="<%=formBean %>"/>
구매가격은 <jsp:getProperty property="tot" name="logic" /><br> <!-- getTot 값을 찍는다. -->
</body>
</html>