package pipeline.basics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import pipeline.interfaces.Pipe;

public class BasicPipe<T> implements Pipe<T>{
	
	private static final int MAXQUEUE = 5;
	private List<T> queue = new LinkedList<>();

	//called by the Producer
	@Override
	public synchronized void put(T t) {
//		System.out.println("Put(" + t + "): " + queue);

		while(queue.size() >= MAXQUEUE)
			try {
				wait();
			} catch (InterruptedException e) {}
		queue.add(t);
		notifyAll();
	}

	//called by the Consumer
	@Override
	public synchronized T get() {
//		System.out.println("Get: " + queue);
		while(queue.size() == 0)
			try {
				notifyAll();
				wait();
			} catch (InterruptedException e) {}
		T t = queue.remove(0);
		notifyAll();
//		System.out.println("Waited: Get: " + queue);
		return t;
	}

	//called by the Consumer
	@Override
	public synchronized List<T> get(int n) {
//		System.out.println("Get(" + n + "): " + queue);
		while(queue.size() == 0)
			try {
				notifyAll();
				wait();
			} catch (InterruptedException e) {}
//		System.out.println("Waited: Get(" + n + "): " + queue);
		
		if(n > queue.size()) n = queue.size();
		List<T> ts = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			T t = queue.remove(0);
			if(t == null)
				queue.add(null); // push back
			else
				ts.add(t);
		}
		if(ts.isEmpty())
			ts = null;

		notifyAll();
		return ts;
	}

	//called by the Consumer
	@Override
	public synchronized List<T> getAll(int n) {
		List<T> ts = new ArrayList<>();		
		for(int i = 0; i < n; i++) {
			T t = get();
			if(t == null) {
				queue.add(null); // push back
				break;
			} else
				ts.add(t);
		}
		if(ts.isEmpty())
			ts = null;

		notifyAll();
		return ts;
	}
	
	public synchronized List<T> getAll() {
		List<T> ts = new ArrayList<>();		
		while(true) {
			T t = get();
			if(t == null) {
				queue.add(null); // push back
				break;
			} else
				ts.add(t);
		}
		if(ts.isEmpty())
			ts = null;

		notifyAll();
		return ts;
	}
}

