package de.hftstuttgart.hellodata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class HelloDataApplication {

@Autowired
CustomerRepository repo;

@PutMapping("/strings/{firstName}/{lastName}")
public String addNewCustomer(@PathVariable String firstName, @PathVariable String lastName){

	Customer newCustomer = new Customer(firstName,lastName);
	repo.save(newCustomer);
	
	
	return  newCustomer + "added";
}

@GetMapping("/getAllCustomers")
public String getAllCustomer(){

	return repo.findAll().toString();
	
}





	public static void main(String[] args) {
		SpringApplication.run(HelloDataApplication.class, args);
	}

}
