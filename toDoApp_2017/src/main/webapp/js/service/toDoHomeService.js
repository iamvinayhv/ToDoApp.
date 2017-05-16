myToDo.service("homeService",function ($http) {
	this.signIn = function(user){ 
		return $http({
			url:"http://localhost:8012/toDoApp_2017/getNotes",
			method:"GET",
		});
	}
});