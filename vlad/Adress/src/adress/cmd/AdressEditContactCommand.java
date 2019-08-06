package adress.cmd;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import adress.editor.AdressBookEditor;
import adress.editor.AdressbookEditorInput;
import checker.SelectChecker;

public class AdressEditContactCommand extends AbstractHandler implements IHandler {

	public static final String ID = "Adress.editor1";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		AdressbookEditorInput input = new AdressbookEditorInput();
		SelectChecker check = new SelectChecker();

		try {
			if (check.isSelected() == true) {
				AdressBookEditor.setState("edit");
				((IWorkbenchPage) check.getPage()).openEditor(input, AdressBookEditor.ID);
			}
		} catch (PartInitException e) {
			System.out.println("Error:" + this.getClass().getName() + ":" + e);
			e.printStackTrace();
			throw new ExecutionException("Error open UserEditor");
		}
		check.refresh();

		return null;
	}

}
