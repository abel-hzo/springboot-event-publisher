package com.abelhzo.event.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: Abel HZO
 * @project: springboot-event-publisher
 * @file: App.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 02 Junio 2023, 21:59:04.
 * @description: El presente archivo App.java fue creado por Abel HZO.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.abelhzo.event" })
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
