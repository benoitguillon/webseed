'use strict';

var webseed_core = angular.module('WebSeedCore', ['ngRoute', 'ngResource']);

webseed_core.controller('CoreCtrl', function($scope, User){
	$scope.userId=0;
	$scope.label = "This is a label in scope";
	$scope.testIndex = 0;
	$scope.incrementTestIndex = function(){
		$scope.testIndex++;
	}
	$scope.userDetails = function(){
		console.log("Getting details on user " + $scope.userId);
		$scope.userDetail = User.get({id:$scope.userId, firm:'firmOne'});
	}
});


webseed_core.config(function($routeProvider){
	$routeProvider.when('/Admin/AddUser', {
		templateUrl: 'partials/admin/user.html'
	});
	$routeProvider.otherwise({
		templateUrl: 'partials/main.html'
	});
});

webseed_core.factory('User', ['$resource',
    function($resource) {
    	return $resource('/portal/:firm/api/users/:id');
    }]);

