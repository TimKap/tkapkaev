<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 06.01.2018
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <style>
        form {
            background: rgba(236, 236, 236, 0.98);
            padding:100px;
            border-radius:5px;
            box-shadow: 0 0px 12px rgba(0, 0, 0, 0.74);
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
        <div class="col-md-6">

            <div style="margin-top:150px">

                <form class="center-block" action="${pageContext.servletContext.contextPath}/authorization" method="post">
                    <div class="row">
                        <c:if test="${error != null}">
                            <div class="col alert alert-danger">
                                <c:out value="${error}"/>
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-3">
                            <label>Login</label>
                        </div>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="login">
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-3">
                            <label>Password</label>
                        </div>
                        <div class="col-md-9">
                            <input type="password" class="form-control" name="password">
                        </div>
                    </div>
                    <div class="d-flex flex-row">
                        <input type="submit" class="btn btn-primary" value="Вход">
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

</body>
</html>
