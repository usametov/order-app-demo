package com.airtek.orders;
import java.util.ArrayList;
import java.util.List;


public class FlightRepository implements IFlightRepository {
	
  public List<Flight> getFlights() {
	
	ArrayList<Flight> flights = new ArrayList<Flight>();
	flights.add(new Flight(1, "YUL", "YYZ", 1));
	flights.add(new Flight(2, "YUL", "YYC", 2));
	flights.add(new Flight(3, "YUL", "YVR", 3));
	
	flights.add(new Flight(4, "YUL", "YYZ", 4));
	//flights.add(new Flight(5, "YUL", "YYC", 5));
	//flights.add(new Flight(6, "YUL", "YVR", 6));
	
	return flights;
  }  
}
