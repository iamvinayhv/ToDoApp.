package com.bridgelabz.toDoApp.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.toDoApp.dao.doaInterface.ToDoDao;
import com.bridgelabz.toDoApp.model.Collaborator;
import com.bridgelabz.toDoApp.model.ToDo;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService {
	
	@Autowired
	private ToDoDao toDoDao;

	@Override
	@Transactional
	public boolean addNote(ToDo toDo) {
		
		return toDoDao.addNote(toDo);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ToDo> getNotes(User user) {

		List<ToDo> myNotes = toDoDao.getNotes(user);
		
		List<ToDo> sharedNotes = toDoDao.getSharedNotes(user);
		
		if(sharedNotes != null)
			myNotes.addAll(sharedNotes);
		
		return myNotes;
	}

	
	
	@Override
	@Transactional
	public int deleteNote(int id) {
		
		return toDoDao.deleteNote(id);
	}

	@Override
	@Transactional
	public boolean updateNote(ToDo toDo) {
		
		return toDoDao.updateNote(toDo);
	}

	@Override
	@Transactional
	public boolean copyToDo(ToDo copy) {
		
		return toDoDao.copyToDo(copy);
	}

	@Override
	@Transactional
	public void update(ToDo toDo) {
		
		toDoDao.update(toDo);
	}

	@Override
	@Transactional
	public boolean collaborator(Collaborator collaborator) {
		
		return toDoDao.collaborator(collaborator);
	}

}
