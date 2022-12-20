package com.azure.ServiceBusJms;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {

    @Autowired
    private ServiceBusSenderClient senderClient;

    private static final Logger logger = LoggerFactory.getLogger(SendController.class);


    @PostMapping("/messages")
    public String postMessage(@RequestParam String message){
        logger.info("Sending message");
        senderClient.sendMessage(new ServiceBusMessage(message));
        senderClient.close();
        return message;
    }
}
