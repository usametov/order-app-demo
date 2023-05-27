package com.airtek.orders;
import java.util.Map;
import java.util.Optional;

public interface IOrderRepository {
	
	Optional<Map<String, Order>> getOrders();
}
