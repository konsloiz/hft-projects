package eg11.producer_consumer_with_view;

import java.awt.Color;

public class Product {
	
	private String message;
	private Color color;
	
	
	public Product(String message, Color color) {
		super();
		this.message = message;
		this.color = color;
	}

	public String getMessage() {
		return message;
	}

	public Color getColor() {
		return color;
	}
}
