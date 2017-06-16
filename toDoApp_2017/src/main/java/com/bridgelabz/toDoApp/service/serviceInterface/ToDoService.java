package com.bridgelabz.toDoApp.service.serviceInterface;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.bridgelabz.toDoApp.model.ToDo;
import com.bridgelabz.toDoApp.model.User;
import com.fasterxml.jackson.core.JsonParseException;

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
	 * @return {@link ResponseEntity}
	 */
	public String getNotes(User user) throws Exception;

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

	/**
	 * @param copy
	 * @return
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
