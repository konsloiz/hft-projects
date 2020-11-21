package eg01.runnable;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Runnable target = new Target();			System.err.println("Runnable created");
		Thread t = new Thread(target);			System.err.println("Thread created");
		t.start();								System.err.println("Thread started");
		System.out.println("something else");
		Thread.sleep(3000);						System.err.println("main finished");		
	}

}
