package Remover;

	
	import java.util.Iterator;
	import java.util.List;

	import org.eclipse.core.commands.AbstractHandler;
	import org.eclipse.core.commands.ExecutionEvent;
	import org.eclipse.core.commands.ExecutionException;
	import org.eclipse.jface.viewers.ISelection;
	import org.eclipse.jface.viewers.IStructuredSelection;
	import org.eclipse.ui.IWorkbenchPage;
	import org.eclipse.ui.IWorkbenchWindow;
	import org.eclipse.ui.handlers.HandlerUtil;

import Model.Contact;
import Model.ContactProvider;
import adress.View;

	

	public class DeleteContact extends AbstractHandler {
	    @SuppressWarnings("unchecked")
	    @Override
	    public Object execute(ExecutionEvent event) throws ExecutionException {
	        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
	        IWorkbenchPage page = window.getActivePage();
	        View view = (View) page.findView(View.ID);
	        ISelection selection = view.getSite().getSelectionProvider()
	                .getSelection();

	        if (selection != null && selection instanceof IStructuredSelection) {
	            List<Contact> persons = ContactProvider.INSTANCE.getContacts();
	            IStructuredSelection sel = (IStructuredSelection) selection;

	            for (Iterator<Contact> iterator = sel.iterator(); iterator.hasNext();) {
	            	Contact contact = iterator.next();
	                persons.remove(contact);
	            }
	            view.refresh();
	        }
	        return null;
	    }
	}


