package com.abelhzo.event.components;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.abelhzo.event.dtos.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * 
 * @author: Abel HZO
 * @project: springboot-event-publisher
 * @file: EventHandler.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 02 Junio 2023, 22:33:27.
 * @description: El presente archivo EventHandler.java fue creado por Abel HZO.
 */
@Component
@EnableAsync
public class EventHandler {

	private Logger logger = LoggerFactory.getLogger(EventHandler.class);

	@Async
	@EventListener
	public void handleEvent01(Event<Map<String, Object>> event) throws JsonProcessingException {

		try {
			/*
			 * Este tiempo muerto de espera simula un proceso largo, mientras se ejecuta el
			 * listener, el proceso que publica el mensaje, termina sin esperar al listener.
			 */
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("\nHandle 01: " + new ObjectMapper()
				.registerModule(new JavaTimeModule())  // Evita: Java 8 date/time type `java.time.LocalDateTime` not supported by default
				.writerWithDefaultPrettyPrinter()
				.writeValueAsString(event));

	}
	
	@Async
	@EventListener
	public void handleEvent02(Event<Map<String, Object>> event) throws JsonProcessingException {

		try {
			/*
			 * Este tiempo muerto de espera simula un proceso largo, mientras se ejecuta el
			 * listener, el proceso que publica el mensaje, termina sin esperar al listener.
			 */
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("\nHandle 02: " + new ObjectMapper()
				.registerModule(new JavaTimeModule())  // Evita: Java 8 date/time type `java.time.LocalDateTime` not supported by default
				.writerWithDefaultPrettyPrinter()
				.writeValueAsString(event));

	}	

}

/**
 
Handle 01: {
	  "source" : {
	    "host" : "localhost:8080",
	    "user-agent" : "PostmanRuntime/7.32.2",
	    "event" : {
	      "idEvent" : "833f8854-4e8d-45a7-89aa-c72b4dfd954c",
	      "message" : "Pago con prestamo bancario",
	      "typeEvent" : "LOAN",
	      "amount" : 150000.0,
	      "localDateTime" : [ 2023, 6, 3, 1, 31, 26, 862176760 ]
	    }
	  },
	  "timestamp" : 1685773886862
	}
	
Handle 02: {
  "source" : {
    "host" : "localhost:8080",
    "user-agent" : "PostmanRuntime/7.32.2",
    "event" : {
      "idEvent" : "833f8854-4e8d-45a7-89aa-c72b4dfd954c",
      "message" : "Pago con prestamo bancario",
      "typeEvent" : "LOAN",
      "amount" : 150000.0,
      "localDateTime" : [ 2023, 6, 3, 1, 31, 26, 862176760 ]
    }
  },
  "timestamp" : 1685773886862
}	

*/