package definitions;


/**
 * @author Konstantinos Loizas
 * @version 1.0
 * @created 20-?e?-2020 11:35:12 pµ
 */
public interface Device {

	/**
	 * 
	 * @param a
	 * @exception WrongVoltage
	 */
	public void connect(Socket a)
	  throws WrongVoltage;

	public boolean isOff();

	public boolean isOn();

	public void onOff();

}