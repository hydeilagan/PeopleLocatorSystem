package com.java.pointwest.bean;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private String username;
	private String password;
	private String id;
	private String firstname;
	private String lastname;
	private String role;
	private int local;
	private String shift;
	private List<Project> projects;
	private List<Seat> seats;

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Seat> getSeats() {
		if (seats == null) {
			seats = new ArrayList<Seat>();
		}
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getLocal() {
		return local;
	}

	public void setLocal(int local) {
		this.local = local;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
