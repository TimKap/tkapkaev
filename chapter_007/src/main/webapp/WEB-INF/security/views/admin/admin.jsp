<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 16.11.2017
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

</head>
<body>
<table>
    <tr>
        <td>Role :</td>
        <td><c:out value="${role}"/></td>
    </tr>
    <tr>
        <td>Login :</td>
        <td><c:out value="${login}"/></td>
    </tr>
    <tr>
        <td>Name :</td>
        <td><c:out value="${name}"/></td>
    </tr>
    <tr>
        <td>Email :</td>
        <td><c:out value="${email}"/></td>
    </tr>
</table>
<form>
    <input type='submit' value='Выход' formaction="${pageContext.servletContext.contextPath}/admin/exit" formmethod='post'>
    <input type='submit' value='Редактор пользователей' formaction="${pageContext.servletContext.contextPath}/admin/userRedactor" formmethod='get'>
</form>
</body>
</html>
