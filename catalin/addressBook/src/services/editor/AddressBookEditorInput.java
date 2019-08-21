package services.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class AddressBookEditorInput implements IEditorInput
{

    @Override
    public Object getAdapter(Class adapter)
    {
	return null;
    }

    @Override
    public boolean exists()
    {
	return false;
    }

    @Override
    public ImageDescriptor getImageDescriptor()
    {
	return null;
    }

    @Override
    public String getName()
    {
	return "Contact Editor";
    }

    @Override
    public IPersistableElement getPersistable()
    {
	return null;
    }

    @Override
    public String getToolTipText()
    {
	return "Contact Editor";
    }
}