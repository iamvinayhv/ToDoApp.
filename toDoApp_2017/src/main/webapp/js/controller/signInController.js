myToDo.controller("signInController",function ($scope,  $state, signInService, signInValidationService ) {	
	
	signInValidationService.signInvalidation();
	
     	$scope.signIn = function () {
     		
		var user = {};
		
		user.email=$scope.email;
		user.password=$scope.password;
		
		var httpObje=signInService.signIn(user);
		
		httpObje.then(function (data) {
			console.log(data);
			
			console.log(data.status);
			if( data.status == "200"){
				
				$state.go('home');
			}
		})
		.catch( function(error){
			
			$scope.errorMessage = error.data.status;
		});
	}	
});


myToDo.service("signInService",function ($http) {
	this.signIn = function(user){ 
		return $http({
			url:"http://localhost:8080/toDoApp_2017/signIn",
			method:"post",
			data:user
		});
	}
});