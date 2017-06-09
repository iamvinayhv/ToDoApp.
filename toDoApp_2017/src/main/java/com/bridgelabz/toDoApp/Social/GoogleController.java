package com.bridgelabz.toDoApp.Social;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.model.GmailProfile;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.UserService;

@RestController
public class GoogleController {

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private GoogleConnection googleConnection;
	
	@RequestMapping(value="loginWithGoogle")
	public void googleConnection(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String unid = UUID.randomUUID().toString();
		request.getSession().setAttribute("STATE", unid);
		
		String googleLoginURL = googleConnection.getGoogleAuthURL(unid);
		
		System.out.println(googleLoginURL);
		response.sendRedirect(googleLoginURL);
		return;
	}
	
	
	@RequestMapping(value="googleConnection")
	public void redirectFromGoogle(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		System.out.println("sundar pinchai calling to toDoApp");
		
		String sessionState = (String) request.getSession().getAttribute("STATE");
		String googlestate = request.getParameter("state");
		
		System.out.println(googlestate);
		
		if( sessionState == null || !sessionState.equals(googlestate) ){
			response.sendRedirect("loginWithGoogle");
			return;
		}
		
		String error = request.getParameter("error");
		if( error != null && error.trim().isEmpty() ) {
			response.sendRedirect("signIn");
			return;
		}
		
		String authCode = request.getParameter("code");
		
		//System.out.println(authCode);
		
		String accessToken = googleConnection.getAccessToken(authCode);
		
		System.out.println("ACCess Token--> "+accessToken);
		
		//get user profile 
		
		GmailProfile profile= googleConnection.getUserProfile(accessToken);
		System.out.println(profile.getId());
		
		//User user = userService.getUserByEmail(profile.getId());
	}
}
