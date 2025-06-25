# Microservices System – User, Product, Inventory, and Order Services

This repository hosts a suite of Spring Boot microservices that together model a basic e-commerce system. Each service is independently deployable and responsible for a distinct business capability.

## Technology Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Web
- Spring Validation
- H2 / MySQL (configurable)
- Maven

## Microservice Overview

| Service            | Responsibility                                      |
|--------------------|------------------------------------------------------|
| `user_service`     | Manages user registration, login, and role assignment |
| `product-service`  | Maintains product catalog and associated metadata     |
| `inventory-service`| Tracks stock levels and inventory updates            |
| `order-service`    | Coordinates order creation and processing             |

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17+
- Maven
- Git

### Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/your-repo-name.git
   cd your-repo-name
