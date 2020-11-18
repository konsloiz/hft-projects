FROM openjdk:14
RUN mkdir -p /opt/spring-project
WORKDIR /opt/spring-project
COPY /target/spring-project-0.0.1-SNAPSHOT.jar /opt/spring-project
CMD ["java", "-jar", "spring-project-0.0.1-SNAPSHOT.jar"]