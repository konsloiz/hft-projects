##Exploration: Spring Boot and Swagger
> This project was implemented for the
alphaQuest Project - Work Item:
[22](https://dev.azure.com/alphaquest/2020%20-%20Nurse%20Rostering/_boards/board/t/2020%20-%20Nurse%20Rostering%20Team/Issues/?workitem=22)

####Task Description
Set up a Java Spring Boot application with Swagger

Note: As you go, please create a global glossary on the Wiki.

Also, please document your findings as a team in the Wiki as a tutorial.

1. Set up a simple Spring Boot application locally: http://www.baeldung.com/spring-boot-start
2. Integrate OpenAPI (Swagger) http://www.baeldung.com/swagger-2-documenation-for-spring-rest-api
3. Integrate Lombok
4. Create a new Spring Boot application "time tracking" with basic CRUD functionality:
(no persistence layer required at this stage) Your application's REST interface should provide the following methods:

    * A GET method "addEntry" that saves a time entry:
    (user_name, date, duration, project_name, task_description),
    * a GET method "listAll" to list all entries in JSON format,
    * a GET method "deleteEntry" that deletes a specified entry,
    * a GET method "listUsers" that lists all users, and
    * a GET method "listUserEntries" that lists all entries belonging for a given user_name.
    * Add unit tests using JUnit and AsserThat (assertj)

----
####Instructions
1. Run the SpringBootExApplication.class
2. Connect to: http:/localhost:8080/
3. To view Swagger UI visit: http://localhost:8080/swagger-ui.html
3. To add a new entry visit: http://localhost:8080/addEntry/user_name/date/duration/project_name/task_description 
    - user_name: Add a string name
    - date: Add a string date with YYYYMMDD format (eg. 20201117)
    - duration: Add a integer number wich represents days 
    - project_name: Add a string project name
    - task_description: Add a string task description
    - Example: http://localhost:8080/addEntry/konstantinos/20201117/7/springbootexample/exampleproject
4. To list all entities visit: http://localhost:8080/listAll
5. To delete an entry visit: http://localhost:8080/deleteEntry/project_name
    - project_name: Add the project name that you want to delete  
6. To list all users visit: http://localhost:8080/listUsers
7. To list all entries belonging to a user visit: http://localhost:8080/listUserEntries/user_name
   - user_name: Add the user name you want 

