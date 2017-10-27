var userRegistrationApp = angular.module("userRegistrationApp", []);

userRegistrationApp.controller("userRegistrationController",
    function($scope, $http){

        $scope.message = "";
        $scope.clearMessage = function(){
            $scope.message = "";
        };

        $scope.nwUser = {};
        $scope.saveNewUser = function(){
            $http.post('/api/user', $scope.nwUser)
                .then(
                    function successCallback(obj){
                        console.log(obj);
                        $scope.message = obj.data.message;
                        $scope.nwUser = {};

                        $scope.listUsers();
                    },
                    function errorCallback(error){
                        console.log("[ERROR]: " + error);
                        $scope.message = error;
                    }
                );
        };

        $scope.listUsers = function () {
            $http.get('/api/listContact?owner=' + $scope.owner + '&v=' + Date.now())
                .then(
                    function successCallback(obj){
                        $scope.users = obj.data;
                    },
                    function errorCallback(msg){
                        //console.log(obj);
                        $scope.message = msg;
                    }
                );
        };

        $scope.listUsers();
    }
);
