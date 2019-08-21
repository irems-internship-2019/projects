//package rcpbook.editor;
//
//import java.util.HashMap;
//
//import rcpbook.contacts.ContactsManager;
//import rcpbook.contacts.ContactsModel;
//
//public class CreateDatabaseHashMap
//{
//    private ContactsModel newContact = new ContactsModel();
//    private static HashMap<Integer, Integer> arrayToDatabaseMap = new HashMap<Integer, Integer>();
//    
//    public void createHashMap() 
//    {
//	int i = 0;
//	
//	 for(ContactsManager contact : newContact.getElements()) 
//	 {
//	     arrayToDatabaseMap.put( contact.getIntId(),i);
//	     i++;
//	 }
//    }
//    
//    public HashMap<Integer, Integer> getArrayToDatabaseMap() 
//    {
//	return arrayToDatabaseMap;
//    }
//}
