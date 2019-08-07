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
import org.eclipse.ui.handlers.HandlerUtil;

import adress.View;
import adress.editor.AdressBookEditor;
import adress.editor.AdressbookEditorInput;
import checker.SelectChecker;

public class AdressEditContactCommand extends AbstractHandler implements IHandler {

	public static final String ID = "Adress.editor1";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchPage activePage = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage();
		View view = (View) activePage.findView(View.ID);
		
		AdressBookEditor.openEditor(view.getSelectedItem());
		


		return null;
	}

}
