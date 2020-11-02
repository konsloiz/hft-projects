package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ObservingComponent implements PropertyChangeListener {

	 @Override 
	 public void propertyChange(PropertyChangeEvent evt) { 
		 System.out.println("received: " ); 
		 System.out.println("\tproperty: "+ evt.getPropertyName()); 
		 System.out.println("\told: "+ evt.getOldValue()); 
		 System.out.println("\tnew: "+ evt.getNewValue()); 
		 System.out.println("\tid: "+ evt.getPropagationId());
	 } 
}
