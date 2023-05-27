package com.airtek.orders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface IOrderScheduler {
	
    public Flight ScheduleFlightOrders(Flight flight, Map<String, Order> orders); 
    
    public List<Flight> ScheduleOrdersForManyFlights(List<Flight> flights, Map<String, Order> orders);
}
