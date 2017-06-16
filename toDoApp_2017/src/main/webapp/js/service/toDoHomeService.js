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
	
	this.updateNote = function(toDo) {
		
		return $http({
			url:"http://localhost:8080/toDoApp_2017/updateNote",
			method:"POST",
			data:toDo
		});
	}
	
	this.copyToDo = function(toDo) {
		return $http({
			url:"http://localhost:8080/toDoApp_2017/copyToDo",
			method:"POST",
			data:toDo
		});
	}
	
	this.toToReminder = function(toDo) {
		return $http({
			url:"http://localhost:8080/toDoApp_2017/update",
			method:"POST",
			data:toDo
		});
	}
	
	this.cancelRemainder = function(toDo) {
		return $http({
			url:"http://localhost:8080/toDoApp_2017/update",
			method:"POST",
			data:toDo
		});
	}
	
	this.setColor = function(toDo) {
		return $http({
			url:"http://localhost:8080/toDoApp_2017/update",
			method:"POST",
			data:toDo
		});
	}
	
	this.pinUp = function(toDo) {
		return $http({
			url:"http://localhost:8080/toDoApp_2017/update",
			method:"POST",
			data:toDo
		});
	}
	
	
	this.unPin = function(toDo) {
		return $http({
			url:"http://localhost:8080/toDoApp_2017/update",
			method:"POST",
			data:toDo
		});
	}
	
	this.archive = function(toDo) {
		return $http({
			url:"http://localhost:8080/toDoApp_2017/update",
			method:"POST",
			data:toDo
		});
	}
	
	this.pinAndUnarch = function(toDo) {
		return $http({
			url:"http://localhost:8080/toDoApp_2017/update",
			method:"POST",
			data:toDo
		});
	}
	
	this.unArchive = function(toDo) {
		return $http({
			url:"http://localhost:8080/toDoApp_2017/update",
			method:"POST",
			data:toDo
		});
	}
	
	this.collaborator = function(shareWith) {
		return $http({
			url:"http://localhost:8080/toDoApp_2017/collaborator",
			method:"POST",
			data:shareWith
		});
	}
	
	this.signOut = function() {
		return $http({
			url:"http://localhost:8080/toDoApp_2017/signOut",
			method:"POST"
		});
	}
	
	
	
	
});

