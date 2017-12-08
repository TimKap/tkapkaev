<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 18.11.2017
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <style>
        .myH {
            font-family: "Times New Roman";
            color: red
        }

        .table.table-bordered > tbody > tr > td,
        .table.table-bordered > thead > tr > th {
            border: 2px solid black;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
            integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
            integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
            crossorigin="anonymous"></script>


    <script  src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/userRedactor.js"></script>
    <script>
        $(document).ready(function() {loadUserTable()});
        $(document).ready(function() {selectRole()});
        $(document).ready(function() {selectCountry()});
    </script>

</head>
<body>


<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
        integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
        crossorigin="anonymous"></script>
<div class="container">

    <h1 class="myH">Редактор пользователей</h1>

    <form id="myForm">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label>Login</label>
                <input type="text" class="form-control" name="login" placeholder="login">
            </div>
            <div class="form-group col-md-6">
                <label>Password</label>
                <input type="text" class="form-control" name="password" placeholder="password">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-4">
                <label>Name</label>
                <input type="text" class="form-control" name="name" placeholder="name">
            </div>
            <div class="form-group col-md-4">
                <label>Email</label>
                <input type="email" class="form-control" name="email" placeholder="email">
            </div>
            <div class="form-group col-md-4">
                <label>Role</label>
                <select id="select_role" class="form-control" name="role" onfocus="selectRole()" >

                </select>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label>City</label>
                <select id="select_city" class="form-control" name="city">
                </select>
            </div>
            <div class="form-group col-md-6">
                <label>Country</label>
                <select id="select_country" class="form-control" name="country" onfocus="selectCountry()" onchange="selectCity()">

                </select>
            </div>
        </div>
        <div class="d-flex">
            <div class="p-2">
                <input type='button' class="btn btn-primary" value='Добавить' onclick="insertUserAjax(form)">
            </div>
            <div class="p-2">
                <input type='button' class="btn btn-primary" value='Обновить' onclick="updateUserAjax(form)">
            </div>
            <div class="p-2">
                <input type='button' class="btn btn-warning" value='Удалить' onclick="deleteUserAjax(form)">
            </div>
        </div>
    </form>
    <h1 class="myH">Таблица пользователей</h1>


    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th scope="col" class="text-center">login</th>
            <th scope="col" class="text-center">name</th>
            <th scope="col" class="text-center">email</th>
            <th scope="col" class="text-center">date</th>
            <th scope="col" class="text-center">role</th>
            <th scope="col" class="text-center">password</th>
            <th scope="col" class="text-center">city</th>
            <th scope="col" class="text-center">country</th>
        </tr>
        </thead>
        <tbody id="us">
        <%--<c:forEach items="${users}" var="user">--%>
            <%--<tr>--%>
                <%--<td><c:out value="${user.getLogin()}"></c:out></td>--%>
                <%--<td><c:out value="${user.getName()}"></c:out></td>--%>
                <%--<td><c:out value="${user.getEmail()}"></c:out></td>--%>
                <%--<td><c:out value="${user.dateString()}"></c:out></td>--%>
                <%--<td><c:out value="${user.getRole()}"></c:out></td>--%>
                <%--<td><c:out value="${user.getPassword()}"></c:out></td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
        </tbody>
    </table>
</div>
</body>
</html>
