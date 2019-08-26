package ui.labels;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.graphics.Image;

import models.persons.Contact;

public class InnerLabelProvider implements ITableLabelProvider
{
    private ArrayList<TableViewerColumn> tableDetails = new ArrayList<TableViewerColumn>();

    public InnerLabelProvider(ArrayList<TableViewerColumn> tableDetails)
    {
	this.tableDetails = tableDetails;
    }

    @Override
    public void addListener(ILabelProviderListener listener)
    {
	// TODO Auto-generated method stub

    }

    @Override
    public void dispose()
    {
	// TODO Auto-generated method stub

    }

    @Override
    public boolean isLabelProperty(Object element, String property)
    {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void removeListener(ILabelProviderListener listener)
    {
	// TODO Auto-generated method stub

    }

    @Override
    public Image getColumnImage(Object element, int columnIndex)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getColumnText(Object element, int columnIndex)
    {
	if (element instanceof Contact)
	{
	    Contact contact = (Contact) element;

	    switch (tableDetails.get(columnIndex).getColumn().getText())
	    {
	    case "ID":
		return Long.toString(contact.getId());

	    case "First Name":
		return contact.getFirstName();

	    case "Last Name":
		return contact.getLastName();

	    case "Street":
		return contact.getAddress().getStreet();

	    case "Phone Number":
		return contact.getPhoneNumber();

	    case "Email":
		return contact.getEmailAddress();

	    default:
		throw new IllegalArgumentException("Case not implemented!!");
	    }
	}
	// TODO Auto-generated method stub
	return null;
    }
}