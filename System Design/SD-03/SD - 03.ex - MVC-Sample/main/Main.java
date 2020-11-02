package main;

import model.ObservedModel;
import view.ObservingComponent;

public class Main {

	 public static void main(String[] args) { 
		 ObservedModel observable = new ObservedModel(); 
		 ObservingComponent observer = new ObservingComponent(); 
		 
		 ObservingComponent observer1 = new ObservingComponent(); 
		 ObservingComponent observer2 = new ObservingComponent(); 
		 
		 observable.addObserver(observer);
		 observable.addObserver(observer1); 
		 observable.addObserver(observer2); 
		 
		 observable.setData("new value"); 
	} 
}
