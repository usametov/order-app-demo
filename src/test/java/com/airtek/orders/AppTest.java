package com.airtek.orders;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{    
    @Test    
    public void testOrderRepo_OutOfBounds() {
        IOrderRepository orderRepo = new OrderRepository();
        assert(orderRepo.getOrders(110, 110).get().size()==0);
    }

    @Test    
    public void testOrderRepo_GetAll() {
        IOrderRepository orderRepo = new OrderRepository();
        assert(orderRepo.getOrders(0, -1).get().size()==96);
    }

    @Test
    public void testOrderSchedule_OrdersNull1() {

        IOrderScheduler scheduler = new OrderScheduler();
        assert(scheduler.ScheduleFlightOrders(
            new Flight(1, "YYL"
                       , "YYZ", 1), null) != null);
    }

    @Test
    public void testOrderSchedule_OrdersNull2() {

        IOrderScheduler scheduler = new OrderScheduler();
        assert(scheduler.ScheduleFlightOrders(
            new Flight(1, "YYL"
                       , "YYZ", 1), null).getScheduledOrders() == null);
    }

    @Test
    public void testOrderSchedule_NullFlight() {

        IOrderScheduler scheduler = new OrderScheduler();
        assertThrows(IllegalArgumentException.class
        , () -> scheduler.ScheduleFlightOrders(null, null)
        , "it's supposed to throw IllegalArgument here!");
    }

}
