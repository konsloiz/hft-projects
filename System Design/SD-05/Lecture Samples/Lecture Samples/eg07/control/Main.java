package eg07.control;

public class Main {

	public static void main(String[] args) {
		Controlled co = new Controlled();
		
		while(true){
			System.out.println("'>' Start/Resume | '!' Stop/Suspend | 't' Change Time | 'x' Exit");
			char c = Input.readChar();
			
			switch(c){
				case '>' : co.start(); break;
				case '!' : co.stop(); break;
				case 't' : System.out.print("Time [ms]: "); co.setSleepTime(Input.readLong()); break;
				case 'x' : System.exit(0);
			}			
		}
	}

}
