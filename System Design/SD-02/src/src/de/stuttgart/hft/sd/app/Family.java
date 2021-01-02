package src.de.stuttgart.hft.sd.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Family {

	private LocalDate marriageDate;
	private Man husband;
	private Woman wife;
	private List<Child> children;
	
	
	public Family(Man husband, Woman wife, LocalDate marriageDate) {
		super();
		this.husband = husband;
		this.wife = wife;
		this.marriageDate = marriageDate;
	    this.children = new ArrayList<>();
	}


	public void add(Child child) {
		
		children.add(child);
		
	}


	@Override
	public String toString() {
		return "Family [husband=" + husband + ", wife=" + wife + ", marriageDate=" + marriageDate + ", children="
				+ children + "]";
	}
	
	

	

}