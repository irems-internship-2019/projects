package addressbook.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import addressbook.contentofeditor.ContentOfEdit;
import addressbook.editor.AddressBookEditor;
import addressbook.editor.AddressBookEditorInput;

public class EditContactHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		
		AddressBookEditorInput input = new AddressBookEditorInput();
		try {
			AddressBookEditor.numberOfChoice = 2;
			if (!ContentOfEdit.contactSelected.isEmpty())
				page.openEditor(input, AddressBookEditor.ID);
		} catch (PartInitException e) {
			System.out.println("Error:" + this.getClass().getName() + ":" + e);
			e.printStackTrace();
			throw new ExecutionException("Error open UserEditor");
		}
		return null;
	}

}
