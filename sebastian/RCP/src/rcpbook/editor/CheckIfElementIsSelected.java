package rcpbook.editor;

import rcpbook.contacts.ContactsManager;

public class CheckIfElementIsSelected {

	private static int editorMode = 0;
	private static int createMode = 0;
	
	private static ContactsManager contact;
	
	public static void setSelectedItem(ContactsManager selectedContact )
	{
		contact = selectedContact;
	}
	
	public static ContactsManager getSelectedItem( )
	{
		return contact;
	}
	
	public static void setEditorMode(int selector )
	{
		editorMode = selector;
	}
	
	public static void setEditMode(int create )
	{
		createMode = create;
	}
	
	public static int getEditorMode() 
	{
		return editorMode;
	}
	
	public static int getEditMode() 
	{
		return createMode;
	}
	
}
