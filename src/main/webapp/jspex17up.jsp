<%@page import="pack.GuestDto"%>
<%@page import="pack.SangpumDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String code = request.getParameter("code");
%>

<jsp:useBean id="dbConnPooling2" class="pack.DbConnPooling2" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
GuestDto dto = dbConnPooling2.updateDataRead(code);
if (dto == null) { // 자바니까 ==
%>
	<script type="text/javascript">
	alert("등록된 상품 코드가 아닙니다.");
	location.href="jspex17dbcp.jsp";
	</script>
<%
	return;
}
%>

상품 수정<br>
<form action="jspex17upok.jsp" method="post">
코드 : <%=dto.getCode() %><br>
<input type="hidden" name="code" value="<%=dto.getCode() %>">
작성자 : <input type="text" name="writer" value="<%=dto.getWriter() %>"><br> <!-- dto가 자바 안에 소스니까 %안에 넣는 거임 -->
제목 : <input type="text" name="title" value="<%=dto.getTitle() %>"><br>
내용 : <input type="text" name="contents" value="<%=dto.getContents() %>"><br>
<input type="submit" value="자료수정">
<input type="button" value="수정취소" onclick="javascript:location.href='jspex17dbcp.jsp'">
</form>
</body>
</html>