package adress.cmd;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import adress.editor.AdressBookContactEditorInput;
import adress.editor.AdressBookEditContact;



public class AdressEditContactCommand extends AbstractHandler implements IHandler  {

	public static final String ID ="Adress.editor1";
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		 IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
	       IWorkbenchPage page = window.getActivePage();

	       AdressBookContactEditorInput input = new AdressBookContactEditorInput();
	       try {
	           page.openEditor(input, AdressBookEditContact.ID);
	       } catch (PartInitException e) {
	           System.out.println("Error:" + this.getClass().getName() + ":" + e);
	           e.printStackTrace();
	           throw new ExecutionException("Error open UserEditor");
	       }
		return null;
	}

}
