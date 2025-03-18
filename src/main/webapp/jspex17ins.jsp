<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// insert 후 목록보기로 이동
request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="formBean" class="pack.GuestFormBean" />
<jsp:setProperty property="*" name="formBean" />
<jsp:useBean id="dbConnPooling2" class="pack.DbConnPooling2" />

<%
boolean b = dbConnPooling2.insertData(formBean);

if (b) {
	response.sendRedirect("jspex17dbcp.jsp"); // 추가 성공 후 상품 보기
} else {
	response.sendRedirect("jspex17fail.html"); // 추가 실패 시 이동
}
%>
