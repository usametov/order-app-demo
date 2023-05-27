package com.airtek.orders;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Stream;

public class OrderScheduler implements IOrderScheduler {

	public Flight ScheduleFlightOrders(Flight flight, Map<String, Order> ordersMap) {
		
		List<String> sortedKeys = ordersMap.keySet().stream().sorted().limit(20).toList();
		List<Order> orders = new ArrayList<Order>();
		for(String orderKey:sortedKeys) {
			orders.add(ordersMap.get(orderKey));
		}
		
		flight.setScheduledOrders(orders);
		return flight;
	}

	@Override
	public List<Flight> ScheduleOrdersForManyFlights(List<Flight> flights, Map<String, Order> orders) {
		
		// TODO Auto-generated method stub		
		Collections.sort(flights, (f1, f2) -> f1.getDay() < f2.getDay() ? -1 : 1);
    	
		Iterator<Map<String, Order>> batchIterator = partitionMap(orders, 20).iterator(); 
		
		while(batchIterator.hasNext()) {
			Map<String, Order> batch = batchIterator.next();
			flights.forEach((Flight flight)-> ScheduleFlightOrders(flight, batch));
		}
		return flights;
	}

	private static List<Map<String, Order>> partitionMap(Map<String, Order> ordersMap, int batchSize) {
		
		int i = 0;
		List<Map<String, Order>> result = new ArrayList<Map<String, Order>>();
		List<String> sortedKeys = ordersMap.keySet().stream().sorted().toList();
		
		while(i < ordersMap.keySet().size()) {	
			//sort keys
			List<String> keyBatch = sortedKeys.stream().skip(i).limit(batchSize).toList();
			
			//make a batch
			HashMap<String, Order> batch = new HashMap<String, Order>();
			keyBatch.forEach(s->batch.put(s, ordersMap.get(s)));
			
			result.add(batch);
			i+=batchSize;
		} 
		return result;
	}	
}
