package pipeline.basics;

import pipeline.interfaces.Checker;
import pipeline.kernel.Filter;

public class BasicChecker<S> extends Filter<S, S>{
	
	private Checker<S> checker;

	public BasicChecker(Checker<S> checker) {
		super();
		this.checker = checker;
	}

	@Override
	public void process() {
		while(running) {
			S s = in.get();
			if(s == null) { // done
				running = false;
				out.put(s);
			}else if(checker.check(s)) { // found
				out.put(s);
				break; 
			}
		}
	}
}
