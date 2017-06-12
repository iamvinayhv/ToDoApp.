package com.bridgelabz.toDoApp.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.toDoApp.dao.doaInterface.ToDoDao;
import com.bridgelabz.toDoApp.model.ToDo;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.ToDoService;

public class ToDoServiceImpl implements ToDoService {
	
	@Autowired
	private ToDoDao toDoDao;

	@Override
	public boolean addNote(ToDo toDo) {
		
		return toDoDao.addNote(toDo);
	}

	@Override
	public List<ToDo> getNotes(int userId) {

		return toDoDao.getNotes(userId);
	}

	@Override
	public int deleteNote(int id) {
		
		return toDoDao.deleteNote(id);
	}

	@Override
	public boolean updateNote(ToDo toDo) {
		
		return toDoDao.updateNote(toDo);
	}

	@Override
	public boolean copyToDo(ToDo copy) {
		
		return toDoDao.copyToDo(copy);
	}

	/*@Override
	public void setReminder(ToDo toDo) {
		
		toDoDao.setReminder(toDo);
	}

	@Override
	public void cancelRemainder(ToDo toDo) {
		
		toDoDao.cancelRemainder(toDo);
	}*/

	@Override
	public void update(ToDo toDo) {
		
		toDoDao.update(toDo);
	}

}
