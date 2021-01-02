package devices;

import definitions.TV;
import definitions.TooLoud;
import definitions.WrongVoltage;
import definitions.Socket;

/**
 * @author Konstantinos Loizas
 * @version 1.0
 * @created 20-?e?-2020 11:35:12 pµ
 */
public class RCATelevision implements TV {

	private boolean on;
	private String program;
	private int volume;
	public Socket tvSocket;

	private static int range = 120;

	public RCATelevision() {

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param a
	 * @return
	 * @exception WrongVoltage
	 */
	public void connect(Socket a) throws WrongVoltage {

		if ((a.getVoltage() != range)) {
			throw new WrongVoltage();
		}

		else {
			this.tvSocket = a;
		}

	}

	public String getProgram() {
		return "";
	}

	public int getVolume() {
		return 0;
	}

	public boolean isOff() {
		if (!isOn()) {
			return true;
		}

		else
			return false;
	}

	public boolean isOn() {
		return false;
	}

	public void onOff() {

	}

	/**
	 * 
	 * @param a
	 */
	public void setProgram(String a) {

	}

	public String toString() {
		return "";
	}

	/**
	 * 
	 * @exception TooLoud
	 */
	public void turnDown() throws TooLoud {

	}

	public void turnUp() {

	}

}