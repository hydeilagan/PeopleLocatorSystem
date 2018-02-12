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
import com.java.pointwest.bean.Seat;
import com.java.pointwest.bean.SeatMap;
import com.java.pointwest.utils.PeopleLocatorConstants;

public class SeatMapDao {
	Logger logger = Logger.getLogger(EmployeeDao.class);
	public List<SeatMap> retrieveSeatsbyLocationAndFloor(String locationUserInput, String floorUserInput) {	
		List<SeatMap> seatMapList = new ArrayList<SeatMap>();
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String seat_query = "select * from seat  where bldg_id = ? and floor_number = ?";
			Connection dbConn = DriverManager.getConnection(PeopleLocatorConstants.CONNECTION_STRING, PeopleLocatorConstants.DB_USERNAME, PeopleLocatorConstants.DB_PASSWORD);
			PreparedStatement preparedSt = dbConn.prepareStatement(seat_query);
			preparedSt.setString(1, locationUserInput);
			preparedSt.setString(2, floorUserInput);
			ResultSet searchResult = preparedSt.executeQuery();
			
			while(searchResult.next()) {
				SeatMap seatMap = new SeatMap();
				Seat seat = new Seat();
				
				Location location = new Location();
				location.setId(searchResult.getString("bldg_id"));
				seat.setLocation(location);
				seat.setId(searchResult.getString("seat_id"));
				seat.setFloor_number(searchResult.getInt("floor_number"));
				seat.setQuadrant(searchResult.getString("quadrant"));
				seat.setRow_number(searchResult.getInt("row_number"));
				seat.setCol_number(searchResult.getInt("column_number"));
				seat.setLocal(searchResult.getString("local_number"));
				seatMap.setSeat(seat);
				seatMapList.add(seatMap);
			}
			closeConnection(dbConn, preparedSt, searchResult);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return seatMapList;
	}
	
	public Employee retrieveEmployeeBySeat (Seat seat) {	
		Employee employee = new Employee();
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String seat_query = "select *\r\n" + 
					"    from employee emp\r\n" + 
					"    left join employee_seat emp_seat\r\n" + 
					"    on emp.emp_id = emp_seat.emp_id\r\n" + 
					"    left join seat seat\r\n" + 
					"    on emp_seat.seat_id = seat.seat_id\r\n" + 
					"    where seat.seat_id  = ? ";
			
			Connection dbConn = DriverManager.getConnection(PeopleLocatorConstants.CONNECTION_STRING, PeopleLocatorConstants.DB_USERNAME, PeopleLocatorConstants.DB_PASSWORD);
			PreparedStatement preparedSt = dbConn.prepareStatement(seat_query);
			preparedSt.setString(1, seat.getId());
			ResultSet searchResult = preparedSt.executeQuery();
			
			while(searchResult.next()) {
				employee.setId(searchResult.getString("emp_id"));
				employee.setFirstname(searchResult.getString("first_name"));
				employee.setLastname(searchResult.getString("last_name"));
			}
			closeConnection(dbConn, preparedSt, searchResult);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}
		
		return employee;
	}
	
	public List<SeatMap> retrieveSeatsbyLocationAndFloorAndQuadrant(String locationUserInput, String floorUserInput, String quadrant) {	
		List<SeatMap> seatMapList = new ArrayList<SeatMap>();
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String seat_query = "select * from seat  where bldg_id = ? and floor_number = ? and quadrant = ?";
			Connection dbConn = DriverManager.getConnection(PeopleLocatorConstants.CONNECTION_STRING, PeopleLocatorConstants.DB_USERNAME, PeopleLocatorConstants.DB_PASSWORD);
			PreparedStatement preparedSt = dbConn.prepareStatement(seat_query);
			preparedSt.setString(1, locationUserInput);
			preparedSt.setString(2, floorUserInput);
			preparedSt.setString(3, quadrant);
			ResultSet searchResult = preparedSt.executeQuery();
			
			while(searchResult.next()) {
				SeatMap seatMap = new SeatMap();
				Seat seat = new Seat();
				
				Location location = new Location();
				location.setId(searchResult.getString("bldg_id"));
				seat.setLocation(location);
				seat.setId(searchResult.getString("seat_id"));
				seat.setFloor_number(searchResult.getInt("floor_number"));
				seat.setQuadrant(searchResult.getString("quadrant"));
				seat.setRow_number(searchResult.getInt("row_number"));
				seat.setCol_number(searchResult.getInt("column_number"));
				seat.setLocal(searchResult.getString("local_number"));
				seatMap.setSeat(seat);
				seatMapList.add(seatMap);
			}
			closeConnection(dbConn, preparedSt, searchResult);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}
		
		return seatMapList;
	}
	
	public Location retrieveLocationAddress(String locationUserInput) {
		Location location = new Location();
			try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String location_query = "select * from location where bldg_id = ? ";
			Connection dbConn = DriverManager.getConnection(PeopleLocatorConstants.CONNECTION_STRING, PeopleLocatorConstants.DB_USERNAME, PeopleLocatorConstants.DB_PASSWORD);
			PreparedStatement preparedSt = dbConn.prepareStatement(location_query);
			preparedSt.setString(1, locationUserInput);
			ResultSet searchResult = preparedSt.executeQuery();
			
			while(searchResult.next()) {
				location.setId(searchResult.getString("bldg_id"));
				location.setName(searchResult.getString("bldg_name"));
				location.setAddress(searchResult.getString("bldg_address"));
			}
			closeConnection(dbConn, preparedSt, searchResult);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
			logger.error(e.getMessage());
		}
		return location;
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
				logger.error("Encountered issues from Database. For more info, contact JC Balasabas");
				logger.error(e.getMessage());
			}
		}
	}

	
	
}
