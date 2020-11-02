package server;

import java.rmi.RemoteException;
import common.ResponseService;

public class ResponseServiceImp implements ResponseService {

	@Override
	public String request() throws RemoteException {
		return "Hello World";
	}
}