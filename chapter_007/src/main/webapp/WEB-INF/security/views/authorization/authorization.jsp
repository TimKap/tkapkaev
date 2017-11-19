<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 17.11.2017
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title></title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red" >
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/authorization" method="post">
    <table>
        <tr>
            <td>Login:</td><td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td> Password:</td> <td><input type="password" name="password"></td>
        </tr>
    </table>
    <input type="submit" value="Вход">
</form>

</body>
</html>
