<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 18.11.2017
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form>
    <table>
        <tr>
            <td>Login: </td><td><input type="text" name = "login"></td>
        </tr>
        <tr>
            <td>Name: </td><td><input type="text" name = "name"></td>
        </tr>
        <tr>
            <td>Email: </td><td><input type="text" name = "email"></td>
        </tr>
        <tr>
            <td>Role: </td><td><input type="text" name = "role"></td>
        </tr>
        <tr>
            <td>Password: </td><td><input type="text" name = "password"></td>
        </tr>
    </table>
    <p>
        <input type='submit' value='Add' formaction="${pageContext.servletContext.contextPath}/admin/insertUser" formmethod='post'>
        <input type='submit' value='Delete' formaction="${pageContext.servletContext.contextPath}/admin/deleteUser" formmethod='post'>
        <input type='submit' value='Update' formaction="${pageContext.servletContext.contextPath}/admin/updateUser" formmethod='post'>
    </p>
</form>
<table style="border: 1px solid black;", cellpadding="1", cellspacing="1", border="1">
    <tr>
        <th>login</th>
        <th>name</th>
        <th>email</th>
        <th>date</th>
        <th>role</th>
        <th>password</th>
        <c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.getLogin()}"></c:out></td>
        <td><c:out value="${user.getName()}"></c:out></td>
        <td><c:out value="${user.getEmail()}"></c:out></td>
        <td><c:out value="${user.dateString()}"></c:out></td>
        <td><c:out value="${user.getRole()}"></c:out></td>
        <td><c:out value="${user.getPassword()}"></c:out></td>
    </tr>
    </c:forEach>


</table>
</body>
</html>
