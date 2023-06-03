package com.abelhzo.event.dtos;

import java.time.LocalDateTime;

/**
 * @author: Abel HZO
 * @project: springboot-event-publisher
 * @file: EventDTO.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 02 Junio 2023, 22:12:51.
 * @description: El presente archivo EventDTO.java fue creado por Abel HZO.
 */
public class EventDTO {

	private String idEvent;
	private String message;
	private TypeEvent typeEvent;
	private Double amount;
	private LocalDateTime localDateTime;

	public String getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TypeEvent getTypeEvent() {
		return typeEvent;
	}

	public void setTypeEvent(TypeEvent typeEvent) {
		this.typeEvent = typeEvent;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

}
