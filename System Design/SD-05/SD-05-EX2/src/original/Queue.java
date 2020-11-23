package original;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	public static final int MAXQUEUE = 5;
	private List<String> messages = new ArrayList<>();

//called by the Producer
	public synchronized void putMessage(String message) {
		while (messages.size() >= MAXQUEUE)
			try {
				wait();
			} catch (InterruptedException e) {
			}
		messages.add(message);
		notifyAll();
	}

//called by the Consumer
	public synchronized String getMessage() {
		while (messages.size() == 0)
			try {
				notifyAll();
				wait();
			} catch (InterruptedException e) {
			}
		String message = messages.remove(0);
		notifyAll();
		return message;
	}
}