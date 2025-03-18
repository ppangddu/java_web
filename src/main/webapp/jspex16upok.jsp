<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="formBean" class="pack.SangpumFormBean" />
<jsp:setProperty property="*" name="formBean" />
<jsp:useBean id="dbConnPooling" class="pack.DbConnPooling" />

<%
// boolean b = dbConnPooling.updateData(formBean); //자료가 다 formBean에 있으니 formBean을 들고 간다.

if (dbConnPooling.updateData(formBean))
	response.sendRedirect("jspex16dbcp.jsp"); // 수정 후 목록보기
else
	response.sendRedirect("jspex16fail.html"); // 수정 실패 시 이동 페이지
%>