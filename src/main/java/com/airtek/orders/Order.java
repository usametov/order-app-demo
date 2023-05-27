package com.airtek.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
      
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
