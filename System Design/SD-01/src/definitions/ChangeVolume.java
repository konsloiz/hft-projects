package definitions;


/**
 * @author Konstantinos Loizas
 * @version 1.0
 * @created 20-?e?-2020 11:35:12 pµ
 */
public interface ChangeVolume {

	public int getVolume();

	public void turnDown();

	/**
	 * 
	 * @exception TooLoud
	 */
	public void turnUp()
	  throws TooLoud;

}