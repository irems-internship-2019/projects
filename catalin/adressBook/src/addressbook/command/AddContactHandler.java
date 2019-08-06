package addressbook.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import addressbook.editor.AddContact;
import addressbook.editor.AddContactInput;

public class AddContactHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();

		AddContactInput input = new AddContactInput();
		try {
			page.openEditor(input, AddContact.ID);
		} catch (PartInitException e) {
			System.out.println("Error:" + this.getClass().getName() + ":" + e);
			e.printStackTrace();
			throw new ExecutionException("Error open AddContact");
		}
		return null;
	}

}
