var consumeRestApiApp = angular.module("consumeRestApiApp", []);
consumeRestApiApp.controller("consumeRestApiController",
    function($scope, $http) {

        $scope.owner = $('#hdnOwner').val();

        $http.get('/api/listContact?owner=' + $scope.owner + '&v=' + Date.now())
            .then(
                function successCallback(obj){
                    //console.log(obj);
                    $scope.contacts = obj.data;
                },
                function errorCallback(msg){
                    console.log(msg);
                    $scope.message = msg;
                }
            );

        console.log("[owner]: " + $scope.owner);

        // for message alert
        $scope.message = "";
        $scope.clearMessage = function(){
            $scope.message = "";
        };

        $scope.listContacts = function () {
            $http.get('/api/listContact?owner=' + $scope.owner + '&v=' + Date.now())
                .then(
                    function successCallback(obj){
                        $scope.contacts = obj.data;
                    },
                    function errorCallback(msg){
                        //console.log(obj);
                        $scope.message = msg;
                    }
                );
        };

        $scope.newContact = {};
        $scope.saveNewContact = function(){
            $scope.newContact.owner = $scope.owner;
            $http.post('/api/addContact', $scope.newContact)
                .then(
                    function successCallback(obj){
                        console.log(obj);
                        $scope.message = obj.data.message;
                        $scope.newContact = {};
                        $scope.listContacts();
                    },
                    function errorCallback(error){
                        console.log("[ERROR]: " + error);
                        $scope.message = error;
                    }
                );
        };

        $scope.selectedContact = {};
        $scope.displayContactDetails = function(id){
            //console.log('username: ' + username);
            $http.get('/api/getContact?id=' + id)
                .then(
                    function successCallback(obj){
                        $scope.selectedContact = obj.data;
                    },
                    function errorCallback(obj){
                        $scope.message = obj.data;
                    }
                );
        };

        $scope.updateContact = function(){
            $http.post('/api/updateContact', $scope.selectedContact)
                .then(
                    function successCallback(obj){
                        console.log(obj);
                        $scope.message = obj.data.message;
                        $scope.selectedContact = {};
                        $scope.listContacts();
                    },
                    function errorCallback(error){
                        console.log("[ERROR]: " + error);
                        $scope.message = error;
                    }
                );
        };

        $scope.removeContact = function(id){
            console.log(id);
            if(id){
              $http.post('/api/removeContact?id=' + id)
                  .then(
                      function successCallback(obj){
                          console.log(obj);
                          $scope.message = obj.data.message;
                          $scope.selectedContact = {};
                          $scope.listContacts();
                      },
                      function errorCallback(error){
                          $scope.message = error;
                      }
                  );
            }
        };
    }
);