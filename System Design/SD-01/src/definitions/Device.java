package definitions;


/**
 * @author Konstantinos Loizas
 * @version 1.0
 * @created 20-??t-2020 1:00:42 µµ
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