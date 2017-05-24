package com.bridgelabz.toDoApp.dao.doaInterface;

import java.util.List;

import com.bridgelabz.toDoApp.model.ToDo;

public interface ToDoDao {

	/**
	 * to add new Note
	 * 
	 * @param toDo
	 * @return true/false
	 */
	public boolean addNote(ToDo toDo);

	/**
	 * to get all the notes for the particular user by passing userId
	 * 
	 * @param id
	 * @return List
	 */
	public List<ToDo> getNotes(int UserId);

	/**
	 * to delete the particular note by using noteId
	 * 
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

	public boolean copyToDo(ToDo copy);

}
