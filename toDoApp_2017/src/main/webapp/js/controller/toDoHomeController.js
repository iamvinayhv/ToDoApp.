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
		
	
	this.addNote = function() {
		
		var toDo = {};
		toDo.title = $scope.title;
		toDo.note = $scope.note;
		toDo.remainder = null;
		
		$scope.toDoList.push(toDo);
		
		var addToDoObj = homeService.addNote(toDo);
		
		addToDoObj.then( function(data) {
			
			if( data.status == 200 ) {
				console.log(data);
				
			}
			
		}).catch( function(error) {
			console.log(error);
			$state.go('signIn');
		});
	}
	
	this.deleteNote = function(id) {
		
		var delToDoObj = homeService.deleteNote(id);
		
	}
	
});
