var helloworldApp = angular.module('helloworldApp', [
    'ngRoute',
    'helloControllers'
]);

helloworldApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/hello', {
                templateUrl: '/partials/hello.html',
                controller: 'HelloWorldCtrl'
            }).
            otherwise({
                redirectTo: '/hello'
            });
    }]);