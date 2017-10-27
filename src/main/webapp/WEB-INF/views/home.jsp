<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html ng-app="consumeRestApiApp">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Callbook: Home</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <!-- Custom styles for this template -->
        <link href="css/phonebook.css" rel="stylesheet">
        <link href="css/home.css" rel="stylesheet">
        <link href="css/materialize.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
        <link href="favicon.ico" rel="icon" type="image/x-icon">
    </head>
    <body ng-controller="consumeRestApiController">

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


        <input type="hidden" id="hdnOwner" ng-model="owner" value="${userInfo}" />

        <div class="container">
            <hr>
            <button type="button" class="btn btn-primary btn-lg pull-right" data-toggle="modal" data-target="#addContact"><span class="glyphicon glyphicon-plus-sign small-icon"></span>Add New</button>
            <h2><span class="glyphicon glyphicon-phone-alt big-icon"></span><span>Manage Contacts</span></h2>
            <span class="clearfix"></span>
            <hr>

            <div class="alert alert-success text-center" ng-if="message">
                <a href="#" class="close" aria-label="close" ng-click="clearMessage()">&times;</a>
                <strong>{{message}}</strong>
            </div>

            <table class="table responsive-table highlight striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Contact No.</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="contact in contacts">
                        <td>{{contact.details.firstName + " " + contact.details.lastName}}</td>
                        <td>{{contact.details.email}}</td>
                        <td>{{contact.contactNumbers.mobileNo}}</td>
                        <td class="button">
                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#editContact" ng-click="displayContactDetails(contact.id)">
                                <span class="glyphicon glyphicon-edit"></span>
                            </button>
                        </td>
                        <td class="button">
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteContact" ng-click="displayContactDetails(contact.id)">
                                <span class="glyphicon glyphicon-trash"></span>
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <footer class="footer">
            <div class="container">
                <b>Session: </b>&nbsp;<span>${sessionId}</span>&nbsp;<span><b>(${userInfo})</b></span>
            </div>
        </footer>

        <!-- Modal for Add Contact -->
        <div class="modal fade" id="addContact" role="dialog">
            <!-- Modal content-->
            <div>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Contact Registration</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="txtNewFirtstName">First Name</label>
                        <input type="text" class="form-control" id="txtNewFirtstName" placeholder="First Name" ng-model="newContact.details.firstName">
                    </div>
                    <div class="form-group">
                        <label for="txtNewLastName">Last Name</label>
                        <input type="text" class="form-control" id="txtNewLastName" placeholder="Last Name" ng-model="newContact.details.lastName">
                    </div>
                    <div class="form-group">
                        <label for="txtNewEmail">Email Address</label>
                        <input type="email" class="form-control" id="txtNewEmail" placeholder="Email Address" ng-model="newContact.details.email">
                    </div>
                    <div class="form-group">
                        <label for="txtAddress">Address</label>
                        <textarea id="txtAddress" cols="30" rows="10" ng-model="newContact.details.address"></textarea>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label for="txtMobileNo">Mobile Number</label>
                        <input type="tel" class="form-control" id="txtMobileNo" placeholder="Mobile Number" ng-model="newContact.contactNumbers.mobileNo">
                    </div>
                    <div class="form-group">
                        <label for="txtTelephoneNo">Telephone Number</label>
                        <input type="tel" class="form-control" id="txtTelephoneNo" placeholder="Telephone Number" ng-model="newContact.contactNumbers.telNo">
                    </div>
                    <div class="form-group">
                        <label for="txtHomeTelephoneNo">Home Telephone Number</label>
                        <input type="tel" class="form-control" id="txtHomeTelephoneNo" placeholder="Home Telephone Number" ng-model="newContact.contactNumbers.homeNo">
                    </div>
                    <div class="form-group">
                        <label for="txtWorkTelephoneNo">Work Telephone Number</label>
                        <input type="tel" class="form-control" id="txtWorkTelephoneNo" placeholder="Work Telephone Number" ng-model="newContact.contactNumbers.workNo">
                    </div>
                    <hr>
                    <div class="form-group">
                        <label for="txtFacebook">Facebook</label>
                        <input type="tel" class="form-control" id="txtFacebook" placeholder="Facebook account" ng-model="newContact.socials.facebook">
                    </div>
                    <div class="form-group">
                        <label for="txtInstagram">Instagram</label>
                        <input type="tel" class="form-control" id="txtInstagram" placeholder="Instagram account" ng-model="newContact.socials.instagram">
                    </div>
                    <div class="form-group">
                        <label for="txtTwitter">Twitter</label>
                        <input type="tel" class="form-control" id="txtTwitter" placeholder="Twitter account" ng-model="newContact.socials.twitter">
                    </div>
                    <div class="form-group">
                        <label for="txtGooglePlus">Google+</label>
                        <input type="tel" class="form-control" id="txtGooglePlus" placeholder="Google+ account" ng-model="newContact.socials.google">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" data-dismiss="modal" ng-click="saveNewContact()">Save Details</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
        <!-- End: Modal For Add Contact -->

        <!-- Modal for Edit Contact -->
        <div class="modal modal-edit-contact fade" id="editContact" role="dialog">
            <!-- Modal content-->
            <div>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Contact Details</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="txtUpdateID">ID</label>
                        <input type="text" class="form-control" id="txtUpdateID" placeholder="Contact ID" name="id" ng-model="selectedContact.id" readonly>
                    </div>
                    <div class="form-group">
                        <label for="txtEditFirtstName">First Name</label>
                        <input type="text" class="form-control" id="txtEditFirtstName" placeholder="First Name" ng-model="selectedContact.details.firstName">
                    </div>
                    <div class="form-group">
                        <label for="txtEditLastName">Last Name</label>
                        <input type="text" class="form-control" id="txtEditLastName" placeholder="Last Name" ng-model="selectedContact.details.lastName">
                    </div>
                    <div class="form-group">
                        <label for="txtEditEmail">Email Address</label>
                        <input type="email" class="form-control" id="txtEditEmail" placeholder="Email Address" ng-model="selectedContact.details.email">
                    </div>
                    <div class="form-group">
                        <label for="txtEditAddress">Address</label>
                        <textarea id="txtEditAddress" cols="30" rows="10" ng-model="selectedContact.details.address"></textarea>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label for="txtEditMobileNo">Mobile Number</label>
                        <input type="tel" class="form-control" id="txtEditMobileNo" placeholder="Mobile Number" ng-model="selectedContact.contactNumbers.mobileNo">
                    </div>
                    <div class="form-group">
                        <label for="txtEditTelephoneNo">Telephone Number</label>
                        <input type="tel" class="form-control" id="txtEditTelephoneNo" placeholder="Telephone Number" ng-model="selectedContact.contactNumbers.telNo">
                    </div>
                    <div class="form-group">
                        <label for="txtEditHomeTelephoneNo">Home Telephone Number</label>
                        <input type="tel" class="form-control" id="txtEditHomeTelephoneNo" placeholder="Home Telephone Number" ng-model="selectedContact.contactNumbers.homeNo">
                    </div>
                    <div class="form-group">
                        <label for="txtEditWorkTelephoneNo">Work Telephone Number</label>
                        <input type="tel" class="form-control" id="txtEditWorkTelephoneNo" placeholder="Work Telephone Number" ng-model="selectedContact.contactNumbers.workNo">
                    </div>
                    <hr>
                    <div class="form-group">
                        <label for="txtEditFacebook">Facebook</label>
                        <input type="tel" class="form-control" id="txtEditFacebook" placeholder="Facebook account" ng-model="selectedContact.socials.facebook">
                    </div>
                    <div class="form-group">
                        <label for="txtEditInstagram">Instagram</label>
                        <input type="tel" class="form-control" id="txtEditInstagram" placeholder="Instagram account" ng-model="selectedContact.socials.instagram">
                    </div>
                    <div class="form-group">
                        <label for="txtEditTwitter">Twitter</label>
                        <input type="tel" class="form-control" id="txtEditTwitter" placeholder="Twitter account" ng-model="selectedContact.socials.twitter">
                    </div>
                    <div class="form-group">
                        <label for="txtEditGooglePlus">Google+</label>
                        <input type="tel" class="form-control" id="txtEditGooglePlus" placeholder="Google+ account" ng-model="selectedContact.socials.google">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" ng-click="updateContact()" data-dismiss="modal">Update Details</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
        <!-- End: Modal For Edit Contact -->

        <!-- Modal for Delete Contact -->
        <div class="modal fade" id="deleteContact" role="dialog">
            <!-- Modal content-->
            <div>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Remove User</h4>
                </div>
                <div class="modal-body">
                    <p><strong>Are you sure you want to delete {{selectedContact.details.firstName + " " + selectedContact.details.lastName}}?</strong></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="removeContact(selectedContact.id)">Yes</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                </div>
            </div>
        </div>
        <!-- End: Modal For Delete Contact -->

        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script type="text/javascript" src="js/ngContactRestAPI.js"></script>

    </body>
</html>
