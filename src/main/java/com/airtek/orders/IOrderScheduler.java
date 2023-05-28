package com.airtek.orders;
import java.util.List;
import java.util.Map;

public interface IOrderScheduler {
	
    public Flight ScheduleFlightOrders(Flight flight, Map<String, Order> orders); 
    
    public List<Flight> ScheduleOrdersForManyFlights(List<Flight> flights, IOrderRepository orderRepo, int batchSize);

    public List<String> getNonScheduledOrders(List<Flight> flights, IOrderRepository orderRepo);
}
