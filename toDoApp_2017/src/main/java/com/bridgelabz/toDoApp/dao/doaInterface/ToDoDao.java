package com.bridgelabz.toDoApp.dao.doaInterface;

import java.util.List;

import com.bridgelabz.toDoApp.model.ToDo;

public interface ToDoDao {

	/**
	 * @param toDo
	 * @return true/false
	 */
	public boolean addNote(ToDo toDo);

	/**
	 * @param id
	 * @return List
	 */
	public List<ToDo> getNotes(int UserId);

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
	public boolean updateNote(ToDo toDo);

	/**
	 * @param copy
	 * @return true/false
	 */
	public boolean copyToDo(ToDo copy);

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

}
