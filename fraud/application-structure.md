# Fraud Detection Application Architecture

## Overview

This document outlines the structure and responsibilities of various components in the **Fraud Detection Application**. The application follows a layered architecture, consisting of the **Presentation Layer**, **Service Layer**, and **Data Layer**.

---

## Layers

### 1. **Presentation Layer (Controller)**

The Presentation Layer is responsible for handling HTTP requests and responses. It acts as the interface between the client (e.g., a web or mobile application) and the server.

- **FraudController**:
    - Handles incoming HTTP requests (e.g., GET requests for fraud checks).
    - Responds with appropriate data based on the business logic provided by the service layer.
    - Defines the API endpoint for interacting with the fraud detection service.

### 2. **Service Layer (Service)**

The Service Layer contains the business logic of the application and interacts with the repository to perform necessary operations.

- **FraudCheckService**:
    - Contains the core business logic for fraud detection.
    - Communicates with the `FraudCheckHistoryRepository` to fetch and persist data related to fraud checks.
    - Implements methods that perform fraud checks for customers and return results.

### 3. **Data Layer (Repository & Model)**

The Data Layer is responsible for interacting with the database and managing the persistence of the application’s data.

- **FraudCheckHistory**:
    - The model (or entity) that represents a fraud check record.
    - Maps to a database table in PostgreSQL.
    - Contains properties such as `customerId`, `fraudStatus`, and timestamps.

- **FraudCheckHistoryRepository**:
    - An interface extending `JpaRepository` to provide built-in CRUD operations for the `FraudCheckHistory` entity.
    - Enables interactions with the database without needing custom SQL queries.

---

## Components Overview

### 1. **FraudApplication**

- **Role**: Starts the Spring Boot application.
- **Purpose**: The entry point of the application that runs the Spring Boot application.

### 2. **FraudCheckHistory (Model)**

- **Role**: Defines the data model and maps it to a PostgreSQL table.
- **Purpose**: Represents the structure of the data that will be stored in the database.

### 3. **FraudCheckHistoryRepository (Repository)**

- **Role**: Provides CRUD functionality for the model.
- **Purpose**: Simplifies database interactions by leveraging Spring Data JPA.

### 4. **FraudCheckService (Service)**

- **Role**: Contains business logic and interacts with the repository.
- **Purpose**: Processes fraud check requests and handles fraud detection logic.

### 5. **FraudController (Controller)**

- **Role**: Handles HTTP requests and communicates with the service layer.
- **Purpose**: Exposes the API for fraud checking and relays requests to the `FraudCheckService`.

---

## Example Flow

1. The **FraudController** receives an HTTP request (e.g., `/api/v1/fraud-check/{customerId}`).
2. It delegates the request to the **FraudCheckService**.
3. The **FraudCheckService** queries the **FraudCheckHistoryRepository** to check if a fraud history exists.
4. If the fraud history exists, it returns the result; otherwise, it creates a new record and stores the information in the database.
5. The **FraudController** returns the appropriate response to the client.

---

## Summary

This architecture follows a **clean separation of concerns**:
- The **Presentation Layer** manages HTTP requests and responses.
- The **Service Layer** handles the business logic.
- The **Data Layer** manages data persistence and interactions with the database.
