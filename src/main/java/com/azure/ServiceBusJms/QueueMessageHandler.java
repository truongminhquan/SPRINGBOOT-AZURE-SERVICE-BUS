package com.azure.ServiceBusJms;

import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Component
public class QueueMessageHandler {

    @Autowired
    private IQueueClient sendClient;

    @PostConstruct
    public void handlingMessage() {
        IMessageHandler handler = new IMessageHandler() {
            @Override
            public CompletableFuture<Void> onMessageAsync(IMessage message) {
                MessageBody messageBody = message.getMessageBody();
                byte[] body = messageBody.getBinaryData().get(0);
                String s = new String(body, StandardCharsets.UTF_8);
                System.out.println("===============" + s);
                return sendClient.completeAsync(message.getLockToken());
            }

            @Override
            public void notifyException(Throwable throwable, ExceptionPhase exceptionPhase) {
                System.out.printf(exceptionPhase + "-" + throwable.getMessage());
            }
        };

        try {
            sendClient.registerMessageHandler(
                    handler, new MessageHandlerOptions(1, false, Duration.ofMinutes(1)), Executors.newFixedThreadPool(1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ServiceBusException e) {
            throw new RuntimeException(e);
        }
    }
}
