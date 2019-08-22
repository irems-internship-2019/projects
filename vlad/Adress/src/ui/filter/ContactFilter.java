package ui.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import model.Contact;

public class ContactFilter extends ViewerFilter {

    private String searchString;

    public void setSearchText(String s) {
        // ensure that the value can be used for matching
        this.searchString = ".*" + s + ".*";
    }

    @Override
    public boolean select(Viewer viewer,
            Object parentElement,
            Object element) {
        if (searchString == null || searchString.length() == 0) {
            return true;
        }
        Contact contact = (Contact) element;
        if (contact.getFirstName().matches(searchString)) {
            return true;
        }
        if (contact.getLastName().matches(searchString)) {
            return true;
        }
        if (contact.getEmailAdress().matches(searchString)) {
            return true;
        }
        if (contact.getphoneNumber().matches(searchString)) {
            return true;
        }
        return false;
    }
}
