package com.airtek.orders;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class App 
{    
	public static void main( String[] args )
    {             
            //TODO: add dependency injection machinery here:   
             IFlightRepository flightRepo = new FlightRepository();           
             List<Flight> flights = flightRepo.getFlights();
        	
        	 for(Flight flight : flights) {
        		System.out.println(String.format("Flight: %s, Departure: %s, Arrival %s, Day %s",
        				flight.getFlightId(), flight.getDeparture(), flight.getArrival(), flight.getDay()));	
        	 }
        	
        	IOrderRepository orderRepo = new OrderRepository();
        	Optional<Map<String, Order>> ordersMap = orderRepo.getOrders();
            
        	if (ordersMap.isPresent()) {
        		
        		IOrderScheduler orderScheduler = new OrderScheduler();
        		List<Flight> schedules = orderScheduler.ScheduleOrdersForManyFlights(flights, ordersMap.get());
        		schedules.forEach(flight -> System.out.println(String.format("Flight: %s, Order Count: %d ", 
        				flight.getFlightId(), flight.getScheduledOrders().size())));
        	}
    }
    
}
