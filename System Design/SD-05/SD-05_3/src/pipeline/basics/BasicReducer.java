package pipeline.basics;

import java.util.List;

import pipeline.interfaces.Reducer;
import pipeline.kernel.Filter;

public class BasicReducer<S, T> extends Filter<S, T>{
	
	private Reducer<S, T> reducer;

	public BasicReducer(Reducer<S, T> reducer) {
		super();
		this.reducer = reducer;
	}

	@Override
	public void process() {
		if(!running) return;
		List<S> s = in.getAll();
		T t = null;
		if(s == null) // done
			running = false;
		else
			t = reducer.reduce(s);
		out.put(t);
	}
}
