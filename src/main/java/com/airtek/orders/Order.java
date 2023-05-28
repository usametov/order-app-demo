package com.airtek.orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
   
   @JsonIgnore
   private String orderId;
   
   public String getOrderId() {
      return orderId;
   }

   public void setOrderId(String orderId) {
      this.orderId = orderId;
   }

   private String destination;

   public String getDestination() {
	return destination;
   }

   public void setDestination(String destination) {
	this.destination = destination;
   }
   
//   public Order(@JsonProperty("Destination") String destination) {
//	   this.destination = destination;
//   }
}
