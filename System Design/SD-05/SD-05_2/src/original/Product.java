package original;

import java.util.Date;

public class Product {
	
	private Date date;
	private String producer;
	
	public Product(String producer) {
		super();
		this.date = new Date();
		this.producer = producer;
	}

	@Override
	public String toString() {
		return "Product [date=" + date + ", producer=" + producer + "]";
	}

}
