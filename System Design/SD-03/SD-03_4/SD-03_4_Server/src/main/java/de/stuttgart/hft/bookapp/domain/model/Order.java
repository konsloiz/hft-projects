package de.stuttgart.hft.bookapp.domain.model;

public class Order {
	
	private String name, email, address;
	private int bookId;
	
	public Order() {} // This is crucial for JSON

	public Order(String name, String email, String address, int bookId) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public int getBookId() {
		return bookId;
	}

	@Override
	public String toString() {
		return "Order [name=" + name + ", email=" + email + ", address=" + address + ", bookId=" + bookId + "]";
	}
}
