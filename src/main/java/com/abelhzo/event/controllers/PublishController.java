package com.abelhzo.event.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abelhzo.event.dtos.EventDTO;
import com.abelhzo.event.services.EventPublisherService;

/**
 * @author: Abel HZO
 * @project: springboot-event-publisher
 * @file: PublishController.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 02 Junio 2023, 22:26:37.
 * @description: El presente archivo PublishController.java fue creado por Abel
 *               HZO.
 */
@RestController
public class PublishController implements EventPublisherService {

	@Autowired
	private EventPublisherService eventPublisherService;

	@Override
	@PostMapping("/publish")
	public ResponseEntity<String> publishEvent(@RequestBody EventDTO eventDTO) {
		
		return eventPublisherService.publishEvent(eventDTO);	
		
	}

}
/**
	Desde el postman mandamos a la url: http://localhost:8080/publish
	
	{
	    "message": "Pago con prestamo bancario",
	    "amount": "150000.00",
	    "typeEvent": "LOAN"
	}

	Mensaje Entregado!!!
*/