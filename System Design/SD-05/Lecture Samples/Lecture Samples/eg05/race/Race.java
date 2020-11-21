package eg05.race;

public class Race {

	// see: https://en.wikipedia.org/wiki/Race_condition
	
	static int data = 0;
	
	private static void increment() {
		int x = data; 					// read
		x++; 							// increment
		for(int i = 0; i < 3500; i++)   // do some computation here, depending on the  
			x = -x;						// machine the loop has to be longer or shorter
		data = x; 						// write back
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> increment());
		Thread t2 = new Thread(() -> increment());
		t1.start();
		t2.start();
		
		Thread.sleep(100);
		System.out.println(data + (data != 2 ? " ERROR" : ""));
	}
}
