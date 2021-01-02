package de.alphaquest.SpringBootEx;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	String project_name4 = "Test4";
	String task_description1 = "Example1";
	String task_description2 = "Example2";
	String task_description3 = "Example3";
	String task_description4 = "Example4";
	LocalDate ld1 = LocalDate.of(2020, 11, 18);
	LocalDate ld2 = LocalDate.of(2017, 10, 18);
	LocalDate ld3 = LocalDate.of(2018, 04, 11);
	LocalDate ld4 = LocalDate.of(2016, 11, 17);
	Duration dr1 = Duration.ofDays(7);
	Duration dr2 = Duration.ofDays(5);
	Duration dr3 = Duration.ofDays(4);
	Duration dr4 = Duration.ofDays(9);

	TimeEntry entry1 = new TimeEntry(user_name1, ld1, dr1, project_name1, task_description1);
	TimeEntry entry2 = new TimeEntry(user_name2, ld2, dr2, project_name2, task_description2);
	TimeEntry entry3 = new TimeEntry(user_name3, ld3, dr3, project_name3, task_description3);
	TimeEntry entry4 = new TimeEntry(user_name1, ld4, dr4, project_name4, task_description4);

	ArrayList<TimeEntry> test_entries = new ArrayList<TimeEntry>();

	@Test
	public void testHome() {
		String result = applicationController.home();
		String expected = "Hello world! This is a Spring Boot application made for alphaQuest project";
		assertThat(result.contains(expected));
	}

	@Test
	public void testAddEntry() {
		test_entries.add(entry1);
		TimeEntry add_entry1 = applicationController.addEntry(user_name1, "20201118", 7, project_name1,
				task_description1);
		assertThat(add_entry1.equals(entry1));
	}

	@Test
	public void testListAllIsEmpty() {

		assertThat((applicationController.listAll()).isEmpty());
	}

	@Test
	public void testListAll() {

		test_entries.add(entry1);
		test_entries.add(entry2);
		test_entries.add(entry3);

		applicationController.addEntry(user_name1, "20201118", 7, project_name1, task_description1);
		applicationController.addEntry(user_name2, "20171018", 5, project_name2, task_description2);
		applicationController.addEntry(user_name3, "20180411", 4, project_name3, task_description3);
		String result = applicationController.listAll().toString();

		assertThat(result.equals(test_entries.toString()));

	}

	@Test
	public void testDeleteEntry() {

		test_entries.add(entry1);
		test_entries.add(entry2);

		applicationController.addEntry(user_name1, "20201118", 7, project_name1, task_description1);
		applicationController.addEntry(user_name2, "20171018", 5, project_name2, task_description2);

		test_entries.removeIf(e -> e.getProject_name().equals(project_name1));

		ArrayList<TimeEntry> delete_entry1 = applicationController.deleteEntry(project_name1);

		assertThat(delete_entry1.equals(test_entries));
	}

	@Test
	public void testListUsers() {
		test_entries.add(entry1);
		test_entries.add(entry2);
		test_entries.add(entry3);

		List<String> test_users = test_entries.stream().map(u -> u.getUser_name()).collect(Collectors.toList());

		applicationController.addEntry(user_name1, "20201118", 7, project_name1, task_description1);
		applicationController.addEntry(user_name2, "20171018", 5, project_name2, task_description2);
		applicationController.addEntry(user_name3, "20180411", 4, project_name3, task_description3);

		assertThat((applicationController.listUsers().equals(test_users)));
	}

	@Test
	public void testListUserEntities() {

		test_entries.add(entry1);
		test_entries.add(entry2);
		test_entries.add(entry3);
		test_entries.add(entry4);

		applicationController.addEntry(user_name1, "20201118", 7, project_name1, task_description1);
		applicationController.addEntry(user_name2, "20171018", 5, project_name2, task_description2);
		applicationController.addEntry(user_name3, "20180411", 4, project_name3, task_description3);
		applicationController.addEntry(user_name1, "20161117", 9, project_name4, task_description4);

		List<TimeEntry> test_user_entries = test_entries.stream().filter(u -> user_name1.equals(u.getUser_name()))
				.collect(Collectors.toList());

		assertThat((applicationController.listUserEntries(user_name1).equals(test_user_entries)));
	}
}
