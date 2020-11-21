package eg09.master_worker;

public class Worker {
	
	private String name;
	private int result;	
	
	public Worker(String name) {
		super();
		this.name = name;
	}

	public int getResult() {
		return result;
	}

	public void doSomething(int n) {
		result = 0;
		while(n > 0) {
			System.out.println(name);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			n--;
			result++;
		}
	}
}
