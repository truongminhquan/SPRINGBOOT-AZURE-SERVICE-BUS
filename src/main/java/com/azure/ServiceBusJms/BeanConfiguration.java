package com.azure.ServiceBusJms;

import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;


@Configuration
public class BeanConfiguration {
    @Value("${SERVICE_BUS_CONNECTION_STRING}")
    private String SERVICE_BUS_CONNECTION_STRING;

    @Value("${SERVICE_BUS_QUEUE_NAME}")
    private String SERVICE_BUS_QUEUE_NAME;

    @Bean
    public IQueueClient queueClient() {
        try {
            IMessageHandler handler = new IMessageHandler() {
                @Override
                public CompletableFuture<Void> onMessageAsync(IMessage iMessage) {
                    return null;
                }

                @Override
                public void notifyException(Throwable throwable, ExceptionPhase exceptionPhase) {

                }
            };
            IQueueClient queueClient = new QueueClient(new ConnectionStringBuilder(SERVICE_BUS_CONNECTION_STRING, SERVICE_BUS_QUEUE_NAME), ReceiveMode.PEEKLOCK);
            return queueClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
