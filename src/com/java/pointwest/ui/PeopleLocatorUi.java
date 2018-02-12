package com.java.pointwest.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.java.pointwest.bean.Employee;
import com.java.pointwest.bean.Location;
import com.java.pointwest.bean.Project;
import com.java.pointwest.bean.Seat;
import com.java.pointwest.bean.SeatMap;
import com.java.pointwest.manager.EmployeeManager;
import com.java.pointwest.manager.SeatMapManager;
import com.java.pointwest.utils.PeopleLocatorConstants;

public class PeopleLocatorUi {
	Scanner myScanner = new Scanner(System.in);
	EmployeeManager empMgr = new EmployeeManager();
	SeatMapManager seatMapMgr = new SeatMapManager();
	Employee employee;
	Employee emp_login = new Employee();
	List<Employee> employeeList = new ArrayList<Employee>();
	List<SeatMap> seatMapList = new ArrayList<SeatMap>();

	/**
	 * method to ask user to login go to Home page if successful otherwise, ask to
	 * login again
	 */
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

	/**
	 * display home page after successful login user will select from 3 options
	 * 
	 * @param employee
	 */
	public void displayHomePage(Employee employee) {
		System.out.println("\n##HOME##");
		System.out.println(
				"Welcome " + employee.getFirstname() + " " + employee.getLastname() + "[" + employee.getRole() + "]!");
		System.out.println("MENU:");
		System.out.println("[1] Search");
		System.out.println("[2] View Seatplan");
		System.out.println("[3] Logout");
		System.out.print("Enter your choice: ");
		String homePageUserInput = myScanner.next();
		displayHomeSubMenu(homePageUserInput, employee);
	}

	/**
	 * using the user input from displayHomePage, call this method to display the
	 * new options
	 * 
	 * @param homePageUserInput
	 */
	public void displayHomeSubMenu(String homePageUserInput, Employee employee) {
		switch (homePageUserInput) {
		case "1":
			// call this method when option 1 is selected
			displaySearchMenu();
			break;
		case "2":
			// call this method when option 2 is selected
			displayViewSeatPlanMenu();
			break;
		case "3":
			// user will exit the application and will be asked to login again
			System.out.println("Exit! Please login again");
			displayLogin();
			break;
		default:
			System.out.println("Please enter a valid input from 1 to 3 only!");
			displayHomePage(employee);
		}
	}

	/**
	 * based on user's input, navigate user to corresponding search option
	 * 
	 * @param searchUserInput
	 */
	public void displaySearchEmployee(String searchUserInput) {
		// String project_holder = "";
		switch (searchUserInput) {
		case "1":
			// ask the user to enter employee id
			System.out.println("\n##SEARCH - By Employee Id##");
			System.out.print("Enter Employee Id: ");
			String empIdUserInput = myScanner.next();
			// call this method from manager to get employee based on user input and store
			// to an object
			employee = empMgr.displayEmployeeById(empIdUserInput);

			// store all projects of employee to a variable
			// for (Project project : employee.getProjects()) {
			// project_holder = project_holder + project.getName() + ",";
			// }

			// display header names before printing search result
			PeopleLocatorConstants.displaySearchResultHeaderNames();
			// set seats of each employee as the loop counter
			for (Seat seat : employee.getSeats()) {
				// put NONE as value of local # if empty (employee has no local #)
				if (seat.getLocal().isEmpty()) {
					seat.setLocal("NONE");
				}
				// display employee info, location, seat and project(s)
				System.out.print(employee.getId() + "|" + employee.getFirstname() + "|" + employee.getLastname() + "|"
						+ seat.getId() + seat.getFloor_number() + "F" + seat.getQuadrant() + seat.getRow_number() + "-"
						+ seat.getCol_number() + "|" + seat.getLocal() + "|" + employee.getShift() + "|");
				// + project_holder);
				for (Project project : employee.getProjects()) {
					System.out.print(project.getName() + ",");
				}
				System.out.println();
			}
			// call this method to ask user for next action after search result
			displayNextActionAfterSearch();
			break;
		case "2":
			System.out.println("\n##SEARCH - By Employee's Lastname##");
			// ask user to enter employee's last name
			System.out.print("Enter Employee's Lastname: ");
			String empLastNameUserInput = myScanner.next();

			// call method from manager to get list of employees based on user input and
			// store to arraylist

			employeeList = empMgr.displayEmployeeByLastName(empLastNameUserInput);

			// display header names before printing search result
			PeopleLocatorConstants.displaySearchResultHeaderNames();

			// set each employee as the outer loop counter
			for (Employee employee : employeeList) {

				for (Seat seat : employee.getSeats()) {
					if (seat.getLocal().isEmpty()) {
						seat.setLocal("NONE");
					}
					// display employee info, location, and seat
					System.out.print(employee.getId() + "|" + employee.getFirstname() + "|" + employee.getLastname()
							+ "|" + seat.getLocation().getId() + seat.getFloor_number() + "F" + seat.getQuadrant()
							+ seat.getRow_number() + "-" + seat.getCol_number() + "|" + seat.getLocal() + "|"
							+ employee.getShift() + "|");
					// display all employee's project(s)
					for (Project project : employee.getProjects()) {
						System.out.print(project.getName() + ",");
					}
					System.out.println();
				}
			}
			// call this method to ask user for next action after search result
			displayNextActionAfterSearch();
			break;
		case "3":
			System.out.println("\n##SEARCH - By Project Name");
			// ask user to enter project name
			System.out.print("Enter Project Name: ");
			String empProjUserInput = myScanner.next();
			//// call method from manager to get list of employees based on user input and
			//// store to arraylist
			employeeList = empMgr.displayEmployeeByProject(empProjUserInput);

			PeopleLocatorConstants.displaySearchResultHeaderNames();
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

	/**
	 * first version--- using user input, call this method to navigate user to the
	 * view seat plan option public void displayViewSeatPlan(int
	 * viewSeatPlanUserInput) { switch (viewSeatPlanUserInput) { case 1:
	 * System.out.println("\n##VIEW SEATPLAN - By Location - Floor Level##"); //ask
	 * the user to enter location System.out.print("Enter Location: "); String
	 * locationUserInput = myScanner.next(); //ask the user to enter floor number
	 * System.out.print("Enter Floor Level: "); int floorLevelUserInput =
	 * myScanner.nextInt(); //call a method from manager to get a list of seats
	 * based on location and floor employeeList =
	 * empMgr.displaySeatPlanByLocationAndFloor(locationUserInput,
	 * floorLevelUserInput); //display header names before printing result
	 * displayViewSeatPlanHeaderNames(); //loop in each employee in the list for
	 * (Employee employee : employeeList) { //print the employee name
	 * System.out.print(employee.getLastname() + ", " + employee.getFirstname() +
	 * "|"); //loop in each employee seat for (Seat seat : employee.getSeats()) {
	 * //put NONE as value of local # if empty if (seat.getLocal().isEmpty()) {
	 * seat.setLocal("NONE"); } //print seat location of each employee
	 * System.out.println(seat.getLocation().getId() + seat.getFloor_number() + "F"
	 * + seat.getQuadrant() + seat.getRow_number() + "-" + seat.getCol_number() + "
	 * loc." + seat.getLocal()); } } //display next action
	 * displayNextActionAfterViewSeatPlan(); break; case 2:
	 * System.out.println("\n##VIEW SEATPLAN - By Quadrant##");
	 * System.out.print("Enter Location: "); locationUserInput = myScanner.next();
	 * System.out.print("Enter Floor Level: "); floorLevelUserInput =
	 * myScanner.nextInt(); System.out.print("Enter quadrant: "); String
	 * quadrantUserInput = myScanner.next(); employeeList =
	 * empMgr.displaySeatPlanByLocationAndFloorAndQuadrant(locationUserInput,
	 * floorLevelUserInput, quadrantUserInput); displayViewSeatPlanHeaderNames();
	 * for (Employee employee : employeeList) {
	 * System.out.print(employee.getLastname() + ", " + employee.getFirstname()
	 * +"|"); for (Seat seat : employee.getSeats()) { if (seat.getLocal().isEmpty())
	 * { seat.setLocal("NONE"); } System.out.println(seat.getLocation().getId() +
	 * seat.getFloor_number() + "F" + seat.getQuadrant() + seat.getRow_number() +
	 * "-" + seat.getCol_number() + " loc." + seat.getLocal()); } }
	 * displayNextActionAfterViewSeatPlan(); break; default:
	 * System.out.println("Please enter a valid input!"); } }
	 * 
	 */

	public void displayViewSeatPlan(String viewSeatPlanUserInput) {
		switch (viewSeatPlanUserInput) {
		case "1":
			System.out.println("\n##VIEW SEATPLAN - By Location - Floor Level##");
			System.out.print("Enter Location: ");
			String locationUserInput = myScanner.next();
			System.out.print("Enter Floor Level: ");
			String floorUserInput = myScanner.next();
			seatMapList = seatMapMgr.getAllSeatsByFloorAndLocation(locationUserInput, floorUserInput);
			displayViewSeatPlanHeaderNames(locationUserInput, floorUserInput);
			for (SeatMap seatMap : seatMapList) {
				if (seatMap.getSeat().getLocal().isEmpty()) {
					seatMap.getSeat().setLocal("NONE");
				}
				if (seatMap.getEmployee().getId() == null) {
					seatMap.getEmployee().setFirstname("N/A,");
					seatMap.getEmployee().setLastname("");
				}
				if (seatMap.getSeat().getRow_number() == 1 && seatMap.getSeat().getCol_number() == 1) {
					System.out.println(
							"======================" + seatMap.getSeat().getQuadrant() + "======================");
				}
				System.out.print(seatMap.getSeat().getLocation().getId() + seatMap.getSeat().getFloor_number() + "F"
						+ seatMap.getSeat().getQuadrant() + seatMap.getSeat().getRow_number() + "-"
						+ seatMap.getSeat().getCol_number() + "|");
				System.out
						.print(seatMap.getEmployee().getLastname() + "," + seatMap.getEmployee().getFirstname() + "|");
				System.out.print("loc." + seatMap.getSeat().getLocal());
				if (seatMap.getSeat().getCol_number() == 3) {
					System.out.println();
				} else {
					System.out.print(" || ");
				}

			}

			displayNextActionAfterViewSeatPlan();
			break;
		case "2":
			System.out.println("\n##VIEW SEATPLAN - By Quadrant##");
			System.out.print("Enter Location: ");
			locationUserInput = myScanner.next();
			System.out.print("Enter Floor Level: ");
			floorUserInput = myScanner.next();
			System.out.print("Enter quadrant: ");
			String quadrantUserInput = myScanner.next();
			seatMapList = seatMapMgr.getAllSeatsByFloorAndLocationAndQuadrant(locationUserInput, floorUserInput,
					quadrantUserInput);
			displayViewSeatPlanHeaderNames(locationUserInput, floorUserInput);
			System.out.println(" QUADRANT: " + quadrantUserInput);
			for (SeatMap seatMap : seatMapList) {
				if (seatMap.getSeat().getLocal().isEmpty()) {
					seatMap.getSeat().setLocal("NONE");
				}
				if (seatMap.getEmployee().getId() == null) {
					seatMap.getEmployee().setFirstname("N/A,");
					seatMap.getEmployee().setLastname("");
				}
				if (seatMap.getSeat().getRow_number() == 1 && seatMap.getSeat().getCol_number() == 1) {
					System.out.println(
							"======================" + seatMap.getSeat().getQuadrant() + "======================");
				}
				System.out.print(seatMap.getSeat().getLocation().getId() + seatMap.getSeat().getFloor_number() + "F"
						+ seatMap.getSeat().getQuadrant() + seatMap.getSeat().getRow_number() + "-"
						+ seatMap.getSeat().getCol_number() + "|");
				System.out
						.print(seatMap.getEmployee().getLastname() + "," + seatMap.getEmployee().getFirstname() + "|");
				System.out.print("loc." + seatMap.getSeat().getLocal());
				if (seatMap.getSeat().getCol_number() == 3) {
					System.out.println();
				} else {
					System.out.print(" || ");
				}

			}

			displayNextActionAfterViewSeatPlan();
			break;
		default:
			System.out.println("Please enter a valid input!");
			displayViewSeatPlanMenu();
		}
	}

	// display search options and ask for user input
	public void displaySearchMenu() {
		System.out.println("\n##SEARCH##");
		System.out.println("MENU:");
		System.out.println("[1]By Employee Id");
		System.out.println("[2]By Employee's Last Name");
		System.out.println("[3]By Project");
		System.out.print("Enter your choice: ");
		String searchUserInput = myScanner.next();
		displaySearchEmployee(searchUserInput);
	}

	public void displayViewSeatPlanMenu() {
		System.out.println("\n##VIEW SEATPLAN##");
		System.out.println("MENU:");
		System.out.println("[1]By Location - Floor Level");
		System.out.println("[2]By Quadrant");
		System.out.print("Enter your choice: ");
		String viewSeatPlanUserInput = myScanner.next();
		displayViewSeatPlan(viewSeatPlanUserInput);
	}

	public void displayViewSeatPlanHeaderNames(String locationUserInput, String floorUserInput) {
		Location location = new Location();
		System.out.println("\n##VIEW SEATPLAN##");
		System.out.print("LOCATION:" + locationUserInput);
		System.out.print("[");
		location = seatMapMgr.getLocationAddress(locationUserInput);
		System.out.print(location.getAddress());
		System.out.print("],");
		System.out.print(" FLOOR:" + floorUserInput);
	}

	// display next action options after Search and ask for user input
	public void displayNextActionAfterSearch() {
		System.out.print("\nACTIONS: ");
		System.out.print("[1] Search Again ");
		System.out.println("[2] Home ");
		System.out.print("Enter your choice : ");
		String nextActionUserInput = myScanner.next();
		switch (nextActionUserInput) {
		case "1":
			displaySearchMenu();
			break;
		case "2":
			displayHomePage(emp_login);
			break;
		}
	}

	// display next action options after View Seat Plan and ask for user input
	public void displayNextActionAfterViewSeatPlan() {
		System.out.print("\nACTIONS: ");
		System.out.print("[1] View Seatplan again ");
		System.out.println("[2] Home ");
		System.out.print("Enter your choice : ");
		String nextActionUserInput = myScanner.next();
		switch (nextActionUserInput) {
		case "1":
			displayViewSeatPlanMenu();
			break;
		case "2":
			displayHomePage(emp_login);
			break;
		}
	}

}
