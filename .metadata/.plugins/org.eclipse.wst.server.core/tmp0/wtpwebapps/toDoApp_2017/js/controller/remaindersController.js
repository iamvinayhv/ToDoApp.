myToDo.controller("remaindersController", ['$scope', '$controller', function($scope, $controller) {
	
	$controller('homeController',{$scope:$scope}),
	
	$scope.remainders = true;
	$scope.archive = false;
	$scope.pinUp = true;
	$scope.others = true;
	$scope.addNote = true;
	$scope.navBar = {"background-color":"rgb(96, 125, 139)"};
	$scope.search = {"background-color":"rgb(96, 110, 120)"};
	
	
}]);
