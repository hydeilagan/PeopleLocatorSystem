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

	public Employee displayEmployeeById(String empIdUserInput) {
		employee = empDao.getEmployeesbyId(empIdUserInput);
		if (empIdUserInput.equalsIgnoreCase(employee.getId())) {
			seatList = empDao.getSeatsById(empIdUserInput);
			employee.setSeats(seatList);
			projectList = empDao.getProjectsById(empIdUserInput);
			employee.setProjects(projectList);
		}
		return employee;
	}

	public List<Employee> displayEmployeeByLastName(String empLastNameUserInput) {
		employeeList = empDao.getEmployeesbyLastName(empLastNameUserInput);
		for (Employee employee : employeeList) {
			seatList = empDao.getSeatsByEmployeeId(employee);
			projectList = empDao.getProjectsById(employee);
			employee.setSeats(seatList);
			employee.setProjects(projectList);

		}
		return employeeList;
	}

	public List<Employee> displayEmployeeByProject(String empProjUserInput) {
		employeeList = empDao.getEmployeesbyProject(empProjUserInput);
		for (Employee employee : employeeList) {
			seatList = empDao.getSeatsByEmployeeId(employee);
			projectList = empDao.getProjectsByProjects(empProjUserInput);
			// projectList = empDao.getProjectsById(employee);
			employee.setSeats(seatList);
			employee.setProjects(projectList);
		}
		return employeeList;
	}

	public List<Employee> displaySeatPlanByLocationAndFloor(String locationUserInput, int floorLevelUserInput) {
		employeeList = empDao.getEmpInfoPlanByLocationAndFloor(locationUserInput, floorLevelUserInput);

		for (Employee employee : employeeList) {
			Seat seat = empDao.getEmpSeatsPlanByLocationAndFloor(employee);
			employee.getSeats().add(seat);
		}
		return employeeList;

	}

	public List<Employee> displaySeatPlanByLocationAndFloorAndQuadrant(String locationUserInput,
			int floorLevelUserInput, String quadrantUserInput) {
		employeeList = empDao.getEmpInfoPlanByLocationFloorAndQuadrant(locationUserInput, floorLevelUserInput,
				quadrantUserInput);

		for (Employee employee : employeeList) {
			Seat seat = empDao.getEmpSeatsPlanByLocationAndFloor(employee);
			employee.getSeats().add(seat);
		}
		return employeeList;
	}

}
