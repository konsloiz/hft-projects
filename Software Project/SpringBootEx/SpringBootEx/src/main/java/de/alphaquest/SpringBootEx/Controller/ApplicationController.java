package de.alphaquest.SpringBootEx.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.alphaquest.SpringBootEx.Domain.TimeEntry;
import io.swagger.annotations.Api;

@Api(value = "Swagger Controller", description = "Demo API made for alphaQuest")
@RestController
public class ApplicationController {

	ArrayList<TimeEntry> entries = new ArrayList<TimeEntry>();
	private Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@RequestMapping("/")
	public String home() {
		return "Hello world! This is a Spring Boot application made for alphaQuest project";

	}

	@GetMapping("/addEntry/{user_name}/{stringDate}/{intDuration}/{project_name}/{task_description}")
	public TimeEntry addEntry(@PathVariable String user_name, @PathVariable String stringDate,
			@PathVariable Integer intDuration, @PathVariable String project_name,
			@PathVariable String task_description) {

		LocalDate date = TimeEntry.stringToDate(stringDate);
		Duration duration = TimeEntry.intToDuration(intDuration);

		logger.info(
				"addEntry method invoked with paramters user_name = {}, date= {}, duration= {}, project_name= {}, project_description= {}",
				user_name, date, duration, project_name, task_description);
		TimeEntry entry = new TimeEntry(user_name, date, duration, project_name, task_description);
		entries.add(entry);
		return entry;

	}

	@GetMapping("/listAll")
	public ArrayList<TimeEntry> listAll() {
		return entries;
	}

	@GetMapping("/deleteEntry/{projectToDelete}")
	public ArrayList<TimeEntry> deleteEntry(@PathVariable String projectToDelete) {

		logger.debug("trying to delete Project {}", projectToDelete);
		logger.info("list prior to deletion: {}", listAll());
		entries.removeIf(e -> e.getProject_name().equals(projectToDelete));
		logger.debug("list after deletion: {}", listAll());
		return entries;

	}

	@GetMapping("/listUsers")
	public List<String> listUsers() {

		List<String> users = entries.stream().map(u -> u.getUser_name()).collect(Collectors.toList());
		return users;
	}

	@GetMapping("/listUserEntries/{user_name}")
	public List<TimeEntry> listUserEntries(@PathVariable String user_name) {

		List<TimeEntry> user_entries = entries.stream().filter(u -> user_name.equals(u.getUser_name()))
				.collect(Collectors.toList());
		return user_entries;
	}

}
