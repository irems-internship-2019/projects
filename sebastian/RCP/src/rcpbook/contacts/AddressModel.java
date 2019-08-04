package rcpbook.contacts;

import java.util.ArrayList;
import java.util.List;

public class AddressModel {

	// ViewerTools vTools = new ViewerTools();

	private static List<AddressManager> addresses = new ArrayList<AddressManager>();
	private static int numbe;

	public void setNumber(int number) {
		numbe = number;
	}

	public void getData() {
		for (int i = 0; i < numbe; i++)
			addresses.add(AddressManager.createRandomAddress());
	}

	public List<AddressManager> getAddresses() {
		return addresses;
	}

	public static void addNewEntry(AddressManager newEntry) {
		addresses.add(newEntry);
	}

}