package com.java.pointwest.bean;

public class Seat {
	private String id;
	private Location location;
	private int floor_number;
	private String quadrant;
	private int row_number;
	private int col_number;
	private String local;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public int getFloor_number() {
		return floor_number;
	}
	public void setFloor_number(int floor_number) {
		this.floor_number = floor_number;
	}
	public String getQuadrant() {
		return quadrant;
	}
	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}
	public int getRow_number() {
		return row_number;
	}
	public void setRow_number(int row_number) {
		this.row_number = row_number;
	}
	public int getCol_number() {
		return col_number;
	}
	public void setCol_number(int col_number) {
		this.col_number = col_number;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	
}
