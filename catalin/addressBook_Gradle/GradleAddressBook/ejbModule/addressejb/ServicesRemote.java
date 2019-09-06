package addressejb;

import java.util.List;

import javax.ejb.Remote;

import models.persons.Contact;

@Remote
public interface ServicesRemote {
	 public List<Contact> getServerContacts();
	 public void addServerContact(Contact contact);
	 public void editServerContact(Contact contact);
	 public void deleteServerContact(Contact contact);
}