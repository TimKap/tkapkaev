<%--<%@ page import="ru.job4j.cartrade.storage.Storage" %>--%>
<%--<%@ page import="ru.job4j.cartrade.storage.dao.IAdvertisementDAO" %>--%>
<%--<%@ page import="ru.job4j.cartrade.model.advertisement.Advertisement" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<%@ page import="ru.job4j.cartrade.model.car.Car" %>--%>
<%--<%@ page import="java.util.Set" %>--%>
<%--<%@ page import="ru.job4j.cartrade.model.user.User" %>--%>
<%--<%@ page import="javax.xml.bind.DatatypeConverter" %>--%>
<%--<%@ page import="ru.job4j.cartrade.model.photo.Photo" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: Tim
  Date: 24.01.2018
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="./js/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <script>


        function changeStatus(element, advertisementId) {
            var advertisement = {
                id: advertisementId,
                seller: null,
                product: null,
                sold: element.checked,
                comments: null
            };
            $.ajax({
                url: './changeStatus',
                method: 'POST',
                dataType: 'JSON',
                data: JSON.stringify(advertisement),
                complete: function (data) {
                    var item = JSON.parse(data.responseText);
                    element.checked = item.sold;
                }
            })
        }

        function loadImage(advertisementId) {
            $.ajax({
                url: "./getImage",
                method: "GET",
                data: {advId: advertisementId.toString()},
                complete: function (data) {
                    var photo = data.responseText;
                    var elem = document.getElementById(advertisementId);
                    elem.setAttribute("src", "data:image/jpg;base64, " + photo);
                }
            });
        }

        function showTable(advertisements) {
            var result = "";
            for (var i = 0; i < advertisements.length; i++) {
                result += "<tr>"
                result += "<td>" + advertisements[i].id + "</td>";
                result += "<td>" + advertisements[i].seller.name + "</td>";
                <!-- add img -->
                var img = document.createElement("img");
                img.width = 256;
                img.height = 192;
                img.setAttribute("id", advertisements[i].id);
                loadImage(advertisements[i].id);
                result += "<td>" + img.outerHTML + "</td>";
                <!--add comment-->
                var description = "Model : " + advertisements[i].product.model + " <br> Engine : " + advertisements[i].product.engine.model
                    +
                    "<br> Body type: " + advertisements[i].product.body.type + "<br> Body color : " + advertisements[i].product.body.color;
                result += "<td>" + description + "</td>";

                var len = advertisements[i].comments.length;
                if (len != 0) {
                    result += "<td>" + advertisements[i].comments[len - 1] + "</td>";
                } else {
                    result += "<td>" + "</td>";
                }
                <!--add check box-->
                var checkbox = document.createElement("input");
                checkbox.setAttribute("type", "checkbox");
                if (advertisements[i].sold == true) {
                    checkbox.setAttribute("checked", "true");
                }
                checkbox.setAttribute("onclick", "changeStatus(this," + advertisements[i].id + ")");
                result += "<td>" + checkbox.outerHTML + "</td>";
                result += "</tr>"
            }
            var table = document.getElementById("adv");
            table.innerHTML = result;
        }

        function getAdvertisements(form) {
            var parameters;
            if (form != null) {
                parameters = {filter : form.selector.value, fValue : form.qu.value};
            } else {
                parameters = {filter : "all", fValue : ""};
            }
            $.ajax({
                url: './getAdvertisements',
                method: 'GET',
                data: parameters,
                complete: function (data) {
                    var advertisements = JSON.parse(data.responseText);
                    showTable(advertisements);
                }
            })
        }
        $(document).ready(getAdvertisements(null));
    </script>
</head>
<body>
<div class="container">

    <%--<table class="table table-bordered">--%>
    <%--<thead class="thead-dark">--%>
    <%--<tr>--%>
    <%--<th scope="col" class="text-center">id</th>--%>
    <%--<th scope="col" class="text-center">author</th>--%>
    <%--<th scope="col" class="text-center">photo</th>--%>
    <%--<th scope="col" class="text-center">description</th>--%>
    <%--<th scope="col" class="text-center">comment</th>--%>
    <%--<th scope="col" class="text-center">status</th>--%>
    <%--</tr>--%>
    <%--</thead>--%>
    <%--<tbody id="advertisements">--%>
    <%--<%--%>
    <%--Storage storage = new Storage();--%>
    <%--storage.open();--%>
    <%--IAdvertisementDAO advertisementDAO = storage.getAdvertisementDAO();--%>
    <%--List<Advertisement> advertisements = advertisementDAO.getAll();--%>
    <%--for (Advertisement advertisement : advertisements) {--%>
    <%--Car car = advertisement.getProduct();--%>
    <%--String description = String.format("Model : %s <br> Engine : %s <br> Body type: %s <br> Body color : %s", car.getModel(), car.getEngine().getModel(), car.getBody().getType(), car.getBody().getColor());--%>
    <%--StringBuilder checkbox = new StringBuilder("<input type='checkbox' ");--%>
    <%--if (advertisement.getSold()) {--%>
    <%--checkbox.append("checked ");--%>
    <%--}--%>
    <%--checkbox.append(String.format("onclick='changeStatus(this, %d)'", advertisement.getId()));--%>
    <%--checkbox.append("/>");--%>
    <%--List<String> comments = advertisement.getComments();--%>
    <%--String comment = comments.isEmpty() ? "" : comments.get(comments.size() - 1);--%>
    <%--User author = advertisement.getSeller();--%>
    <%--Set<Photo> photos = car.getPhotos();--%>
    <%--String b64 = null;--%>
    <%--if (!photos.isEmpty()) {--%>
    <%--Photo photo = photos.iterator().next();--%>
    <%--b64 = DatatypeConverter.printBase64Binary(photo.getFile());--%>
    <%--}--%>
    <%--%>--%>
    <%--<tr>--%>
    <%--<td><%=advertisement.getId()%></td>--%>
    <%--<td><%=author.getName()%></td>--%>
    <%--<td><img  class="img-responsive" src="data:image/jpg;base64, <%=b64%>" width="256" height="192"/></td>--%>
    <%--<td><%=description%></td>--%>
    <%--<td><%=comment %></td>--%>
    <%--<td><%=checkbox.toString()%></td>--%>
    <%--</tr>--%>
    <%--<%--%>
    <%--}--%>
    <%--storage.submit();--%>
    <%--%>--%>

    <%--</tbody>--%>
    <%--</table>--%>
    <form>
        <div class="d-flex flex-row">
            <div class="p-2"><input type='submit' class="btn btn-primary" value='Выход'
                                    formaction="${pageContext.servletContext.contextPath}/exit"
                                    formmethod='post'></div>
            <div class="p-2"><input type='submit' class="btn btn-primary" value='Редактор объявлений'
                                    formaction="${pageContext.servletContext.contextPath}/advertisementredactor"
                                    formmethod='get'></div>
        </div>
    </form>
    <form>
        <div class="form-row">
            <div class="form-group col-md-2">
                <select name="selector">
                    <option selected>all</option>
                    <option>seller</option>
                    <option>model</option>
                    <option>unsold</option>
                </select>
            </div>
            <div class="form-group col-md-8">
                <input type="text" class="form-control" name="qu" placeholder="Запрос">
            </div>
            <div class="form-group col-md-2">
                <input type='button' class="btn btn-primary" value='Поиск' onclick="getAdvertisements(form)">
            </div>
        </div>
    </form>
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th scope="col" class="text-center">id</th>
            <th scope="col" class="text-center">author</th>
            <th scope="col" class="text-center">photo</th>
            <th scope="col" class="text-center">description</th>
            <th scope="col" class="text-center">comment</th>
            <th scope="col" class="text-center">status</th>
        </tr>
        </thead>
        <tbody id="adv">
        </tbody>
    </table>
</div>

</body>


</html>
