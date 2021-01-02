package src.de.stuttgart.hft.sd.main;

import java.time.LocalDate;
import java.time.Month;

import src.de.stuttgart.hft.sd.app.Child;
import src.de.stuttgart.hft.sd.app.Family;
import src.de.stuttgart.hft.sd.app.Man;
import src.de.stuttgart.hft.sd.app.Person;
import src.de.stuttgart.hft.sd.app.Woman;
import src.de.stuttgart.hft.sd.util.GaneshasService;

public class FamilyOffice {

	public static void main(String[] args) {
		
		Person.setService(new GaneshasService());
		
		Man john = new Man("John", "Doe", LocalDate.of(1990, Month.OCTOBER, 9));
	    Woman jane = new Woman("Jane", "Miles", LocalDate.of(1991, Month.MARCH, 15));
		
	    Family family = john.marries(jane, LocalDate.of(2015, Month.AUGUST, 1));

	    Child jack = jane.newChild("Jack", LocalDate.of(2016, Month.DECEMBER, 24));
		Child jill = jane.newChild("Jill", LocalDate.of(2018, Month.JULY, 15));
		
		System.out.println(john);
		System.out.println(jane);
		System.out.println(jack);
		System.out.println(family);
		
		System.out.println(john.getHoroscope());
		System.out.println(jane.getHoroscope());
		System.out.println(jack.getHoroscope());
		System.out.println(jill.getHoroscope());
	

	}

}
