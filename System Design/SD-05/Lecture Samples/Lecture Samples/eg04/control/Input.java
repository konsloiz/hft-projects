package eg04.control;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Random;

//Sie erhalten eine HTML-Dokumentation diser Klasse, 
//wenn Sie den Befehl "javadoc Input.java" in dem Verzeichnis 
//dieser Klasse ausführen. Öffnen Sie die damit erzeugte 
//Datei "index.html" in Ihrem Browser

/** Klassenmethoden zur einfachen Eingabe und Erzeugung von Daten in java.
 * Die Daten können entweder über die Tastatur eingegeben oder
 * zufällig generiert werden. <p>
 * Bisher werden die folgenden, primitiven Datentypen unterstützt:
 * String, int, float, long, double, char, boolean;<p>
  * <p>
 * @author Marcus Deininger
 * @version 3.0 / 11.07.2015
 */

public class Input {
	
	private static Random generator = new Random();
	
	private static class StringInputStream extends ByteArrayInputStream{
		
		private InputStream in = null;
		
		private byte[][] bytes;
		private String[] strings;
		private int current;
		private boolean echo;
		
		@SuppressWarnings("unused")
		public StringInputStream(String ... strings) {
			this(false, strings, encode(strings));
		}

		public StringInputStream(boolean echo, String ... strings) {
			this(echo, strings, encode(strings));
		}

		private StringInputStream(boolean echo, String[] strings, byte[][] bytes) {
			super(bytes[0]);
			this.strings = strings;
			this.bytes = bytes;
			current = 1;
			this.echo = echo;
			
			this.in = System.in;
			System.setIn(this);
		}

		private static byte[][] encode(String[] strings) {
			Charset charset = Charset.defaultCharset();		
			byte[][] bytes = new byte[strings.length][];
			for (int i = 0; i < strings.length; i++){
					ByteBuffer buffer = charset.encode(strings[i]);
					bytes[i] = buffer.array();
			}
			return bytes;
		}

		public synchronized int read(byte[] bytes, int offset, int length) {
			int read = super.read(bytes, offset, length);
			if(read == -1){
				if(echo)
					System.out.println(strings[current - 1]);
				if(current < this.bytes.length){
					this.buf = this.bytes[current];
					current++;
					this.count = this.buf.length;
					this.mark = 0;
					this.pos = 0;
				}else
					System.setIn(this.in); // Restore original stream
			}
			return read;
		}
	}
	
	private Input(){
		//keine explizite Erzeugung der Klasse
	}

	/**
	 * Leitet die Eingabe für die übergebenen Parameter um. Nachdem 
	 * alle Strings konsumiert wurden, wird die Standardeingabe
	 * wieder hergestellt.
	 * @param strings Strings, die von der Eingabe konsumiert werden.
	 */
	public static void redirect(String ... strings){
		redirect(true, strings);
	}
	
	/**
	 * Leitet die Eingabe für die übergebenen Parameter um. Nachdem 
	 * alle Strings konsumiert wurden, wird die Standardeingabe
	 * wieder hergestellt.
	 * @param echo true, wenn die Eingabe am Bildschirm wiederholt werden soll.
	 * @param strings Strings, die von der Eingabe konsumiert werden.
	 */
	public static void redirect(boolean echo, String ... strings){
		if(strings.length == 0)
			return;
		
		InputStream stream = new StringInputStream(echo, strings);
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Liest eine Zeile als String von der Tastatur ein;
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @param prompt Stellt einen Text vor die Eingabe.
	 * @return Die eingegebene Zeile
	 */
	public static String readString(String prompt){
		System.out.print(prompt);
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader buffer = new BufferedReader(in);
		String input = "";
		try {
			input = buffer.readLine();
		} catch ( IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	/**
	 * Liest eine Zeile als String von der Tastatur ein;
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @return Die eingegebene Zeile
	 */
	public static String readString(){
		return readString("");
	}
	
	/**
	 * Liest eine Zeile von der Tastatur ein und zerlegt die Eingabe 
	 * an den Leerräumen in Teilstrings. Die Eingabe wird durch &lt;return&gt; beendet.
	 * @param prompt Stellt einen Text vor die Eingabe.
	 * @return Ein Feld mit den eigegebenen Strings
	 */
	public static String[] readStringVector(String prompt){
		String input = readString(prompt);
		String[] tokens = input.split(" +");
		return tokens;
	}
	
	
	/**
	 * Liest eine Zeile von der Tastatur ein und zerlegt die Eingabe 
	 * an den Leerräumen in Teilstrings. Die Eingabe wird durch &lt;return&gt; beendet.
	 * @return Ein Feld mit den eigegebenen Strings
	 */
	public static String[] readStringVector(){
		return readStringVector("");
	}
	
	
	/**
	 * Wandelt einen <tt>String</tt> in einen <tt>int</tt>-Wert um.
	 */
	private static int toInt(String input){
		int value = Integer.parseInt(input);
		return value;		
	}

	/**
	 * Liest einen <tt>int</tt>-Wert von der Tastatur ein.
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @param prompt Stellt einen Text vor die Eingabe.
	 * @return Der eingelesene Wert
	 */
	public static int readInt(String prompt){
		String input = readString(prompt);
		int value = toInt(input);
		return value;
	}

	/**
	 * Liest einen <tt>int</tt>-Wert von der Tastatur ein.
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @return Der eingelesene Wert
	 */
	public static int readInt(){
		return readInt("");
	}

	/**
	 * Liest einen <tt>int</tt>-Vektor von der Tastatur ein. 
	 * Die einzelnen Werte werden durch Leerzeichen getrennt. 
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @param prompt Stellt einen Text vor die Eingabe.
	 * @return Der eingelesene Vektor
	 */
	public static int[] readIntVector(String prompt){
		String[] tokens = readStringVector(prompt);
		int[] vector = new int[tokens.length];
		for(int i = 0; i < vector.length;i++)
			vector[i] = toInt(tokens[i]);
		return vector;
	}

	/**
	 * Liest einen <tt>int</tt>-Vektor von der Tastatur ein. 
	 * Die einzelnen Werte werden durch Leerzeichen getrennt. 
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @return Der eingelesene Vektor
	 */
	public static int[] readIntVector(){
		return readIntVector("");
	}

	/**
	 * Erzeugt einen zufälligen <tt>int</tt>-Wert.
	 * @param von untere Grenze
	 * @param bis obere Grenze
	 * @return Der erzeugte Wert
	 */
	public static int randomInt(int von, int bis){
		int bereich = bis - von + 1;			// normierter Bereich
		int zahl = generator.nextInt(bereich);	// Zufallszahl zwischen 0 und bereich - 1
		int ergebnis = bis - zahl;				// zurück transformieren
		return ergebnis;
	}

	/**
	 * Erzeugt einen zufällig besetzen <tt>int</tt>-Vektor mit der vorgegebenen Länge.
	 * @param laenge die Länge des zu erzeugenden Vektors
	 * @param von untere Grenze der Zufallszahlen
	 * @param bis obere Grenze der Zufallszahlen
	 * @return Der erzeugte Vektor
	 */
	public static int[] randomIntVector(int laenge, int von, int bis){
		int[] result = new int[laenge];
		for (int i = 0; i < laenge; i++)
			result[i] = randomInt(von, bis);
		return result;
	}

	/**
	 * Wandelt einen <tt>String</tt> in einen <tt>Long</tt>-Wert um.
	 */
	private static long toLong(String input){
		long value = Long.parseLong(input);
		return value;		
	}

	/**
	 * Liest einen <tt>Long</tt>-Wert von der Tastatur ein.
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @param prompt Stellt einen Text vor die Eingabe.
	 * @return Der eingelesene Wert
	 */
	public static long readLong(String prompt){
		String input = readString(prompt);
		long value = toLong(input);
		return value;
	}

	/**
	 * Liest einen <tt>Long</tt>-Wert von der Tastatur ein.
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @return Der eingelesene Wert
	 */
	public static long readLong(){
		return readLong("");
	}

	/**
	 * Liest einen <tt>long</tt>-Vektor von der Tastatur ein. 
	 * Die einzelnen Werte werden durch Leerzeichen getrennt. 
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @param prompt Stellt einen Text vor die Eingabe.
	 * @return Der eingelesene Vektor
	 */
	public static long[] readLongVector(String prompt){
		String[] tokens = readStringVector(prompt);
		long[] vector = new long[tokens.length];
		for(int i = 0; i < vector.length;i++)
			vector[i] = toLong(tokens[i]);
		return vector;
	}

	/**
	 * Liest einen <tt>long</tt>-Vektor von der Tastatur ein. 
	 * Die einzelnen Werte werden durch Leerzeichen getrennt. 
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @return Der eingelesene Vektor
	 */
	public static long[] readLongVector(){
		return readLongVector("");
	}

	/**
	 * Erzeugt einen zufälligen <tt>long</tt>-Wert.
	 * @param von untere Grenze
	 * @param bis obere Grenze
	 * @return Der erzeugte Wert
	 */
	public static long randomLong(long von, long bis){
		long bereich = bis - von + 1;			// normierter Bereich
		long zahl = (long)(generator.nextDouble() * bereich);
												// Zufallszahl zwischen 0 und bereich - 1
		long ergebnis = bis - zahl;				// zurück transformieren
		return ergebnis;
	}

	/**
	 * Erzeugt einen zufällig besetzen <tt>long</tt>-Vektor mit der vorgegebenen Länge.
	 * @param laenge die Länge des zu erzeugenden Vektors
	 * @param von untere Grenze der Zufallszahlen
	 * @param bis obere Grenze der Zufallszahlen
	 * @return Der erzeugte Vektor
	 */
	public static long[] randomLongVector(int laenge, long von, long bis){
		long[] result = new long[laenge];
		for (int i = 0; i < laenge; i++)
			result[i] = randomLong(von, bis);
		return result;
	}
	
	/**
	 * Wandelt einen <tt>String</tt> in einen <tt>float</tt>-Wert um.
	 */
	private static float toFloat(String input){
		float value = Float.parseFloat(input);
		return value;
	}

	/**
	 * Liest einen <tt>float</tt>-Wert von der Tastatur ein.
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @param prompt Stellt einen Text vor die Eingabe.
	 * @return Der eingelesene Wert
	 */
	public static float readFloat(String prompt){
		String input = readString(prompt);
		float value = toFloat(input);
		return value;
	}

	/**
	 * Liest einen <tt>float</tt>-Wert von der Tastatur ein.
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @return Der eingelesene Wert
	 */
	public static float readFloat(){
		return readFloat("");
	}

	/**
	 * Liest einen <tt>float</tt>-Vektor von der Tastatur ein. 
	 * Die einzelnen Werte werden durch Leerzeichen getrennt. 
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @param prompt Stellt einen Text vor die Eingabe.
	 * @return Der eingelesene Vektor
	 */
	public static float[] readFloatVector(String prompt){
		String[] tokens = readStringVector(prompt);
		float[] vector = new float[tokens.length];
		for(int i = 0; i < vector.length;i++)
			vector[i] = toFloat(tokens[i]);
		return vector;
	}

	/**
	 * Liest einen <tt>float</tt>-Vektor von der Tastatur ein. 
	 * Die einzelnen Werte werden durch Leerzeichen getrennt. 
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @return Der eingelesene Vektor
	 */
	public static float[] readFloatVector(){
		return readFloatVector("");
	}

	/**
	 * Erzeugt einen zufälligen <tt>float</tt>-Wert.
	 * @param von untere Grenze
	 * @param bis obere Grenze
	 * @return Der erzeugte Wert
	 */
	public static float randomFloat(float von, float bis){
		float zahl = generator.nextFloat(); 		// Zufallszahl zwischen 0.0 und 1.0
		float ergebnis = zahl * (bis - von) + von;	// auf Bereich transformieren
		return ergebnis;
	}

	/**
	 * Erzeugt einen zufällig besetzen <tt>float</tt>-Vektor mit der vorgegebenen Länge.
	 * @param laenge die Länge des zu erzeugenden Vektors
	 * @param von untere Grenze der Zufallszahlen
	 * @param bis obere Grenze der Zufallszahlen
	 * @return Der erzeugte Vektor
	 */
	public static float[] randomFloatVector(int laenge, float von, float bis){
		float[] result = new float[laenge];
		for (int i = 0; i < laenge; i++)
			result[i] = randomFloat(von, bis);
		return result;
	}

	/**
	 * Wandelt einen <tt>String</tt> in einen <tt>double</tt>-Wert um.
	 */
	private static double toDouble(String input){
		double value = Double.parseDouble(input);
		return value;
	}

	/**
	 * Liest einen <tt>double</tt>-Wert von der Tastatur ein.
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @param prompt Stellt einen Text vor die Eingabe.
	 * @return Der eingelesene Wert
	 */
	public static double readDouble(String prompt){
		String input = readString(prompt);
		double value = toDouble(input);
		return value;
	}

	/**
	 * Liest einen <tt>double</tt>-Wert von der Tastatur ein.
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @return Der eingelesene Wert
	 */
	public static double readDouble(){
		return readDouble("");
	}

	/**
	 * Liest einen <tt>double</tt>-Vektor von der Tastatur ein. 
	 * Die einzelnen Werte werden durch Leerzeichen getrennt. 
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @param prompt Stellt einen Text vor die Eingabe.
	 * @return Der eingelesene Vektor
	 */
	public static double[] readDoubleVector(String prompt){
		String[] tokens = readStringVector(prompt);
		double[] vector = new double[tokens.length];
		for(int i = 0; i < vector.length;i++)
			vector[i] = toDouble(tokens[i]);
		return vector;
	}

	/**
	 * Liest einen <tt>double</tt>-Vektor von der Tastatur ein. 
	 * Die einzelnen Werte werden durch Leerzeichen getrennt. 
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @return Der eingelesene Vektor
	 */
	public static double[] readDoubleVector(){
		return readDoubleVector("");
	}

	/**
	 * Erzeugt einen zufälligen <tt>double</tt>-Wert.
	 * @param von untere Grenze
	 * @param bis obere Grenze
	 * @return Der erzeugte Wert
	 */
	public static double randomDouble(double von, double bis){
		double zahl = generator.nextDouble(); 		// Zufallszahl zwischen 0.0 und 1.0
		double ergebnis = zahl * (bis - von) + von;	// auf Bereich transformieren
		return ergebnis;
	}

	/**
	 * Erzeugt einen zufällig besetzen <tt>double</tt>-Vektor mit der vorgegebenen Länge.
	 * @param laenge die Länge des zu erzeugenden Vektors
	 * @param von untere Grenze der Zufallszahlen
	 * @param bis obere Grenze der Zufallszahlen
	 * @return Der erzeugte Vektor
	 */
	public static double[] randomDoubleVector(int laenge, double von, double bis){
		double[] result = new double[laenge];
		for (int i = 0; i < laenge; i++)
			result[i] = randomDouble(von, bis);
		return result;
	}


	
	/**
	 * Wandelt einen <tt>String</tt> in einen <tt>char</tt>-Wert um.
	 * Dabei wird das erste Zeichen des Strings genommen. Ist der
	 * String länger als ein Zeichen, wird eine Exception ausgelöst.
	 */
	private static char toChar(String input){
		if (input.length() != 1) throw new RuntimeException();
		char value = input.charAt(0);
		return value;
	}

	/**
	 * Liest einen <tt>char</tt>-Wert von der Tastatur ein.
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @param prompt Stellt einen Text vor die Eingabe.
	 * @return Der eingelesene Wert
	 */
	public static char readChar(String prompt){
		String input = readString(prompt);
		char value = toChar(input);
		return value;
	}
	
	/**
	 * Liest einen <tt>char</tt>-Wert von der Tastatur ein.
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @return Der eingelesene Wert
	 */
	public static char readChar(){
		return readChar("");
	}
	
	/**
	 * Liest einen <tt>char</tt>-Vektor von der Tastatur ein. 
	 * Die einzelnen Werte werden durch Leerzeichen getrennt. 
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @param prompt Stellt einen Text vor die Eingabe.
	 * @return Der eingelesene Vektor
	 */
	public static char[] readCharVector(String prompt){
		String[] tokens = readStringVector(prompt);
		char[] vector = new char[tokens.length];
		for(int i = 0; i < vector.length;i++)
			vector[i] = toChar(tokens[i]);
		return vector;
	}

	/**
	 * Liest einen <tt>char</tt>-Vektor von der Tastatur ein. 
	 * Die einzelnen Werte werden durch Leerzeichen getrennt. 
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @return Der eingelesene Vektor
	 */
	public static char[] readCharVector(){
		return readCharVector("");
	}

	/**
	 * Erzeugt einen zufälligen <tt>char</tt>-Wert zwischen ASCII(33) und ASCII(127).
	 * Das sind die sichtbaren ASCII-Zeichen ohne Zeilenumbruch.
	 * @return Der erzeugte Wert
	 */
	public static char randomChar(){
		//Sichtbare Zeichen,ohne Zeilenumbruch
		return (char)randomInt(33, 126);
	}

	/**
	 * Erzeugt einen zufällig besetzen <tt>char</tt>-Vektor mit der vorgegebenen Länge.
	 * Die Werte liegen zwischen ASCII(33) und ASCII(127).
	 * Das sind die sichtbaren ASCII-Zeichen ohne Zeilenumbruch.
	 * @param laenge die Länge des zu erzeugenden Vektors
	 * @return Der erzeugte Vektor
	 */
	public static char[] randomCharVector(int laenge){
		char[] result = new char[laenge];
		for (int i = 0; i < laenge; i++)
			result[i] = randomChar();
		return result;
	}
	

	/**
	 * Wandelt einen <tt>String</tt> in einen <tt>boolean</tt>-Wert um.
	 * Mögliche Eingaben sind <tt>t</tt>, <tt>true</tt>, <tt>f</tt>, 
	 * oder <tt>false</tt> (unabhängig von Groß- und Kleinschreibung).
	 */
	private static boolean toBoolean(String input){		
		if(input.equalsIgnoreCase("t")) return true;
		if(input.equalsIgnoreCase("true")) return true;
		if(input.equalsIgnoreCase("f")) return false;
		if(input.equalsIgnoreCase("false")) return false;

		throw new NumberFormatException();
	}

	/**
	 * Liest einen <tt>boolean</tt>-Wert von der Tastatur ein.
	 * Boolsche Werte können durch <tt>t</tt>, <tt>true</tt>, <tt>f</tt>, 
	 * oder <tt>false</tt> (unabhängig
	 * von Groß- und Kleinschreibung) eingegeben werden.
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @return Der eingelesene Wert
	 */
	public static boolean readBoolean(String prompt){
		String input = readString(prompt);
		boolean value = toBoolean(input);
		return value;
	}
	
	/**
	 * Liest einen <tt>boolean</tt>-Wert von der Tastatur ein.
	 * Boolsche Werte können durch <tt>t</tt>, <tt>true</tt>, <tt>f</tt>, 
	 * oder <tt>false</tt> (unabhängig
	 * von Groß- und Kleinschreibung) eingegeben werden.
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @param prompt Stellt einen Text vor die Eingabe.
	 * @return Der eingelesene Wert
	 */
	public static boolean readBoolean(){
		return readBoolean("");
	}
	
	/**
	 * Liest einen <tt>boolean</tt>-Vektor von der Tastatur ein. 
	 * Boolsche Werte können durch <tt>t</tt>, <tt>true</tt>, <tt>f</tt>, 
	 * oder <tt>false</tt> (unabhängig
	 * von Groß- und Kleinschreibung) eingegeben werden.
	 * Die einzelnen Werte werden durch Leerzeichen getrennt. 
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @param prompt Stellt einen Text vor die Eingabe.
	 * @return Der eingelesene Vektor
	 */
	public static boolean[] readBooleanVector(String prompt){
		String[] tokens = readStringVector(prompt);
		boolean[] vector = new boolean[tokens.length];
		for(int i = 0; i < vector.length;i++)
			vector[i] = toBoolean(tokens[i]);
		return vector;
	}

	
	/**
	 * Liest einen <tt>boolean</tt>-Vektor von der Tastatur ein. 
	 * Boolsche Werte können durch <tt>t</tt>, <tt>true</tt>, <tt>f</tt>, 
	 * oder <tt>false</tt> (unabhängig
	 * von Groß- und Kleinschreibung) eingegeben werden.
	 * Die einzelnen Werte werden durch Leerzeichen getrennt. 
	 * Die Eingabe wird durch &lt;return&gt; beendet.
	 * @return Der eingelesene Vektor
	 */
	public static boolean[] readBooleanVector(){
		return readBooleanVector("");
	}

	
	/**
	 * Erzeugt einen zufälligen <tt>boolean</tt>-Wert.
	 * @return Der erzeugte Wert
	 */
	public static boolean randomBoolean(){
		return randomInt(0, 1) == 1;
	}

	/**
	 * Erzeugt einen zufällig besetzten <tt>booelan</tt>-Vektor mit der vorgegebenen Länge.
	 * @param laenge die Länge des zu erzeugenden Vektors
	 * @return Der erzeugte Vektor
	 */
	public static boolean[] randomBooleanVector(int laenge){
		boolean[] result = new boolean[laenge];
		for (int i = 0; i < laenge; i++)
			result[i] = randomBoolean();
		return result;
	}
	

	

}

