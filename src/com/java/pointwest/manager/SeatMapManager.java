package com.java.pointwest.manager;

import java.util.List;

import com.java.pointwest.bean.Employee;
import com.java.pointwest.bean.Location;
import com.java.pointwest.bean.Seat;
import com.java.pointwest.bean.SeatMap;
import com.java.pointwest.dao.SeatMapDao;

public class SeatMapManager {
	SeatMapDao seatMapDao = new SeatMapDao();

	public List<SeatMap> getAllSeatsByFloorAndLocation(String locationUserInput, String floorUserInput) {
		List<SeatMap> seatMapList = seatMapDao.retrieveSeatsbyLocationAndFloor(locationUserInput, floorUserInput);

		for (SeatMap seatMap : seatMapList) {
			Seat seat = seatMap.getSeat();
			Employee employee = seatMapDao.retrieveEmployeeBySeat(seat);
			seatMap.setEmployee(employee);
		}
		return seatMapList;
	}
	
	public List<SeatMap> getAllSeatsByFloorAndLocationAndQuadrant(String locationUserInput, String floorUserInput, String quadrant) {
		List<SeatMap> seatMapList = seatMapDao.retrieveSeatsbyLocationAndFloorAndQuadrant(locationUserInput, floorUserInput, quadrant );

		for (SeatMap seatMap : seatMapList) {
			Seat seat = seatMap.getSeat();
			Employee employee = seatMapDao.retrieveEmployeeBySeat(seat);
			seatMap.setEmployee(employee);
		}
		return seatMapList;
	} 
	
	public Location getLocationAddress (String locationUserInput) {
		Location location = new Location();
		location = seatMapDao.retrieveLocationAddress(locationUserInput);
		return location;
		
	}
}
