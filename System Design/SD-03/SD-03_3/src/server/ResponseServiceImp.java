package server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

import common.Message;
import common.ResponseService;

public class ResponseServiceImp implements ResponseService {
	
	private PropertyChangeSupport support;

	public ResponseServiceImp() {
		support = new PropertyChangeSupport(this);
	}

	public void addObserver(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	public void removeObserver(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}
	
	
	@Override
	public String request(Message message) throws RemoteException {

		if (message == null) {
			return "No Message";
		}
		
		support.firePropertyChange("message", null, message.getMessageText());
		return message.getMessageText();
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}