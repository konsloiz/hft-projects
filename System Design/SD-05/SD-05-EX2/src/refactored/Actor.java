package refactored;

public abstract class Actor implements Runnable {

	private String name;
	private Queue queue;
	private long time;

	public Actor(String name, Queue queue, long time) {
		super();
		this.name = name;
		this.queue = queue;
		this.time = time;
	}
	
	@Override
	public final void run() {
		while (true) {
			action(name, queue);
			try {
				Thread.sleep(this.time);
			} catch (InterruptedException e) {
			}
		}
	}

	protected abstract void action(String name, Queue queue);

}