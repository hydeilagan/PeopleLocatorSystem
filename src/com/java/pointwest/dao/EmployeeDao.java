package com.java.pointwest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.pointwest.bean.Employee;
import com.java.pointwest.bean.Location;
import com.java.pointwest.bean.Project;
import com.java.pointwest.bean.Seat;

public class EmployeeDao {
	Logger logger = Logger.getLogger(EmployeeDao.class);

	public Employee getLoginCredentials(String emp_username, String emp_password) {
		Employee employee = new Employee();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String login_query = "select * from employee where employee.username = ? and employee.password  = ?";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(login_query);
			preparedSt.setString(1, emp_username);
			preparedSt.setString(2, emp_password);
			ResultSet loginResult = preparedSt.executeQuery();

			while (loginResult.next()) {
				employee.setUsername(loginResult.getString("username"));
				employee.setPassword(loginResult.getString("password"));
				employee.setFirstname(loginResult.getString("first_name"));
				employee.setLastname(loginResult.getString("last_name"));
				employee.setRole(loginResult.getString("role"));
			}

			closeConnection(dbConn, preparedSt, loginResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			logger.info("===Program at EmployeeDao.getLoginCredentials===");
			logger.info("username" + emp_username);
			// e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}
		// logger.info("===End of employeeDao.getLoginCredentials===");
		return employee;
	}

	public Employee getEmployeesbyId(String empIdUserInput) {
		Employee employee = new Employee();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String employee_query = "select * from employee where emp_id = ?";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(employee_query);
			preparedSt.setString(1, empIdUserInput);
			ResultSet searchResult = preparedSt.executeQuery();

			while (searchResult.next()) {
				employee.setId(searchResult.getString("emp_id"));
				employee.setUsername(searchResult.getString("username"));
				employee.setPassword(searchResult.getString("password"));
				employee.setFirstname(searchResult.getString("first_name"));
				employee.setLastname(searchResult.getString("last_name"));
				employee.setRole(searchResult.getString("role"));
				employee.setShift(searchResult.getString("shift"));
			}
			closeConnection(dbConn, preparedSt, searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			// e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}

		return employee;
	}

	public List<Seat> getSeatsById(String empIdUserInput) {
		List<Seat> seatList = new ArrayList<Seat>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String seat_query = "select * from seat seat\r\n" + "left join employee_seat emp_seat\r\n"
					+ "on seat.seat_id = emp_seat.seat_id\r\n" + "where emp_id  = ?";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(seat_query);
			preparedSt.setString(1, empIdUserInput);
			ResultSet searchResult = preparedSt.executeQuery();

			while (searchResult.next()) {
				Seat seat = new Seat();
				seat.setId(searchResult.getString("bldg_id"));
				seat.setFloor_number(searchResult.getInt("floor_number"));
				seat.setQuadrant(searchResult.getString("quadrant"));
				seat.setRow_number(searchResult.getInt("row_number"));
				seat.setCol_number(searchResult.getInt("column_number"));
				seat.setLocal(searchResult.getString("local_number"));
				seatList.add(seat);
			}
			closeConnection(dbConn, preparedSt, searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}

		return seatList;
	}

	public List<Project> getProjectsById(String empIdUserInput) {
		List<Project> projectList = new ArrayList<Project>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String project_query = "select * \r\n" + "from project proj \r\n"
					+ "left join employee_project emp_project\r\n" + "on proj.proj_alias = emp_project.proj_alias\r\n"
					+ "where emp_project.employee_id = ?";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(project_query);
			preparedSt.setString(1, empIdUserInput);
			ResultSet searchResult = preparedSt.executeQuery();

			while (searchResult.next()) {
				Project project = new Project();
				project.setAlias(searchResult.getString("proj_alias"));
				project.setName(searchResult.getString("proj_name"));
				projectList.add(project);
			}
			closeConnection(dbConn, preparedSt, searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}

		return projectList;
	}

	public List<Employee> getEmployeesbyLastName(String empLastNameUserInput) {
		List<Employee> employeeList = new ArrayList<Employee>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String employee_query = "select * from employee where last_name = ?";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(employee_query);
			preparedSt.setString(1, empLastNameUserInput);
			ResultSet searchResult = preparedSt.executeQuery();

			while (searchResult.next()) {
				Employee employee = new Employee();
				employee.setId(searchResult.getString("emp_id"));
				employee.setUsername(searchResult.getString("username"));
				employee.setPassword(searchResult.getString("password"));
				employee.setFirstname(searchResult.getString("first_name"));
				employee.setLastname(searchResult.getString("last_name"));
				employee.setRole(searchResult.getString("role"));
				employee.setShift(searchResult.getString("shift"));
				employeeList.add(employee);
			}
			closeConnection(dbConn, preparedSt, searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}

		return employeeList;
	}

	public List<Seat> getSeatsByLastName(String empLastNameUserInput) {
		List<Seat> seatList = new ArrayList<Seat>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String seat_query = "select * \r\n" + "from employee emp\r\n" + "left join  employee_seat  emp_seat\r\n"
					+ "on emp.emp_id = emp_seat.emp_id\r\n" + "left join seat seat\r\n"
					+ "on seat.seat_id = emp_seat.seat_id\r\n" + "where emp.last_name = ?";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(seat_query);
			preparedSt.setString(1, empLastNameUserInput);
			ResultSet searchResult = preparedSt.executeQuery();

			while (searchResult.next()) {
				Seat seat = new Seat();
				seat.setId(searchResult.getString("bldg_id"));
				seat.setFloor_number(searchResult.getInt("floor_number"));
				seat.setQuadrant(searchResult.getString("quadrant"));
				seat.setRow_number(searchResult.getInt("row_number"));
				seat.setCol_number(searchResult.getInt("column_number"));
				seat.setLocal(searchResult.getString("local_number"));
				seatList.add(seat);
			}
			closeConnection(dbConn, preparedSt, searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}

		return seatList;
	}

	public List<Project> getProjectsByLastName(String empLastNameUserInput) {
		List<Project> projectList = new ArrayList<Project>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String project_query = "select * \r\n" + "from employee emp\r\n"
					+ "left join employee_project emp_project \r\n" + "on emp.emp_id = emp_project.employee_id\r\n"
					+ "left join project proj \r\n" + "on emp_project.proj_alias = proj.proj_alias \r\n"
					+ "where emp.last_name = ?";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(project_query);
			preparedSt.setString(1, empLastNameUserInput);
			ResultSet searchResult = preparedSt.executeQuery();

			while (searchResult.next()) {
				Project project = new Project();
				project.setAlias(searchResult.getString("proj_alias"));
				project.setName(searchResult.getString("proj_name"));
				projectList.add(project);
			}
			closeConnection(dbConn, preparedSt, searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}

		return projectList;
	}

	public List<Employee> getEmployeesbyProject(String empProjUserInput) {
		List<Employee> employeeList = new ArrayList<Employee>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String employee_query = "select * \r\n" + "from employee emp \r\n"
					+ "left join employee_project emp_project\r\n" + "on emp.emp_id = emp_project.employee_id\r\n"
					+ "left join project proj\r\n" + "on emp_project.proj_alias = proj.proj_alias\r\n"
					+ "where proj.proj_name = ?";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(employee_query);
			preparedSt.setString(1, empProjUserInput);
			ResultSet searchResult = preparedSt.executeQuery();

			while (searchResult.next()) {
				Employee employee = new Employee();
				employee.setId(searchResult.getString("emp_id"));
				employee.setUsername(searchResult.getString("username"));
				employee.setPassword(searchResult.getString("password"));
				employee.setFirstname(searchResult.getString("first_name"));
				employee.setLastname(searchResult.getString("last_name"));
				employee.setRole(searchResult.getString("role"));
				employee.setShift(searchResult.getString("shift"));
				employeeList.add(employee);
			}
			closeConnection(dbConn, preparedSt, searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}

		return employeeList;
	}

	public List<Seat> getSeatsByProject(String empProjUserInput) {
		List<Seat> seatList = new ArrayList<Seat>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String seat_query = "select * \r\n" + "from seat seat\r\n" + "left join  employee_seat  emp_seat\r\n"
					+ "on seat.seat_id= emp_seat.seat_id\r\n" + "left join employee_project emp_project\r\n"
					+ "on emp_project.employee_id = emp_seat.emp_id\r\n" + "left join project proj\r\n"
					+ "on emp_project.proj_alias = proj.proj_alias \r\n" + "where proj.proj_name = ?";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(seat_query);
			preparedSt.setString(1, empProjUserInput);
			ResultSet searchResult = preparedSt.executeQuery();

			while (searchResult.next()) {
				Seat seat = new Seat();
				seat.setId(searchResult.getString("bldg_id"));
				seat.setFloor_number(searchResult.getInt("floor_number"));
				seat.setQuadrant(searchResult.getString("quadrant"));
				seat.setRow_number(searchResult.getInt("row_number"));
				seat.setCol_number(searchResult.getInt("column_number"));
				seat.setLocal(searchResult.getString("local_number"));
				seatList.add(seat);
			}
			closeConnection(dbConn, preparedSt, searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}

		return seatList;
	}

	public List<Seat> getSeatsByEmployeeId(Employee employee) {
		List<Seat> seatList = new ArrayList<Seat>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String seat_query = "select * from seat seat\r\n" + "left join employee_seat emp_seat \r\n"
					+ "on seat.seat_id = emp_seat.seat_id\r\n" + "where emp_id  = ?";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(seat_query);
			preparedSt.setString(1, employee.getId());
			ResultSet searchResult = preparedSt.executeQuery();

			while (searchResult.next()) {
				Seat seat = new Seat();
				Location location = new Location();

				location.setId(searchResult.getString("bldg_id"));
				seat.setLocation(location);
				// seat.setId(searchResult.getString("bldg_id"));
				seat.setFloor_number(searchResult.getInt("floor_number"));
				seat.setQuadrant(searchResult.getString("quadrant"));
				seat.setRow_number(searchResult.getInt("row_number"));
				seat.setCol_number(searchResult.getInt("column_number"));
				seat.setLocal(searchResult.getString("local_number"));
				seatList.add(seat);
			}
			closeConnection(dbConn, preparedSt, searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}

		return seatList;
	}

	public List<Project> getProjectsByProjects(String empProjUserInput) {
		List<Project> projectList = new ArrayList<Project>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String project_query = "select * from project where proj_name = ?";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(project_query);
			preparedSt.setString(1, empProjUserInput);
			ResultSet searchResult = preparedSt.executeQuery();

			while (searchResult.next()) {
				Project project = new Project();
				project.setAlias(searchResult.getString("proj_alias"));
				project.setName(searchResult.getString("proj_name"));
				projectList.add(project);
			}
			closeConnection(dbConn, preparedSt, searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}

		return projectList;
	}

	public List<Project> getProjectsById(Employee employee) {
		List<Project> projectList = new ArrayList<Project>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String project_query = "select *\r\n" + "from employee emp\r\n"
					+ "left join employee_project emp_project\r\n" + "on emp.emp_id = emp_project.employee_id\r\n"
					+ "left join project proj\r\n" + "on emp_project.proj_alias = proj.proj_alias \r\n"
					+ "where emp.emp_id = ?";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(project_query);
			preparedSt.setString(1, employee.getId());
			ResultSet searchResult = preparedSt.executeQuery();

			while (searchResult.next()) {
				Project project = new Project();
				project.setAlias(searchResult.getString("proj_alias"));
				project.setName(searchResult.getString("proj_name"));
				projectList.add(project);
			}
			closeConnection(dbConn, preparedSt, searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}

		return projectList;
	}

	public List<Employee> getEmpInfoPlanByLocationAndFloor(String locationUserInput, int floorLevelUserInput) {
		List<Employee> employeeList = new ArrayList<Employee>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String searchEmpById_query = "select *\r\n" + "from employee emp\r\n"
					+ "left join employee_seat emp_seat\r\n" + "on emp.emp_id = emp_seat.emp_id\r\n"
					+ "left join seat seat\r\n" + "on emp_seat.seat_id = seat.seat_id\r\n"
					+ "where seat.bldg_id = ? and seat.floor_number = ? ";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(searchEmpById_query);
			preparedSt.setString(1, locationUserInput);
			preparedSt.setInt(2, floorLevelUserInput);
			ResultSet searchResult = preparedSt.executeQuery();

			while (searchResult.next()) {
				Employee employee = new Employee();

				employee.setId(searchResult.getString("emp_id"));
				employee.setFirstname(searchResult.getString("first_name"));
				employee.setLastname(searchResult.getString("last_name"));
				employeeList.add(employee);
			}

			closeConnection(dbConn, preparedSt, searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}

		return employeeList;
	}

	public Seat getEmpSeatsPlanByLocationAndFloor(Employee employee) {
		Seat seat = new Seat();
		Location location = new Location();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String seatplan_query = "select *\r\n" + "from employee_seat emp_seat\r\n" + "left join seat seat\r\n"
					+ "on emp_seat.seat_id = seat.seat_id\r\n" + "where emp_seat.emp_id = ? ";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(seatplan_query);
			preparedSt.setString(1, employee.getId());
			ResultSet searchResult = preparedSt.executeQuery();

			while (searchResult.next()) {
				location.setId(searchResult.getString("bldg_id"));
				seat.setLocation(location);
				seat.setFloor_number(searchResult.getInt("floor_number"));
				seat.setQuadrant(searchResult.getString("quadrant"));
				seat.setRow_number(searchResult.getInt("row_number"));
				seat.setCol_number(searchResult.getInt("column_number"));
				seat.setLocal(searchResult.getString("local_number"));
			}

			closeConnection(dbConn, preparedSt, searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}

		return seat;
	}

	public List<Employee> getEmpInfoPlanByLocationFloorAndQuadrant(String locationUserInput, int floorLevelUserInput,
			String quadrantUserInput) {
		List<Employee> employeeList = new ArrayList<Employee>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String db = "jdbc:mysql://172.26.83.193:3306/plsdb";
			String user = "newuser";
			String password = "password123";
			String searchEmpById_query = "select *\r\n" + "from employee emp\r\n"
					+ "left join employee_seat emp_seat\r\n" + "on emp.emp_id = emp_seat.emp_id\r\n"
					+ "left join seat seat\r\n" + "on emp_seat.seat_id = seat.seat_id\r\n"
					+ "where seat.bldg_id = ? and seat.floor_number = ? and seat.quadrant = ?";

			Connection dbConn = DriverManager.getConnection(db, user, password);
			PreparedStatement preparedSt = dbConn.prepareStatement(searchEmpById_query);
			preparedSt.setString(1, locationUserInput);
			preparedSt.setInt(2, floorLevelUserInput);
			preparedSt.setString(3, quadrantUserInput);
			ResultSet searchResult = preparedSt.executeQuery();

			while (searchResult.next()) {
				Employee employee = new Employee();

				employee.setId(searchResult.getString("emp_id"));
				employee.setFirstname(searchResult.getString("first_name"));
				employee.setLastname(searchResult.getString("last_name"));
				employeeList.add(employee);
			}

			closeConnection(dbConn, preparedSt, searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}

		return employeeList;
	}

	void closeConnection(Connection conn, Statement ps, ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
	}
}
