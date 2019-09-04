package ui.lableProviders;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.graphics.Image;

import services.database.BookContacts;



public class ContactsLabelProvider implements ITableLabelProvider
{
    
    private List<TableViewerColumn> tableCollums = new ArrayList<TableViewerColumn>();

    
    public ContactsLabelProvider(List<TableViewerColumn> tableCollums)
    {
	this.tableCollums=tableCollums;
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
	    if (element instanceof BookContacts)
	    {

		BookContacts contactMgr = (BookContacts) element;

		switch (tableCollums.get(columnIndex).getColumn().getText())
		    {
		
		    case "ID":
			return contactMgr.getID();
		    
		    case "First Name":
			return contactMgr.getFirst_name();
		    
		    case "Last Name":
			return contactMgr.getLast_name();
		    
		    case "Street":
			return contactMgr.getAddress().getStreet();
		    
		    case "Phone Number":
			return contactMgr.getPhone_number();
			
		    case "Email":
			return contactMgr.getEmail();
		    
		    default:
			//return "Test";
			throw new IllegalArgumentException("Case not implemented!!");
		    }
	    }

	    // TODO Auto-generated method stub
	    return null;
	}

}