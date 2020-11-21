package eg08.control_with_view;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Controlled{

    private PropertyChangeSupport support;
    
    public Controlled() {
        support = new PropertyChangeSupport(this);
    }
 
    public void addObserver(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
 
    public void removeObserver(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
    

    private Thread thread = new Thread(() -> printDates());

	private boolean suspended = false;
	private long sleepTime = 1000;

	public void printDates() {
		while (true) {
			try {
				synchronized (Controlled.this) {
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
	
	public long getSleepTime(){
		return sleepTime;
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
		String value = new java.util.Date().toString();
        support.firePropertyChange("data", null, value);
	}
}
