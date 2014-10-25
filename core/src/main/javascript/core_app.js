'use strict';

var webseed_core = angular.module('WebSeedCore', ['ngRoute', 'ngResource', 'ui.validate']);

webseed_core.controller('CoreCtrl', function($scope, User, AppContextService){
	AppContextService.initializeCurrentFirm();
	$scope.userId=0;
	$scope.label = "This is a label in scope";
	$scope.testIndex = 0;
	$scope.incrementTestIndex = function(){
		$scope.testIndex++;
	}
	$scope.userDetails = function(){
		console.log("Getting details on user " + $scope.userId);
		$scope.userDetail = User.get({id:$scope.userId, firm:'firmOne'});
	};
});

webseed_core.controller('AddUserCtrl', function($scope, User){
	$scope.user = new User();
	$scope.user.id = 0;
	$scope.user.userName = "";
	$scope.user.firstName = "";
	$scope.user.lastName = "";
	$scope.user.email = "";
	$scope.save = function(){
		$scope.user.$save();
	};
	$scope.validateUserName = function(){
		return $scope.user.userName != undefined && $scope.user.userName.charAt(0) == 'a';
	};
});


webseed_core.config(function($routeProvider){
	$routeProvider.when('/admin/user/add', {
		templateUrl: 'partials/admin/user.html',
		controller: 'AddUserCtrl'
	});
	$routeProvider.when('/admin/user/list', {
		templateUrl: 'partials/admin/list.html'
	});
	$routeProvider.when('/admin/user/edit/:id', {
		templateUrl: 'partials/admin/user.html'
	});
	$routeProvider.otherwise({
		templateUrl: 'partials/main.html'
	});
});

webseed_core.factory('AppContextService', ['$location', function($location){
	var service = {};
	service.currentFirm = {}; // the current firm object
	service.initializeCurrentFirm = function(){
		// used to detect the current firm from the URL
		var homeUrl = $location.absUrl();
		var homeUrlParts = homeUrl.split('/');
		var firmWebContext = homeUrlParts[homeUrlParts.length - 2];
		service.currentFirm.webContext = firmWebContext;
	};
	return service;
}]);

webseed_core.factory('User', ['$resource', 'AppContextService',
    function($resource, AppContextService) {
    	return $resource(
    			'/portal/:firm/api/users/:id', // URL pattern
    			{firm: function(){return AppContextService.currentFirm.webContext;}, id: '@id'} // URL parameters map for pattern substitution
    	); 
    }]);

