package com.bridgelabz.toDoApp.Social;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.toDoApp.model.FaceBookProfile;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.UserService;

@RestController
public class FaceBookControler {
	
	@Autowired
	FaceBookConnection connection;
	
	@Autowired
	private UserService userService;

	
	@RequestMapping(value="loginWithfacebook")
	public void fbConnecion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		String unid = UUID.randomUUID().toString();
		request.getSession().setAttribute("STATE", unid);
		
		String fbLoginURL = connection.getFbAuthURL(unid);
		
		System.out.println(fbLoginURL);
		
		response.sendRedirect(fbLoginURL);
		
		return;
		
		
	}
	
	
	@RequestMapping(value="fbConnection")
	public void redirectFromFacebook (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("mark zukarburg calling to todo App");
		
		
		String sessionState = (String) request.getSession().getAttribute("STATE");
		String fbState = request.getParameter("state");
		
		if( sessionState == null  || !sessionState.equals(fbState) ) {
			
			System.out.println("State is not matching or not found");
			response.sendRedirect("loginWithfacebook");
			return;
		}
		String error = request.getParameter("error");
		
		
		if( error != null && error.trim().isEmpty() ){
			System.out.println("getting error");
			response.sendRedirect("signIn");
			return;
		}
		
		String authCode = request.getParameter("code");
		
		String accessToken = connection.getAccessToken(authCode);
		
		FaceBookProfile profile = connection.getUserProfile(accessToken);
		
		User user=userService.getUserByEmail(profile.getEmail());
		if(user==null){
			user = new User();
			user.setName(profile.getName());
			user.setEmail(profile.getEmail());
			user.setPassword("");
			System.out.println(profile.getCover()+"vover");
			userService.signUp(user);
		}
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		System.out.println(profile.toString());
		response.sendRedirect("http://localhost:8080/toDoApp_2017/#!/home");
		
	}
	
}
