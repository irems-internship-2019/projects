package rcpbook.editor;

import rcpbook.contacts.ContactsManager;

public class CheckSelected {


	private static ContactsManager contact;
	
	public static void setSelectedItem(ContactsManager selectedContact )
	{
		contact = selectedContact;
	}
	
	public static ContactsManager getSelectedItem()
	{
		return contact;
	}
	
	public static void setSelectedToNull() 
	{
		contact = null;
	}
}
