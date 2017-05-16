package com.bridgelabz.toDoApp.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.UserService;
import com.bridgelabz.toDoApp.vlidator.UserValidation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * this controller class handles the request "/signUp"
 * 
 * @author bridgeit vinay
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidation userValidation;

	/**
	 * to signUp for the new User
	 * 
	 * @param user
	 * @param bindingResult
	 * @param request
	 * @param response
	 * @return String message and status
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ResponseEntity<String> signUp(@RequestBody User user, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

		userValidation.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {

			List<ObjectError> errors = bindingResult.getAllErrors();
			Iterator<ObjectError> i = errors.iterator();
			while (i.hasNext()) {
				System.out.println(i.next());
			}

			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			
			//ObjectNode todo = mapper.createObjectNode();
			//todo.put("id", toDo.getId());
			
			root.put("status", "errors");
			
			//toDo.setUser(null);
			
			//root.putPOJO("todo", toDo); 
			
			// set("todo", todo);
			String data = mapper.writeValueAsString(root);
			System.out.println( data );
			
			return new ResponseEntity<String>(data, HttpStatus.NOT_ACCEPTABLE);
		} else {

			boolean result = userService.signUp(user);

			if (result) {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				
				//ObjectNode todo = mapper.createObjectNode();
				//todo.put("id", toDo.getId());
				
				root.put("status", "success");
				
				//toDo.setUser(null);
				
				//root.putPOJO("todo", toDo); 
				
				// set("todo", todo);
				String data = mapper.writeValueAsString(root);
				System.out.println( data ); 
				return new ResponseEntity<String>(data, HttpStatus.OK);
				
			} else {
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode root = mapper.createObjectNode();
				
				//ObjectNode todo = mapper.createObjectNode();
				//todo.put("id", toDo.getId());
				
				root.put("status", "failure");
				
				//toDo.setUser(null);
				
				//root.putPOJO("todo", toDo); 
				
				// set("todo", todo);
				String data = mapper.writeValueAsString(root);
				System.out.println( data ); 
				return new ResponseEntity<String>(data, HttpStatus.ALREADY_REPORTED);
			}
		}
	}
}
