package pipeline.kernel;

import java.util.ArrayList;
import java.util.List;

import pipeline.basics.BasicChecker;
import pipeline.basics.BasicMapper;
import pipeline.basics.BasicPipe;
import pipeline.basics.BasicReducer;
import pipeline.interfaces.Checker;
import pipeline.interfaces.Mapper;
import pipeline.interfaces.Pipe;
import pipeline.interfaces.Reducer;

public class Pipeline {
	
	private List<Thread> threads;

	private Pipeline(List<Thread> threads) {
		this.threads = threads;
	}

	public static class Segment<S>{
		
		private List<Thread> threads;
		private Pipe<S> in = null;

		private Segment(Pipe<S> in, List<Thread> threads) {
			this.in = in;
			this.threads = threads;
		}

		private Segment(Pipe<S> in) {
			this(in, new ArrayList<>());
		}

		public <T> Segment<T>  map(Mapper<S, T> mapper) {
			return filter(new BasicMapper<S, T>(mapper));
		}

		public <T> Segment<T>  reduce(Reducer<S, T> reducer) {
			return filter(new BasicReducer<S, T>(reducer));
		}

		public Segment<S>  check(Checker<S> checker) {
			return filter(new BasicChecker<S>(checker));
		}

		public <T> Segment<T> filter(Filter<S, T> filter) {
			filter.in = in;
			Pipe<T> p = new BasicPipe<T>();
			filter.out = p;			
			threads.add(new Thread(filter));
			Segment<T> next = new Segment<>(p, threads);
			return next;
		}

		public Pipeline drain(Pipe<S> out) {
			Filter<S, S> filter = new BasicMapper<S, S>(s -> s);
			filter.in = in;
			filter.out = out;			
			threads.add(new Thread(filter));
			Pipeline pl = new Pipeline(threads);
			return pl;
		}
	}

	public static <S>Segment<S> source(Pipe<S> source) {
		Segment<S> inter = new Segment<>(source);
		return inter;
	}

	public void start() {
		for(Thread thread : threads)
			thread.start();
	}
}
