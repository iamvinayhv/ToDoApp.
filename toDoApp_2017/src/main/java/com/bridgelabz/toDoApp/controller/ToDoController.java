package com.bridgelabz.toDoApp.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.model.ToDo;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.ToDoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * this is the Controller class,it handles some the requests which are used to
 * managing the ToDo
 * 
 * @author bridgeit vinay
 */
@RestController
public class ToDoController {

	@Autowired
	private ToDoService toDoService;

	
	
	/**
	 * to add the new note
	 * 
	 * @param toDo
	 * @param request
	 * @param response
	 * @return String message and status
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/addNote", method = RequestMethod.POST)
	public ResponseEntity<String> addNote(@RequestBody ToDo toDo, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

		System.out.println("Add note java");
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		if ( session != null && user != null ) {

			toDo.setUser(user);
			
			System.out.println(toDo.getId());

			boolean result = toDoService.addNote(toDo);
			
			System.out.println(toDo.getId());

			if (result) {
				
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				
				
				root.put("status", "sucess");
				//toDo.setUser(null);
				root.putPOJO("todo", toDo); 
				
				String data = mapper.writeValueAsString(root);
				
				System.out.println( data );
				return new ResponseEntity<String>(data, HttpStatus.OK);
			} else {
				

				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				
				root.put("status", "failure");
				
				String data = mapper.writeValueAsString(root);
				
				System.out.println( data );
				
				return new ResponseEntity<String>(data,HttpStatus.NO_CONTENT);
			}
		}

		else {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			
			root.put("status", "failure");

			String data = mapper.writeValueAsString(root);
			System.out.println( data ); 
			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);		}
	}
	
	
	

	/**
	 * to get all the notes by using the usedId
	 * 
	 * @param UserId
	 * @param request
	 * @param response
	 * @return String message and status
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "getNotes", method = RequestMethod.GET)
	public ResponseEntity<String> getNotes( HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

		System.out.println("Comming");
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		if (user != null && session != null) {
			List<ToDo> todoList = toDoService.getNotes(user.getId());
			System.out.println(user.getId());
			
			if (  !todoList.isEmpty()  ) {


				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				
				root.put("status", "success");
				
				root.putPOJO("todo", todoList); 
				
				String data = mapper.writeValueAsString(root);
				System.out.println( data ); 
				
				
				return new ResponseEntity<String>(data, HttpStatus.OK);
				
			} else {
				
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				
				root.put("status", "notes are not added");
				
				
				root.putPOJO("todo", todoList); 
				
				String data = mapper.writeValueAsString(root);
				System.out.println( data ); 
				
				return new ResponseEntity<String>(data, HttpStatus.NOT_FOUND);
				
			}
		}
		else {
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			
			root.put("status", "notes are not added");
		
			String data = mapper.writeValueAsString(root);
			System.out.println( data ); 
			
			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		}
	}

	
	
	
	/**
	 * to delete the particular note
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return String message and status
	 * @throws JsonProcessingException 
	 */
	
	@RequestMapping(value = "deleteNote/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> deleteNote(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		if (session != null && user != null) {

			int result = toDoService.deleteNote(id);

			if (result != 0) {
				
				List<ToDo> todoList = toDoService.getNotes(user.getId());
				
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				
				root.put("status", "success");
				root.putPOJO("todo", todoList);
				
				String data = mapper.writeValueAsString(root);
				System.out.println( data ); 
				
				return new ResponseEntity<String>(data, HttpStatus.OK);
				
			} else {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				
				root.put("status", "toDoNot there");
				String data = mapper.writeValueAsString(root);
				System.out.println( data ); 
				
				return new ResponseEntity<String>(data, HttpStatus.NOT_FOUND);
			}
		}
		else{
		
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			
			root.put("status", "signIn first");
			String data = mapper.writeValueAsString(root);
			System.out.println( data );
		
		return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);
		
		}
	}

	/**
	 * to update the note
	 * 
	 * @param toDo
	 * @param toDoid
	 * @param request
	 * @param response
	 * @return String message and status
	 */
	@RequestMapping(value = "editNote/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateNote(@RequestBody ToDo toDo, @PathVariable("id") int toDoid,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);

		if (session != null) {

			User user = (User) session.getAttribute("user");
			toDo.setUser(user);
			toDo.setId(toDoid);

			List<ToDo> toDoList = toDoService.getNotes(user.getId());

			Iterator<ToDo> i = toDoList.iterator();
			boolean flag = false;

			while (i.hasNext()) {
				ToDo do1 = i.next();
				if (do1.getId() == toDo.getId()) {
					flag = true;
				}
			}

			if (!toDoList.isEmpty() && flag) {
				boolean result = toDoService.updateNote(toDo);
				if (result) {
					return new ResponseEntity<String>("updated", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("not updated", HttpStatus.NOT_ACCEPTABLE);
				}
			} else {
				return new ResponseEntity<String>("you dont have any notes", HttpStatus.NOT_ACCEPTABLE);
			}
		} else {
			return new ResponseEntity<String>("Login first", HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
