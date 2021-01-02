package src.de.stuttgart.hft.sd.app;

import java.time.LocalDate;

public class Woman extends GrownUp {

	private String birthName;

	public Woman(String name, String surname, LocalDate birthday) {

		super(name, surname, birthday);
		this.birthName = surname;

	}

	public Family marries(Man husband, LocalDate marriageDate) {

		Family family = new Family(husband, this, marriageDate);
		this.setLastName(husband.getLastName());
		this.setFamily(family);
		husband.setFamily(family);

		return family;

	}

	public Child newChild(String firstName, LocalDate birthday) {

		Child child = new Child(firstName, this.getLastName(), birthday);
		this.add(child);
		child.setFamily(this.getFamily());

		return child;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getFirstName() +" "+ this.getLastName() + " born as " + this.getFirstName() + " "+ this.birthName +" on "+ this.getBirthday();
	}

}