package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import common.ResponseService;

import static common.Constants.*;

public class Server {

	public static void startUp() {
		try {
			// Initialize Service
			ResponseServiceImp service = new ResponseServiceImp();
			ResponseService serviceStub = (ResponseService) UnicastRemoteObject.exportObject(service, 0);

			// Publish Service
			System.setProperty("java.rmi.server.hostname", SERVER_IP);
			Registry registry = LocateRegistry.createRegistry(RMI_PORT);
			registry.rebind(SERVICE_NAME, serviceStub);

			System.out.println("Server running ...");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		startUp();
	}
}