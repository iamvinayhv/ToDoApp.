package com.bridgelabz.toDoApp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ToDo_Manager")
public class ToDo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	private int id;
	
	@Column(name="title", nullable=false)
	private String title;
	private String note;
	private Date remainder;
	private String color;
	private boolean pin;
	private boolean archive;
	private int indexValue;
	private Date upDated = new Date();
	
	private String sharedWithUserEmail;

	
	@ManyToOne(cascade = CascadeType.DETACH)
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getRemainder() {
		return remainder;
	}

	public void setRemainder(Date remainder) {
		this.remainder = remainder;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getUpDated() {
		return upDated;
	}

	public void setUpDated(Date upDated) {
		this.upDated = upDated;
	}

	public boolean isPin() {
		return pin;
	}

	public void setPin(boolean pin) {
		this.pin = pin;
	}
	
	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public int getIndexValue() {
		return indexValue;
	}

	public void setIndexValue(int indexValue) {
		this.indexValue = indexValue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public String getSharedWithUserEmail() {
		return sharedWithUserEmail;
	}

	public void setSharedWithUserEmail(String sharedWithUserEmail) {
		this.sharedWithUserEmail = sharedWithUserEmail;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", title=" + title + ", note=" + note + ", remainder=" + remainder + ", color="
				+ color + ", pin=" + pin + ", archive=" + archive + ", indexValue=" + indexValue + ", upDated="
				+ upDated + ", sharedWithUserEmail=" + sharedWithUserEmail + ", user=" + user + "]";
	}

	
}
