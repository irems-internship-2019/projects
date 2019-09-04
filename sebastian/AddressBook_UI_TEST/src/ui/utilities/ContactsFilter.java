package ui.utilities;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import services.database.BookContacts;

public class ContactsFilter extends ViewerFilter
{

    private String searchString;

    public void setSearchText(String s)
    {
	// ensure that the value can be used for matching
	this.searchString = ".*" + s + ".*";
    }

    @Override
    public boolean select(Viewer tableViewer, Object parentElement, Object element)
    {

	if (searchString == null || searchString.length() == 0)
	{
	    return true;
	}

	BookContacts p = (BookContacts) element;
	if (p != null && p.getID().matches(searchString))
	{
	    return true;
	}

	if (p != null && p.getFirst_name().matches(searchString))
	{
	    return true;
	}

	if (p != null && p.getLast_name().matches(searchString))
	{
	    return true;
	}

	if (p != null && p.getAddress().getStreet().matches(searchString))
	{
	    return true;
	}

	if (p != null && p.getPhone_number().matches(searchString))
	{
	    return true;
	}

	if (p != null && p.getEmail().matches(searchString))
	{
	    return true;
	}

	return false;
    }
}