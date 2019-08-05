package addressbook.comparator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import addressbook.persons.ContactClass;

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
    public int compare(Viewer viewer, Object element1, Object element2) {
    	ContactClass person1 = (ContactClass) element1;
    	ContactClass person2 = (ContactClass) element2;
        int result = 0;
        switch (propertyIndex) {
        case 0:
        	result = Integer.toString(person1.getId()).compareTo(Integer.toString(person2.getId()));
            break;
        case 1:
        	result = person1.getFirstName().compareTo(person2.getFirstName());
            break;
        case 2:
        	result = person1.getLastName().compareTo(person2.getLastName());
            break;
        case 3:
        	result = person1.getAddress().getStreet().compareTo(person2.getAddress().getStreet());
        	break;
        case 4:
        	result = Integer.toString(person1.getPhoneNumber()).compareTo(Integer.toString(person2.getPhoneNumber()));
            break;
        case 5:
        	result = person1.getEmailAddress().compareTo(person2.getEmailAddress());
            break;
        default:
        	result = 0;
        }
        // If descending order, flip the direction
        if (direction == DESCENDING) {
        	result = -result;
        }
        return result;
    }

}

