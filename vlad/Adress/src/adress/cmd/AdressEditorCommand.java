package adress.cmd;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import adress.View;
import adress.editor.AdressBookEditor;
import adress.editor.AdressbookEditorInput;
import checker.SelectChecker;



public class AdressEditorCommand extends AbstractHandler implements IHandler {

	public static final String ID ="cmd";
	 @Override
	   public Object execute(ExecutionEvent event) throws ExecutionException {
		 
         SelectChecker check =new SelectChecker();
	       AdressbookEditorInput input = new AdressbookEditorInput();
	       try {
	    		   
	           ((IWorkbenchPage) check.getPage()).openEditor(input, AdressBookEditor.ID);
	    	   
	       } catch (PartInitException e) {
	           System.out.println("Error:" + this.getClass().getName() + ":" + e);
	           e.printStackTrace();
	           throw new ExecutionException("Error open UserEditor");
	       }
	       return null;
	   }
}