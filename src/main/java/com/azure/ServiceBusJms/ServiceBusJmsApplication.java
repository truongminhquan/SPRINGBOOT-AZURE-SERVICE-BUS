package com.azure.ServiceBusJms;

import com.microsoft.azure.servicebus.IQueueClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ServiceBusJmsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ServiceBusJmsApplication.class, args);

	}

}
