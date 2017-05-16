package com.bridgelabz.toDoApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	 */
	@RequestMapping(value="/signOut")
	public ResponseEntity<String> logOut(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.removeAttribute("user");
			session.invalidate();
			return new ResponseEntity<String>("you are Logged Out!!! ThankYou",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("SignIn First",HttpStatus.BAD_REQUEST);
		}
	}

}
