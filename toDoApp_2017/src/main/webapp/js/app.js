var myToDo = angular.module('toDoApp', ['ui.router']);

myToDo.config(function ($stateProvider, $urlRouterProvider) {
  
  $stateProvider
 
  .state('signIn',{
    url:"signIn",
    templateUrl:"html/signIn.html",
    controller:"signInController"
  })
  
  .state('signUp',{
    url:"signUp",
    templateUrl:"html/Signup.html",
    controller:"signUpController"
  
  })
  
    .state('home',{
	    url:"home",
	    templateUrl:"html/home.html",
	    controller:"toDoHomeController"
	  })
  
  $urlRouterProvider.otherwise('/signIn');

});