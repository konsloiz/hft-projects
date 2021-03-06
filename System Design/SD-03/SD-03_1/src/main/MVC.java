package main;

import model.ObservedModel;
import view.ObservingComponent;

public class MVC {

	public static void main(String[] args) {
		
		ObservedModel observable = new ObservedModel();
		ObservingComponent observer1 = new ObservingComponent("Observer 1");
		ObservingComponent observer2 = new ObservingComponent("Observer 2");
		
		observable.addObserver(observer1);
		observable.addObserver(observer2);
		observable.setData("new value");
	}

}
