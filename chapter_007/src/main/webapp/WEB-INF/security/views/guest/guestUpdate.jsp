<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 18.11.2017
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/guest/guestUpdate" method="post">
    <table>
        <tr>
            <td>Name:</td><td><input type="text" name="name" value="${name}"></td>
        </tr>
        <tr>
            <td>Email:</td><td><input type="text" name="email" value="${email}"></td>
        </tr>
        <tr>
            <td>Password:</td><td><input type="text" name="password"></td>
        </tr>
    </table>
    <input type="submit" value="Обновить">
</form>
</body>
</html>
