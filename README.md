# Event-Driven E-Banking

An event-driven microservices platform for e-banking built with **Spring Boot**, **Spring Cloud**, and **Axon Framework**. It implements CQRS and Event Sourcing patterns, using **Apache Kafka** (with Debezium CDC) for asynchronous inter-service communication and **Redis** for caching. The system is composed of core banking services (Account, Customer, Pipeline), Spring Cloud infrastructure components (API Gateway, Eureka, Config Server, BFFs), and an IAM service — all containerised with Docker Compose and backed by PostgreSQL/Oracle databases.
