package com.abelhzo.event.services.impl;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.abelhzo.event.dtos.Event;
import com.abelhzo.event.dtos.EventDTO;
import com.abelhzo.event.services.EventPublisherService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author: Abel HZO
 * @project: springboot-event-publisher
 * @file: EventPublisherServiceImpl.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 02 Junio 2023, 22:17:47.
 * @description: El presente archivo EventPublisherServiceImpl.java fue creado
 *               por Abel HZO.
 */
@Service
public class EventPublisherServiceImpl implements EventPublisherService {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public ResponseEntity<String> publishEvent(EventDTO eventDTO) {

		eventDTO.setIdEvent(UUID.randomUUID().toString());
		eventDTO.setLocalDateTime(LocalDateTime.now());

		HttpServletRequest request = ((ServletRequestAttributes) 
				RequestContextHolder
				.getRequestAttributes())
				.getRequest();

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("host", request.getHeader("host"));
		map.put("user-agent", request.getHeader("User-Agent"));
		map.put("event", eventDTO);

		applicationEventPublisher.publishEvent(new Event<Map<String, Object>>(map));

		return new ResponseEntity<String>("Mensaje Entregado!!!\n", HttpStatus.OK);

	}

}
