package de.alphaquest.SpringBootEx;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeEntry {
    private String user_name;
    private LocalDate date;
    private Duration duration;
    private String project_name;
    private String task_description;

    public TimeEntry(String user_name, LocalDate date, Duration duration, String project_name, String task_description) {
        this.user_name = user_name;
        this.date = date;
        this.duration = duration;
        this.project_name = project_name;
        this.task_description = task_description;
    }

    public static LocalDate stringToDate(String StringDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(StringDate, formatter);

        return date;
    }

    public static Duration intToDuration(int intDuration){
        Duration duration = Duration.ofDays(intDuration);
        return duration;
    }

    public String getProjectName() {
        return project_name;
    }

    public String getUserName() {
        return user_name;
    }

    @Override
    public String toString() {
        return "TimeEntry [date=" + date + ", duration=" + duration + ", project_name=" + project_name
                + ", task_description=" + task_description + ", user_name=" + user_name + "]";
    }

   

    

}
