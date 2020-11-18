package de.alphaquest.SpringBootEx;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import de.alphaquest.SpringBootEx.Controller.ApplicationController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringBootExApplicationTests {

	ApplicationController applicationController = new ApplicationController();
	
	@Test
	void contextLoads() {
	}


	@Test
	public void testHome(){
		String result = applicationController.home();
		assertEquals(result,"Hello world! This is a Spring Boot application made for alphaQuest project");
	}

}
