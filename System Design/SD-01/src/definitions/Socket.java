package definitions;


/**
 * @author Konstantinos Loizas
 * @version 1.0
 * @created 20-?e?-2020 11:35:12 pµ
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
		
		this.voltage = voltage;
	}

	public int getVoltage(){
		return voltage;
	}
}