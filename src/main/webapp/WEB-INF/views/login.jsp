<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Contacts - Login</title>

        <link href="css/main.css" rel="stylesheet">
        <link href="favicon.ico" rel="icon" type="image/x-icon">
    </head>
    <body>
        <div class="login-page">
            <div class="form">
                <c:if test="${null!=errorText}">
                    <span class="error">${errorText}</span>
                </c:if>
                <form class="login-form" action="/login" method="post">
                    <input type="text" name="username" placeholder="Username"/>
                    <input type="password" name="password" placeholder="Password"/>
                    <input name="submit" type="submit" value="Login" class="button" />
                    <p class="message">Not registered? <a href="/register">Create an account</a></p>
                </form>
            </div>
        </div>

    </body>
</html>