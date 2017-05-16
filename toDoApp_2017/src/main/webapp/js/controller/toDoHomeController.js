myToDo.controller("homeController", function($scope, $state, homeService) {
	
	homeService.getNotes();
})