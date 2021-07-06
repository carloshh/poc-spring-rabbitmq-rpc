# poc-spring-rabbitmq-rpc

### Introduction
The following proof of concept aims to validate *synchronous* communication using RabbitMQ RPC support using Spring

### Requisites
To run this proof of concept the following requirements have to be met

* Java 16
* Docker

### How to run

rabbitmq
```shell
docker-compose up
```

poc-spring-rabbitmq-rpc-client
```shell
./mvnw spring-boot:run
```

poc-spring-rabbitmq-rpc-server
```shell
./mvnw spring-boot:run
```