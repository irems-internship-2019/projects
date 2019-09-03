package addressejb;

import java.util.ArrayList;

import javax.ejb.Remote;

import models.persons.Contact;

@Remote
public interface ServicesRemote {
	 public ArrayList<Contact> getServerContacts();
	 public void addServerContact(Contact contact);
	 public void editServerContact(Contact contact);
	 public void deleteServerContact(Contact contact);
}