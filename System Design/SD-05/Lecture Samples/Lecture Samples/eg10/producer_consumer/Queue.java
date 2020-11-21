package eg10.producer_consumer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Queue {
	
	public static final int MAXQUEUE = 5;
	private List<String> messages = new ArrayList<>();

	//called by the Producer
	public synchronized void putMessage() {
		while(messages.size() >= MAXQUEUE)
			try {
				wait(); // for notification by "getMessage"
			} catch (InterruptedException e) {}
		messages.add(new Date().toString());
		notifyAll(); // the queue has elements - wake up "getMessage"
	}

	//called by the Consumer
	public synchronized String getMessage() {
		while(messages.size() == 0)
			try {
				notifyAll(); // try to wake up "putMessage" to prevent deadlock
				wait(); // for notification by "putMessage"
			} catch (InterruptedException e) {}
		String message = messages.remove(0);
		notifyAll(); // the queue needs elements - wake up "putMessage"
		return message;
	}
}
