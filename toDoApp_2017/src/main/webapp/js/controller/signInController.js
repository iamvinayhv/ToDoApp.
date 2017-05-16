myToDo.controller("signInController",function ($scope,  $state, signInService, signInValidationService ) {	
	
	signInValidationService.signInvalidation();
	
     	$scope.signIn = function () {
     		
		var user = {};
		
		user.email=$scope.email;
		user.password=$scope.password;
		
		var httpObje=loginService.signIn(user);
		
		httpObje.then(function (data) {
			console.log(data);
			
			console.log(data.status);
			if( data.status=="200"){
					$state.go('home');
			}
			else
			{
				$scope.emailError = data.data.emailError;
				var message = data.data.message;
				$scope.errorMessage = message;
			}
		})
		.catch( function(error){
			console.log(error);
		});
	}	
});


myToDo.service("signInService",function ($http) {
	this.signIn = function(user){ 
		return $http({
			url:"http://localhost:8012/toDoApp_2017/signIn",
			method:"post",
			data:user
		});
	}
});