package rcpbook.contacts;

import java.util.Random;

public class AddressManager {

	private static final String[] COUNTRY = { "USA", "Ukraine", "Liliput", "Germany", "Kingdom of Buganda" };
	private static final String[] CITY = { "New York", "Idiotville", "Saint-Louis-du-Ha! Ha!", "Worcestershire","Brokenwind" };
	private static final String[] STREET = { "Herculane 07", " Libertatii 22", "Revolutiei 77", "Ady Endre 11","Viseului 04" };
	private static final String[] POSTALCODE = { "07450", "33917", "415300", "329875", "412800" };

	private static int index = 1;
	private int ID = 0;

	private static Random random = new Random(System.currentTimeMillis());

	private String country;
	private String city;
	private String street;
	private String postalCode;

	public AddressManager(String country, String city, String street, String postalCode) {
		// super();
		this.ID = index++;
		this.country = country;
		this.city = city;
		this.street = street;
		this.postalCode = postalCode;
	}

	public String getID() {
		return Integer.toString(ID);
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	
//	public static AddressManager createAddress() {
//		return new AddressManager("USA", "New York", "Cattle Heaven", "415300");
//	}
	
			public static AddressManager createRandomAddress(){
				return new  AddressManager(COUNTRY[random.nextInt(COUNTRY.length)], 
						CITY[random.nextInt(CITY.length)],STREET[random.nextInt(STREET.length)], 
						POSTALCODE[random.nextInt(POSTALCODE.length)]);
			}
}
