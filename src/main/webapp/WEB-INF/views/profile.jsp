<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html ng-app="profileRegistrationApp">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Callbook: Profile</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <!-- Custom styles for this template -->
        <link href="css/phonebook.css" rel="stylesheet">
        <link href="css/home.css" rel="stylesheet">
        <link href="css/materialize.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
        <link href="favicon.ico" rel="icon" type="image/x-icon">
    </head>
    <body ng-controller="profileRegistrationController">

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="navigation-panel container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbarResponsive" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a id="aBrand" class="navbar-brand text-capitalize" href="#"><img src="images/logo.png" class="logo" alt="logo"><span class="brand-name">Contacts</span></a>
                </div>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item"><a href="/profile" class="nav-link"><span class="glyphicon glyphicon-user medium-icon"></span>Profile</a></li>
                        <li class="nav-item"><a href="/logout" class="nav-link"><span class="glyphicon glyphicon-log-out medium-icon"></span>Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <input type="hidden" id="hdnOwner" ng-model="owner" value="${userInfo.username}" />

        <div class="container">
            <hr>
            <h2><span class="glyphicon glyphicon-user big-icon"></span><span>Profile</span></h2>
            <span class="clearfix"></span>
            <hr>

            <div class="alert alert-success text-center" ng-if="message">
                <a href="#" class="close" aria-label="close" ng-click="clearMessage()">&times;</a>
                <strong>{{message}}</strong>
            </div>

            <!-- Modal for Edit Contact -->
            <div id="editProfile">
                <div class="col-lg-6">
                    <div class="form-group">
                        <label for="txtUsername">Username</label>
                        <input type="text" class="form-control" id="txtUsername" placeholder="Username" name="username" ng-model="profile.username" readonly>
                    </div>
                    <div class="form-group">
                        <label for="txtCurrentPassword">Current Password</label>
                        <input type="password" class="form-control" id="txtCurrentPassword" placeholder="Enter current password" name="password" ng-model="profile.password">
                    </div>
                    <div class="form-group">
                        <label for="txtNewPassword">New Password</label>
                        <input type="password" class="form-control" id="txtNewPassword" placeholder="Enter new password" name="newPassword" ng-model="profile.newPassword">
                    </div>
                    <div class="form-group">
                        <label for="txtVerifyPassword">Verify Password</label>
                        <input type="password" class="form-control" id="txtVerifyPassword" placeholder="Confirm new password" name="verifyPassword" ng-model="profile.verifyPassword">
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary" ng-click="updateProfile()">Update Details</button>
                    </div>
                </div>
            </div>
            <!-- End: Modal For Edit Contact -->

        </div>

        <footer class="footer">
            <div class="container">
                <b>Session: </b>&nbsp;<span><b>(${userInfo.username})</b></span>
            </div>
        </footer>

        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script type="text/javascript" src="js/ngUserProfile.js"></script>

    </body>
</html>
