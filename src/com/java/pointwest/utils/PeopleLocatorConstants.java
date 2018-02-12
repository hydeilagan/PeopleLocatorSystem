package com.java.pointwest.utils;

public class PeopleLocatorConstants {
	
	public static final String DB_USERNAME = "newuse";
	public static final String DB_PASSWORD = "password123";
	public static final String CONNECTION_STRING = "jdbc:mysql://172.26.83.193:3306/plsdb";

	public static void displaySearchResultHeaderNames() {
		System.out.println("----------------------------------------------------------");
		System.out.println("EMPLOYEE ID|FIRSTNAME|LASTNAME|SEAT|LOCAL|SHIFT|PROJECT(S)");
		System.out.println("----------------------------------------------------------");
	}

	public static void displayViewSeatPlanHeaderNames1() {
		System.out.println("------------------------------");
		System.out.println("LASTNAME|FIRSTNAME|SEAT|LOCAL|");
		System.out.println("------------------------------");
	}
	
	
}
