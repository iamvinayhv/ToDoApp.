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
		
		//$scope.toDo.remainder = null;
		
		var addToDoObj = homeService.addNote($scope.toDo);
		addToDoObj.then( function(data) {
			
			if( data.status == 200 ) {
				$scope.toDoList.push(data.data.todo);
				console.log(data.data.todo);
				$scope.toDo.title=null;
				$scope.toDo.note=null;
				//$state.reload();
				$state.go('home');
				
			}
			
		}).catch( function(error) {
			console.log(error);
			$state.go('signIn');
		});
	}
	
	
	
	this.deleteNote = function(id) {
		console.log(id);
		var delToDoObj = homeService.deleteNote(id);
		
		delToDoObj.then(function(data) {
			
			if( data.status == 200 ) {
				
				console.log(data);
				
				$scope.toDoList.push(data.data.todo);
				$state.reload();
			}
			
		}).catch( function(error) {
			
			$state.go('signIn');
		});
	}
	
	
	this.showHide = function() {
		$scope.IsVisible = $scope.IsVisible ? false :true;
	}
	
});
