package com.airtek.orders;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Optional;
import java.util.Iterator;

public class OrderScheduler implements IOrderScheduler {

	public Flight ScheduleFlightOrders(Flight flight, Map<String, Order> ordersMap) {
		
		if(flight==null)
			throw new IllegalArgumentException("can not work with null flight!", null);

		if(ordersMap==null)
			return flight;

		List<String> sortedKeys = ordersMap.keySet().stream().sorted().toList();
		List<Order> orders = new ArrayList<Order>();
		for(String orderKey:sortedKeys) {
			Order ord = ordersMap.get(orderKey);
			ord.setOrderId(orderKey);
			orders.add(ord);
		}
		
		flight.setScheduledOrders(orders);
		return flight;
	}

	@Override
	public List<Flight> ScheduleOrdersForManyFlights(List<Flight> flights, IOrderRepository orderRepository, int batchSize) {
				
		Collections.sort(flights, (f1, f2) -> f1.getDay() < f2.getDay() ? -1 : 1);
    	
		Iterator<Flight> flightIterator = flights.iterator();
		int i = 0;

		while(i < flights.size() * batchSize) {
			Flight flight = flightIterator.next();
			Optional<Map<String, Order>> maybeOrders = orderRepository.getOrders(i, batchSize);
			if(maybeOrders.isPresent()) 
				flight = ScheduleFlightOrders(flight, maybeOrders.get());
			else
				flight.setScheduledOrders(new ArrayList<>());	

			i+=batchSize;
		}

		return flights;
	}

	@Override
	public List<String> getNonScheduledOrders(List<Flight> flights, IOrderRepository orderRepo) {
		
		final Map<String, Order> allOrders = orderRepo.getOrders(0, -1)
													.orElseGet(() -> new HashMap<String, Order>());

        Set<String> assignedOrders = flights
						.stream()
						.flatMap(f -> 
							f.getScheduledOrders()
							.stream())
						.map(ord -> ord.getOrderId()).collect(Collectors.toSet());
						
		return allOrders.keySet().stream()
				.filter(o->!assignedOrders.contains(o))
				.collect(Collectors.toList());
	}
		
}
