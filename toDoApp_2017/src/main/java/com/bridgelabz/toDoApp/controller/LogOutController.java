package com.bridgelabz.toDoApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * this class is used to logOut the user handles the "/signOut" request"
 * @author bridgeit vinay
 *
 */
@RestController
public class LogOutController {
	
	/**
	 * @param request
	 * @param response
	 * @return String message and Status
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value="/signOut")
	public ResponseEntity<String> logOut(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.removeAttribute("user");
			session.invalidate();
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			
			root.put("message", "you are Logged Out");
			
			String data = mapper.writeValueAsString(root);
			
			return new ResponseEntity<String>(data, HttpStatus.OK);
		}
		else {
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode root = mapper.createObjectNode();
			
			root.put("message", "You have to SignIn");
			
			String data = mapper.writeValueAsString(root);
			
			return new ResponseEntity<String>(data, HttpStatus.OK);
		}
	}
}
