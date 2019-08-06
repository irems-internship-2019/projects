package rcpbook.cmd;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import rcpbook.editor.AddressBookEditorInput;
import rcpbook.view.EditorView;

public class AddNewElement extends AbstractHandler {


		@Override
		   public Object execute(ExecutionEvent event) throws ExecutionException {
		       IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		       IWorkbenchPage page = window.getActivePage();

		       AddressBookEditorInput input = new AddressBookEditorInput();
		       
		       try {
		           page.openEditor(input, EditorView.ID);
		       } catch (PartInitException e) {
		           System.out.println("Error:" + this.getClass().getName() + ":" + e);
		           e.printStackTrace();
		           throw new ExecutionException("Error open EditorView");
		       }
		return null;
	}
	
}
