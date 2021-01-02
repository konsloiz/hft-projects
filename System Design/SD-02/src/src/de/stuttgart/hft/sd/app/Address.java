package src.de.stuttgart.hft.sd.app;


public class Address {
	private final String street;
	private final String city;
	private final String code;
	private final String country;

	public Address(String street, String city, String code, String country) {
		this.street = street;
		this.city = city;
		this.code = code;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getCode() {
		return code;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", code=" + code + ", country=" + country + "]";
	}
}