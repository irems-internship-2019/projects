package rcpbook.cmd;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import rcpbook.editor.AddressBookNewContactImput;
import rcpbook.view.Editor;

public class AddNewElement extends AbstractHandler {


		@Override
		   public Object execute(ExecutionEvent event) throws ExecutionException {
		       IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		       IWorkbenchPage page = window.getActivePage();

		       AddressBookNewContactImput input = new AddressBookNewContactImput();
		       
		       try {
		           page.openEditor(input, Editor.ID);
		       } catch (PartInitException e) {
		           System.out.println("Error:" + this.getClass().getName() + ":" + e);
		           e.printStackTrace();
		           throw new ExecutionException("Error open EditorView");
		       }
		return null;
	}
	
}


//implements IViewActionDelegate
//{
//
//	@Override
//	public void run(IAction action) 
//	{
//		MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Info", "You run Add new element");
//		
//	}
//
//	@Override
//	public void selectionChanged(IAction action, ISelection selection) 
//	{
//		
//	}
//
//	@Override
//	public void init(IViewPart view) 
//	{
//		
//	}
//}



