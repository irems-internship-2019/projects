package addressbook.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import addressbook.editor.AddressBookEditor;
import addressbook.editor.AddressBookEditorInput;
import addressbook.persons.Contact;
import addressbook.view.AddressBookView;

public class EditContactHandler extends AbstractHandler 
{
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException 
	{
		IWorkbenchPage activePage = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage();
		AddressBookView addressBookView = (AddressBookView) activePage.findView(AddressBookView.ID);
		
		AddressBookEditor.openEditor(addressBookView.getSelectedItem());
		
		return null;
	}

}
