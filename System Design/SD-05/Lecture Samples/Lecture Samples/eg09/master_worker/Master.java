package eg09.master_worker;

public class Master {

	public static void main(String[] args) throws InterruptedException {
		Worker w1 = new Worker("Worker 1");
		Worker w2 = new Worker("Worker 2");
		
		Thread t1 = new Thread(() -> w1.doSomething(5));
		Thread t2 = new Thread(() -> w2.doSomething(10));
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		
		System.out.println("Reesult 1: " + w1.getResult());
		System.out.println("Reesult 2: " + w2.getResult());		
	}
}
