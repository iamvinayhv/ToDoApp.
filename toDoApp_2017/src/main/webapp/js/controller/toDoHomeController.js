myToDo.controller("homeController", function($scope, $state, homeService) {
	
	var toDoList = [];
	var getToDoHtOb = homeService.getNotes();
	
	getToDoHtOb.then(function(data) {
		
		console.log("comming data "+data.status);
		console.log(data.data.todo[0].title);
		
		if( data.status == 200 ) {
			console.log(data);
			$scope.toDoList = data.data.todo;
		}
		else{
			
			$scope.toDoList = null;
		}
		
	}).catch( function(error) {
		
		$state.go('signIn');
		console.log(error);
	});
		
	
	
	
	var addToDo = $scope.homeService.addNote();
	
	addToDo.then( function(data) {
		
		if( data.status == 200 ) {
			console.log(data);
		}
		
	}).catch( function(error) {
		console.log(error);
		$state.go('signIn');
	});
	
	
});
