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
		
		$scope.done = $scope.IsVisible ? false :false;
		$scope.IsVisible = $scope.IsVisible ? false :false;
		
			
			var addToDoObj = homeService.addNote($scope.toDo);
			addToDoObj.then( function(data) {
				
				if( data.status == 200 ) {
					$scope.toDoList.push(data.data.todo);
					console.log(data.data.todo);
					$scope.toDo.title=null;
					$scope.toDo.note=null;
					
				}
				
			}).catch( function(error) {
				console.log(error);
				$state.go('signIn');
			});
		
	}
	
	
	
	this.deleteNote = function(id, index) {
		console.log(id+" "+index);
		var delToDoObj = homeService.deleteNote(id);
		
		delToDoObj.then(function(data) {
			
			if( data.status == 200 ) {
				if(index>-1){
					$scope.toDoList.splice(index, 1);
					//alert("delete");
				}
				
			}
			
		}).catch( function(error) {
			
			$state.go('signIn');
		});
	}
	
	
	this.showHide = function() {
		$scope.IsVisible = $scope.done ? true :true;
		$scope.done = $scope.IsVisible ? true :false;
	}
	
 	
});



