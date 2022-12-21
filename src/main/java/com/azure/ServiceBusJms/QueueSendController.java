package com.azure.ServiceBusJms;


import com.microsoft.azure.servicebus.IQueueClient;
import com.microsoft.azure.servicebus.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueueSendController {
    private static final String QUEUE_NAME = "myqueue";

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueSendController.class);

    @Autowired
    private IQueueClient sendClient;

    @GetMapping("/queue")
    public String postMessage(@RequestParam String message) {

        LOGGER.info("Sending message");

        this.sendClient.sendAsync(new Message(message));


        return message;
    }
}
