package de.alphaquest.SpringBootEx;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import de.alphaquest.SpringBootEx.Controller.ApplicationController;
import de.alphaquest.SpringBootEx.Domain.TimeEntry;

@SpringBootTest
class SpringBootExApplicationTests {

	ApplicationController applicationController = new ApplicationController();

			String user_name1 = "Konstantinos";
		String user_name2 = "Loizas";
		String user_name3 = "Kostas";
		String project_name1 = "Test1";
		String project_name2 = "Test2";
		String project_name3 = "Test3";
		String task_description1 = "Example1";
		String task_description2 = "Example2";
		String task_description3 = "Example3";
		LocalDate ld1 = LocalDate.of( 2020 , 11 , 18 );
		LocalDate ld2 = LocalDate.of( 2017 , 10 , 18 );
		LocalDate ld3 = LocalDate.of( 2018 , 04 , 11 );
		Duration dr1 = Duration.ofDays(7);
		Duration dr2 = Duration.ofDays(5);
		Duration dr3 = Duration.ofDays(4);
		
		TimeEntry entry1 = new TimeEntry(user_name1,ld1,dr1,project_name1,task_description1);
		TimeEntry entry2 = new TimeEntry(user_name2,ld2,dr2,project_name2,task_description2);
		TimeEntry entry3 = new TimeEntry(user_name3,ld3,dr3,project_name3,task_description3);

		ArrayList<TimeEntry> entries = new ArrayList<TimeEntry>();
	

	@Test
	void contextLoads() {
	}


	@Test
	public void testHome(){
		String result = applicationController.home();
		assertEquals(result,"Hello world! This is a Spring Boot application made for alphaQuest project");
	}

	@Test
	public void testAddEntry(){
		String result = applicationController.addEntry(user_name1, "20201118", 7, project_name1, task_description1);
		assertEquals(result, entry1.toString()+ " added successfully");
	}

}
