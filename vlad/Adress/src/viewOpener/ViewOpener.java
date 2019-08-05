package viewOpener;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import adress.AdressDetailsView;
import adress.View;

public class ViewOpener extends AbstractHandler {
	@SuppressWarnings("unchecked")
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();

		AdressDetailsView view = (AdressDetailsView) page.findView(AdressDetailsView.ID);
//
//		ISelection selection = view.getSite().getSelectionProvider().getSelection();
//		if (selection != null && selection instanceof IStructuredSelection && event.) {
//			
//			   
//
//		}

		return null;
	}
}
