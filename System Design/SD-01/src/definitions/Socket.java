package definitions;


/**
 * @author Konstantinos Loizas
 * @version 1.0
 * @created 20-??t-2020 1:00:43 µµ
 */
public enum Socket {
	EU(230),
	US(120);

	private int voltage;

	/**
	 * 
	 * @param voltage
	 */
	private Socket(int voltage){

		this.voltage=voltage;
	}

	public int getVoltage(){
		return voltage;
	}
}