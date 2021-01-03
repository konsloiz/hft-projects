== Module: Spring Boot Introduction

* Background: Spring Framework - History & components
* Spring <- -> Spring Boot
* Spring Initializr (start.spring.io) & starter dependencies
* Basic project structure (folders, configuration ..)
* "Hello, World!" example explained
* Basic logging and testing
* Using Actuator

=== Objectives
_The student is able to build and configure an own Spring Boot application from scratch with the IDE of choice. The exercise is to build an own "Hello, World!" application that exposes various REST endpoints and is able to execute CRUD operations on the state of the application. Optional: Add logging and testing, configure Actuator._

=== Exercises

* Build a simple Spring Boot Microservice with the following starter dependencies: Web, Actuator
* Run the microservice and test the endpoint /actuator/health and /actuator/info
* Annotate the Microservice with @RestController and implement a "Hello, World!" method, which responds to an HTTP GET request.
* Add some basic "functionality" to make the service react to HTTP GET, POST, PUT and DELETE calls (e.g. modify an internal list)
* Change the configuration to make the server run on port 8081 instead of 8080

=== Links

* https://start.spring.io/
* https://spring.io/guides/
* https://www.baeldung.com/spring-boot/