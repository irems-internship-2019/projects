package Model;

import java.util.ArrayList;
import java.util.List;


public enum ContactProvider {
	
	    INSTANCE;

		  private static  ArrayList<Contact> contactList=new ArrayList<Contact>();
	    private ContactProvider() {

	    }

	    public ArrayList<Contact> getContacts() {
	        return contactList;
	    }
	    
	      public void addContacts(Contact contact) {
	    	  contactList.add(contact);
	      }
	     
	}


