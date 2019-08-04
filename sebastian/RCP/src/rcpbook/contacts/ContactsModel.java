package rcpbook.contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactsModel 
{
	//ViewerTools vTools = new ViewerTools();
	
	private static List<ContactsManager> contacts = new ArrayList<ContactsManager>();
	private int number;
	
	public ContactsModel (int number) {
		this.number = number;
		getData();
	}
	
    void getData()
    {
        for(int i=0; i<number; i++)
            contacts.add(ContactsManager.createRandomPerson());
    }
    
    public  List<ContactsManager> getElements() {
        return contacts;
    }
    
    public static void addNewEntry(ContactsManager newEntry) 
    {
    	contacts.add(newEntry);
    }

}