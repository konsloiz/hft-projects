package definitions;


/**
 * @author Konstantinos Loizas
 * @version 1.0
 * @created 20-??t-2020 1:00:42 ��
 */
public interface ChangeVolume {

	public int getVolume();

	/**
	 * 
	 * @exception TooLoud
	 */
	public void turnDown()
	  throws TooLoud;

	public void turnUp();

}