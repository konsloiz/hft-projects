package eg07.control;
public class Controlled {

	private Thread thread = new Thread(() -> printDates());

	private boolean suspended = false;
	private long sleepTime = 1000;


	public void printDates() {
		while (true) {
			try {
				synchronized (this) {
					while (suspended)
						Controlled.this.wait();
				}
				Thread.sleep(sleepTime);
			} catch (InterruptedException ignored) {}
			doSomething();
		}
	}

	public void setSleepTime(long sleepTime) {
		thread.interrupt();
		this.sleepTime = sleepTime;
	}

	public synchronized void start() {
		if (!thread.isAlive())
			thread.start();
		if (suspended) {
			suspended = false;
			this.notify();
		}
	}

	public synchronized void stop() {
		if (!suspended)
			suspended = true;
	}

	public boolean isRunning() {
		return thread.isAlive() & !suspended;
	}

	public void doSomething() {
		System.err.println(new java.util.Date().toString());
	}
}
