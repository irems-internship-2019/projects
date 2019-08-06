package rcpbook.contacts;

import java.util.ArrayList;
import java.util.List;

public class AddressModel {

	// ViewerTools vTools = new ViewerTools();

	private static List<ContactsManager> addresses = new ArrayList<ContactsManager>();
	

	public List<ContactsManager> getAddresses() {
		return addresses;
	}

	public static void addNewEntry(ContactsManager newEntry) {
		addresses.add(newEntry);
	}

}