package com.surajinc.mytickets.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="User")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public User(){
		
	}
	@Id
	@Column(name="USER_ID")
	private String email;
	
	@Column(nullable=false)
	private String firstName;

	@Column
	private String lastName;
	
	@Column(nullable=false)
	private String password;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date dob;
	
	@Column
	private String role;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private List<Bookings> bookings = new ArrayList<Bookings>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private List<Comments> userComments= new ArrayList<Comments>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Bookings> getBookings() {
		return bookings;
	}

	public void setBookings(List<Bookings> bookings) {
		this.bookings = bookings;
	}
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<Comments> getUserComments() {
		return userComments;
	}

	public void setUserComments(List<Comments> userComments) {
		this.userComments = userComments;
	}	
	
	
}
