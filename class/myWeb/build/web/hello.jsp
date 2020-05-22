<%-- 
    Document   : hello
    Created on : 2020/5/21, 下午 04:30:07
    Author     : Admin
--%>

<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World JSP!</h1>
        <h2><%=LocalDate.now() %></h2>
        <h3>servlet DateTime ${ currentDateTime}</h3>
        <hr/>
        
        <table border="1">
            <tr>
                <th>姓名</th>
                <th>EMAIL</th>
                <th>城市</th>
                <th>電話</th>
            </tr>
            <c:forEach var="custs" items="${custs}">
                <tr>
                    <td>${custs.name}</td>
                    <td>${custs.email}</td>
                    <td>${custs.city}</td>
                    <td>${custs.phone}</td>
                </tr>
            </c:forEach>
            
        </table>
        
    </body>
</html>
