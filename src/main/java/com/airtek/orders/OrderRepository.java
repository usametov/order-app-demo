package com.airtek.orders;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.io.BufferedReader;
import java.io.FileReader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderRepository implements IOrderRepository{

    Map<String, Order> ordersMap = null;		

	public Optional<Map<String, Order>> getOrders(int skip, int limit) {

        Logger logger = Logger.getLogger(App.class.getName());
		logger.setLevel(Level.FINE);	
        logger.addHandler(new ConsoleHandler());
        
        logger.log(Level.FINE, "test logging");
				
		
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
          
          if(limit > 0) {
            List<String> sortedKeys = ordersMap.keySet().stream().sorted().toList();
            List<String> keyBatch = sortedKeys.stream().skip(skip).limit(limit).toList();

            HashMap<String, Order> batch = new HashMap<String, Order>();
		    keyBatch.forEach(s->batch.put(s, ordersMap.get(s)));

            return Optional.of(batch);
          } 
          else {
            return Optional.of(ordersMap);  
          }
		
		} catch (IOException e) {
            logger.log(Level.SEVERE, e.getStackTrace().toString());
            e.printStackTrace();
            return Optional.empty();
        }
		
	}    

}
