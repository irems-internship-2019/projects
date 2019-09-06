package ui.comparator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import model.ContactsTable;

public class ContactComparator extends ViewerComparator {
    private int propertyIndex;
    private static final int DESCENDING = 1;
    private int direction = DESCENDING;

    public ContactComparator() {
        this.propertyIndex = 0;
        direction = DESCENDING;
    }

    public int getDirection() {
        return direction == 1 ? SWT.DOWN : SWT.UP;
    }

    public void setColumn(int column) {
        if (column == this.propertyIndex) {
            // Same column as last sort; toggle the direction
            direction = 1 - direction;
        } else {
            // New column; do an ascending sort
            this.propertyIndex = column;
            direction = DESCENDING;
        }
    }

    @Override
    public int compare(Viewer viewer, Object firstElement, Object secondElement) {
        ContactsTable firstContact = (ContactsTable) firstElement;
        ContactsTable secondContact = (ContactsTable) secondElement;
        int rc = 0;
        switch (propertyIndex) {
        case 0:
            rc = firstContact.getFirstName().compareTo(secondContact.getFirstName());
            break;
        case 1:
            rc = firstContact.getLastName().compareTo(secondContact.getLastName());
            break;
        case 2:
            rc = Long.toString(firstContact.getContactid()).compareTo(Long.toString(secondContact.getContactid()));
            break;
        case 3:
        	rc= firstContact.getEmailAddress().compareTo(secondContact.getEmailAddress());
            break;
        case 4:
        	rc= firstContact.getPhoneNumber().compareTo(secondContact.getPhoneNumber());
            break;
        default:
        	
            rc = 0;
        }
        // If descending order, flip the direction
        if (direction == DESCENDING) {
            rc = -rc;
        }
        return rc;
    }

}