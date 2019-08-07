package rcpbook.editor;

import rcpbook.contacts.ContactsManager;

public class CheckIfElementIsSelected {

	private static boolean editorMode = false;
	private static boolean createMode = false;
	
	private static ContactsManager contact;
	
	public static void setSelectedItem(ContactsManager selectedContact )
	{
		contact = selectedContact;
	}
	
	public static ContactsManager getSelectedItem( )
	{
		return contact;
	}
	
	public static void setEditorMode(boolean selector )
	{
		editorMode = selector;
	}
	
	public static void setEditMode(boolean create )
	{
		createMode = create;
	}
	
	public static boolean getEditorMode() 
	{
		return editorMode;
	}
	
	public static boolean getEditMode() 
	{
		return createMode;
	}
	
}
