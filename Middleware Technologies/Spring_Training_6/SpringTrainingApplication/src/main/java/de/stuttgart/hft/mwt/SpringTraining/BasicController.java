package de.stuttgart.hft.mwt.SpringTraining;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

	private Logger logger = LoggerFactory.getLogger(BasicController.class);
	ArrayList<String> basicList = new ArrayList<String>();

	@GetMapping("/")
	public String index() {
		return "Hello, World!";
	}

	@GetMapping("/personalizedHello/{name}")
	@ResponseBody
	public String personalizedHello(@PathVariable String name) {
		logger.info("personalizedHello invoked ith parameter {}", name);
		return "Hello, " + name;
	}

	@GetMapping("/strings")
	@ResponseBody
	public String getAllStrings() {
		return basicList.toString();
	}

	@PostMapping("/strings/{newString}")
	@ResponseBody
	public String addNewString(@PathVariable String newString) {
		basicList.add(newString);
		return newString + " added";
	}

	@DeleteMapping("/strings/{stringToDelete}")
	@ResponseBody
	public String deleteString(@PathVariable String stringToDelete) {

		logger.debug("trying to delete {}", stringToDelete);
		logger.info("list prior to deletion: {}", getAllStrings());
		basicList.remove(stringToDelete);
		logger.debug("list after deletion: {}", getAllStrings());
		return stringToDelete + " removed";
	}

}