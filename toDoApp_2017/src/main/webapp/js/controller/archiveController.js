myToDo.controller("archiveController", ['$scope', '$controller', function($scope, $controller) {
	
	$controller('homeController',{$scope:$scope}),
	
	$scope.archive = true;
	$scope.pinUp = true;
	$scope.others = true;
	$scope.addNote = true;
	$scope.navBar = {"background-color":"rgb(96, 125, 139)"};
	$scope.search = {"background-color":"rgb(96, 110, 120)"};
	
	
}]);
