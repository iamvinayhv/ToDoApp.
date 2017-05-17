myToDo.service("homeService",function ($http) {
	
	this.getNotes = function(){
		return $http({
			url:"http://localhost:8080/toDoApp_2017/getNotes",
			method:"GET",
		});
	}
	
	
	this.addNote = function(toDo) {
		return $http({
			url:"http://localhost:8080/toDoApp_2017/addNote",
			method:"POST",
			data:toDo
		});
	}
	
	this.deleteNote = function(id) {
		
		return $http({
			url:"http://localhost:8080/toDoApp_2017/deleteNote/"+id,
			method:"POST"
		});
	}
	
});