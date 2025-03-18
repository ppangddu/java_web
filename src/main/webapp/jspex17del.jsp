<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String code = request.getParameter("code");
%>

<jsp:useBean id="dbConnPooling2" class="pack.DbConnPooling2" />


<%
if (dbConnPooling2.deleteData(code))
	response.sendRedirect("jspex17dbcp.jsp"); // 삭제 후 목록보기
else
	response.sendRedirect("jspex17fail.html"); // 수정 실패 시 이동 페이지
%>