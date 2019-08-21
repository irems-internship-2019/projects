package ui.lableProviders;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.graphics.Image;

import model.contacts.ContactsManager;

public class DetailesLabelProvider implements ITableLabelProvider
{
    
    private List<TableViewerColumn> tableCollums = new ArrayList<TableViewerColumn>();

    
    public DetailesLabelProvider(List<TableViewerColumn> tableCollums)
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
	    if (element instanceof ContactsManager)
	    {

		ContactsManager contactMgr = (ContactsManager) element;

		switch (tableCollums.get(columnIndex).getColumn().getText())
		    {
		
		    case "ID":
			return contactMgr.getID();
		    
		    case "First Name":
			return contactMgr.getFirstName();
		    
		    case "Last Name":
			return contactMgr.getLastName();
		    
		    case "Country":
			return contactMgr.getAddress().getCountry();
		    
		    case "City":
			return contactMgr.getAddress().getCity();
			
		    case "Street":
			return contactMgr.getAddress().getStreet();
			
		    case "Postal Code":
			return contactMgr.getAddress().getPostalCode();
		    
		    default:
			//return "Test";
			throw new IllegalArgumentException("Case not implemented!!");
		    }
	    }

	    // TODO Auto-generated method stub
	    return null;
	}

}
