
# Notification Service

## Getting Started

These instructions will give you a copy of the project up and running on
your local machine for development and testing purposes. 

### Prerequisites

Requirements for the software and other tools to build, test and push 
- [Java](https://www.oracle.com/java/technologies/downloads/#jdk21-windows)
	- Download Java 21
	- Run installer, keep all defaults
	- Set JAVA_HOME environment variable - [Follow answer by Taras Melnyk](https://stackoverflow.com/questions/11161248/setting-java-home)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
	- [Windows | Docker Docs](https://docs.docker.com/desktop/setup/install/windows-install/#install-docker-desktop-on-windows) follow this guide to install Docker on Windows

## Running the Application

[Download](https://github.com/jAlbright2002/TaskManagementNotification.git) and open the project in an IDE or locate the root directory within your terminal

Run the following command, this will pull and run the docker images for the project, RabbitMQ and Mongo

	docker-compose up

The project will now be running and you can access its [endpoint](http://localhost:8080/allNotifs/email)

Replace **email** with an email registered using the Task Registration Service 

It is recommended to use an API testing tool such as [Postman](https://www.postman.com/downloads/) or [Talend API Extension](https://chromewebstore.google.com/detail/talend-api-tester-free-ed/aejoelaoggembcahagimdiliamlcdmfm) 
(Note: Must use a Chromium based browser to use this extension)

## Project Architecture
The **Task Management Notification Service** is built to send notifications when tasks are created, updated and deleted or if a user registers/signs in. It is built using SpringBoot and written in Java. The service listens to task events through RabbitMQ (message queues) and sends notifications to users. It stores all notifications within the Mongo database.

## Running the Tests
[Download](https://github.com/jAlbright2002/TaskManagementNotification.git) and open the project in an IDE or locate the root directory within your terminal and run the following command

	.\mvnw verify

This will test all unit and integration tests

## Authors
  - **James Albright** - *Project Owner* -
  - **Billie Thompson** - *Provided README Template* -
    [PurpleBooth](https://github.com/PurpleBooth)
