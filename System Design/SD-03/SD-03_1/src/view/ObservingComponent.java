package view;

import java.beans.*;

public class ObservingComponent implements PropertyChangeListener {
	
	String id;
	
	public ObservingComponent(String id) {
		super();
		this.id = id;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		System.out.println(id +" received: ");
		System.out.println("\tproperty: " + evt.getPropertyName());
		System.out.println("\told: " + evt.getOldValue());
		System.out.println("\tnew: " + evt.getNewValue());
	}
}