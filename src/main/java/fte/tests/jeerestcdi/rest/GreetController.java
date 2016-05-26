package fte.tests.jeerestcdi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import fte.tests.jeerestcdi.rest.entity.Greet;
import fte.tests.jeerestcdi.rest.events.GreetEventConsumer;
import fte.tests.jeerestcdi.rest.events.rabbitmq.RabbitMq;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/greet")
public class GreetController {

	private static final String QUEUE_NAME = "test";

	@Inject
	private GreetEventConsumer greetEvents;
	
	@Inject
	private GreetService service;
	
	@Path("/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String greet(@PathParam("name") String name) throws IOException {
		return "Hello " + service.greet(name).getName();
	}
	
	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<Greet> list() {
		return greetEvents.getGreets();
	}

	
}
