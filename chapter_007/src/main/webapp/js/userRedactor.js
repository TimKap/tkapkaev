function loadUserTable() {
    $.ajax('../admin/ajax/loadTable', {
        method: 'get',
        complete:
            function (data) {
                var users = JSON.parse(data.responseText);
                var result ="";
                for (var i = 0; i < users.length; i++) {
                    result += "<tr>" +
                        "<td>" + users[i].login + "</td>" +
                        "<td>" + users[i].name + "</td>" +
                        "<td>" + users[i].email + "</td>" +
                        "<td>" + users[i].createDate + "</td>" +
                        "<td>" + users[i].role + "</td>" +
                        "<td>" + users[i].password + "</td>" +
                        "<td>" + users[i].city + "</td>" +
                        "<td>" + users[i].country + "</td>"
                        + "</tr>"
                }
                var userTable = document.getElementById("us");
                userTable.innerHTML = result;
            }
    })
}

function checkFormWhenInsert(forma) {
    var login = forma.login.value;
    var role = forma.role.value;
    var password = forma.password.value;
    var city = forma.city.value;
    var country = forma.country.country;
    return (login != "") && (role != "") && (password != "") && (city !='') && (country !='');
}

function insertUserAjax(forma) {
    var isFormValid = checkFormWhenInsert(forma);
    var user  = {login : forma.login.value, name : forma.name.value, email : forma.email.value, role : forma.role.value, password : forma.password.value, city : forma.city.value, country : forma.country.value};
    if (isFormValid) {
        $.ajax(
            {
                url : '../admin/ajax/insertUser',
                dataType: 'json',
                method: 'POST',
                data : JSON.stringify(user),
                complete : function () {loadUserTable()}
            }
        );

    } else {
        alert("Check entered login, role, password, country and city");
    }
}


function checkFormWhenDelete(forma) {
    var login = forma.login.value;
    var name = forma.name.value;
    var password = forma.password.value;
    var email = forma.email.value;
    var role = forma.role.value;
    var flag = true;
    if (login == "") {
        alert("Check login");
        flag = false;
    }
    if ((name != "") || (email != "") || (password != "") || (role != "")) {
        alert("When delete then fields name, email, password, role must be empty");
        flag = false;
    }
    return flag;
}

function deleteUserAjax(forma) {
    var isFormValid = checkFormWhenDelete(forma);
    var user  = {login : forma.login.value, name : forma.name.value, email : forma.email.value, role : forma.role.value, password : forma.password.value, city : forma.city.value, country : forma.country.value};
    if (isFormValid) {
        $.ajax(
            {
                url: '../admin/ajax/deleteUser',
                dataType: 'json',
                method: 'POST',
                data: JSON.stringify(user),
                complete: function() {
                    loadUserTable();
                }
            }
        );
    }
}

function checkFormWhenUpdate(forma) {
    var login = forma.login.value;
    var result = false;
    if (login == "") {
        alert("Check login");
    } else {
        result = true;
    }
    return result;
}

function updateUserAjax(forma) {
    var isFormValid = checkFormWhenUpdate(forma);
    var user  = {login : forma.login.value, name : forma.name.value, email : forma.email.value, role : forma.role.value, password : forma.password.value, city : forma.city.value, country : forma.country.value};
    if (isFormValid) {
        $.ajax( {
            url: '../admin/ajax/updateUser',
            method: 'POST',
            dataType: 'json',
            data: JSON.stringify(user),
            complete: function() {
                loadUserTable();
            }
        });
    }
}

function selectRole() {

    $.ajax (
        {
            url : '../admin/ajax/getRoles',
            method: 'GET',
            complete: function(data) {
                console.log(data);
                var roles  = JSON.parse(data.responseText);
                var previous_value = document.getElementById("select_role").value;
                $(document.getElementById("select_role")).children().remove();
                $(document.getElementById("select_role")).append("<option selected></option>");
                for (var i = 0; i < roles.length; i++) {
                    $(document.getElementById("select_role")).append("<option>" + roles[i].role + "</option>");
                }
                document.getElementById("select_role").value = previous_value;
            }

        }
    );
}

function selectCountry() {

    $.ajax(
        {
            url:'../ajax/getCountries',
            method: "GET",
            complete: function(data) {
                var countries = JSON.parse(data.responseText);
                var previous_value = document.getElementById("select_country").value;
                $(document.getElementById("select_country")).children().remove();
                $(document.getElementById("select_country")).append("<option selected></option>");
                for (var i = 0; i < countries.length; i++) {
                    $(document.getElementById("select_country")).append("<option>" + countries[i].country + "</option>");
                }
                document.getElementById("select_country").value = previous_value;
            }
        }
    )
}

function selectCity() {
    var country = {country : document.getElementById("select_country").value};
    $.ajax( {
        url: '../ajax/getCities',
        method: 'POST',
        dataType: 'json',
        data: JSON.stringify(country),
        complete: function(data) {
            var cities = JSON.parse(data.responseText);
            $(document.getElementById("select_city")).children().remove();
            $(document.getElementById("select_city")).append("<option selected></option>");
            for (var i = 0; i < cities.length; i++) {
                $(document.getElementById("select_city")).append("<option>" + cities[i].city + "</option>");
            }
        }
    });

}