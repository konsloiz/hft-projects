package de.alphaquest.SpringBootEx.Domain;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimeEntry {
    private String user_name;
    private LocalDate date;
    private Duration duration;
    private String project_name;
    private String task_description;

  
    public static LocalDate stringToDate(String StringDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(StringDate, formatter);

        return date;
    }

    public static Duration intToDuration(int intDuration){
        Duration duration = Duration.ofDays(intDuration);
        return duration;
    }

}
