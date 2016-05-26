package fte.tests.jeerestcdi.rest.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import fte.tests.jeerestcdi.rest.entity.Greet;
import fte.tests.jeerestcdi.rest.events.rabbitmq.RabbitMq;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by fteychene on 5/26/16.
 */
@ApplicationScoped
public class GreetEventProducer {

    private static final String CREATION_TOPIC = "greet_creation";

    @Inject
    @RabbitMq
    private Channel greetCreationChannel;

    public void creditPayment(@Observes @CreationEvent Greet greet) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        greetCreationChannel.basicPublish("", CREATION_TOPIC, null, mapper.writeValueAsString(greet).getBytes());
    }

    @PostConstruct
    private void init () throws IOException {
        greetCreationChannel.queueDeclare(CREATION_TOPIC, false, false, true, null);
    }
}
