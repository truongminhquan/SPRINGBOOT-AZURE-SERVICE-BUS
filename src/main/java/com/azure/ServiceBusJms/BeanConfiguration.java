package com.azure.ServiceBusJms;

import com.azure.messaging.servicebus.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Value("${SERVICE_BUS_CONNECTION_STRING}")
    private String SERVICE_BUS_CONNECTION_STRING;

    @Value("${SERVICE_BUS_QUEUE_NAME}")
    private String SERVICE_BUS_QUEUE_NAME;

    @Bean
    public ServiceBusSenderClient getServiceBusSenderClientBean() {
        System.out.println("================ SERVICE_BUS_CONNECTION_STRING: " + SERVICE_BUS_CONNECTION_STRING);
        ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
                .connectionString(SERVICE_BUS_CONNECTION_STRING)
                .processor()
                .queueName(SERVICE_BUS_QUEUE_NAME)
                .buildProcessorClient();


        return new ServiceBusClientBuilder()
                .connectionString(SERVICE_BUS_CONNECTION_STRING)
                .sender()
                .queueName(SERVICE_BUS_QUEUE_NAME)
                .buildClient();
    }

    private static void processMessage(ServiceBusReceivedMessageContext context) {
        ServiceBusReceivedMessage message = context.getMessage();
        System.out.printf("Processing message. Session: %s, Sequence #: %s. Contents: %s%n", message.getMessageId(),
                message.getSequenceNumber(), message.getBody());
    }
}
