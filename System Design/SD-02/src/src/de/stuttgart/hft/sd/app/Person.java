package src.de.stuttgart.hft.sd.app;

import java.time.LocalDate;


import org.json.JSONObject;

import src.de.stuttgart.hft.sd.util.HoroscopeService;
import src.de.stuttgart.hft.sd.util.ServiceAccess;
import src.de.stuttgart.hft.sd.util.Sign;

public abstract class Person {

	private LocalDate birthday;
	private String firstName;
	private String lastName;
	private Family family;
	
	private Address address;
	private static HoroscopeService horoscopeService;

	
	public Person(String firstName, String lastName, LocalDate birthday) {

		this.birthday = birthday;
		this.firstName = firstName;
		this.lastName = lastName;

	}

	public static void setService(HoroscopeService horoscopeService) {
		
		Person.horoscopeService = horoscopeService;
		
	}
	public String getHoroscope() {

		LocalDate birthday = this.getBirthday();
		Sign sign = Sign.of(birthday);
		String horoscope = horoscopeService.getHoroscope(sign);
		
		return "Horoscope for: " + this.getFirstName() + " " + this.getLastName() + " born on: " + this.getBirthday()
				+ ": " + sign + " " + horoscope;

	}

	public Family getFamily() {
		return family;
	}
	
	protected void add(Child child) {
		
		this.family.add(child);
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + " born " + birthday;
	}

}