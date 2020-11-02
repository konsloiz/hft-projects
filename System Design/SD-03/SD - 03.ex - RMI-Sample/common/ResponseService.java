package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ResponseService extends Remote {
	
	String request() throws RemoteException;
	
}
