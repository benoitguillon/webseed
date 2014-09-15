'use strict';

var webseed_core = angular.module('WebSeedCore', ['ngRoute']);

webseed_core.controller('CoreCtrl', function($scope){
	$scope.label = "This is a label in scope";
	$scope.testIndex = 0;
	$scope.incrementTestIndex = function(){
		$scope.testIndex++;
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
