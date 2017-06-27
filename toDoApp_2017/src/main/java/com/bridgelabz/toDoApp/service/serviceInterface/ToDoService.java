package com.bridgelabz.toDoApp.service.serviceInterface;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.bridgelabz.toDoApp.model.Collaborator;
import com.bridgelabz.toDoApp.model.ToDo;
import com.bridgelabz.toDoApp.model.User;

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
	public void addNote(ToDo toDo);

	/**
	 * to get all notes added for particular user
	 * 
	 * @param id
	 * @return {@link ResponseEntity}
	 */
	public List<ToDo> getNotes(User user);

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
	public void updateNote(ToDo toDo);

	/**
	 * @param copy
	 * @return
	 */
	public void copyToDo(ToDo copy);


	/**
	 * @param toDo
	 */
	public void update(ToDo toDo);

	/**
	 * to collaborate the todo with 
	 * 
	 * @param collaborator
	 * @param toDo
	 */
	public void collaborator(Collaborator collaborator, ToDo toDo);

	/**
	 * to set the index of the todo after the drag and drop
	 * 
	 * @param indexOb
	 */
	public void setIndex(List<Map<String,Integer>> indexOb);

}
