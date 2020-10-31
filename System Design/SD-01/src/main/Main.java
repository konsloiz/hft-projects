package main;

import definitions.Radio;
import definitions.Socket;
import definitions.TooLoud;
import definitions.WrongVoltage;
import devices.GrundigRadio;

public class Main {
	public static void main(String[] args) {
		Radio r = new GrundigRadio();
		boolean ok = false;
		try {
			r.connect(Socket.US);
			System.out.println("Successfully connected to US-Socket");
			ok = true;
		} catch (WrongVoltage e) {
			e.printStackTrace();
		} finally {
			r.onOff();
		}
		if (!ok)
			try {
				r.connect(Socket.EU);
				System.out.println("Successfully connected to EU-Socket");
			} catch (WrongVoltage e) {
				e.printStackTrace();
			} finally {
				r.onOff();
			}
		System.out.println(r);
		try {
			for (int i = 0; i < 105; i++) {
				r.turnUp();
				System.out.println(r);
			}
		} catch (TooLoud e) {
			System.out.println("Too loud!");
			e.printStackTrace();
		}
	}
}