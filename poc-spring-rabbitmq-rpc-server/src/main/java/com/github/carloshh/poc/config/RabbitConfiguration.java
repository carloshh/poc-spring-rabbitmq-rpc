package com.github.carloshh.poc.config;

import com.github.carloshh.poc.service.ReverseStringService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    Queue queue() {
        return new Queue("rpc-queue");
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    AmqpInvokerServiceExporter reverseStringServiceExporter(ReverseStringService implementation, AmqpTemplate template) {
        var amqpInvokerServiceExporter = new AmqpInvokerServiceExporter();
        amqpInvokerServiceExporter.setServiceInterface(ReverseStringService.class);
        amqpInvokerServiceExporter.setService(implementation);
        amqpInvokerServiceExporter.setAmqpTemplate(template);
        amqpInvokerServiceExporter.setMessageConverter(jacksonMessageConverter());
        return amqpInvokerServiceExporter;
    }

    @Bean SimpleMessageListenerContainer listener(ConnectionFactory factory, AmqpInvokerServiceExporter exporter, Queue queue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
        container.setMessageListener(exporter);
        container.setQueueNames(queue.getName());
        return container;
    }

}
