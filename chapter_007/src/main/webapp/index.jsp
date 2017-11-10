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
<html>
<head>
</head>
<body>
<form>
    Name: <input type='text' name='name'>
    Login: <input type='text' name='login'>
    e-mail: <input type='text' name='email'>
    <p>
        <input type='submit' value='Add' formaction="<%=request.getContextPath()%>/mainPage/insertUser" formmethod='post'>
        <input type='submit' value='Delete' formaction="<%=request.getContextPath()%>/mainPage/deleteUser" formmethod='post'>
        <input type='submit' value='Update' formaction="<%=request.getContextPath()%>/mainPage/updateUser" formmethod='post'>
    </p>
</form>
<table>
    <% AdvancedUserStore users = AdvancedUserStore.getInstance(); %>
    <% int i = 0; %>
    <% try { for(User user : users.getAllUsers()) { %>
    <%i++;%>
    <tr>
        <td><%=i + ". " + user.stringForm()%></td>
    </tr>
    <%}%>
    <%} catch (SQLException e) {
        response.sendError(500);
    }%>


</table>
</body>
</html>
