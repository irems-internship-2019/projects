package rcpbook.contacts;

public class SetAddressesToContacts {

	static AddressModel addresses = new AddressModel();

	public static String getAddressOfContact(String contactId) {
		for (AddressManager addressNum : addresses.getAddresses()) {
			if (addressNum.getID().equals(contactId)) {
				return addressNum.getStreet();
			}
		}
		return null;
	}
}
