package addressbook.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import addressbook.persons.Contact;

public class ContactFilter extends ViewerFilter {

	private String searchString;

	public void setSearchText(String s) {
		// ensure that the value can be used for matching
		this.searchString = ".*" + s + ".*";
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (searchString == null || searchString.length() == 0) {
			return true;
		}
		Contact person = (Contact) element;
		if (Integer.toString(person.getId()).matches(searchString)) {
			return true;
		}
		if (person.getFirstName().matches(searchString)) {
			return true;
		}
		if (person.getLastName().matches(searchString)) {
			return true;
		}
		if (person.getAddress().getStreet().matches(searchString)) {
			return true;
		}
		if (Integer.toString(person.getPhoneNumber()).matches(searchString)) {
			return true;
		}
		if (person.getEmailAddress().matches(searchString)) {
			return true;
		}

		return false;
	}
}