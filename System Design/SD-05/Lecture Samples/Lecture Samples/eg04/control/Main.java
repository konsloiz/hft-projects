package eg04.control;

public class Main {

	public static void main(String[] args) {
		Target t = new Target();
		
		while(true){
			System.err.println("'>' Start/Resume | '!' Stop/Suspend | 't' Change Time | 'x' Exit");
			char c = Input.readChar();
			
			switch(c){
				case '>' : t.start(); break;
				case '!' : t.stop(); break;
				case 't' : System.out.print("Time [ms]: "); t.setSleepTime(Input.readLong()); break;
				case 'x' : System.exit(0);
			}			
		}
	}

}
