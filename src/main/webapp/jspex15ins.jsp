<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 수신된 입력 자료를 DB 저장 후 상품정보 보기 창으로 이동
request.setCharacterEncoding("utf-8");
// String sang = request.getParameter("sang"); 
// 폼빈 사용
%>
<jsp:useBean id="DbConnPaging" class="pack.DbConnPaging" />

<%-- 데이터를 받는 페이지 --%>
<jsp:useBean id="formBean" class="pack.SangpumFormBean" />
<jsp:setProperty property="*" name="formBean" />
<%-- 자바에서도 입력 자료 검사 권장 --%>
<%
if (formBean.getSang().equals("") || formBean.getSang() == null) { // sang, su, dan, 수량과 단가는 숫자만 허용
	response.sendRedirect("jspex15insert.html");
	return;
}

DbConnPaging.sangpumInsert(formBean);

// 상품 추가 후 목록보기로 이동 : forward 하면 안 됨
// request.getRequestDispatcher("jspex15paging.jsp").forward(request, response); //forwarding

// 상품 수정, 추가, 삭제 후 목록보기로 이동 : redirect
response.sendRedirect("jspex15paging.jsp");
%>