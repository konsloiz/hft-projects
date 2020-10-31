package devices;

import definitions.TV;
import definitions.TooLoud;
import definitions.WrongVoltage;
import definitions.Socket;

/**
 * @author Konstantinos Loizas
 * @version 1.0
 * @created 20-??t-2020 1:00:43 µµ
 */
@SuppressWarnings("unused")
public class RCATelevision implements TV {

	private boolean on = false;
	private String program = "";
	private int volume = 0;
	public Socket socket = null;

	public void connect(Socket socket) throws WrongVoltage{
		if (!(socket.getVoltage()==120)) {
			throw new WrongVoltage(); }
		this.socket = socket;
		
		}

	private boolean conntected() {
		return socket!=null;
	}

	public void setProgram(String a){
		this.program = a;
	}

	public String getProgram(){
		return program;
	}

	public int getVolume(){
		if (on)
		{return volume;}
		else 
			return 0;
	}

	public boolean isOff(){
		return !on;
	}

	public boolean isOn(){
		return on;
	}

	public void onOff(){
		if (conntected())
			on = !on;
	}
	
	public void turnDown() {
	
	if (on & volume >= 0) {
		volume --;}
	
	}

	public void turnUp () {
		if  ( on && volume < 100) {volume ++;}
	}

	@Override
	public String toString() {
		return "RCATelevision [on=" + on + ", program=" + program + ", volume=" + volume + ", socket=" + socket + "]";
	}

}