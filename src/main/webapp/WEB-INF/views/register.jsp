<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Contacts - Signup</title>

        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="favicon.ico" rel="icon" type="image/x-icon">

        <style>
            div.form-registration { /*width: 800px;*/ }
            div.user-details {
                width: 300px;
            }

            select {
                font-family: "Roboto", sans-serif;
                outline: 0;
                background: #f2f2f2;
                width: 100%;
                border: 0;
                margin: 0 0 15px;
                padding: 15px;
                box-sizing: border-box;
                font-size: 14px;
            }
        </style>
    </head>
    <body>
    <div class="login-page">
        <div class="form-registration">
            <c:if test="${hasError}">
                <span class="error">${statusMessage}</span>
            </c:if>
            <form class="login-form" action="/register" method="post">
                <div class="row">
                    <div class="col-lg-6 user-details" align="left">
                        <label>Details</label>
                        <input type="text" name="username" placeholder="Username" value="${username}" />
                        <input type="password" name="password" placeholder="Password" value="${password}" />
                        <input type="password" name="verifyPassword" placeholder="Confirm password" value="${verifyPassword}" />
                    </div>
                    <hr>
                    <input name="submit" type="submit" value="Register" class="button" />
                </div>

            </form>
        </div>
    </div>
</body>
</html>