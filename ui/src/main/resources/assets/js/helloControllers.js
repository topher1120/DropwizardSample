var helloControllers = angular.module('helloControllers', []);

helloControllers.controller('HelloWorldCtrl', ['$scope', '$http', '$log',
    function ($scope, $http, $log) {
        $log.debug("initialized HelloWorldCtrl");

        $scope.submitName = function(name){
            var urlPath = '/service/hello';
            if(name){
                urlPath +='?name='+name;
            }

            $http.get(urlPath).success(function(data) {
                $scope.helloContent = data.content;
            });
        }

        $scope.submitName();
    }]);