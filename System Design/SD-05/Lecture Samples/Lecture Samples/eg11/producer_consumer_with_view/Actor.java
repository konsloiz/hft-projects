package eg11.producer_consumer_with_view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class Actor extends Observable{
	
	private ActorThread thread;
	private String name;
	private long sleepTime;
	private Queue queue;

	private boolean suspended = false;
	private boolean started = false;

	private List<Product> products = new ArrayList<>();
	
	private class ActorThread extends Thread {

		private Actor controlled;

		public ActorThread(Actor controlled) {
			super();
			this.controlled = controlled;
		}

		public void run() {
			while (true) {
				controlled.act();
				try {
					synchronized(controlled){
						while(suspended)
							controlled.wait();
					}
					sleep(sleepTime);
				} catch (InterruptedException ignored){}
			}
		}
	} 


	public Actor(String name, long sleepTime, Queue queue) {
		super();
		this.name = name;
		this.sleepTime = sleepTime;
		this.queue = queue;
		this.thread = new ActorThread(this);
	}

	public String getName() {
		return name;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public Queue getQueue() {
		return queue;
	}

	public void setSleepTime(long sleepTime) {
		thread.interrupt(); // wake up from sleep
		this.sleepTime = sleepTime;
	}

	public long getSleepTime() {
		return sleepTime;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public boolean isRunning(){
		return started & !suspended;
	}

	public synchronized void start(){
		if(!started){
			thread.start();
			started = true;
		}
		if(suspended){
			suspended = false;
			this.notify();
		}
	}

	public synchronized void stop(){
		if(!suspended)
			suspended = true;
	}

	public abstract void act();
	

}
