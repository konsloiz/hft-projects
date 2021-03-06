package model;

import java.beans.*;

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
		support.firePropertyChange("data", this.data, value);
		this.data = value;
	}
}
