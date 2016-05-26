package fte.tests.jeerestcdi.rest.events.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by fteychene on 5/26/16.
 */
@ApplicationScoped
public class RabbitMqProducer {

    private static String envVarOrElse(String name, String defaultValue) {
        return System.getenv().getOrDefault(name, defaultValue);
    }

    @Produces
    @RabbitMq
    public Channel provideChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(envVarOrElse("RABBITMQ_HOST", "localhost"));
        factory.setPort(Integer.valueOf(envVarOrElse("RABBITMQ_PORT", "5672")));
        factory.setUsername("guest");
        factory.setPassword("guest");
        final Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        return channel;
    }
}
