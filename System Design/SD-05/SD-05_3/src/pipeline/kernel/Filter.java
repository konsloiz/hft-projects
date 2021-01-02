package pipeline.kernel;

import pipeline.interfaces.Pipe;

// Filters from Source to Target
public abstract class Filter<S, T> implements Runnable { 
	
	protected Pipe<S> in = null;
	protected Pipe<T> out = null;
	protected boolean running = true;

	public boolean isRunning() {
		return running;
	}

	public void setIn(Pipe<S> in) {
		this.in = in;
	}
	
	public void setOut(Pipe<T> out) {
		this.out = out;
	}
	
	public abstract void process();
	
	@Override
	public void run() {
		while(isRunning())
			process();
	}	
}
