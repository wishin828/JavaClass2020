<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${param.logout != null}">
    ${pageContext.request.logout()}
    ${pageContext.session.invalidate()}
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='UTF-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1.0'>
        <link rel='stylesheet' type='text/css' href='/pm/css/pm.css'>
        <title>Product Management</title>
    </head>
    <body>
        <header class='header'>Product Management</header>
        <nav class='nav'>
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal != null}">
                    <c:set var="user">${pageContext.request.userPrincipal.name}</c:set>
                    <c:if test="${pageContext.request.isUserInRole('employee') || pageContext.request.isUserInRole('customer')}">
                        <div><a href='/pm/faces/Search.xhtml'>JSF Product Search</a></div>
                        <div><a href='/pm/ProductSearch.html'>Product Search</a></div>
                    </c:if>
                    <script>
                        function openChat() {
                            var chatWindow = window.open('Chat.html', '_blank', height = 500, width = 500);
                        }
                    </script>
                    <div class="field">
                        <input type='button' value="Open Chat Window" onclick="openChat()" >
                    </div>
                    <div><a href='/pm/index.jsp?logout'>Logout</a></div>
                </c:when>
                <c:otherwise>
                    <div><a href='/pm/Login.html'>Login</a></div>
                </c:otherwise>
            </c:choose>        
        </nav>
        <section class='content'>                   
            <div>Welcome ${user}</div>
        </section>
        <footer class='footer'>Product Management Application</footer>
    </body>
</html>
