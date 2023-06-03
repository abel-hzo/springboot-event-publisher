package com.abelhzo.event.dtos;

import org.springframework.context.ApplicationEvent;

/**
 * @author: Abel HZO
 * @project: springboot-event-publisher
 * @file: Event.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 02 Junio 2023, 22:08:59.
 * @description: El presente archivo Event.java fue creado por Abel HZO.
 */
public class Event<T> extends ApplicationEvent {

	private static final long serialVersionUID = 3455837698975858459L;

	public Event(T source) {
		super(source);
	}

}
