package src.de.stuttgart.hft.sd.app;

import java.time.LocalDate;

public class Man extends GrownUp{



	public Man(String name, String surname, LocalDate birthday) {
		super(name, surname, birthday);
	}

	public Family marries(Woman wife, LocalDate marriageDate) {
			
		return wife.marries(this, marriageDate);
	}


}