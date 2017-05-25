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
	
	
	
	this.makeCopy = function(toDo) {
		
		var copy = {};
		
		copy.title = toDo.title;
		copy.note = toDo.note;
		copy.remainder = toDo.remainder;
		
		console.log(toDo);
		var copyToDoObj = homeService.copyToDo(copy);
		
		copyToDoObj.then (function(data) {
			
			if( data.status == 200 ) {
				$scope.toDoList.push(data.data.todoCopy);
			}
		}).catch( function(error) {
			console.log(error);
			$state.go('signIn');
		});
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
	
	
	this.toToReminder = function(toDo, index, day) {
		
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
		        //$scope.showreminder = true;
		       
		       //$scope.refresh();
		    }
		}).catch(function(error) {
		    console.log(error);
		})
		
	}
	
	
	
	
	/*Callender*/
	
	  $scope.today = function() {
		    $scope.dt = new Date();
		  };
		  $scope.today();

		  $scope.clear = function() {
		    $scope.dt = null;
		  };

		  $scope.inlineOptions = {
		    customClass: getDayClass,
		    minDate: new Date(),
		    showWeeks: true
		  };

		  $scope.dateOptions = {
		    dateDisabled: disabled,
		    formatYear: 'yy',
		    maxDate: new Date(2020, 5, 22),
		    minDate: new Date(),
		    startingDay: 1
		  };

		  // Disable weekend selection
		  function disabled(data) {
		    var date = data.date,
		      mode = data.mode;
		   // return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
		  }

		  $scope.toggleMin = function() {
		    $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
		    $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
		  };

		  $scope.toggleMin();

		  $scope.open1 = function() {
		    $scope.popup1.opened = true;
		  };

		  

		  $scope.setDate = function(year, month, day) {
		    $scope.dt = new Date(year, month, day);
		  };

		  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
		  $scope.format = $scope.formats[0];
		  $scope.altInputFormats = ['M!/d!/yyyy'];

		  $scope.popup1 = {
		    opened: false
		  };

		  
		  var tomorrow = new Date();
		  tomorrow.setDate(tomorrow.getDate() + 1);
		  var afterTomorrow = new Date();
		  afterTomorrow.setDate(tomorrow.getDate() + 1);
		  $scope.events = [
		    {
		      date: tomorrow,
		      status: 'full'
		    },
		    {
		      date: afterTomorrow,
		      status: 'partially'
		    }
		  ];

		  function getDayClass(data) {
		    var date = data.date,
		      mode = data.mode;
		    if (mode === 'day') {
		      var dayToCheck = new Date(date).setHours(0,0,0,0);

		      for (var i = 0; i < $scope.events.length; i++) {
		        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

		        if (dayToCheck === currentDay) {
		          return $scope.events[i].status;
		        }
		      }
		    }

		    return '';
		  }
	
	
	
});

(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});

