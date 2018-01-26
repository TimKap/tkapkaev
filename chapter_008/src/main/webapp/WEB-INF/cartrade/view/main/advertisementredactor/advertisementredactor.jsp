<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 25.01.2018
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="./js/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <form id="myForm" enctype="multipart/form-data">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label>Model</label>
                <input type="text" class="form-control" name="model" placeholder="model">
            </div>
            <div class="form-group col-md-6">
                <label>Engine</label>
                <input type="text" class="form-control" name="engine" placeholder="engine">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label>Body type</label>
                <input type="text" class="form-control" name="bodytype" placeholder="body type">
            </div>
            <div class="form-group col-md-6">
                <label>Body color</label>
                <input type="text" class="form-control" name="bodycolor" placeholder="body color">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label>Comment</label>
                <input type="text" class="form-control" name="comment" placeholder="comment">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label>File</label>
                <input type="file" class="form-control" name="photo">
            </div>
        </div>

        <div class="d-flex">
            <div class="p-2">
                <input type='submit' class="btn btn-primary" value='Добавить'
                       formaction="${pageContext.servletContext.contextPath}/advertisementredactor"
                       formmethod='post'>
            </div>
        </div>
    </form>
</div>
</body>
</html>
