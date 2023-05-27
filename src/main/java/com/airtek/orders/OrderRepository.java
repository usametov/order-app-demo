package com.airtek.orders;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.io.BufferedReader;
import java.io.FileReader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderRepository implements IOrderRepository{

	public Optional<Map<String, Order>> getOrders() {
		
		Map<String, Order> ordersMap = null;		
		
		try {
		    FileReader fileReader = new FileReader("./orders.json");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }

            bufferedReader.close();
			
			
		  ObjectMapper objectMapper = new ObjectMapper();
          ordersMap = objectMapper.readValue(content.toString(), 
       		 new TypeReference<Map<String, Order>>() {});
          
          return Optional.of(ordersMap);
		
		} catch (IOException e) {//IOException
            e.printStackTrace();
            return Optional.empty();
        }
		
	}

}
