## Module: Configuration & Profiles

* Various possibilities of external configuration in Spring (Boot)
* Order of configuration options
* Spring profiles
* Consuming configuration properties with @Value and specifying defaults
* Spring Cloud Config Server and Client as reference example for external configuration

### Objectives and exercises
_The student is able to build a Spring Boot application with various configuration profiles and knows how to set the desired one. It is also required to understand how Spring Boot will prioritize between them, e.g. ("Who wins if multiple are specified?") and what the advantages and drawbacks of them are. The exercise is to extend the existing persistence application and provide different persistence backend for different purposes, e.g. set an in-memory database for development and test purposes and a containerized one for production._

### Links:

* https://docs.spring.io/spring-boot/docs/1.0.1.RELEASE/reference/html/boot-features-external-config.html
* https://docs.spring.io/spring-boot/docs/1.2.3.RELEASE/reference/html/boot-features-external-config.html
* https://www.baeldung.com/spring-value-annotation
* https://spring.io/guides/gs/centralized-configuration/
