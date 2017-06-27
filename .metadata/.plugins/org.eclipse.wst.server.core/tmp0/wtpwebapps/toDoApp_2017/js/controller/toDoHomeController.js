myToDo.controller("homeController", function($scope, $state, $uibModal, toastr, homeService) {
	
	var toDoList = [];
	var toDoPin =[];
	var toDoOthers =[];
	var user = [];
	var collaborator = {};
	
	var vi = localStorage.getItem('view');
	
	console.log(vi === 'true');
	

	console.log(vi === 'false');
	$scope.isList = vi === 'true';
	
	
	this.viewChange = function() {
		console.log(typeof $scope.isList)
		localStorage.setItem('view',$scope.isList);
		console.log(typeof localStorage.getItem('view'));
	}
	
	
	var getToDoHtOb = homeService.getNotes();
	
	getToDoHtOb.then(function(data) {
		
		console.log(data);
		$scope.user = data.data.user;
		
		if( data.status == 200 ) {
			
			$scope.toDoList = data.data.todo;
			
		}
		
	}).catch( function(error) {
		//$scope.isList = true;
		$state.go('signIn');
	});
		
	
	
	
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
					toastr.success('Note added successfully !');
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
					toastr.success('Note deleted successfully !');
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
	this.collaborator = function(toDo,user) {
		
		var modal = $uibModal.open({
			templateUrl : "template/collaborator.html",
			size:'md',
			controller:function( $uibModalInstance ){
				
				
				this.id = toDo.id;
		    	this.title = toDo.title;
		    	this.note = toDo.note;
		    	this.remainder = toDo.remainder;
		    	this.upDated = toDo.upDated;
		    	this.color = toDo.color;
		    	this.pin = toDo.pin;
		    	this.archive = toDo.archive;
				this.name = user.name;
				this.email = user.email;
				this.sharedWithUserEmail;
				
				var $ctrlCollab = this;
				
				this.save = function () {
					console.log("ok");
					$uibModalInstance.close({toDo,sharedWith:$ctrlCollab.sharedWithUserEmail});
					
				};
				
				this.cancel = function () {
					
				    $uibModalInstance.dismiss('cancel');
				};
				
			},
			
			controllerAs : "$ctrlCollab"
		});
		
		 modal.result.catch(function(error){
	        	console.log("error::",error); 
	        	
	         }).then(function(data){
	        	if(data) {
	        		console.log(data);
	        		homeCont.collaborat(data);
	        }
	    })
	}
	
	
	this.collaborat = function(data) {
		
		if( data.sharedWith != undefined )
			var colObj = homeService.collaborator(data);
	}
	
	
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
				toastr.success('Note Coppied!');
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
            toastr.success('Remainder Setted !');
		}
		else if( day == "Tomorrow") {
			 var tomorrow = new Date(today);
			tomorrow.setDate(tomorrow.getDate() + 1);
			tomorrow.setHours(08, 00, 00);
            toDo.remainder = tomorrow;
            httpObjRem = homeService.toToReminder(toDo);
            toastr.success('Remainder Setted !');
		}
		else if( day == "Next-Week") {
			var nextWeek = new Date(today);
			nextWeek.setDate(nextWeek.getDate() + 7)
			nextWeek.setHours(08, 00, 00);
            toDo.remainder = nextWeek;
            httpObjRem = homeService.toToReminder(toDo);
            toastr.success('Remainder Setted !');
		}
		
		httpObjRem.then(function(data) {
		    
		    if ( data.data.status == 200 ) {
		    	toastr.success('Remainder Setted !');
		       
		    }
		}).catch(function(error) {
		    console.log(error);
		    $state.go('signIn');
		});
		
	}
	
	
	
	
	this.cancelRemainder = function(todo) {
		console.log(todo.remainder);
		todo.remainder = null;
		
		var cancelObj = homeService.cancelRemainder(todo);
		
		cancelObj.then(function(data) {
			
			if( data.status == 200 ) {
				toastr.success('Remainder Canceled !');
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
				toastr.success('Color of note changed !');
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
	
	
	this.shareWithFb = function(toDo) {
		console.log("fb");
		FB.init({appId: '458817991138556',
			status: true,
			xfbml: true
		});
		
		FB.ui({
			method: 'share_open_graph',
			action_type: 'og.shares',
			action_properties: JSON.stringify({
				object:{
					'og:title' : toDo.title,
					'og:description' : toDo.note
				}
			})
		},function(response){
			if(response && !response.error_message){
				console.log("Added");
			}
			else{
				console.log("not ADded");
			}
		});
	
	}
	
	this.showArchive = function() {
		
	}
	
	
	this.profile = function(user) {
		console.log(user);
		var modal = $uibModal.open({
			templateUrl : "template/profile.html",
			size:'md',
			controller:function( $uibModalInstance ){
				
				this.user = user;
				
				 $scope.myImage='';
				    $scope.myCroppedImage='';

				    var handleFileSelect=function(evt) {
				        var file=evt.currentTarget.files[0];
				        var reader = new FileReader();
				        reader.onload = function (evt) {
				          $scope.$apply(function($scope){
				            $scope.myImage=evt.target.result;
				          });
				        };
				        reader.readAsDataURL(file);
				      };
				    
		angular.element(document.querySelector('#fileInput')).on('change',handleFileSelect);
				
				this.save = function () {
					console.log("ok");
					$uibModalInstance.close({});
					
				};
				
				this.cancel = function () {
					
				    $uibModalInstance.dismiss('cancel');
				};
				
			},
			
			controllerAs : "$ctrlCollab"
		});
		
		 modal.result.catch(function(error){
	        	console.log("error::",error); 
	        	
	         }).then(function(data){
	        	if(data) {
	        		console.log(data.sharedWith);
	        		homeCont.collaborat(data.sharedWith);
	        }
	    })
	}
	
	
	
	
	this.signOut = function() {
		
		var signoutObj = homeService.signOut();
		
		signoutObj.then( function(data) {
			
			if( data.status == 200 ){
				$state.go('signIn');
				toastr.success('you are signed Out from ToDoApp !');
			}
			else{
				$state.go('signUp');
			}
			
		}).catch( function(error) {
			console.log(error);
			$state.go('signUp');
		});
	}
	
	
	$( function() {
	    $( ".cardRow" ).sortable({
		//items: '.cardRow',
		opacity: 1,
		axis: 'z',
		update: function(e, ui) {
			var allIndex = [];
			$(".slides").each( function(index) {
				var position = {};
				position.id = $(this).attr("id");
				position.index = index;
				allIndex.push(position);
				
			});
			console.log(allIndex);
			
	        var data = $(this).sortable('serialize');
	        var indexObj = homeService.setIndex(allIndex);
	    }
		});
	   
	 } );



});








