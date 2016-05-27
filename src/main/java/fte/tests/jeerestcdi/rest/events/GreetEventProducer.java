package fte.tests.jeerestcdi.rest.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import fte.tests.jeerestcdi.rest.entity.Greet;
import fte.tests.jeerestcdi.rest.events.rabbitmq.RabbitMq;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

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
        greetCreationChannel.basicPublish(CREATION_TOPIC, "", null, mapper.writeValueAsString(greet).getBytes());
    }

    private void init (@Observes @Initialized(ApplicationScoped.class) Object init) throws IOException {
        greetCreationChannel.exchangeDeclare(CREATION_TOPIC, "fanout");
    }

    private void preDestroy(@Observes @Destroyed(ApplicationScoped.class) Object init) throws IOException, TimeoutException {
        greetCreationChannel.close();
    }


}
