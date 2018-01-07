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
    <title></title>
    <style>
        .backside {
            background: rgba(236, 236, 236, 0.98);
            padding: 100px;
            border-radius: 5px;
            box-shadow: 0 0px 12px rgba(0, 0, 0, 0.74);
            margin-top: 150px;
        }
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
        integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
        crossorigin="anonymous"></script>

<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6 backside">
            <div class="row">
                <div class="col-3"></div>
                <div class="col-7">Информация пользователя</div>
                <div class="col-2"></div>
            </div>
            <div class="d-flex flex-row">
                <div class="p-2">Role:</div>
                <div class="p-2"><c:out value="${role.role}"/></div>
            </div>
            <div class="d-flex flex-row">
                <div class="p-2">Login:</div>
                <div class="p-2"><c:out value="${login}"/></div>
            </div>
            <div class="d-flex flex-row">
                <div class="p-2">Surname:</div>
                <div class="p-2"><c:out value="${surname}"/></div>
            </div>
            <div class="d-flex flex-row">
                <div class="p-2">Street:</div>
                <div class="p-2"><c:out value="${address.street}"/></div>
            </div>
            <div class="d-flex flex-row">
                <div class="p-2">City:</div>
                <div class="p-2"><c:out value="${address.city}"/></div>
            </div>
            <div class="d-flex flex-row">
                <div class="p-2">Country:</div>
                <div class="p-2"><c:out value="${address.country}"/></div>
            </div>
            <form>
                <div class="d-flex flex-row">
                    <div class="p-2"><input type='submit' class="btn btn-primary" value='Выход' formaction="${pageContext.servletContext.contextPath}/testTask/exit" formmethod='post'></div>
                </div>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>


</div>

</body>
</html>