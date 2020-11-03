package server;

import java.rmi.RemoteException;

import common.Message;
import common.ResponseService;

public class ResponseServiceImp implements ResponseService {

	@Override
	public String request (Message message) throws RemoteException {
		
		if (message == null) {
			return "No Message";
		}
		return "Processed: " + message.getMessageText();
	}
}