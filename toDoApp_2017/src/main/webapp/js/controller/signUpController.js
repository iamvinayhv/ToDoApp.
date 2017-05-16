myToDo.controller("SignUpController",function ($scope,  $state, signUpService ,signUpValidationService) {	
	
	signUpValidationService.signUpValidation();
	
	
     	$scope.signUp = function () {
     		
		var user = {};
		
		user.firstName = $scope.firstName;
		user.lastname = $scope.lastName;
		user.mobile = $scope.mobile;
		user.email = $scope.email;
		user.password = $scope.password;
		
		var httpObje = signUpService.signUp(user);
		
		console.log(user);
		
		httpObje.then(function (data) {
			
			console.log(data);
			console.log(data.status);
			if( data.status == "200" ){
					$state.go('signIn');
			}
			else
			{
				$scope.emailError = data.data.emailError;
				var message = data.data.message;
				$scope.errorMessage = message;
			}
		}).catch( function(error){
			
			console.log(error);
		});
	}	
});


myToDo.service("signUpService",function ($http) {
	this.signUp = function(user){ 
		return $http({
			url:"http://localhost:8080/toDoApp_2017/signUp",
			method:"post",
			data:user
		});
	}
});