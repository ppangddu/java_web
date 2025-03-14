<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="pack.JikwonDto"%>
<%@ page import="java.util.Iterator"%>
<jsp:useBean id="dbConn" class="pack.DbConnClass2" scope="page" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>사번</th><th>직원명</th><th>직급</th><th>성별</th>
        </tr>
        <%
            String bName = request.getParameter("name"); // 부서명 파라미터 가져오기
            ArrayList<JikwonDto> list = dbConn.getDataAll(bName); // 부서명으로 필터링된 데이터 가져오기

            int maxPay = Integer.MIN_VALUE; // 최고 연봉을 추적
            int totalEmployees = list.size(); // 총 인원수

            // 최고 연봉을 계산
            for (JikwonDto s : list) {
                if (s.getJikwonpay() != null && !s.getJikwonpay().isEmpty()) {
                    try {
                        int pay = Integer.parseInt(s.getJikwonpay()); // 급여 값을 정수로 변환
                        if (pay > maxPay) {
                            maxPay = pay;
                        }
                    } catch (NumberFormatException e) {
                        // 급여 값이 없거나 잘못된 경우 처리
                    }
                }
        %>
        <tr>
            <td><%= s.getJikwonno() %></td>
            <td><%= s.getJikwonname() %></td>
            <td><%= s.getJikwonjik() %></td>
            <td><%= s.getJikwongen() %></td>
        </tr>
        <%
            }

            // 최고 연봉이 Integer.MIN_VALUE인 경우, 연봉 데이터가 없다는 의미로 처리
            if (maxPay == Integer.MIN_VALUE) {
                maxPay = 0; // 급여 데이터가 없는 경우
            }
        %>
    </table>

    <div>
        <!-- 인원수와 최고 연봉 출력 -->
        <p>인원수: <%= totalEmployees %>명</p>
        <p>최고 연봉: <%= maxPay %>원</p>
    </div>
</body>
</html>
