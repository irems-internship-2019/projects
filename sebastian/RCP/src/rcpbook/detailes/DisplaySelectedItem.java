//package rcpbook.detailes;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import rcpbook.contacts.ContactsManager;
//import rcpbook.contacts.SetAddressesToContacts;
//
//public class DisplaySelectedItem {
//
//	private static ContactsManager contact;
//	private static String[] addressString = new String[4];
//	private static List<DetailesManager> detailesArray = new ArrayList<DetailesManager>();
//	private DetailesManager detailes;
//
//	public void setSelectedItem(ContactsManager selectedContact) {
//		contact = selectedContact;
//		addSelectedItemToList();
//	}
//
//	public void addSelectedItemToList() 
//	{
//		addressString = Arrays.copyOf(SetAddressesToContacts.getAddressFromIndex(contact.getID()), 4);
//		detailesArray.add(detailes.createNewDetailes(contact.getID(), contact.getFirst(), contact.getSecond(), addressString[0], addressString[1], addressString[2], addressString[3]));
//	}
//	
//	public List<DetailesManager> setDetailesArray() 
//	{
//		return detailesArray;
//	}
//
////	public String[] getAllDetailes() {
////		
////		// THIS IS NOT OPTIMAL###############################################
////		stringContacts[0] = contact.getFirst();
////		stringContacts[1] = contact.getSecond();
////		stringContacts[2] = addressString[0];
////		stringContacts[3] = addressString[1];
////		stringContacts[4] = addressString[2];
////		stringContacts[5] = addressString[3];
////		stringContacts[6] = contact.getPhone();
////		stringContacts[7] = contact.getEmail();
////		// THIS IS NOT OPTIMAL###############################################
////
////		return stringContacts;
////	}
//
//}
