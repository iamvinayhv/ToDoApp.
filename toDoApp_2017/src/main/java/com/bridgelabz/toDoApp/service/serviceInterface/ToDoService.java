package com.bridgelabz.toDoApp.service.serviceInterface;

import java.util.List;

import com.bridgelabz.toDoApp.model.ToDo;

/**
 * @author bridgeit vinay
 *
 */
public interface ToDoService {

	/**
	 * to add the new Note
	 * 
	 * @param toDo
	 * @return true/false
	 */
	public boolean addNote(ToDo toDo);

	/**
	 * to get all notes added for particular user
	 * 
	 * @param id
	 * @return List
	 */
	public List<ToDo> getNotes(int userId);

	/**
	 * to delete the particular note by using id
	 * 
	 * @param id
	 * @return number of rows affected in the database
	 */
	public int deleteNote(int id);

	/**
	 * to update the particular note
	 * 
	 * @param toDo
	 * @return true/false
	 */
	public boolean updateNote(ToDo toDo);

	public boolean copyToDo(ToDo copy);

}
