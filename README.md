## La clase ApplicationEventPublisher

La clase ApplicationEventPublisher permite enviar datos a una clase dentro de la misma aplicación anotada con ```@EnableAsync```, y los métodos que recibirán la respuesta serán anotados con ```@Async``` y ```@EventListener```; esto permite hacer un broadcast dentro de la misma aplicación.

 > EventPublisherServiceImpl.java

``` Java	
@Autowired
private ApplicationEventPublisher applicationEventPublisher;

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

  // Método que enviará el mensaje a travéz de la aplicación
	applicationEventPublisher.publishEvent(new Event<Map<String, Object>>(map));

	return new ResponseEntity<String>("Mensaje Entregado!!!", HttpStatus.OK);

}

```

> EventHandler.java

``` Java
@Component
@EnableAsync
public class EventHandler {
		
private Logger logger = LoggerFactory.getLogger(EventHandler.class);

// Método que recíbe el mensaje y así pueden ser creados más métodos dentro de la aplicación para que reciban el mensaje.
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

}

```

Realizamos la prueba con el siguiente comando:

``` Bash
curl -X POST -H "Content-Type: application/json" -d '{"message":"Pago con prestamo bancario.", "typeEvent":"LOAN", "amount":"12000.59"}' http://localhost:8080/publish
```
Como respuesta obtendremos los datos que recibirán los métodos anotados con ```@EventListener```:

```
2025-12-01T14:59:16.325Z  INFO 4972 --- [         task-1] c.abelhzo.event.components.EventHandler  : 
Handle 01: {
  "timestamp" : 1764601154296,
  "source" : {
    "host" : "localhost:8080",
    "user-agent" : "curl/7.88.1",
    "event" : {
      "idEvent" : "1e0fce62-6f59-4052-ac73-74c3de5e8a3e",
      "message" : "Pago con prestamo bancario.",
      "typeEvent" : "LOAN",
      "amount" : 12000.59,
      "localDateTime" : [ 2025, 12, 1, 14, 59, 14, 296461512 ]
    }
  }
}
2025-12-01T14:59:18.308Z  INFO 4972 --- [         task-2] c.abelhzo.event.components.EventHandler  : 
Handle 02: {
  "timestamp" : 1764601154296,
  "source" : {
    "host" : "localhost:8080",
    "user-agent" : "curl/7.88.1",
    "event" : {
      "idEvent" : "1e0fce62-6f59-4052-ac73-74c3de5e8a3e",
      "message" : "Pago con prestamo bancario.",
      "typeEvent" : "LOAN",
      "amount" : 12000.59,
      "localDateTime" : [ 2025, 12, 1, 14, 59, 14, 296461512 ]
    }
  }
}
```
