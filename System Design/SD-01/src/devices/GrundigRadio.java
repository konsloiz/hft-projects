package devices;

import definitions.Radio;
import definitions.Socket;
import definitions.TooLoud;
import definitions.WrongVoltage;

/**
 * @author Konstantinos Loizas
 * @version 1.0
 * @created 20-??t-2020 1:00:42 µµ
 */
public class GrundigRadio implements Radio {

	private double frequency = 0;
	private boolean on = false;
	private int volume = 0;
	public Socket socket = null;
	
	

	public void connect(Socket socket) throws WrongVoltage{
		if (!(220 <= socket.getVoltage() && 230 >= socket.getVoltage())) {
			throw new WrongVoltage(); }
		this.socket = socket;
	}
	
	private boolean connected() {
		return socket !=null;
	}


	public boolean isOff(){
		return !on;
	}

	public boolean isOn(){
		return on;
	}

	public void onOff(){
	 if (connected())
		 on = !on; 
	}

	public void setFrequency(double a){
		this.frequency = a;
	}

	public double getFrequency(){
		return frequency;
	}

	public int getVolume(){
		if(on)
		 {return volume;}
		else
			return 0;
	}
	public void turnDown() {
		if (on && volume > 0)
		{volume --;}
	}

	public void turnUp() throws TooLoud{
		if (on) {
			if (volume >=100) { throw new TooLoud();}
			else volume++;
		}
	}

	@Override
	public String toString() {
		return "GrundigRadio [frequency=" + frequency + ", on=" + on + ", volume=" + volume + ", socket=" + socket
				+ "]";
	}

}