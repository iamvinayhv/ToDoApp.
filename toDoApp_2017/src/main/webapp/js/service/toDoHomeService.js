myToDo.service("homeService",function ($http) {
	
	this.getNotes = function(){
		return $http({
			url:"http://localhost:8080/toDoApp_2017/getNotes",
			method:"GET",
		});
	}
	
	
	this.addNote = function() {
		return $http({
			url:"http://localhost:8080/toDoApp_2017/addNote",
			method:"POST",
		});
	}
	
});