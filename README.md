# Microservices System with RabbitMQ, Eureka, API Gateway, and Docker

This project demonstrates a microservices architecture where different modules communicate with each other using RabbitMQ for message queuing, Eureka for service discovery, and PostgreSQL for data storage. The services are containerized using Docker to make it easy to deploy, scale, and manage the entire application.

## Architecture Overview

The system consists of the following key components:

1. **API Gateway (ApiGWApplication)**:
    - The API Gateway serves as the entry point for clients to interact with the backend services.
    - It registers itself with Eureka for service discovery and forwards requests to the appropriate backend microservices.
    - It centralizes routing, load balancing, and cross-cutting concerns like authentication and logging.

2. **Eureka Server (EurekaServerApplication)**:
    - Acts as a service registry, enabling microservices to register themselves and dynamically discover each other.
    - Uses the `@EnableEurekaServer` annotation to function as the Eureka server, allowing for flexible and scalable service communication.

3. **Fraud Service**:
    - Provides functionality to check if a customer is fraudulent.
    - Uses a `FraudCheckService` to simulate fraud detection and logs the results in the `FraudCheckHistoryRepository`.

4. **Customer Service**:
    - Handles customer registration and checks if the customer is flagged as a fraudster using the Fraud Service.
    - If the customer is not flagged, it publishes a notification request via RabbitMQ to notify the customer.

5. **Notification Service**:
    - Sends notifications (e.g., welcome messages) to customers after successful registration.
    - Uses RabbitMQ to receive notification requests and stores them in the `NotificationRepository`.

6. **RabbitMQ Communication**:
    - AMQP (Advanced Message Queuing Protocol) is used for asynchronous communication between services.
    - RabbitMQ acts as the message broker, with the system components publishing and consuming messages using `AmqpTemplate`.
    - `Jackson2JsonMessageConverter` is used for message serialization, converting objects into JSON format for message transmission.

7. **PostgreSQL Database**:
    - The project uses PostgreSQL as the database for storing customer and notification data. It is managed via Docker for easy setup and containerization.

## Docker Setup

The application is fully containerized using Docker. The following services are defined in `docker-compose.yml`:

### Services
1. **PostgreSQL**:
    - A container running PostgreSQL for data storage.
    - Configured with a user `amigoscode` and password `password`.

2. **pgAdmin**:
    - A container running pgAdmin to interact with the PostgreSQL database via a web interface.
    - Accessible on port `5050`.

3. **RabbitMQ**:
    - A container running RabbitMQ, the message broker.
    - Provides access to the RabbitMQ management interface on port `15672`.

4. **Zipkin**:
    - A container running Zipkin for distributed tracing, helping with monitoring and debugging microservices.

5. **Eureka Server**:
    - A container running Eureka Server for service discovery.
    - Accessible on port `8761`.

6. **Other Services**:
    - Other services like `apigw`, `customer`, `fraud`, and `notification` can be defined similarly to the others, using Docker containers with respective configurations.