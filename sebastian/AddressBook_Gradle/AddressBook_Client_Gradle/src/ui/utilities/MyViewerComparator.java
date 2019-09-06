package ui.utilities;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import services.database.BookContacts;

public class MyViewerComparator extends ViewerComparator
{
    private int propertyIndex;
    private static final int DESCENDING = 1;
    private int direction = DESCENDING;

    public MyViewerComparator()
    {
	this.propertyIndex = 0;
	direction = DESCENDING;
    }

    public int getDirection()
    {
	return direction == 1 ? SWT.DOWN : SWT.UP;
    }

    public void setColumn(int column)
    {
	if (column == this.propertyIndex)
	{
	    // Same column as last sort; toggle the direction
	    direction = 1 - direction;
	} else
	{
	    // New column; do an ascending sort
	    this.propertyIndex = column;
	    direction = DESCENDING;
	}
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2)
    {
	BookContacts p1 = (BookContacts) e1;
	BookContacts p2 = (BookContacts) e2;
	int rc = 0;
	switch (propertyIndex)
	    {

	    case 0:
		if (p1.getLongID() == (p2.getLongID()))
		    rc = 0;
		else if (p1.getLongID() >= (p2.getLongID()))
		    rc = 1;
		else
		    rc = (-1);
		break;
	    case 1:
		rc = p1.getFirst_name().compareTo(p2.getFirst_name());
		break;
	    case 2:
		rc = p1.getLast_name().compareTo(p2.getLast_name());
		break;
	    case 3:
		rc = p1.getAddress().getStreet().compareTo(p2.getAddress().getStreet());
		break;
	    case 4:
		rc = p1.getLast_name().compareTo(p2.getLast_name());
		break;
	    case 5:
		rc = p1.getEmail().compareTo(p2.getEmail());
		break;
	    default:
		rc = 0;
	    }
	// If descending order, flip the direction
	if (direction == DESCENDING)
	{
	    rc = -rc;
	}
	return rc;
    }

}