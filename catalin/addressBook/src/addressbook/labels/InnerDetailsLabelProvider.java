package addressbook.labels;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.graphics.Image;

import addressbook.persons.Contact;

public class InnerDetailsLabelProvider implements ITableLabelProvider
{
    private ArrayList<TableViewerColumn> tableDetails = new ArrayList<TableViewerColumn>();

    public InnerDetailsLabelProvider(ArrayList<TableViewerColumn> tableDetails)
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
		return Integer.toString(contact.getId());

	    case "First Name":
		return contact.getFirstName();

	    case "Last Name":
		return contact.getLastName();

	    case "Country":
		return contact.getAddress().getCountry();

	    case "City":
		return contact.getAddress().getCity();

	    case "Street":
		return contact.getAddress().getStreet();

	    case "Postal Code":
		return contact.getAddress().getPostal_code();

	    default:
		throw new IllegalArgumentException("Case not implemented!!");
	    }
	}
	// TODO Auto-generated method stub
	return null;
    }
}