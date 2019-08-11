package rcpbook.table;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import rcpbook.contacts.ContactsManager;

public class MyViewerComparator extends ViewerComparator {
	private int propertyIndex;
	private static final int DESCENDING = 1;
	private int direction = DESCENDING;

	public MyViewerComparator() {
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
	public int compare(Viewer viewer, Object e1, Object e2) {
		ContactsManager p1 = (ContactsManager) e1;
		ContactsManager p2 = (ContactsManager) e2;
		int rc = 0;
		switch (propertyIndex) {
		
		case 0:
			if (p1.getIntId() == (p2.getIntId()))
				rc = 0;
			else if (p1.getIntId() >= (p2.getIntId()))
				rc = 1;
			else
				rc = (-1);
			break;
		case 1:
			rc = p1.getFirst().compareTo(p2.getFirst());
			break;
		case 2:
			rc = p1.getSecond().compareTo(p2.getSecond());
			break;
		case 3:
			rc = p1.getAddress().getStreet().compareTo(p2.getAddress().getStreet());
			break;
			
//		case 4:
//			if (p1.geta() == (p2.getAge()))
//				rc = 0;
//			else if (p1.getAge() >= (p2.getAge()))
//				rc = 1;
//			else
//				rc = (-1);
//			break;
		case 4:
			rc = p1.getPhone().compareTo(p2.getPhone());
			break;
		case 5:
			rc = p1.getEmail().compareTo(p2.getEmail());
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