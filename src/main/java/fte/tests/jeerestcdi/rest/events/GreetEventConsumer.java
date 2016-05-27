package fte.tests.jeerestcdi.rest.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

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

    private void init (@Observes @Initialized(ApplicationScoped.class) Object init) throws IOException {
        greetCreationChannel.exchangeDeclare(CREATION_TOPIC, "fanout");
        String queueName = greetCreationChannel.queueDeclare().getQueue();
        greetCreationChannel.queueBind(queueName, CREATION_TOPIC, "");
        greetCreationChannel.basicConsume(queueName, true, new DefaultConsumer(greetCreationChannel) {

            ObjectMapper mapper = new ObjectMapper();

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                Greet greet = mapper.readValue(message, Greet.class);
                System.out.println(" [x] Received '" + greet + "'");
                greets.add(greet);
            }
        });
        System.out.println("Waiting messages from Greet creation queue");
    }

    private void preDestroy(@Observes @Destroyed(ApplicationScoped.class) Object init) throws IOException, TimeoutException {
        greetCreationChannel.close();
    }

    public List<Greet> getGreets() {
        return greets;
    }
}
