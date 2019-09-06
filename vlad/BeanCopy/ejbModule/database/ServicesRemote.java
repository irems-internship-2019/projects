package database;

import java.util.ArrayList;

import javax.ejb.Remote;

import model.Contact;

@Remote
public interface ServicesRemote {
 
	  public ArrayList<Contact> loadContatcs();
	  
}
