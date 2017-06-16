package com.bridgelabz.toDoApp.service.serviceImpl;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bridgelabz.toDoApp.dao.doaInterface.ToDoDao;
import com.bridgelabz.toDoApp.model.ToDo;
import com.bridgelabz.toDoApp.model.User;
import com.bridgelabz.toDoApp.service.serviceInterface.ToDoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ToDoServiceImpl implements ToDoService {
	
	@Autowired
	private ToDoDao toDoDao;

	@Override
	public boolean addNote(ToDo toDo) {
		
		return toDoDao.addNote(toDo);
	}

	@Override
	public String getNotes( User user ) throws Exception {

		final Logger logger = Logger.getLogger(ToDoServiceImpl.class);
		
		logger.setLevel(Level.WARN);
		logger.warn("inside get service");
		
		ObjectMapper mapper = null;
		ObjectNode root = null;
		
		if( user!= null ){
			
			List<ToDo> todolist = toDoDao.getNotes( user.getId() );
			
				
				Collections.reverse( todolist );

				mapper = new ObjectMapper();
				root = mapper.createObjectNode();
				
				root.put("status", 200);
				
				root.putPOJO("todo", todolist);
				
				root.putPOJO("user", user);
				
				String data = mapper.writeValueAsString(root);
				logger.warn("inside ghhhhhhhhhhhh");
				return data;
			
		}
		
		else{
			
			logger.error("Need to sign In");
			
			mapper = new ObjectMapper();
			root = mapper.createObjectNode();
			
			root.put("status", 100);
			
			String data = mapper.writeValueAsString(root);
			
			return data;
		}
		
		
	}

	
	
	@Override
	public int deleteNote(int id) {
		
		return toDoDao.deleteNote(id);
	}

	@Override
	public boolean updateNote(ToDo toDo) {
		
		return toDoDao.updateNote(toDo);
	}

	@Override
	public boolean copyToDo(ToDo copy) {
		
		return toDoDao.copyToDo(copy);
	}

	@Override
	public void update(ToDo toDo) {
		
		toDoDao.update(toDo);
	}

}
