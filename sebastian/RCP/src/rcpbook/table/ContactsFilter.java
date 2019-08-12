package rcpbook.table;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import rcpbook.contacts.ContactsManager;

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

	ContactsManager p = (ContactsManager) element;
	if (p != null && p.getID().matches(searchString))
	{
	    return true;
	}

	if (p != null && p.getFirst().matches(searchString))
	{
	    return true;
	}

	if (p != null && p.getSecond().matches(searchString))
	{
	    return true;
	}

	if (p != null && p.getAddress().getStreet().matches(searchString))
	{
	    return true;
	}

	if (p != null && p.getPhone().matches(searchString))
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