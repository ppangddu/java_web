<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    // 세션 읽기
    if (session.getAttribute("userid") != null) {
        // 사용자가 로그인하고 이 파일을 호출한 경우
%>
<h2>축하합니다. 인가(Authorization)에 성공했어요.</h2>
<pre>
준비된 
쇼핑, 
게시판, 
회의 참여 등의 작업을 할 수 있어요.
</pre>
<a href="jspex8logout.jsp">로그아웃</a>
<%
    } else {
        // 사용자가 로그인하지 않고 이 파일을 호출한 경우
        response.sendRedirect("jspex8login.html");
    }
%>
</body>
</html>
