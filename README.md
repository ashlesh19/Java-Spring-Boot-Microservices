# Java-Spring-Boot-Microservices
Java-Spring-Boot-Microservices with Docker and Kubernetes

## Description

Project Description: This project comprises several microservices along with supporting services such as an API Gateway, Configuration Server, and Service Registry. Additionally, it includes Docker-compose configurations for services like PostgreSQL, pgAdmin, Zipkin, and RabbitMQ. All microservices are also enabled to run in Kubernetes, with application properties containing resources for active profiles for both Kubernetes and Docker. Minikube is used for Kubernetes deployment. Kubernetes configuration files are available in the k8s folder for easy deployment.

## Contents
- [Microservices](#microservices)
  - [Company](#company)
  - [Job](#job)
  - [Reviews](#reviews)
- [Supporting Services](#supporting-services)
  - [API Gateway](#api-gateway)
  - [Configuration Server](#configuration-server)
  - [Service Registry](#service-registry)
- [Docker Services](#docker-services)
  - [PostgreSQL](#postgresql)
  - [pgAdmin](#pgadmin)
  - [Zipkin](#zipkin)
  - [RabbitMQ](#rabbitmq)
- [Kubernetes Deployment](#kubernetes-deployment)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)

## Microservices

## Company

- **Description:** Manages company information.

## Job

- **Description:** Handles job-related functionalities.

## Reviews

- **Description:** Allows users to submit and retrieve reviews.

## Supporting Services

## API Gateway

- **Description:** Acts as a single entry point for all microservices.
  
## Configuration Server

- **Description:** Manages configuration properties for all microservices.

## Service Registry

- **Description:** Provides service discovery for microservices.

## Docker Services

## PostgreSQL

- **Description:** Database service for storing application data.

## pgAdmin

- **Description:** Web-based administration tool for PostgreSQL.

## Zipkin

- **Description:** Distributed tracing system to gather timing data for requests.

## RabbitMQ

- **Description:** Message broker for handling asynchronous communication between microservices.

## Kubernetes Deployment

All microservices are configured to run in Kubernetes. The application properties contain resources for active profiles for both Kubernetes and Docker. Minikube is used for Kubernetes deployment. Kubernetes configuration files are available in the `k8s` folder for easy deployment. The `k8s` folder contains configurations for microservices in the `apps` folder and supporting services like PostgreSQL, RabbitMQ, and Zipkin in the `services` folder.

## Getting Started

To get started with this project, follow the instructions below:

## Prerequisites

Make sure you have the following prerequisites installed:

- Java Development Kit (JDK) 8 or later
- Maven
- Docker
- Minikube

## Installation

1. Clone the repository:

```bash
git clone https://github.com/ashlesh19/Java-Spring-Boot-Microservices.git
