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
		IOrderScheduler orderScheduler = new OrderScheduler();
		List<Flight> schedules = orderScheduler.ScheduleOrdersForManyFlights(flights, orderRepo, 20);
		schedules.forEach(flight -> flight.getScheduledOrders().forEach
			(order -> System.out.println(
				String.format("order: %s, flightNumber: %s, departure: %s, arrival %s, Day %s"
				, order.getOrderId(), flight.getFlightId(), flight.getDeparture()
				, flight.getArrival(), flight.getDay()))));

		orderScheduler.getNonScheduledOrders(flights, orderRepo)
					  .forEach(orderId -> 
					  				System.out.println(String.format("order: %s, flightNumber: not scheduled", orderId)));       
	}    
}
