myToDo.controller("homeController", function($scope, $state, $uibModal, homeService) {
	
	var toDoList = [];
	var user = [];
	var getToDoHtOb = homeService.getNotes();
	
	
	getToDoHtOb.then(function(data) {
		
		console.log("comming data "+data.status);
		$scope.user = data.data.user;
		
		if( data.status == 200 ) {
			console.log(data);
			$scope.toDoList = data.data.todo;
			$scope.toDoPin = data.data.todo;
			/*if(data.data.todo.pin == true ){
				$scope.toDoPin = data.data.todo;
			}*/
			
			console.log($scope.toDoPin);
		}
		else{
			$state.go('signUp');
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
					$scope.toDoList.unshift(data.data.todo);
					console.log(data.data.todo);
					$scope.toDo.title=null;
					$scope.toDo.note=null;
					$scope.toDo.color=null;
					$scope.toDo.remainder=null;
					$scope.toDo.pin=null;
					
				}
				else{
					$state.go('signIn');
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
					console.log(index);
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
	
	this.clear = function() {
		$scope.searchText = undefined;
		$scope.clear = $scope.clear ? false :false;
	}
	
	
	var homeCont=this;
	this.popUp = function(toDo, index) {
		console.log(toDo+index);
			var modal = $uibModal.open({
		     templateUrl: "template/popUp.html",
		     size:'md',
		     controller:function( $uibModalInstance ){
		    	 this.index = index;
		    	 this.id = toDo.id;
		    	 this.title = toDo.title;
		    	 this.note = toDo.note;
		    	 this.remainder = toDo.remainder;
		    	 this.upDated = toDo.upDated;
		    	 this.color = toDo.color;
		    	 this.pin = toDo.pin;
		    	 this.archive = toDo.archive;
		    	 var $ctrl = this;
		    	 
		    	 
		    	 
		    	this.ok = function () {
		    		
					$uibModalInstance.close({id:$ctrl.id, title:$ctrl.title, note:$ctrl.note, remainder:$ctrl.remainder, color:$ctrl.color, pin:$ctrl.pin, archive:$ctrl.archive});
					
				};
				
				this.cancel = function () {
					
				    $uibModalInstance.dismiss('cancel');
				};
				
				this.deleteTodo = function(id) {
					
					homeCont.deleteNote(id, index);
					$uibModalInstance.dismiss('cancel');
				};
				
				this.makeCopy = function(toDo) {
					
					toDo.id=null;//not required
					homeCont.makeCopy(toDo);
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
		toDo.upDated = new Date();
		
		var updToDoObj = homeService.updateNote(toDo);
		
		updToDoObj.then(function(data) {
			if(data.status == 200) {
				
			}
		}).catch(function(error) {
			$state.go('signIn');
		});
	}

	
	
	
	this.move = function() {
		if($scope.visible){
			$scope.move = {"margin-left":"10%","transition":"0.4s ease"}
		}
		else {
			$scope.move = {"margin-left":"0px","transition":"0.4s ease"}
		}
		
	}
	
	
	
	this.makeCopy = function(toDo) {
		
		var copy = {};
		
		copy.title = toDo.title;
		copy.note = toDo.note;
		copy.remainder = toDo.remainder;
		copy.color = toDo.color;
		
		console.log(toDo);
		var copyToDoObj = homeService.copyToDo(copy);
		
		copyToDoObj.then (function(data) {
			
			if( data.status == 200 ) {
				$scope.toDoList.unshift(data.data.todoCopy);
			}
		}).catch( function(error) {
			console.log(error);
			$state.go('signIn');
		});
	}
	

	
	this.toToReminder = function(toDo, day) {
		
		var today = new Date();
		var httpObjRem ;
		
		if( day == "Today" ) {
			today.setHours(20, 00, 00);
            console.log(today);
            toDo.remainder = today;
            console.log(toDo);
            httpObjRem = homeService.toToReminder(toDo);
		}
		else if( day == "Tomorrow") {
			 var tomorrow = new Date(today);
			tomorrow.setDate(tomorrow.getDate() + 1);
			tomorrow.setHours(08, 00, 00);
            toDo.remainder = tomorrow;
            httpObjRem = homeService.toToReminder(toDo);
		}
		else if( day == "Next-Week") {
			var nextWeek = new Date(today);
			nextWeek.setDate(nextWeek.getDate() + 7)
			nextWeek.setHours(08, 00, 00);
            toDo.remainder = nextWeek;
            httpObjRem = homeService.toToReminder(toDo);
		}
		
		httpObjRem.then(function(data) {
		    
		    if ( data.data.status == 200 ) {
		        console.log(data);
		       
		    }
		}).catch(function(error) {
		    console.log(error);
		    $state.go('signIn');
		});
		
	}
	
	
	/*this.updateReminder = function(toDo, remainder) {
		console.log(toDo+" "+remainder);
	}*/
	
	
	
	this.cancelRemainder = function(todo) {
		console.log(todo.remainder);
		todo.remainder = null;
		console.log(todo.remainder);
		var cancelObj = homeService.cancelRemainder(todo);
		
		cancelObj.then(function(data) {
			
			if( data.status == 200 ) {
			}
		}).catch(function(error) {
			console.log(error);
			$state.go('signIn');
		})
	}
	
	
	this.setColor = function(todo, color) {
		todo.color = color;
		console.log(todo+" "+color);
		var colorObj = homeService.setColor(todo);
		
		colorObj.then(function(data) {
			
			if( data.status == 200 ) {
				console.log("color set ogaya");
			}
		}).catch(function(error) {
			console.log(error);
			$state.go('signIn');
		})
	}
	
	
	
	this.refresh = function() {
		$state.reload();
	}
	
	
	
	this.pinUp = function(toDo) {
		toDo.pin = true;
		var pinObj = homeService.pinUp(toDo);
		
		pinObj.then( function(data) {
			if( data.status == 200 ){
				//$scope.toDoList.splice(index, 1);
			}
		})
		
	}
	
	
	this.unPin = function(toDo) {
		toDo.pin = false;
		var pinObj = homeService.unPin(toDo);
		
		pinObj.then( function(data) {
			if( data.status == 200 ){
				//$scope.toDoList.splice(index, 1);
			}
		})
		
	}
	
	
	this.archive = function(toDo) {
		toDo.archive = true;
		toDo.pin = false;
		var archObj = homeService.archive(toDo);
		
		archObj.then( function(data) {
			if( data.status == 200 ){
				
			}
		})
	}
	
	
	this.pinAndUnarch = function(toDo) {
		toDo.pin = true;
		toDo.archive = false;
		var archObj = homeService.pinAndUnarch(toDo);
		
		archObj.then( function(data) {
			if( data.status == 200 ){
				
			}
		})
	}
	
	this.unArchive = function(toDo) {
		toDo.archive = false;
		var archObj = homeService.unArchive(toDo);
		
		archObj.then( function(data) {
			if( data.status == 200 ){
				
			}
		})
	}
	
	
	this.showArchive = function() {
		
	}
	
	
	this.signOut = function() {
		
		var signoutObj = homeService.signOut();
		
		signoutObj.then( function(data) {
			
			if( data.status == 200 ){
				$state.go('signIn');
			}
			else{
				$state.go('signUp');
			}
			
		}).catch( function(error) {
			console.log(error);
			$state.go('signUp');
		});
	}
	
	
	
});

