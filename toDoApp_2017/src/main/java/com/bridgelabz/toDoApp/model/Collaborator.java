package com.bridgelabz.toDoApp.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Collaborator implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="gen", strategy="increment")
	@GeneratedValue(generator="gen")
	private int id;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn
	private ToDo toDo;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn
	private User user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ToDo getToDo() {
		return toDo;
	}
	public void setToDo(ToDo toDo) {
		this.toDo = toDo;
	}
	public User getSharedWith() {
		return user;
	}
	public void setSharedWith(User user) {
		this.user = user;
	}
	
	
}
