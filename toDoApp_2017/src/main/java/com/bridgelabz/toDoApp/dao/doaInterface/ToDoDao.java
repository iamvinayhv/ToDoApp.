package com.bridgelabz.toDoApp.dao.doaInterface;

import java.util.List;

import com.bridgelabz.toDoApp.model.Collaborator;
import com.bridgelabz.toDoApp.model.ToDo;
import com.bridgelabz.toDoApp.model.User;

public interface ToDoDao {

	/**
	 * @param toDo
	 * @return true/false
	 */
	public void addNote(ToDo toDo);

	/**
	 * @param id
	 * @return List
	 */
	public List<ToDo> getNotes(User user);

	/**
	 * @param id
	 * @return int number of rows affected
	 */
	public int deleteNote(int id);

	/**
	 * to update the particular note
	 * 
	 * @param toDo
	 * @return true/false
	 */
	public void updateNote(ToDo toDo);

	/**
	 * @param copy
	 * @return true/false
	 */
	public void copyToDo(ToDo copy);

	/**
	 * @param toDo
	 *//*
	public void setReminder(ToDo toDo);

	*//**
	 * @param toDoId
	 *//*
	public void cancelRemainder(ToDo toDo);*/

	/**
	 * @param toDo
	 */
	public void update(ToDo toDo);

	public void collaborator(Collaborator collaborator);

	public List<ToDo> getSharedNotes(User user);

}
