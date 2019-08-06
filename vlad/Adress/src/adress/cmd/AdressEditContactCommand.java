package adress.cmd;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import Model.Contact;
import Model.ContactProvider;
import adress.View;
import adress.editor.AdressBookContactEditorInput;
import adress.editor.AdressBookEditContact;
import adress.editor.AdressBookEditor;
import adress.editor.AdressbookEditorInput;
import checker.SelectChecker;



public class AdressEditContactCommand extends AbstractHandler implements IHandler  {

	public static final String ID ="Adress.editor1";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		  AdressbookEditorInput input = new AdressbookEditorInput();
		 SelectChecker check =new SelectChecker();

		try {
			   if(check.isSelected()==true)
			   ((IWorkbenchPage) check.getPage()).openEditor(input, AdressBookEditContact.ID);
		} catch (PartInitException e) {
			System.out.println("Error:" + this.getClass().getName() + ":" + e);
			e.printStackTrace();
			throw new ExecutionException("Error open UserEditor");
		}
		check.refresh();

		return null;
}

}
