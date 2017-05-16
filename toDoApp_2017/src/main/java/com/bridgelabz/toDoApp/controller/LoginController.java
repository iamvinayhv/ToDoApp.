package com.bridgelabz.toDoApp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * this is the login controller class it handles the request "/signIn"
 * 
 * @author bridgeit vinay
 *
 */
@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	/**
	 * to authenticate the user
	 * 
	 * @param loginMap
	 * @param bindingResult
	 * @param request
	 * @param response
	 * @return String message and status
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public ResponseEntity<String> logIn(@RequestBody Map<String, String> loginMap, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		

		User user = userService.authUser(loginMap.get("email"), loginMap.get("password"));

		if (user != null ) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);

				
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
			return new ResponseEntity<String>(data, HttpStatus.UNAUTHORIZED);		}
	}
}
