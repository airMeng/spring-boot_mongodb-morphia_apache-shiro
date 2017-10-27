var profileRegistrationApp = angular.module("profileRegistrationApp", []);

profileRegistrationApp.controller("profileRegistrationController",
    function($scope, $http){

        $scope.message = "";
        $scope.clearMessage = function(){
            $scope.message = "";
        };

        $scope.profile = {};
        $scope.displayProfileDetails = function(){
            var username = $('#hdnOwner').val();
            $http.get('/api/user?un=' + username)
                .then(
                    function successCallback(obj){
                        $scope.profile = obj.data;
                    },
                    function errorCallback(obj){
                        $scope.message = obj.data;
                    }
                );
        };

        $scope.updateProfile = function () {
            $http.put('/api/user', $scope.profile)
                .then(
                    function successCallback(obj){
                        console.log(obj);
                        $scope.message = obj.data.message;
                        $scope.profile = {};
                    },
                    function errorCallback(error){
                        console.log("[ERROR]: " + JSON.stringify(error));
                        $scope.message = error.data.message;
                    }
                );
        };

        var init = function () {
            $scope.displayProfileDetails();
        };

        init();
    }
);
