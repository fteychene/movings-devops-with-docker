package fte.tests.jeerestcdi.rest.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import fte.tests.jeerestcdi.rest.entity.Greet;
import fte.tests.jeerestcdi.rest.events.rabbitmq.RabbitMq;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fteychene on 5/26/16.
 */
@ApplicationScoped
public class GreetEventConsumer {

    private static final String CREATION_TOPIC = "greet_creation";

    private List<Greet> greets;

    public GreetEventConsumer() {
        super();
        greets = new ArrayList<>();
    }

    @Inject
    @RabbitMq
    private Channel greetCreationChannel;

    @PostConstruct
    private void init () throws IOException {
        greetCreationChannel.queueDeclare(CREATION_TOPIC, false, false, true, null);
        greetCreationChannel.basicConsume(CREATION_TOPIC, true, new DefaultConsumer(greetCreationChannel) {

            ObjectMapper mapper = new ObjectMapper();

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                Greet greet = mapper.readValue(message, Greet.class);
                System.out.println(" [x] Received '" + greet + "'");
                greets.add(greet);
            }
        });
    }

    public List<Greet> getGreets() {
        return greets;
    }
}
