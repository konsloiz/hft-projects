package client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.Message;
import common.ResponseService;

import static common.Constants.*;

public class BasicClient2 {

	private ResponseService service = null;

	public void connect() {
		try {
			Registry registry = LocateRegistry.getRegistry(SERVER_IP);
			service = (ResponseService) registry.lookup(SERVICE_NAME);
			System.out.println("Client 2 connected");
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public String getRespone() {
		try {
			return service.request(new Message ("text", "Hello World too"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws RemoteException {
		BasicClient2 client = new BasicClient2();
		client.connect();
		String response = client.getRespone();
		System.out.println("received '" + response + "'");
	}
}
