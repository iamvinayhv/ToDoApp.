myToDo.controller("homeController", function($scope, $state, $uibModal, homeService) {
	
	var toDoList = [];
	var getToDoHtOb = homeService.getNotes();
	
	getToDoHtOb.then(function(data) {
		
		console.log("comming data "+data.status);
		
		if( data.status == 200 ) {
			console.log(data);
			$scope.toDoList = data.data.todo;
			
			/*if(localStorage.getItem("view") == "list") {
				$scope.list();
			}
			else if(localStorage.getItem("view") == "grid") {
				$scope.grid();
			}*/
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
	
	
	
	$scope.list = function() {
		
		console.log("list view btn");
		
		$scope.listType = true;
		$scope.gridType = false;
		
		$scope.listIcon = {
				"display":"none"
		}
		$scope.gridIcon = {
				"display":"block"
		}
	   /* localStorage.setItem("view", "list");*/
		
	}
	
	
	$scope.grid = function() {
		
		console.log("grid view btn");
		
		$scope.gridType = true;
		$scope.listType = false;
		
		$scope.listIcon = {
				"display":"block"
		}
		$scope.gridIcon = {
				"display":"none"
		}
	    /*localStorage.setItem("view", "grid");*/
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
	        	//$state.reload();
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
	
	
	
	this.signOut = function() {
		
		console.log("iiiiiiiiiiii");
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
	
	
	
	//$scope.toDoList = _.range(1, 5);
	  
	  
	  
	  
});



