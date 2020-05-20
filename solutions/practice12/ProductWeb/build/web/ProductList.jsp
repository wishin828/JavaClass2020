<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='UTF-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1.0'>
        <link rel='stylesheet' type='text/css' href='/pm/css/pm.css'>
        <title>Product List</title>
    </head>
    <body>
        <header class='header'>Products</header>
        <nav class='nav'><a href="/pm/ProductSearch.html">Product Search</a></nav>
        <section class='content'>                   
            <c:choose>
                <c:when test="${!pm.errors}">
                    <c:forEach items="${pm.products}" var="p">
                        <div class='data'>
                            <c:choose>
                                <c:when test="${pageContext.request.isUserInRole('employee')}">
                                     <a href="ProductEdit.jsp?p_id=${p.id}">${p.id}</a> 
                                </c:when>
                                <c:otherwise>${p.id}</c:otherwise>
                            </c:choose>
                            ${p.name}
                            ${p.price}
                            ${p.bestBefore}
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class='error'>${pm.status}</div>
                </c:otherwise>
            </c:choose>
        </section>
        <footer class='footer'>Invoker used method ${pageContext.request.method}</footer>
    </body>
</html>
