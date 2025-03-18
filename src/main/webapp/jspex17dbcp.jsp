<%@page import="pack.GuestDto"%>
<%@page import="pack.SangpumDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="dbConnPooling2" class="pack.DbConnPooling2"/>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>방명록</title>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Nanum Gothic', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            color: #333;
        }

        header {
            background-color: #4CAF50;
            padding: 15px;
            text-align: center;
            color: white;
        }

        header h1 {
            margin: 0;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        a {
            text-decoration: none;
            color: #4CAF50;
        }

        a:hover {
            color: #45a049;
        }

        .btn-container {
            text-align: center;
            margin: 20px;
        }

        .btn {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            text-align: center;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none;
        }

        .btn:hover {
            background-color: #45a049;
        }

        footer {
            text-align: center;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            position: fixed;
            width: 100%;
            bottom: 0;
        }
    </style>
    <!-- 
    <script type="text/javascript">
        function funcUpdate() {
            let code = prompt("수정할 코드 입력", "");
            if (code !== "" && code !== null) {
                location.href = "jspex17up.jsp?code=" + code;
            }
        }

        function funcDelete() {
            let code = prompt("삭제할 코드 입력", "");
            if (code !== "" && code !== null) {
                if (confirm("정말 삭제할까요?")) {
                    location.href = "jspex17del.jsp?code=" + code;
                }
            }
        }
    </script>
     -->
</head>
<body>
<header>
    <h1>방명록</h1>
</header>

<div class="btn-container">
    <a href="jspex17ins.html" class="btn">글쓰기</a>
</div>

<table>
    <tr>
        <th>코드</th>
        <th>작성자</th>
        <th>제목</th>
        <th>내용</th>
    </tr>
    <%
        ArrayList<GuestDto> slist = dbConnPooling2.getDataAll();
        for(GuestDto s : slist) {
    %>
    <tr>
        <td><a href="jspex17del.jsp?code=<%= s.getCode() %>" onclick="return confirm('정말 삭제하시겠습니까?');"><%= s.getCode() %></a></td>
        <td><a href="jspex17up.jsp?code=<%= s.getCode() %>"><%= s.getWriter() %></a></td>
        <td><%= s.getTitle() %></td>
        <td><%= s.getContents() %></td>
    </tr>
    <%
        }
    %>
</table>
<br><br><hr>코드를 누르면 삭제되고 작성자를 누르면 수정됩니다.
<footer>
    <p>&copy; 2025 방명록 시스템</p>
</footer>
</body>
</html>