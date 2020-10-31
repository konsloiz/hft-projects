package de.hftstuttgart.st.mwt.springproject;

import java.util.ArrayList;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class SpringProjectApplication {
	
	private Logger logger = LoggerFactory.getLogger(SpringProjectApplication.class);

	ArrayList<String> listOfStrings = new ArrayList<String>();


		@GetMapping ("/")
		public String greeting(){
			return "Hello, ST students!";
		} 

		@RequestMapping ("/test")
		public String sayHelloFromOtherEndpoint(){
			logger.info("sayHelloFromOtherEndpoint invoked!");
			return "Hello from the Request Endpoint";
		}

		@GetMapping ("/personalizedHello/{name}")
		@ResponseBody
		public String personalizedHello(@PathVariable String name){
			logger.info("personalizedHello invoked ith parameter {}", name);
			return "Hello, " + name;
		} 

		@GetMapping ("/strings")
		public String getAllStrings() {
			return listOfStrings.toString();
		}

		@PutMapping("/strings/{newString}")
		public String addNewString (@PathVariable String newString){
			listOfStrings.add(newString);
			return newString+" added";
		}

		@DeleteMapping("/strings/{stringToDelete}")
		public String deleteString (@PathVariable String stringToDelete){
			
			logger.debug("trying to delete {}", stringToDelete);
			logger.info("list prior to deletion: {}", getAllStrings());
			listOfStrings.remove(stringToDelete);
			logger.debug("list after deletion: {}", getAllStrings());
			return stringToDelete+" removed";
		}

	
	public static void main(String[] args) {
		SpringApplication.run(SpringProjectApplication.class, args);
	}

}
