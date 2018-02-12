package com.java.pointwest.manager;

import java.util.ArrayList;
import java.util.List;

import com.java.pointwest.bean.Employee;
import com.java.pointwest.bean.Project;
import com.java.pointwest.bean.Seat;
import com.java.pointwest.dao.EmployeeDao;

public class EmployeeManager {
	EmployeeDao empDao = new EmployeeDao();
	Employee employee = new Employee();
	Seat seat = new Seat();
	List<Employee> employeeList = new ArrayList<Employee>();
	List<Seat> seatList = new ArrayList<Seat>();
	List<Project> projectList = new ArrayList<Project>();
	
	
	/**
	 * This method calls from dao to get the corresponding employee based on user input
	 * @param emp_username
	 * @param emp_password
	 * @return employee
	 */
	public Employee verifyLogin(String emp_username, String emp_password) {
		Employee employee;
		Employee emp_tmp = empDao.getLoginCredentials(emp_username, emp_password);
		if (emp_username.equals(emp_tmp.getUsername()) && emp_password.equals(emp_tmp.getPassword())) {
			employee = emp_tmp;
		} else {
			employee = null;
		}
		return employee;
	}
	
	/**
	 * This method calls from dao to get the employee from the DB based on the user input
	 * @param empIdUserInput
	 * @return Employee
	 */
	public Employee displayEmployeeById(String empIdUserInput) {
		//store the value returned by dao to employee object
		employee = empDao.getEmployeesbyId(empIdUserInput);
		
		//if the user input matches with employee id returned by dao
		//if (empIdUserInput.equalsIgnoreCase(employee.getId())) {
		if (employee.getId()!= null && !employee.getId().equals("") )	{
			seatList = empDao.getSeatsById(empIdUserInput);
			employee.setSeats(seatList);
			projectList = empDao.getProjectsById(empIdUserInput);
			employee.setProjects(projectList);
		}
		//return the employee back to ui for display
		return employee;
	}
	
	//
	
	/**
	 * This method calls from dao to get list of employees from the DB based on user input
	 * @param empLastNameUserInput
	 * @return
	 */
	public List<Employee> displayEmployeeByLastName(String empLastNameUserInput) {
		//store the value returned by dao to employee arraylist
		employeeList = empDao.getEmployeesbyLastName(empLastNameUserInput);
		
		//loop each employee from the list
		for (Employee employee : employeeList) {
			//get all employees returned and use it as parameter (emp ID) to get all seats of each employee
			//store the value returned by dao to seat arraylist
			seatList = empDao.getSeatsByEmployeeId(employee);
			//get all employees returned and use it as parameter (emp ID) to get all projects of each employee
			//store the value returned by day to project arraylist
			projectList = empDao.getProjectsById(employee);
			//add each seatList and projectList to each employee
			employee.setSeats(seatList);
			employee.setProjects(projectList);

		}
		//return the employeeList back to ui for display
		return employeeList;
	}
	
	/**
	 * This method calls from dao to get list of employees from the DB based on user input
	 * @param empProjUserInput
	 * @return employeeList
	 */
	public List<Employee> displayEmployeeByProject(String empProjUserInput) {
		//store the value returned by dao to employee arraylist
		employeeList = empDao.getEmployeesbyProject(empProjUserInput);
		
		//loop each employee from the list
		for (Employee employee : employeeList) {
			//get all employees returned and use it as parameter (emp ID) to get all seats of each employee
			//store the value returned by dao to seat arraylist
			seatList = empDao.getSeatsByEmployeeId(employee);
			//get all employees returned and use it as parameter (proj alias) to get all projects of each employee
			//store the value returned by day to project arraylist
			projectList = empDao.getProjectsByProjects(empProjUserInput);
			//add each seatList and projectList to each employee
			employee.setSeats(seatList);
			employee.setProjects(projectList);
		}
		//return the employeeList back to ui for display
		return employeeList;
	}

	/**
	 * this method calls from dao to get a list of employee based on user input  
	 * @param locationUserInput
	 * @param floorLevelUserInput
	 * @return employeeList
	 */
	public List<Employee> displaySeatPlanByLocationAndFloor(String locationUserInput, int floorLevelUserInput) {
		//returned value of dao is stored in employeeList
		employeeList = empDao.getEmpInfoPlanByLocationAndFloor(locationUserInput, floorLevelUserInput);
		
		//loop each employee from the list
		for (Employee employee : employeeList) {
			//call method from dao to get and return seat of each employee and store to variable
			Seat seat = empDao.getEmpSeatsPlanByLocationAndFloor(employee);
			//add each seat to corresponding employee 
			employee.getSeats().add(seat);
		}
		//return the employeeList back to ui for display
		return employeeList;

	}
	
	/**
	 * this method calls from dao to get a list of employee based on user input  
	 * @param locationUserInput
	 * @param floorLevelUserInput
	 * @param quadrantUserInput
	 * @return employeeList
	 */
	public List<Employee> displaySeatPlanByLocationAndFloorAndQuadrant(String locationUserInput,
			int floorLevelUserInput, String quadrantUserInput) {
		employeeList = empDao.getEmpInfoPlanByLocationFloorAndQuadrant(locationUserInput, floorLevelUserInput,
				quadrantUserInput);
		
		//loop each employee from the list
		for (Employee employee : employeeList) {
			//call method from dao to get and return seat of each employee and store to variable
			Seat seat = empDao.getEmpSeatsPlanByLocationAndFloor(employee);
			//add each seat to corresponding employee 
			employee.getSeats().add(seat);
		}
		//return the employeeList back to ui for display
		return employeeList;
	}

}
