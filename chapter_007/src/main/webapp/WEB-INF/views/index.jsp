<%@ page import="ru.job4j.servletsform.AdvancedUserStore" %>
<%@ page import="ru.job4j.firstservlets.User" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 10.11.2017
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<body>
<form>
    Name: <input type='text' name='name'>
    Login: <input type='text' name='login'>
    e-mail: <input type='text' name='email'>
    <p>
        <input type='submit' value='Add' formaction="${pageContext.servletContext.contextPath}/insertUser" formmethod='post'>
        <input type='submit' value='Delete' formaction="${pageContext.servletContext.contextPath}/deleteUser" formmethod='post'>
        <input type='submit' value='Update' formaction="${pageContext.servletContext.contextPath}/updateUser" formmethod='post'>
    </p>
</form>
<table style="border: 1px solid black;", cellpadding="1", cellspacing="1", border="1">

    <tr>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>date</th>
    </tr>
    <c:forEach items="${users}" var="user">


    <tr>
        <td> <c:out value="${user.name}"></c:out> </td>
        <td> <c:out value="${user.login}"></c:out> </td>
        <td> <c:out value="${user.email}"></c:out> </td>
        <td> <c:out value="${user.dateString()}"></c:out> </td>
    </tr>
    </c:forEach>


</table>
</body>
</html>
