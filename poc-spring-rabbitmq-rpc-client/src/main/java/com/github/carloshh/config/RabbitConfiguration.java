package com.github.carloshh.config;

import com.github.carloshh.service.ReverseStringService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfiguration {
    private static final String RPC_BINDING = "rpc.binding";
    private static final String RPC_EXCHANGE = "rpc.exchange";

    @Bean
    Queue queue() {
        return new Queue("rpc-queue");
    }

    @Bean AmqpProxyFactoryBean amqpFactoryBean(AmqpTemplate amqpTemplate) {
        var amqpProxyFactoryBean = new AmqpProxyFactoryBean();
        amqpProxyFactoryBean.setServiceInterface(ReverseStringService.class);
        amqpProxyFactoryBean.setAmqpTemplate(amqpTemplate);
        return amqpProxyFactoryBean;
    }

    @Bean
    Exchange directExchange(Queue queue) {
        var exchange = new DirectExchange("rpc.exchange");
        return exchange;
    }

    @Bean
    Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("rpc.binding").noargs();
    }

    @Bean
    Jackson2JsonMessageConverter jacksonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonMessageConverter());
        rabbitTemplate.setRoutingKey(RPC_BINDING);
        rabbitTemplate.setExchange(RPC_EXCHANGE);
        return rabbitTemplate;
    }

}
