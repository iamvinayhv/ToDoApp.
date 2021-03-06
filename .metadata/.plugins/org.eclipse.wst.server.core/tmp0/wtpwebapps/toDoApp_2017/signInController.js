myToDo.controller("signInController",function ($scope,  $state, signInService, signInValidationService, toastr ) {	
	
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
				toastr.success('signIn successfull !');
			}
		})
		.catch( function(error){
			toastr.error('Invalid e-mail or password', 'Error');
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