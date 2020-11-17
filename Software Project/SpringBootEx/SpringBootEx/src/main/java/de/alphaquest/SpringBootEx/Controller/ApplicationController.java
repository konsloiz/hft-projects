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

import de.alphaquest.SpringBootEx.TimeEntry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	public String addEntry(@PathVariable String user_name, @PathVariable String stringDate,
			@PathVariable Integer intDuration, @PathVariable String project_name,
			@PathVariable String task_description) {

		LocalDate date = TimeEntry.stringToDate(stringDate);
		Duration duration = TimeEntry.intToDuration(intDuration);

		logger.info(
				"addEntry method invoked with paramters user_name = {}, date= {}, duration= {}, project_name= {}, project_description= {}",
				user_name, date, duration, project_name, task_description);
		TimeEntry entry = new TimeEntry(user_name, date, duration, project_name, task_description);
		entries.add(entry);
		return "addEntry method invoked";

	}

	@GetMapping("/listAll")
	public String listAll() {
		return entries.toString();
	}

	@GetMapping("/deleteEntry/{projectToDelete}")
	public String deleteEntry(@PathVariable String projectToDelete) {

		logger.debug("trying to delete Project {}", projectToDelete);
		logger.info("list prior to deletion: {}", listAll());
		entries.removeIf(e -> e.getProjectName().equals(projectToDelete));
		logger.debug("list after deletion: {}", listAll());
		return "Entry with Project Name: " + projectToDelete + ": removed";

	}

	@GetMapping("/listUsers")
	public String listUsers() {

		List<String> users = entries.stream().map(u -> u.getUserName()).collect(Collectors.toList());
		return users.toString();
	}

	@GetMapping("/listUserEntries/{user_name}")
	public String listUserEntries(@PathVariable String user_name) {

		List<TimeEntry> user_entries = entries.stream().filter(u -> user_name.equals(u.getUserName())).collect(Collectors.toList());
		return user_entries.toString();
	}

}
