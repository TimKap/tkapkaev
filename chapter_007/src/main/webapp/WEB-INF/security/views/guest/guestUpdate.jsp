<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 18.11.2017
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
            integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
            integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
            crossorigin="anonymous"></script>

    <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/userRedactor.js"></script>
<script>
    $(document).ready(function() {selectCountry()});
</script>
</head>
<body>
<div class="container">

    <p class="text-center">Редактор пользователя</p>

    <form action="${pageContext.servletContext.contextPath}/guest/guestUpdate" method="post">


        <div class="form-row">
            <div class="form-group col-md-4">
                <label>Name</label>
                <input type="text" class="form-control" name="name" value="${name}">
            </div>
            <div class="form-group col-md-4">
                <label>Email</label>
                <input type="text" class="form-control" name="email" value="${email}">
            </div>
            <div class="form-group col-md-4">
                <label>Password</label>
                <input type="text" class="form-control" name="password">
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
                <select id="select_country" class="form-control" name="country" onfocus="selectCountry()"
                        onchange="selectCity()">

                </select>
            </div>
            <div class="d-flex flex-row">
                <div class="p-2">
                    <input class="btn btn-primary" type="submit" value="Обновить">
                </div>
            </div>

        </div>
    </form>

</div>

</body>
</html>
