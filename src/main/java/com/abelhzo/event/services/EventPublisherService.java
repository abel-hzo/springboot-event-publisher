package com.abelhzo.event.services;

import org.springframework.http.ResponseEntity;

import com.abelhzo.event.dtos.EventDTO;

/**
 * @author: Abel HZO
 * @project: springboot-event-publisher
 * @file: EventPublisherService.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 02 Junio 2023, 22:11:31.
 * @description: El presente archivo EventPublisherService.java fue creado por
 *               Abel HZO.
 */
public interface EventPublisherService {

	ResponseEntity<String> publishEvent(EventDTO eventDTO);

}
