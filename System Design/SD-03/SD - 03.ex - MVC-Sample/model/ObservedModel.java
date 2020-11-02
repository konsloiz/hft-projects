package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ObservedModel {
	
	 private PropertyChangeSupport support; 
	  
	 public ObservedModel() { 
		 support = new PropertyChangeSupport(this); 
	 } 
	  
	 public void addObserver(PropertyChangeListener pcl) { 
		 support.addPropertyChangeListener(pcl); 
	 } 
	  
	 public void removeObserver(PropertyChangeListener pcl) { 
		 support.removePropertyChangeListener(pcl); 
	 } 
	 
	 private String data; 
	 
	 public void setData(String value) {
		 String old = data;
		 this.data = value; 
		 support.firePropertyChange("data", old, this.data);
	 }

}
