package src.de.stuttgart.hft.sd.app;

import java.time.LocalDate;

public abstract class GrownUp extends Person {

	public GrownUp(String name, String surname, LocalDate birthday){
		super(name, surname, birthday);
	}

}