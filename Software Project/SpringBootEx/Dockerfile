FROM openjdk:11
RUN mkdir -p /opt/spring_boot_ex
WORKDIR /opt/spring_boot_ex
COPY /target/SpringBootEx-0.0.1-SNAPSHOT.jar /opt/spring_boot_ex
CMD ["java", "-jar", "SpringBootEx-0.0.1-SNAPSHOT.jar"]