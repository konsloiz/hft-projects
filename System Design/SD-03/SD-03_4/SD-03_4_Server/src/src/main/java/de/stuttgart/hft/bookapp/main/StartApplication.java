package de.stuttgart.hft.bookapp.main;

import de.stuttgart.hft.bookapp.client.view.BookView;
import de.stuttgart.hft.bookapp.domain.application.Application;

public class StartApplication {

	public static void main(String[] args) {
		Application app = new Application();
		new BookView(app);
	}
}
