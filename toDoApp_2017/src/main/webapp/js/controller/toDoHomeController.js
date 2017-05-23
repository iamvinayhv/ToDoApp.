myToDo.controller("homeController", function($scope, $state, $uibModal, homeService) {
	
	var toDoList = [];
	var getToDoHtOb = homeService.getNotes();
	
	getToDoHtOb.then(function(data) {
		
		console.log("comming data "+data.status);
		
		if( data.status == 200 ) {
			console.log(data);
			$scope.toDoList = data.data.todo;
			
		}
		else{
			
			$scope.toDoList = null;
		}
		
	}).catch( function(error) {
		$scope.isList = true;
		$state.go('signIn');
		console.log(error);
	});
		
	$scope.isList = true;
	
	this.addNote = function() {
		
		$scope.done = $scope.done ? false :true;
		$scope.IsVisible = $scope.IsVisible ? false :true;
		
		if($scope.toDo.title != null && $scope.toDo.note != null || ($scope.toDo.title != "" && $scope.toDo.note != ""))	
		
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
				if(index > -1){
					$scope.toDoList.splice(index, 1);
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
	
	
	
	this.popUp = function(toDo, index) {
		
		var modal = $uibModal.open({
		     templateUrl: "template/popUp.html",
		     arialLabelledBy: "modal-title-bottom",
		     arialLabelledBy: "modal-body-bottom",
		     arialLabelledBy: "modal-footer-bottom",
		     size:'sm',
		     controller:function( $uibModalInstance ){
		    	 this.id = toDo.id;
		    	 this.title = toDo.title;
		    	 this.note = toDo.note;
		    	 var $ctrl = this;
		    	 
		    	 
		    	 
		    	this.ok = function () {
		    		console.log("update ok");
					$uibModalInstance.close({id:$ctrl.id, title:$ctrl.title, note:$ctrl.note});
					
				};
				
				this.cancel = function () {
					console.log("update cancle");
				    $uibModalInstance.dismiss('cancel');
				};
		     },
		     
		     controllerAs :"$ctrl"
		});
		
		
		 modal.result.catch(function(error){
	        	console.log("error::",error); 
	        	
	         }).then(function(data){
	        	if(data) {
	        		console.log(data);
	        		$scope.toDoList.splice(index, 1, data);
	        		$scope.updateNote(data);
	        }
	    })
	    
	}
	
	
	$scope.updateNote = function( toDo ) {
		console.log("mobile");
		
		var updToDoObj = homeService.updateNote(toDo);
	}

	
	this.move = function() {
		if($scope.visible){
			$scope.move = {"margin-left":"10%","transition":"0.6s ease"}
		}
		else {
			$scope.move = {"margin-left":"0px","transition":"0.6s ease"}
		}
		
	}
	
	
	this.signOut = function() {
		
		var signoutObj = homeService.signOut();
		
		signoutObj.then = function(data) {
			
			if( data.status == 200 ){
				$state.go('signIn');
			}
			else {
				$state.go('signUp');
			}
		}.catch( function(error) {
			console.log(error);
			$state.go('signUp');
		});
	}
	
	
	
});



