package main;

import model.ObservedModel;
import view.ObservingComponent;

public class Main {

	 public static void main(String[] args) { 
		 ObservedModel observable = new ObservedModel(); 
		 ObservingComponent observer = new ObservingComponent(); 
		 
		 observable.addObserver(observer); 
		 observable.setData("new value"); 
	} 
}
