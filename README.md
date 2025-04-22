# 🧩 Microservices System with RabbitMQ, Eureka, API Gateway, and Docker (Optional: Kubernetes via Minikube)

This project showcases a **microservices architecture** designed to **register new customers for a sports betting and casino website**, while also checking against a list of **known bonus abusers**. 
Services communicate asynchronously using **RabbitMQ**, register via **Eureka** for service discovery, and persist data using **PostgreSQL**. The entire system is containerized with **Docker**, and can also be deployed locally using **Kubernetes with Minikube**.


### Optional Services

This project includes an [Eureka Server](./eureka-server) and an [API Gateway](./api-gateway), which are **not required for local development**.

They are useful for:
- Dynamic service discovery (Eureka)
- Centralized routing and cross-cutting concerns (Gateway)

To run them, activate the `eureka` and `gateway` Spring profiles or include them in Docker Compose.

---

## ⚙️ Architecture Overview

The system is composed of the following microservices and infrastructure components:

### 1. **Customer Service**
- Handles customer registration.
- Calls the Fraud Service to verify legitimacy.
- Publishes a notification event to RabbitMQ if the customer is clean.

### 2. **Fraud Service**
- Checks whether a customer is marked as a fraudster.
- Persists check results in `FraudCheckHistoryRepository`.

### 3. **Notification Service**
- Listens for events from RabbitMQ.
- Sends notifications (e.g., welcome messages) and logs them to `NotificationRepository`.

### 4. **RabbitMQ Messaging**
- Services communicate asynchronously using **AMQP** via RabbitMQ.
- `AmqpTemplate` is used to send/receive messages.
- Messages are serialized into JSON using `Jackson2JsonMessageConverter`.

### 5. **PostgreSQL Database**
- Stores customer and notification data.
- Managed through Docker (or Kubernetes pods with persistent volumes).

---

## 🚀 Deployment Options

### 🐳 Docker

The application is fully containerized with Docker. Services are defined in `docker-compose.yml`:

#### Services:

- **PostgreSQL**  
  Database service
   - User: `amigoscode`
   - Password: `password`

- **pgAdmin**  
  Web UI for PostgreSQL
   - Accessible at `localhost:5050`

- **RabbitMQ**  
  Message broker
   - Management console at `localhost:15672`

- **Zipkin**  
  Distributed tracing tool for monitoring microservices

- **Eureka Server**  
  Service registry for dynamic discovery
   - Accessible at `localhost:8761`

- **Core Services**  
  Microservices such as `customer`, `fraud`, `notification`, and `apigw` are each containerized and wired together via Docker networks.

---

### ☸️ Kubernetes (via Minikube)

You can also run the entire system in Kubernetes using Minikube:

- Each microservice is deployed as a **Pod** using its own **Deployment** and **Service** YAML.
- Infrastructure components like RabbitMQ, PostgreSQL, and Eureka can be exposed via **ClusterIP** or **NodePort**.
- Great for simulating a cloud-native deployment locally.

To get started:
```bash
minikube start
kubectl apply -f k8s/


