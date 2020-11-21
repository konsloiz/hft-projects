package pipeline.basics;

import pipeline.interfaces.Mapper;
import pipeline.kernel.Filter;

public class BasicMapper<S, T> extends Filter<S, T>{	
	
	private Mapper<S, T> mapper;

	public BasicMapper(Mapper<S, T> mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public void process() {
		if(!running) return;
		S s = in.get();
		T t = null;
		if(s == null) // done
			running = false;
		else
			t = mapper.map(s);
		out.put(t);
	}
}
