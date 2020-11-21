package eg04.control;

import java.util.Date;

public class Target implements Runnable {
	
	private Thread thread = new Thread(this);

	private boolean suspended = false;
	private long sleepTime = 1000;

	@Override
	public void run() {
		while (true) {
			try {
				synchronized (this) {
					while (suspended) {
						wait();
						System.err.println("woke up from wait");
					}
				}
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				System.err.println("woke up from sleep");
			}
			System.out.println(new Date());
		}
	}

	public void setSleepTime(long sleepTime) {
	    thread.interrupt();
	    this.sleepTime = sleepTime;
	  }

	  public synchronized void start(){
	    if(!thread.isAlive()) 
	      thread.start();
	    if(suspended){
	      suspended = false;
	      this.notify();
	    }
	  }

	  public synchronized void stop(){
	    if(!suspended)
	      suspended = true;
	  }

	  public boolean isRunning(){
	    return thread.isAlive() & !suspended;
	  }

}
