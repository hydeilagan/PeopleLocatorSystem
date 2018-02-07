package com.java.pointwest.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.java.pointwest.bean.Employee;
import com.java.pointwest.bean.Project;
import com.java.pointwest.bean.Seat;
import com.java.pointwest.manager.EmployeeManager;

public class PeopleLocatorUi {
	Scanner myScanner = new Scanner(System.in);
	EmployeeManager empMgr = new EmployeeManager();
	Employee employee;
	Employee emp_login = new Employee();
	List<Employee> employeeList = new ArrayList<Employee>();

	public void displayLogin() {
		System.out.println("-----");
		System.out.println("LOGIN");
		System.out.println("-----");

		System.out.print("Username: ");
		String emp_username = myScanner.next();
		System.out.print("Password: ");
		String emp_password = myScanner.next();
		emp_login = empMgr.verifyLogin(emp_username, emp_password);
		if (emp_login != null) {
			System.out.println("Login Successful!");
			displayHomePage(emp_login);
		} else {
			System.out.println("Incorrect username/password! Pls try again");
			displayLogin();
		}
	}

	public void displayHomePage(Employee employee) {
		System.out.println("\n##HOME##");
		System.out.println(
				"Welcome " + employee.getFirstname() + " " + employee.getLastname() + "[" + employee.getRole() + "]!");
		System.out.println("MENU:");
		System.out.println("[1] Search");
		System.out.println("[2] View Seatplan");
		System.out.println("[3] Logout");
		System.out.print("Enter your choice: ");
		int homePageUserInput = myScanner.nextInt();
		displayHomeSubMenu(homePageUserInput);
	}

	public void displayHomeSubMenu(int homePageUserInput) {
		switch (homePageUserInput) {
		case 1:
			displaySearchMenu();
			break;
		case 2:
			displayViewSeatPlanMenu();
			break;
		case 3:
			System.out.println("Exit! Please login again");
			displayLogin();
			break;
		default:
			System.out.println("Please enter a valid input from 1 to 3 only!");
		}
	}

	public void displaySearchEmployee(int searchUserInput) {
		String project_holder = "";
		switch (searchUserInput) {
		case 1:
			System.out.println("\n##SEARCH - By Employee Id##");
			System.out.print("Enter Employee Id: ");
			String empIdUserInput = myScanner.next();
			employee = empMgr.displayEmployeeById(empIdUserInput);

			for (Project project : employee.getProjects()) {
				project_holder = project_holder + project.getName() + ",";
			}

			displaySearchResultHeaderNames();
			for (Seat seat : employee.getSeats()) {
				if (seat.getLocal().isEmpty()) {
					seat.setLocal("NONE");
				}
				System.out.println(employee.getId() + "|" + employee.getFirstname() + "|" + employee.getLastname() + "|"
						+ seat.getId() + seat.getFloor_number() + "F" + seat.getQuadrant() + seat.getRow_number() + "-"
						+ seat.getCol_number() + "|" + seat.getLocal() + "|" + employee.getShift() + "|"
						+ project_holder);
			}
			displayNextActionAfterSearch();
			break;
		case 2:
			System.out.println("\n##SEARCH - By Employee's Lastname##");
			System.out.print("Enter Employee's Lastname: ");
			String empLastNameUserInput = myScanner.next();
			myScanner.nextLine();
			employeeList = empMgr.displayEmployeeByLastName(empLastNameUserInput);

			displaySearchResultHeaderNames();
			for (Employee employee : employeeList) {
				for (Seat seat : employee.getSeats()) {
					if (seat.getLocal().isEmpty()) {
						seat.setLocal("NONE");
					}
					System.out.print(employee.getId() + "|" + employee.getFirstname() + "|" + employee.getLastname()
							+ "|" + seat.getLocation().getId() + seat.getFloor_number() + "F" + seat.getQuadrant()
							+ seat.getRow_number() + "-" + seat.getCol_number() + "|" + seat.getLocal() + "|"
							+ employee.getShift() + "|");
					for (Project project : employee.getProjects()) {
						System.out.print(project.getName() + ",");
					}
					System.out.println();
				}
			}

			displayNextActionAfterSearch();
			break;
		case 3:
			System.out.println("\n##SEARCH - By Project Name");
			System.out.print("Enter Employee's Project: ");
			String empProjUserInput = myScanner.next();
			employeeList = empMgr.displayEmployeeByProject(empProjUserInput);

			displaySearchResultHeaderNames();
			for (Employee employee : employeeList) {
				for (Seat seat : employee.getSeats()) {
					if (seat.getLocal().isEmpty()) {
						seat.setLocal("NONE");
					}
					System.out.print(employee.getId() + "|" + employee.getFirstname() + "|" + employee.getLastname()
							+ "|" + seat.getLocation().getId() + seat.getFloor_number() + "F" + seat.getQuadrant()
							+ seat.getRow_number() + "-" + seat.getCol_number() + "|" + seat.getLocal() + "|"
							+ employee.getShift() + "|");
					for (Project project : employee.getProjects()) {
						System.out.println(project.getName());
					}
				}
			}

			displayNextActionAfterSearch();
			break;
		default:
			System.out.println("Please enter valid number!");
			displaySearchMenu();

		}
	}

	public void displayViewSeatPlan(int viewSeatPlanUserInput) {
		switch (viewSeatPlanUserInput) {
		case 1:
			System.out.println("\n##VIEW SEATPLAN - By Location - Floor Level##");
			System.out.print("Enter Location: ");
			String locationUserInput = myScanner.next();
			System.out.print("Enter Floor Level: ");
			int floorLevelUserInput = myScanner.nextInt();
			employeeList = empMgr.displaySeatPlanByLocationAndFloor(locationUserInput, floorLevelUserInput);
			displayViewSeatPlanHeaderNames();
			for (Employee employee : employeeList) {
				System.out.print(employee.getLastname() + ", " + employee.getFirstname() + "|");
				for (Seat seat : employee.getSeats()) {
					if (seat.getLocal().isEmpty()) {
						seat.setLocal("NONE");
					}
					System.out.println(seat.getLocation().getId() + seat.getFloor_number() + "F" + seat.getQuadrant()
							+ seat.getRow_number() + "-" + seat.getCol_number() + " loc." + seat.getLocal());
				}
			}

			displayNextActionAfterViewSeatPlan();
			break;
		case 2:
			System.out.println("\n##VIEW SEATPLAN - By Quadrant##");
			System.out.print("Enter Location: ");
			locationUserInput = myScanner.next();
			System.out.print("Enter Floor Level: ");
			floorLevelUserInput = myScanner.nextInt();
			System.out.print("Enter quadrant: ");
			String quadrantUserInput = myScanner.next();
			employeeList = empMgr.displaySeatPlanByLocationAndFloorAndQuadrant(locationUserInput, floorLevelUserInput,
					quadrantUserInput);
			displayViewSeatPlanHeaderNames();
			for (Employee employee : employeeList) {
				System.out.print(employee.getLastname() + ", " + employee.getFirstname() +"|");
				for (Seat seat : employee.getSeats()) {
					if (seat.getLocal().isEmpty()) {
						seat.setLocal("NONE");
					}
					System.out.println(seat.getLocation().getId() + seat.getFloor_number() + "F" + seat.getQuadrant()
							+ seat.getRow_number() + "-" + seat.getCol_number() + " loc." + seat.getLocal());
				}
			}
			displayNextActionAfterViewSeatPlan();
			break;
		default:
			System.out.println("Please enter a valid input!");
		}
	}

	public void displaySearchMenu() {
		System.out.println("\n##SEARCH##");
		System.out.println("MENU:");
		System.out.println("[1]By Employee Id");
		System.out.println("[2]By Employee's Last Name");
		System.out.println("[3]By Project");
		System.out.print("Enter your choice: ");
		int searchUserInput = myScanner.nextInt();
		displaySearchEmployee(searchUserInput);
	}

	public void displayViewSeatPlanMenu() {
		System.out.println("\n##VIEW SEATPLAN##");
		System.out.println("MENU:");
		System.out.println("[1]By Location - Floor Level");
		System.out.println("[2]By Quadrant");
		System.out.print("Enter your choice: ");
		int viewSeatPlanUserInput = myScanner.nextInt();
		displayViewSeatPlan(viewSeatPlanUserInput);
	}

	public void displaySearchResultHeaderNames() {
		System.out.println("----------------------------------------------------------");
		System.out.println("EMPLOYEE ID|FIRSTNAME|LASTNAME|SEAT|LOCAL|SHIFT|PROJECT(S)");
		System.out.println("----------------------------------------------------------");
	}
	
	public void displayViewSeatPlanHeaderNames() {
		System.out.println("------------------------------");
		System.out.println("LASTNAME|FIRSTNAME|SEAT|LOCAL|");
		System.out.println("------------------------------");
	}

	public void displayNextActionAfterSearch() {
		System.out.print("\nACTIONS: ");
		System.out.print("[1] Search Again ");
		System.out.println("[2] Home ");
		System.out.print("Enter your choice : ");
		int nextActionUserInput = myScanner.nextInt();
		switch (nextActionUserInput) {
		case 1:
			displaySearchMenu();
			break;
		case 2:
			displayHomePage(emp_login);
			break;
		}
	}

	public void displayNextActionAfterViewSeatPlan() {
		System.out.print("\nACTIONS: ");
		System.out.print("[1] View Seatplan again ");
		System.out.println("[2] Home ");
		System.out.print("Enter your choice : ");
		int nextActionUserInput = myScanner.nextInt();
		switch (nextActionUserInput) {
		case 1:
			displayViewSeatPlanMenu();
			break;
		case 2:
			displayHomePage(emp_login);
			break;
		}
	}

}
