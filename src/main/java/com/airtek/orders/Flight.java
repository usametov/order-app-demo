package com.airtek.orders;

import java.util.List;

public class Flight {
  private int flightId;

  int getFlightId() {
	return flightId;
  }

  void setFlightId(int flightId) {
	this.flightId = flightId;
  }
  
  String getDeparture() {
	return departure;
  }

  void setDeparture(String departure) {
	this.departure = departure;
  }

  String getArrival() {
	return arrival;
  }

  void setArrival(String arrival) {
	this.arrival = arrival;
  }

  int getDay() {
	return day;
  }

  void setDay(int day) {
	this.day = day;
  }

  private String departure;
  
  private String arrival;
  
  private int day;
  
  private List<Order> scheduledOrders;
  
  public Flight(int flightId, String departure, String arrival, int day) {
	  setFlightId(flightId);
	  setDeparture(departure);
	  setArrival(arrival);
	  setDay(day);
   }

public List<Order> getScheduledOrders() {
	return scheduledOrders;
}

public void setScheduledOrders(List<Order> scheduledOrders) {
	this.scheduledOrders = scheduledOrders;
}
  
}
