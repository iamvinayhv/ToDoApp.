var myToDo = angular.module("toDoApp", ['ui.router']);

myToDo.config(function ($stateProvider, $urlRouterProvider) {
  
  $stateProvider
 
  .state('signIn',{
    url:"/signIn",
    templateUrl:"template/signIn.html",
    controller:"signInController"
  })
  
  .state('signUp',{
    url:"/signUp",
    templateUrl:"template/signUp.html",
    controller:"signUpController"
  
  })
  
    .state('home',{
	    url:"/home",
	    templateUrl:"template/home.html",
	    controller:""
	  })
  
  $urlRouterProvider.otherwise('/signUp');

});